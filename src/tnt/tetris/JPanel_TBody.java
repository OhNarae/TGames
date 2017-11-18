package tnt.tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import tnt.Statics;

class JLabel_Time extends JLabel {
	private int sec;
	private int min;
	private int hour;

	Timer timer;

	JPanel_TBody BodyPanel;
	
	JLabel_Time(JPanel_TBody BodyPanel) {
		setOpaque(true);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFont(new Font(Statics.fontKorean, Font.PLAIN, 20));
		setBorder(new MatteBorder(10, 20, 10, 20, Color.BLACK));
		setText("00:00:00");
		
		this.BodyPanel = BodyPanel;
	}

	public void start() {
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				plus();
				setText(String.format("%02d:%02d:%02d", hour, min, sec));
			}
		};
		timer.schedule(task, 1000, 1000);
	}

	public void stop() {
		if (timer == null) return;
		timer.cancel();
		timer.purge();
	}

	public void end() {
		if (timer != null) {
			timer.cancel();
			timer.purge();
		}

		sec = 0;
		min = 0;
		hour = 0;

		setText(String.format("%02d:%02d:%02d", hour, min, sec));
	}

	public void plus() {
		if (sec < 59)
			sec++;
		else {
			sec = 0;
			if (min < 59)
				min++;
			else {
				min = 0;
				hour++;
			}
		}		
	}
}

class JLabel_Level extends JLabel {
//	int level = 1;
	
	JLabel_Level(){
		setText("Lv.01");
		setOpaque(true);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFont(new Font(Statics.fontKorean, Font.PLAIN, 20));
		setBorder(new MatteBorder(10, 35, 10, 35, Color.BLACK));
	}
	
//	void up(){
//		setText(String.format("Lv.%02d", ++level));
//	}	
	
	void setLevel(int level) {
		setText(String.format("Lv.%02d", level));
	}
}



class JPanel_TBody_Next extends JPanel{
	
	Queue<TBlock>  preBlocks; //예상 블럭 정보
	final int preBlockNum = 4; //예상 블럭 갯수
	
	//wall_width : 118 / 전체화면 1200  wall_height : 438 / 전체화면 750
	int wall_width = 118; // JPanel_TBody_Next 패널의 넓이
	int wall_height = 438 / preBlockNum; //JPanel_TBody_Next 패널의 높이를 4등분(예상 블럭 갯수로 나눔)
	
	int block_width = wall_width / 6;
	int block_height = wall_height / 6;
	
	Image buffImage;
	Graphics buffg;
	
	JPanel_TBody_Next(){
		setBackground(Color.BLACK);
		setLayout(new GridLayout(preBlockNum, 1, 0, 0));
		
		preBlocks = new LinkedList<TBlock>();
		for(int i = 0 ; i < preBlockNum ; i++) {
			preBlocks.add(new TBlock(block_width, wall_height * i + block_height, block_width, block_height));
		}	
	}
	
	// 다음 블럭 정보를 가져가면서
	TBlock GetNextBlock(){	
		TBlock newBlock = preBlocks.poll();
		for(TBlock block : preBlocks)
			block.ChangeBlockPoint(0, -wall_height);
		preBlocks.add(new TBlock(block_width, wall_height * 3 + block_height, block_width, block_height));
		
		repaint();
		return newBlock;
	}
	
	public void paint(Graphics g) {
		
		buffImage = createImage(getWidth(), getHeight());
		buffg = buffImage.getGraphics();

		buffg.clearRect(0, 0, getWidth(), getHeight());
		
		buffg.setColor(Color.black);
		buffg.fillRect(0, 0, getWidth(), getHeight());
		
		//예상 블럭을 그려준다.
		for(TBlock block : preBlocks) {
			buffg.setColor(block.type.color);
			for(Point point : block.points) {
				buffg.fillRect(point.x, point.y, block.width, block.height);
			}
		}
		
		g.drawImage(buffImage, 0, 0, this);
	}
}

class JPanel_TBody extends JPanel {

	JLabel_Time lblTime;
	JLabel_Level lblLevel;
	JPanel_TBody_Next pnNext;
	JPanel_Game panel_game;	//게임판
	
	final int leftKey;
	final int rightKey;
	final int downKey;
	final int changeKey;
	
	private boolean disable = false;
	
	public int tManageNum;
	
	public JPanel_TBody(JPanel_Tetris_Main panelTetris) {
		this(panelTetris, 0, 0, 0, 0, 0);
		this.disable = true;
	}
	
	public JPanel_TBody(JPanel_Tetris_Main panelTetris, int num, int leftKey, int rightKey, int downKey, int changeKey) {

		this.tManageNum = num;
		
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.downKey = downKey;
		this.changeKey = changeKey;	
				
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_tetris_info = new JPanel();
		panel_tetris_info.setBackground(Statics.colorBackGround);
		add(panel_tetris_info, BorderLayout.EAST);
		panel_tetris_info.setLayout(new BoxLayout(panel_tetris_info, BoxLayout.Y_AXIS));
		panel_tetris_info.setBorder(new MatteBorder(20, 20, 20, 20, Statics.colorBackGround));

		lblTime = new JLabel_Time(this);
		panel_tetris_info.add(lblTime);

		panel_tetris_info.add(Box.createVerticalStrut(25));

		lblLevel = new JLabel_Level();
		panel_tetris_info.add(lblLevel);

		panel_tetris_info.add(Box.createVerticalStrut(25));

		JLabel lblNext = new JLabel("Next");
		lblNext.setForeground(Color.BLACK);
		lblNext.setFont(new Font(Statics.fontKorean, Font.PLAIN, 30));
		lblNext.setBorder(new MatteBorder(0, 20, 0, 0, (Color) new Color(255, 214, 109)));
		panel_tetris_info.add(lblNext);

		pnNext = new JPanel_TBody_Next();
		panel_tetris_info.add(pnNext);

		JPanel panel_tetris_game_border = new JPanel();
		add(panel_tetris_game_border, BorderLayout.CENTER);
		panel_tetris_game_border.setLayout(new GridLayout(0, 1));
		panel_tetris_game_border.setBorder(new MatteBorder(29, 9, 30, 10, Statics.colorBackGround));
		panel_tetris_game_border.setBackground(Color.BLACK);

		panel_game = new JPanel_Game(this);

		panel_tetris_game_border.add(panel_game);		
	}
	
	public boolean getDisable() {
		return this.disable;
	}
	
	//게임 초기화(시작하기 전 준비)
	public void gameReady() { 
		if(this.disable) return;
		
		lblTime.end();
		panel_game.gameReady();
	}

	//게임 시작
	public void gameStart() {
		if(this.disable) return;
		
		lblTime.start();
		panel_game.gameStart();
	}

	//게임 스톱
	public void gameStop() { 
		if(this.disable) return;
		
		lblTime.stop();
		panel_game.gameStop();
	}
	
	public void gameEnd(String msg) {
		if(this.disable) return;
		
		lblTime.stop();
		panel_game.gameEnd(msg);
	}
	
//	public void checkTime(int hour, int min) {
//		int per_min = 1; //5분 후 부터 5분 단위로.
//		if((min > 0 || hour > 0) && min % per_min == 0) 
//			lblLevel.UP();
//	}
	
	public void SetLevel(int level) {
		lblLevel.setLevel(level);
	}
	
	public void keyProcessing(int vkDown) {
		if(this.disable) return;		
		
		if(leftKey == vkDown) {
			panel_game.pressLeftKey();
		}else if(rightKey == vkDown) {
			panel_game.pressRightKey();
		}else if(downKey == vkDown) {
			panel_game.pressDownKey();
		}else if(changeKey == vkDown) {
			panel_game.pressChangeKey();
		}
	}
}