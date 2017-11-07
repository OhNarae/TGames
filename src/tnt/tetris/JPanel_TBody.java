package tnt.tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import tnt.Statics;
//import tnt.tetris.JPanel_TBody.JLabel_Time;

class JPanel_TBody extends JPanel {

	JLabel_Time lblTime;
	JLabel lblLevel;
	JPanel pnNext;
	JPanel_Game panel_game;	//게임판
	
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
			timer = new Timer();
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
	
	public void keyProcessing(int vkDown) {
		panel_game.keyProcessing(vkDown);
	}
}