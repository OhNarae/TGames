package tnt.tetris;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Font;

import tnt.*;

public class JPanel_Tetris_Main extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JPanel panel_body;
	JPanel_TBody panel_tetris1_body;
	JPanel_TBody panel_tetris2_body;

	JLabel lblMessage;

	TetrisManager tManager;

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
//				tManager.gameInit(); // 게임을 초기화 해준다.
				GameFrame.Inst.changePanel(GAME.FIRST);
			}
		});

		panel_body = new JPanel();
		add(panel_body, BorderLayout.CENTER);
		panel_body.setLayout(new BoxLayout(panel_body, BoxLayout.X_AXIS));

		String[] Actions = { "ENTER" };
		addAction(Actions);
	}

	public void init(GAME_MODE gameMode) {
		tManager = new TetrisManager(gameMode, this);

		switch (tManager.getGameMode()) {
		case ONE:
		case DYNAMIC:	
			panel_tetris1_body = new JPanel_TBody(this);
			panel_body.add(panel_tetris1_body);

			panel_tetris2_body = new JPanel_TBody(this, 2, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE,
					KeyEvent.VK_UP);
			panel_body.add(panel_tetris2_body);
			
			String[] ActionsOne = { "LEFT", "RIGHT", "SPACE", "UP"};
			addAction(ActionsOne);
			
			tManager.set(2, panel_tetris2_body);
			break;
		case TWO:
			panel_tetris1_body = new JPanel_TBody(this, 1, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_W);
			panel_body.add(panel_tetris1_body);

			panel_tetris2_body = new JPanel_TBody(this, 2, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN,
					KeyEvent.VK_UP);
			panel_body.add(panel_tetris2_body);

			String[] ActionsTwo = { "LEFT", "RIGHT", "DOWN", "UP", "A", "D", "S", "W", "SPACE" };
			addAction(ActionsTwo);
			
			tManager.set(1, panel_tetris1_body);
			tManager.set(2, panel_tetris2_body);
			break;
		}
		
		tManager.init();
	}
	
	public void setMessage(String msg) {
		lblMessage.setText(msg);
	}

	public void addAction(String[] names) {
		for (String name : names) {
			addAction(name);
		}
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

		private static final long serialVersionUID = 1L;

		String name;

		public MotionAction(String name) {
			super(name);
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {

			switch (this.name) {
			case "ENTER":
				tManager.Enter();
				return;
			}

			if(TetrisManager.inst.getGameStatus() == GAME_STATUS.STOP) return;
			switch (tManager.getGameMode()) {
			case ONE:
			case DYNAMIC:	
				switch (this.name) {
				case "LEFT":
					panel_tetris2_body.keyProcessing(KeyEvent.VK_LEFT);
					break;
				case "RIGHT":
					panel_tetris2_body.keyProcessing(KeyEvent.VK_RIGHT);
					break;
				case "SPACE":
					panel_tetris2_body.keyProcessing(KeyEvent.VK_SPACE);
					break;
				case "UP":
					panel_tetris2_body.keyProcessing(KeyEvent.VK_UP);
					break;
				}
				return;
			case TWO:
				switch (this.name) {
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
				return;
			}
		}
	}

}
