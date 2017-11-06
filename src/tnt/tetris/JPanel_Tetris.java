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

class JPanel_Game extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	Point[] blockPoint;

	private Action down = new AbstractAction("DOWN") {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2042513262173601539L;

		@Override
		public void actionPerformed(ActionEvent e) {
			for (Point points : blockPoint) {
				points.y += 1;
			}
			repaint();
		}
	};

	private class MotionAction extends AbstractAction implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 4169247310119459272L;
		String name;
	    public MotionAction(String name)
	    {
	        super(name);
	        this.name = name;
	    }
	 
	    public void actionPerformed(ActionEvent e)
	    {
	    	switch(this.name) {
	    	case "DOWN":
				for (Point points : blockPoint) {
					points.y += 1;
				}break;
	    	case "UP":
				for (Point points : blockPoint) {
					points.y -= 1;
				}break;	
	    	}
			repaint();
	    }
	}
	
	public MotionAction addAction(String name)
	{
		MotionAction action = new MotionAction(name);
	 
	    KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(name);
	    InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
	    inputMap.put(pressedKeyStroke, name);
	    getActionMap().put(name, action);
	 
	    return action;
	}
	
	JPanel_Game() {
		addAction("DOWN");
		addAction("UP");
//		GameFrame.Inst.RegisterListner(this);
        
		blockPoint = new Point[4];
		blockPoint[0] = new Point(0, 0);
		blockPoint[1] = new Point(0, 1);
		blockPoint[2] = new Point(0, 2);
		blockPoint[3] = new Point(0, 3);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int nowKey = arg0.getKeyCode();

		if (nowKey == KeyEvent.VK_DOWN) {
			for (Point points : blockPoint) {
				points.y += 1;
			}
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	Image buffImage;
	Graphics buffg;

	public void paint(Graphics g) {
		buffImage = createImage(getWidth(), getHeight());
		buffg = buffImage.getGraphics();

		buffg.clearRect(0, 0, getWidth(), getHeight());

		int block_size = getWidth() / 10;

		buffg.setColor(Color.YELLOW);
		for (Point points : blockPoint) {
			buffg.fillRect(points.x * block_size, points.y * block_size, block_size, block_size);
		}

		g.drawImage(buffImage, 0, 0, this);
	}
}

class JPanel_TBody extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1712487684772738792L;

	public JPanel_TBody() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_tetris2_info = new JPanel();
		panel_tetris2_info.setBackground(Statics.colorBackGround);
		add(panel_tetris2_info, BorderLayout.EAST);
		panel_tetris2_info.setLayout(new BoxLayout(panel_tetris2_info, BoxLayout.Y_AXIS));
		panel_tetris2_info.setBorder(new MatteBorder(20, 20, 20, 20, Statics.colorBackGround));

		JLabel lblTime = new JLabel("00:00:00");
		lblTime.setOpaque(true);
		lblTime.setBackground(Color.BLACK);
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font(Statics.fontKorean, Font.PLAIN, 20));
		lblTime.setBorder(new MatteBorder(10, 20, 10, 20, Color.BLACK));
		panel_tetris2_info.add(lblTime);

		panel_tetris2_info.add(Box.createVerticalStrut(25));

		JLabel lblLevel = new JLabel("Lv.01");
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

		JPanel pnNext = new JPanel();
		pnNext.setBackground(Color.BLACK);
		panel_tetris2_info.add(pnNext);

		JPanel panel_tetris2_game_border = new JPanel();
		add(panel_tetris2_game_border, BorderLayout.CENTER);
		panel_tetris2_game_border.setLayout(new GridLayout(0, 1));
		panel_tetris2_game_border.setBorder(new MatteBorder(20, 20, 20, 20, Statics.colorBackGround));
		panel_tetris2_game_border.setBackground(Color.BLACK);

		JPanel_Game panel_game = new JPanel_Game();
		panel_tetris2_game_border.add(panel_game);
	}
}

public class JPanel_Tetris extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		JPanel_TBody panel_tetris1_body = new JPanel_TBody();
		panel_body.add(panel_tetris1_body);

		JPanel_TBody panel_tetris2_body = new JPanel_TBody();
		panel_body.add(panel_tetris2_body);
	}
}
