package tnt.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import tnt.Statics;

import javax.swing.JTextField;

class BlockManager {

	TBlock newBlock;
	BitSet hardenBlock; // 111111111111 초기 구성 1은 벽 혹은 블럭이다. 초기 시 1은 벽이다.
						// 100000000001
						// 100000000001
						// 100000000001
						// 111111111111
						// 000000000000 000000000000 111111111111 100000000001 100000000001 111111111111
	int width;
	int height;

	// width : 420 height : 600
	int WIDTH_BLOCK_NUM = 14;
	int HEIGHT_BLOCK_NUM = 20;
	int BLOCK_LENGTH = 30;
	
	boolean gameOver;
	String gameOverMsg;

	BlockManager(BLOCK_TYPE blockType, int width, int height) {

		newBlock = new TBlock(blockType, 5, 0);

		hardenBlock = new BitSet((HEIGHT_BLOCK_NUM + 2) * (WIDTH_BLOCK_NUM + 2));
		for (int i = 0; i < (HEIGHT_BLOCK_NUM + 2); i++) {
			for (int j = 0; j < (WIDTH_BLOCK_NUM + 2); j++) {
//				if (i == 0 || i == HEIGHT_BLOCK_NUM + 1 || j == 0 || j == WIDTH_BLOCK_NUM + 1)
				if (i == HEIGHT_BLOCK_NUM + 1  || j == 0 || j == WIDTH_BLOCK_NUM + 1)
					hardenBlock.set(i * (WIDTH_BLOCK_NUM + 2) + j);
			}
		}
		
		gameOver = false;
		gameOverMsg = "GameOver";
	}

	void setNewBlock(BLOCK_TYPE blockType) {
		newBlock = new TBlock(blockType, 5, 0);
	}

	boolean goDown() {
		boolean canGo = true;

		// 하나 다운 된 위치를 비트로 계산
		BitSet newBlockBS = new BitSet((HEIGHT_BLOCK_NUM + 2) * (WIDTH_BLOCK_NUM + 2));
		TreeSet<Integer> y_list = new TreeSet<Integer>();//(o1, o2) -> o2 - o1);
		for (Point point : newBlock.points) {
			y_list.add((point.y + 1) * (WIDTH_BLOCK_NUM + 2));
			newBlockBS.set((point.y + 1) * (WIDTH_BLOCK_NUM + 2) + point.x + 1);
			if (hardenBlock.get((point.y + 1) * (WIDTH_BLOCK_NUM + 2) + point.x + 1)) { // 현재 벽&하드블럭 들과 겹치는 부분이 있는지 계산
				// 현재가 겹침
				gameOver = true;
			}
			if (hardenBlock.get((point.y + 2) * (WIDTH_BLOCK_NUM + 2) + point.x + 1)) { // 현재 벽&하드블럭 들과 겹치는 부분이 있는지 계산
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
		if(gameOver)
			return false;

		// 정지 후 한줄이 채워진 블럭이 있는지 확인 (XOR? 로 계산하고 있을 시에는 >> 쉬프트로 이후의 블럭을 0단위까지 >> 다시 해당
		// 위치까지 << 로 해결)
		Iterator<Integer> itr = y_list.iterator();
		while (itr.hasNext()) {
 			int y = itr.next();
			if(hardenBlock.nextClearBit(y) > (y + WIDTH_BLOCK_NUM + 2)) {//한줄이 채워지면
				byte[] hardenBlockByte = hardenBlock.get(0, y).toByteArray();
				byte[] resultByte = new byte[hardenBlockByte.length + 2];
				System.arraycopy(hardenBlockByte, 0, resultByte, 2,  hardenBlockByte.length);
				BitSet bs = BitSet.valueOf(resultByte);
				hardenBlock.clear(0, y + WIDTH_BLOCK_NUM + 2);
				hardenBlock.or(bs);
				hardenBlock.set(0);
				hardenBlock.set(WIDTH_BLOCK_NUM + 1);
			}
		}

		return false;
	}

	void goLeft() {
		boolean canGo = true;
		// 하나 다운 된 위치를 비트로 계산
		for (Point point : newBlock.points) {
			if (hardenBlock.get((point.y + 1) * (WIDTH_BLOCK_NUM + 2) + point.x)) { // 왼쪽으로 움직일 시 장애물이 있는지
				// 다운 되었을 때 겹치는 부분이 있다면..
				canGo = false;
			}
		}

		if (canGo) { // -> 겹치는 부분이 없다면 Left 이동 -> 끝
			newBlock.goLeft();
		}
		// -> 겹치는 부분이 있다면 움직이지 않음
	}

	void goRight() {
		boolean canGo = true;
		// 하나 다운 된 위치를 비트로 계산
		for (Point point : newBlock.points) {
			if (hardenBlock.get((point.y + 1) * (WIDTH_BLOCK_NUM + 2) + point.x + 2)) { // 오른쪽으로 움직일 시 장애물이 있는지
				// 다운 되었을 때 겹치는 부분이 있다면..
				canGo = false;
			}
		}

		if (canGo) { // -> 겹치는 부분이 없다면 Left 이동 -> 끝
			newBlock.goRight();
		}
		// -> 겹치는 부분이 있다면 움직이지 않음
	}

	void change() {
		// change 된 위치를 비트로 계산
		boolean canGo = true;
		for (Point point : newBlock.predictChange()) {
			if (hardenBlock.get((point.y + 1) * (WIDTH_BLOCK_NUM + 2) + point.x + 1)) { // 오른쪽으로 움직일 시 장애물이 있는지
				// 다운 되었을 때 겹치는 부분이 있다면..
				canGo = false;
			}
		}

		if (canGo) { // -> 겹치는 부분이 없다면 Left 이동 -> 끝
			newBlock.goChange();
		}
	}

	boolean ExistHardenBlock(int i, int j) { // i행 j열에 하드블럭이 있는지 체크
		return hardenBlock.get((i + 1) * (WIDTH_BLOCK_NUM + 2) + (j + 1));
	}
}

class JPanel_Game extends JPanel implements Runnable {

	JPanel_TBody panelBody;

	BlockManager blockManager;

	Image buffImage;
	Graphics buffg;

	Thread t;
	int sleepTime = 1000;

	// width : 420 height : 600
	int WIDTH_BLOCK_NUM = 14;
	int HEIGHT_BLOCK_NUM = 20;
	int BLOCK_LENGTH = 30;

	JPanel_Game(JPanel_TBody panelBody) {
		this.panelBody = panelBody;
		setLayout(null);
		// width : 399height : 619
		blockManager = new BlockManager(panelBody.pnNext.GetNextBlock().type, getWidth(), getHeight());
		t = new Thread(this);
	}

	public void keyProcessing(int vkDown) {
		switch (vkDown) {
		case KeyEvent.VK_DOWN:
			blockManager.goDown();
			break;
		case KeyEvent.VK_LEFT:
			blockManager.goLeft();
			break;
		case KeyEvent.VK_RIGHT:
			blockManager.goRight();
			break;
		}

		repaint();
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

	public void paint(Graphics g) {
		buffImage = createImage(getWidth(), getHeight());
		buffg = buffImage.getGraphics();

		buffg.clearRect(0, 0, getWidth(), getHeight());

		if(!blockManager.gameOver) {
			// 현재 블럭
			buffg.setColor(blockManager.newBlock.type.color);
			for (Point point : blockManager.newBlock.points) {
				buffg.fillRect(point.x * BLOCK_LENGTH, point.y * BLOCK_LENGTH, BLOCK_LENGTH, BLOCK_LENGTH);
			}
		}

		// 배경 블럭
		buffg.setColor(Color.lightGray);
		for (int i = 0; i < HEIGHT_BLOCK_NUM; i++) {
			for (int j = 0; j < WIDTH_BLOCK_NUM; j++) {
				if (blockManager.ExistHardenBlock(i, j))
					buffg.fillRect(j * BLOCK_LENGTH, i * BLOCK_LENGTH, BLOCK_LENGTH, BLOCK_LENGTH);
			}
		}
		
		if(blockManager.gameOver) {
			buffg.setColor(Color.BLACK);
			buffg.setFont(new Font(Statics.fontEnglish, Font.BOLD, 40));
			buffg.drawString("GameOver", getWidth()/4, getHeight()/2);
		}

		g.drawImage(buffImage, 0, 0, this);
	}

	@Override
	public void run() {
		System.out.println("Thread running");
		try {
			while (!t.interrupted()) {
				Thread.sleep(sleepTime);
				// blockManager.goDown();
				repaint();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			t = new Thread(this);
		}
	}

	public void GameStart() {
		t.start();
	}

	public void GameStop() {
		t.interrupt();
	}
}