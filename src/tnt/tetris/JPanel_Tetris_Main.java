package tnt.tetris;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;

import tnt.*;

enum GAME_STATUS {
	READY, RUNNING, STOP;
}

enum GAME_MODE{
	ONE, TWO, DYNAMIC
}

public class JPanel_Tetris_Main extends JPanel {
	JPanel_TBody panel_tetris1_body;
	JPanel_TBody panel_tetris2_body;

	JLabel lblMessage;

	GAME_STATUS game_status;
	
	public JPanel_Tetris_Main() {
		setLayout(new BorderLayout(0, 0));
		setSize(Statics.FRAME_WIDTH, Statics.FRAME_HEIGHT);
		
		JPanel panel_bottom = new JPanel();
		add(panel_bottom, BorderLayout.SOUTH);
		panel_bottom.setBackground(Statics.colorBackGround);
		panel_bottom.setLayout(new BorderLayout(0, 0));

		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font(Statics.fontKorean, Font.PLAIN, 40));
		lblMessage.setBorder(new MatteBorder(0, 0, 10, 0, Statics.colorBackGround));
		panel_bottom.add(lblMessage, BorderLayout.CENTER);

		JButton btnExit = new JButton(Statics.imgExit);
		btnExit.setPreferredSize(new Dimension(Statics.imgExit.getIconWidth(), Statics.imgExit.getIconHeight()));
		btnExit.setBorderPainted(false); // 외곽선 없애줌
		btnExit.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnExit.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		panel_bottom.add(btnExit, BorderLayout.EAST);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameReady(); // 게임을 초기화 해준다.
				GameFrame.Inst.changePanel(GAME.FIRST);
			}
		});

		JPanel panel_body = new JPanel();
		add(panel_body, BorderLayout.CENTER);
		panel_body.setLayout(new BoxLayout(panel_body, BoxLayout.X_AXIS));

		panel_tetris1_body = new JPanel_TBody(this, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_W);
		panel_body.add(panel_tetris1_body);

		panel_tetris2_body = new JPanel_TBody(this, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_UP);
		panel_body.add(panel_tetris2_body);
		
		String[] Actions = { "ENTER", "LEFT", "RIGHT", "DOWN", "UP", "A", "D", "S", "W", "SPACE" };
		for (String Action : Actions) {
			addAction(Action);
		}

		gameReady();
	}
	
	public void init(GAME_MODE gameMode) {
		switch(gameMode) {
		case ONE:
			break;
		case TWO:
			break;
		case DYNAMIC:
			break;
		}
	}
	
	private void gameReady() {
		game_status = GAME_STATUS.READY;
		lblMessage.setText("Enter를 치시면 게임이 시작 됩니다.");
		panel_tetris1_body.GameReady();
		panel_tetris2_body.GameReady();
	}

	private void gameStop() {
		game_status = GAME_STATUS.STOP;
		lblMessage.setText("Enter를 치시면 게임이 다시 시작 됩니다.");
		panel_tetris1_body.GameStop();
		panel_tetris2_body.GameStop();
	}

	private void gameStart() {
		game_status = GAME_STATUS.RUNNING;
		lblMessage.setText("Enter를 치시면 게임이 중단 됩니다.");
		panel_tetris1_body.GameStart();
		panel_tetris2_body.GameStart();
	}

	public MotionAction addAction(String name) {
		MotionAction action = new MotionAction(name);

		KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(name);
		InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(pressedKeyStroke, name);
		getActionMap().put(name, action);

		return action;
	}

	private class MotionAction extends AbstractAction implements ActionListener {
		String name;

		public MotionAction(String name) {
			super(name);
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			switch (this.name) {
			case "ENTER":
				switch (game_status) {
				case READY:
					gameStart();
					break;
				case RUNNING:
					gameStop();
					break;
				case STOP:
					gameStart();
					break;
				}
				break;
			case "LEFT":
				panel_tetris2_body.keyProcessing(KeyEvent.VK_LEFT);
				break;
			case "RIGHT":
				panel_tetris2_body.keyProcessing(KeyEvent.VK_RIGHT);
				break;				
			case "DOWN":
				panel_tetris2_body.keyProcessing(KeyEvent.VK_DOWN); 
				break;
			case "UP":
				panel_tetris2_body.keyProcessing(KeyEvent.VK_UP);
				break;
			case "A":
				panel_tetris1_body.keyProcessing(KeyEvent.VK_A);
				break;
			case "D":
				panel_tetris1_body.keyProcessing(KeyEvent.VK_D);
				break;
			case "S":
				panel_tetris1_body.keyProcessing(KeyEvent.VK_S);
				break;
			case "W":
				panel_tetris1_body.keyProcessing(KeyEvent.VK_W);
				break;				
			}
		}
	}

}
