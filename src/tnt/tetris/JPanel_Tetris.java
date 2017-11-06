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

class JPanel_TBody extends JPanel {

//	JLabel lblTime;
	JLabel lblLevel;
	JPanel pnNext;
	public JPanel_Game panel_game;

	class JLabel_Time extends JLabel{
		private int sec = 0;
		private int min = 0;
		private int hour = 0;
		
		Timer timer;

		JLabel_Time(){
			setOpaque(true);
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
			setFont(new Font(Statics.fontKorean, Font.PLAIN, 20));
			setBorder(new MatteBorder(10, 20, 10, 20, Color.BLACK));
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {	
				@Override
				public void run() {
					plus();
					setText(String.format("%02d:%02d:%02d", hour, min, sec));
				}			
			};
			timer.schedule(task, 1000, 1000);
		}
		
		public void start() {
			
		}
		
		public void plus() {
			if (sec < 59)
				sec++;
			else {
				if (min < 59)
					min++;
				else {
					hour++;
					min = 0;
				}
				sec = 0;
			}
		}
	}

	public JPanel_TBody() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_tetris2_info = new JPanel();
		panel_tetris2_info.setBackground(Statics.colorBackGround);
		add(panel_tetris2_info, BorderLayout.EAST);
		panel_tetris2_info.setLayout(new BoxLayout(panel_tetris2_info, BoxLayout.Y_AXIS));
		panel_tetris2_info.setBorder(new MatteBorder(20, 20, 20, 20, Statics.colorBackGround));

		JLabel_Time lblTime = new JLabel_Time();
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel_TBody panel_tetris1_body;
	JPanel_TBody panel_tetris2_body;

	public JPanel_Tetris() {
		setLayout(new BorderLayout(0, 0));
		setSize(Statics.FRAME_WIDTH, Statics.FRAME_HEIGHT);

		JPanel panel_bottom = new JPanel();
		add(panel_bottom, BorderLayout.SOUTH);
		panel_bottom.setLayout(new BoxLayout(panel_bottom, BoxLayout.Y_AXIS));
		panel_bottom.setBackground(Statics.colorBackGround);

		JButton btnExit = new JButton(Statics.imgExit);
		btnExit.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnExit.setHorizontalAlignment(SwingConstants.RIGHT);
		btnExit.setBorderPainted(false); // 외곽선 없애줌
		btnExit.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnExit.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		panel_bottom.add(btnExit);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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

		String[] Actions = { "DOWN", "UP", "LEFT", "RIGHT" };
		for (String Action : Actions) {
			addAction(Action);
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
		String name;

		public MotionAction(String name) {
			super(name);
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			switch (this.name) {
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
