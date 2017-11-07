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

import tnt.*;
import javax.swing.JTextField;
import java.awt.FlowLayout;

class JPanel_TBody extends JPanel {

	JLabel_Time lblTime;
	JLabel lblLevel;
	JPanel pnNext;
	public JPanel_Game panel_game;

	class JLabel_Time extends JLabel {
		private int sec = 0;
		private int min = 0;
		private int hour = 0;

		Timer timer;

		JLabel_Time() {
			setOpaque(true);
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
			setFont(new Font(Statics.fontKorean, Font.PLAIN, 20));
			setBorder(new MatteBorder(10, 20, 10, 20, Color.BLACK));
		}

		public void start() {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					plus();
					setText(String.format("%02d:%02d:%02d", hour, min, sec));
					// System.out.println("시계가 움직이고 있음");
				}
			};
			timer.schedule(task, 1000, 1000);
		}

		public void stop() {
			if(timer == null) return;
			timer.cancel();
			timer.purge();
		}

		public void end() {
			if(timer == null) return;
			
			timer.cancel();
			timer.purge();

			sec = 0;
			min = 0;
			hour = 0;
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
	
	public void GameReady() {
		lblTime.end();
	}

	public void GameStart() {
		lblTime.start();
	}
	
	public void GameStop() {
		lblTime.stop();
	}

	public JPanel_TBody() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_tetris2_info = new JPanel();
		panel_tetris2_info.setBackground(Statics.colorBackGround);
		add(panel_tetris2_info, BorderLayout.EAST);
		panel_tetris2_info.setLayout(new BoxLayout(panel_tetris2_info, BoxLayout.Y_AXIS));
		panel_tetris2_info.setBorder(new MatteBorder(20, 20, 20, 20, Statics.colorBackGround));

		lblTime = new JLabel_Time();
		panel_tetris2_info.add(lblTime);

		panel_tetris2_info.add(Box.createVerticalStrut(25));

		lblLevel = new JLabel("Lv.01");
		lblLevel.setOpaque(true);
		lblLevel.setBackground(Color.BLACK);
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setFont(new Font(Statics.fontKorean, Font.PLAIN, 20));
		lblLevel.setBorder(new MatteBorder(10, 35, 10, 35, Color.BLACK));
		panel_tetris2_info.add(lblLevel);

		panel_tetris2_info.add(Box.createVerticalStrut(25));

		JLabel lblNext = new JLabel("Next");
		lblNext.setForeground(Color.BLACK);
		lblNext.setFont(new Font(Statics.fontKorean, Font.PLAIN, 30));
		lblNext.setBorder(new MatteBorder(0, 20, 0, 0, (Color) new Color(255, 214, 109)));
		panel_tetris2_info.add(lblNext);

		pnNext = new JPanel();
		pnNext.setBackground(Color.BLACK);
		panel_tetris2_info.add(pnNext);

		JPanel panel_tetris2_game_border = new JPanel();
		add(panel_tetris2_game_border, BorderLayout.CENTER);
		panel_tetris2_game_border.setLayout(new GridLayout(0, 1));
		panel_tetris2_game_border.setBorder(new MatteBorder(20, 20, 20, 20, Statics.colorBackGround));
		panel_tetris2_game_border.setBackground(Color.BLACK);

		panel_game = new JPanel_Game();
		panel_tetris2_game_border.add(panel_game);
	}
}

public class JPanel_Tetris extends JPanel {
	JPanel_TBody panel_tetris1_body;
	JPanel_TBody panel_tetris2_body;

	JLabel lblMessage;

	
	enum GAME_STATUS {
		READY, RUNNING, STOP;
	}
	GAME_STATUS game_status;

	public JPanel_Tetris() {
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
				GameReady(); //게임을 초기화 해준다.
				GameFrame.Inst.changePanel(GAME.FIRST);
			}
		});

		JPanel panel_body = new JPanel();
		add(panel_body, BorderLayout.CENTER);
		panel_body.setLayout(new BoxLayout(panel_body, BoxLayout.X_AXIS));

		panel_tetris1_body = new JPanel_TBody();
		panel_body.add(panel_tetris1_body);

		panel_tetris2_body = new JPanel_TBody();
		panel_body.add(panel_tetris2_body);

		String[] Actions = { "DOWN", "UP", "LEFT", "RIGHT", "ENTER" };
		for (String Action : Actions) {
			addAction(Action);
		}

		GameReady();
	}

	private void GameReady() {
		game_status = GAME_STATUS.READY;
		lblMessage.setText("Enter를 치시면 게임이 시작 됩니다.");
		panel_tetris1_body.GameReady();
		panel_tetris2_body.GameReady();
	}

	private void GameStop() {
		game_status = GAME_STATUS.STOP;
		lblMessage.setText("Enter를 치시면 게임이 다시 시작 됩니다.");
		panel_tetris1_body.GameStop();
		panel_tetris2_body.GameStop();
	}

	private void GameStart() {
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
				case STOP:	
					GameStart();
					break;
				case RUNNING:
					GameStop();
					break;
				}
				break;
			case "DOWN":
				panel_tetris1_body.panel_game.keyProcessing(KeyEvent.VK_DOWN);
				panel_tetris2_body.panel_game.keyProcessing(KeyEvent.VK_DOWN);
				break;
			case "UP":
				panel_tetris1_body.panel_game.keyProcessing(KeyEvent.VK_UP);
				panel_tetris2_body.panel_game.keyProcessing(KeyEvent.VK_UP);
				break;
			case "LEFT":
				panel_tetris1_body.panel_game.keyProcessing(KeyEvent.VK_LEFT);
				panel_tetris2_body.panel_game.keyProcessing(KeyEvent.VK_LEFT);
				break;
			case "RIGHT":
				panel_tetris1_body.panel_game.keyProcessing(KeyEvent.VK_RIGHT);
				panel_tetris2_body.panel_game.keyProcessing(KeyEvent.VK_RIGHT);
				break;
			}
		}
	}

}
