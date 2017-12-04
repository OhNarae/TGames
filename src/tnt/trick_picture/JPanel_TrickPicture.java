package tnt.trick_picture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tnt.GAME;
import tnt.GameFrame;
import tnt.Statics;

public class JPanel_TrickPicture extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Timer timer;

	private int timecount = 60;
	
	
//	public void time() {
//		timer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				
//				timecount--;
//				
//				if(timecount==0)
//				{
//					timer.cancel();
//					
//				}
//				}
//		};
//		((Object) timer).schedule(task, 1000, 1000);
//		
//	}
	
	public JPanel_TrickPicture() {

		setLayout(null);
		setBackground(Color.orange);
		setSize(1200, 750);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 608, 780, 52);
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);		
		add(panel);

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setBounds(771, 440, 57, 15);
		add(lblLevel);

		JLabel lblScore = new JLabel("Score:");
		lblScore.setBounds(771, 479, 57, 15);
		add(lblScore);

		JLabel lblWrongAnswer = new JLabel("Wrong answer:");
		lblWrongAnswer.setBounds(771, 514, 92, 52);
		add(lblWrongAnswer);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(394, 27, 329, 539);
		add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(27, 27, 329, 539);
		add(panel_2);

		
		JButton btnExit = new JButton(Statics.imgExit);
	      btnExit.setBounds(1053, 667, 143, 58);
		btnExit.setBorderPainted(false); // 외곽선 없애줌
		btnExit.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnExit.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
	      btnExit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            GameFrame.Inst.changePanel(GAME.FIRST);
	         }
	      });

	      add(btnExit);
	      
		setVisible(true);
	}
}

class JPanel_TPbody extends JPanel {
	
	/*
	 * JPanel tp; JPanel tpScore; JLabel tpLevel; JPanel time; JPanel timeSet;
	 * private */
//	final JPanel panel = new JPanel();

	public JPanel_TPbody() {
		setLayout(null);
		setBackground(Color.orange);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 608, 780, 52);
		add(panel);
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);		

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setBounds(771, 440, 57, 15);
		add(lblLevel);

		JLabel lblScore = new JLabel("Score:");
		lblScore.setBounds(771, 479, 57, 15);
		add(lblScore);

		JLabel lblWrongAnswer = new JLabel("Wrong answer:");
		lblWrongAnswer.setBounds(771, 514, 92, 52);
		add(lblWrongAnswer);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(394, 27, 329, 539);
		add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(27, 27, 329, 539);
		add(panel_2);
	}
}
	/*}
	*/
/*	}
	class TimerBar extends JPanel {
		
		public void TimerBar() {*/
	/*	public void JPanel_TPbody() {*/
//		JPanel pane2 = new JPanel();
//		pane2.setPreferredSize(new Dimension(230, 200));
		/*}
			

	public TimerBar(){*/
	//	pane2.setLayout(new BorderLayout());

/*		
		Color colors = new Color(255, 0, 0);
		for(int i=60; i>=0;i--) {
			try {
//				g.clearRect(0, 0, 500, 300);
//				g.setColor(colors);
//				g.fillRect(100, 100, i*10, 30);
				Thread.sleep(1000);
			} catch(Exception e) {
				e.printStackTrace();
				
			}
			add(pane2);
		}
	*///임의 주석 처리	
//	}

	
	
	/*
	 * public void JPanel_TPbody() { setLayout(new BorderLayout(0, 0));
	 * 
	 * JPanel body_title1 = new JPanel();
	 * body_title1.setBackground(Statics.colorBackGround); body_title1.setLayout(new
	 * BoxLayout(body_title1, BoxLayout.X_AXIS)); body_title1.setBorder(new
	 * MatteBorder(20, 20, 20, 20, Statics.colorBackGround));
	 * 
	 * tpLevel = new JLabel("LEVEL: "); tpLevel.setForeground(Color.BLACK);
	 * tpLevel.setFont(new Font(Statics.fontKorean, Font.PLAIN, 20));
	 * tpLevel.setBorder(new MatteBorder(10, 20, 10, 20, Color.BLACK));
	 * 
	 * body_title1.add(tpLevel);
	 * 
	 * Label str1 = new Label("LEVEL", Label.LEFT);
	 * 
	 * add(body_title1, BorderLayout.CENTER);
	 * body_title1.setBorder(BorderFactory.createTitledBorder("�젙蹂�")); String[] s =
	 * { "LEVEL", "SCORE", "��由� �슏�닔" }; body_title1.add(new JList(s));
	 * 
	 * }
	 */
