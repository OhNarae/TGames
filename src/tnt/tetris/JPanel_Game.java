package tnt.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.util.BitSet;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tnt.Statics;

class BlockManager {

	// width : 420 height : 600
	int WIDTH_BLOCK_NUM = 14;
	int HEIGHT_BLOCK_NUM = 20;
	int BLOCK_LENGTH = 30;

	TBlock newBlock;
	BitSet hardenBlock; // 100000000001 초기 구성 1은 벽 혹은 블럭이다. 초기 시 1은 벽이다.
						// 100000000001
						// 100000000001
						// 111111111111
						// 111111111111 100000000001 100000000001 100000000001 <- 즉 이렇게 구성된다.
	int WIDTH_HARDEN_BLOCK = WIDTH_BLOCK_NUM + 2;
	int HEIGHT_HARDEN_BLOCK = HEIGHT_BLOCK_NUM + 1;

	boolean gameOver;

	private int tManageNum;

	BlockManager(int tManagerNum, BLOCK_TYPE blockType, int width, int height) {
		this.tManageNum = tManagerNum;

		newBlock = new TBlock(blockType, 5, 0);

		hardenBlock = new BitSet(HEIGHT_HARDEN_BLOCK * WIDTH_HARDEN_BLOCK);
		hardenBlock.or(setWall());

		gameOver = false;
	}

	BitSet setWall() {
		byte[] bsByteArray = new byte[HEIGHT_HARDEN_BLOCK * (WIDTH_HARDEN_BLOCK / 8)];

		for (int i = 0; i < bsByteArray.length; i += 2) {
			if (i == bsByteArray.length - 2) {
				bsByteArray[i] = -1; // 1111 1111
				bsByteArray[i + 1] = -1;
			} else {
				bsByteArray[i] = 1;// 0000 0001
				bsByteArray[i + 1] = -128; // 1000 0000
			}
		}
		return BitSet.valueOf(bsByteArray);
	}

	void setNewBlock(BLOCK_TYPE blockType) {
		newBlock = new TBlock(blockType, 5, 0);
	}

	boolean goDown() {
		boolean canGo = true;

		synchronized (this) {
			// 하나 다운 된 위치를 비트로 계산
			BitSet newBlockBS = new BitSet(HEIGHT_HARDEN_BLOCK * WIDTH_HARDEN_BLOCK);
			TreeSet<Integer> y_list = new TreeSet<Integer>();// 하드블럭이 될 시 y의 위치를 확인하여야 함으로 저장 (채워진 블럭은 없애야 함으로)
			for (Point point : newBlock.points) {
				y_list.add(point.y * WIDTH_HARDEN_BLOCK);
				newBlockBS.set(point.y * WIDTH_HARDEN_BLOCK + point.x + 1);
				if (hardenBlock.get(point.y * WIDTH_HARDEN_BLOCK + point.x + 1)) { // 현재 벽&하드블럭 들과 겹치는 부분이 있는지 계산
					// 현재가 겹침
					gameOver = true;
				}
				if (hardenBlock.get((point.y + 1) * WIDTH_HARDEN_BLOCK + point.x + 1)) { // 현재 벽&하드블럭 들과 겹치는 부분이 있는지 계산
					// 다운 되었을 때 겹치는 부분이 있다면..
					canGo = false;
				}
			}

			if (canGo) { // -> 겹치는 부분이 없다면 다운 -> 끝
				newBlock.goDown();
				return true;
			}

			// -> 겹치는 부분이 있다면 정지
			hardenBlock.or(newBlockBS);
			if (gameOver)
				return false;

			// 정지 후 한줄이 채워진 블럭이 있는지 확인 (XOR? 로 계산하고 있을 시에는 >> 쉬프트로 이후의 블럭을 0단위까지 >> 다시 해당
			// 위치까지 << 로 해결)
			Iterator<Integer> itr = y_list.iterator();
			int blockRows = 0;
			while (itr.hasNext()) {
				int y = itr.next();
				if (hardenBlock.nextClearBit(y) > (y + WIDTH_HARDEN_BLOCK)) {// 한줄이 채워지면
					byte[] hardenBlockByte = hardenBlock.get(0, y).toByteArray();
					byte[] resultByte = new byte[hardenBlockByte.length + 2];
					System.arraycopy(hardenBlockByte, 0, resultByte, 2, hardenBlockByte.length);
					resultByte[0] = 1;
					resultByte[1] = -128;
					BitSet bs = BitSet.valueOf(resultByte);

					hardenBlock.clear(0, y + WIDTH_HARDEN_BLOCK);
					hardenBlock.or(bs);
					blockRows++;
				}
			}
			TetrisManager.inst.clearBlockRows(tManageNum, blockRows);
		}

		return false;
	}

	void goLeft() {
		boolean canGo = true;
		// 하나 Left 이동 된 위치를 비트로 계산
		synchronized (this) {
			for (Point point : newBlock.points) {
				if (hardenBlock.get(point.y * WIDTH_HARDEN_BLOCK + point.x)) { // 왼쪽으로 움직일 시 장애물이 있는지
					// Left 이동 되었을 때 겹치는 부분이 있다면..
					canGo = false;
				}
			}

			if (canGo) { // -> 겹치는 부분이 없다면 Left 이동 -> 끝
				newBlock.goLeft();
			}
		}
		// -> 겹치는 부분이 있다면 움직이지 않음
	}

	void goRight() {
		boolean canGo = true;
		synchronized (this) {
			// 하나 Right 이동 된 위치를 비트로 계산
			for (Point point : newBlock.points) {
				if (hardenBlock.get(point.y * WIDTH_HARDEN_BLOCK + point.x + 2)) { // 오른쪽으로 움직일 시 장애물이 있는지
					// Right 이동 되었을 때 겹치는 부분이 있다면..
					canGo = false;
				}
			}

			if (canGo) { // -> 겹치는 부분이 없다면 Right 이동 -> 끝
				newBlock.goRight();
			}
			// -> 겹치는 부분이 있다면 움직이지 않음
		}
	}

	void change() {

		// change된 위치를 비트로 계산
		boolean canGo = true;
		synchronized (this) {
			for (Point point : newBlock.predictChange()) { // change 되었을 시, 블럭의 위치를 예측한다.
				if (point.y < 0 || hardenBlock.get(point.y * WIDTH_HARDEN_BLOCK + point.x + 1)) {
					// change 되었을 때 겹치는 부분이 있다면..
					canGo = false;
				}
			}

			if (canGo) { // -> 겹치는 부분이 없다면 change -> 끝
				newBlock.goChange();
			}
		}
	}

	boolean ExistHardenBlock(int i, int j) { // i행 j열에 하드블럭이 있는지 체크
		return hardenBlock.get(i * (WIDTH_BLOCK_NUM + 2) + (j + 1));
	}
}

class JPanel_Game extends JPanel implements Runnable {

	JPanel_TBody panelBody;

	BlockManager blockManager;

	Image buffImage;
	Graphics buffg;

	Thread t;
	int sleepTime = 1000;

	String gameOverMsg = "";

	JPanel_Game(JPanel_TBody panelBody) {
		this.panelBody = panelBody;

		setLayout(null);

		t = new Thread(this);
	}

	public void pressDownKey() {
		if (!(blockManager.goDown() || blockManager.gameOver)) {
			blockManager.setNewBlock(panelBody.pnNext.GetNextBlock().type);
		}
		repaint();
	}

	public void pressLeftKey() {
		blockManager.goLeft();
		repaint();
	}

	public void pressRightKey() {
		blockManager.goRight();
		repaint();
	}

	public void pressChangeKey() {
		blockManager.change();
		repaint();
	}

	public void gameEnd(boolean victory) {
		blockManager.gameOver = true;
	}

	public void gameStart() {
		if (blockManager == null) {
			blockManager = new BlockManager(panelBody.tManageNum, panelBody.pnNext.GetNextBlock().type, getWidth(),
					getHeight());
		}
		t.start();
	}

	public void gameStop() {
		t.interrupt();
	}

	public void gameEnd(String msg) {
		t.interrupt();

		blockManager.gameOver = true;
		gameOverMsg = msg;
	}

	public void paint(Graphics g) {

		buffImage = createImage(getWidth(), getHeight());
		buffg = buffImage.getGraphics();

		if (this.panelBody.getDisable()) {

			buffg.setColor(Color.BLACK);
			buffg.setFont(new Font(Statics.fontEnglish, Font.BOLD, 40));
			buffg.drawString("Disable", getWidth() / 3, getHeight() / 2);

			g.drawImage(buffImage, 0, 0, this);
			return;
		}

		buffg.clearRect(0, 0, getWidth(), getHeight());

		if (blockManager != null) {
			if (!blockManager.gameOver) {
				// 현재 블럭
				buffg.setColor(blockManager.newBlock.type.color);
				for (Point point : blockManager.newBlock.points) {
					buffg.fillRect(point.x * blockManager.BLOCK_LENGTH, point.y * blockManager.BLOCK_LENGTH,
							blockManager.BLOCK_LENGTH, blockManager.BLOCK_LENGTH);
				}
			}

			// 배경 블럭
			buffg.setColor(Color.lightGray);
			for (int i = 0; i < blockManager.HEIGHT_BLOCK_NUM; i++) {
				for (int j = 0; j < blockManager.WIDTH_BLOCK_NUM; j++) {
					if (blockManager.ExistHardenBlock(i, j))
						buffg.fillRect(j * blockManager.BLOCK_LENGTH, i * blockManager.BLOCK_LENGTH,
								blockManager.BLOCK_LENGTH, blockManager.BLOCK_LENGTH);
				}
			}

			// 게임 끝나면 게임을 멈추고 GameOver, YOU WIN, YOU LOST 등의 메세지를 뿌려준다.
			if (blockManager.gameOver) {
				buffg.setColor(Color.BLACK);
				buffg.setFont(new Font(Statics.fontEnglish, Font.BOLD, 40));
				buffg.drawString(gameOverMsg, getWidth() / 4, getHeight() / 2);
			}
		}

		g.drawImage(buffImage, 0, 0, this);
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(sleepTime / 10);
				pressDownKey();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			t = new Thread(this);
		}
	}
}