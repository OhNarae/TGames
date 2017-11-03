package tnt.tetris;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import tnt.*;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

class JPanel_TBody extends JPanel{
	public JPanel_TBody() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_tetris1_body = new JPanel();
		add(panel_tetris1_body);
		panel_tetris1_body.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_tetris1_info = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(Statics.imgBackground.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				
				super.paintComponent(g);
			}
		};
		panel_tetris1_body.add(panel_tetris1_info, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("New button");
		panel_tetris1_info.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_tetris1_info.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setText("하하하하");
				
			}
		});
		
		JPanel panel_tetris1_game_border = new JPanel();
		panel_tetris1_body.add(panel_tetris1_game_border, BorderLayout.CENTER);
		panel_tetris1_game_border.setLayout(null);
		
		JPanel panel_tetris1_game = new JPanel();
		panel_tetris1_game.setBounds(43, 36, 300, 600);
		panel_tetris1_game.setBackground(Color.BLACK);
		panel_tetris1_game_border.add(panel_tetris1_game);
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
		
		JButton btnExit = new JButton(Statics.imgExit);
		btnExit.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnExit.setHorizontalAlignment(SwingConstants.RIGHT);
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

		///tetris 오른쪽 화면
		JPanel panel_tetris2_body = new JPanel();
		panel_body.add(panel_tetris2_body);
		panel_tetris2_body.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_tetris2_info = new JPanel();
		panel_tetris2_body.add(panel_tetris2_info, BorderLayout.EAST);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_tetris2_info.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_tetris2_info.add(btnNewButton_2);
		
		JPanel panel_tetris2_game_border = new JPanel();
		panel_tetris2_body.add(panel_tetris2_game_border, BorderLayout.CENTER);
		panel_tetris2_game_border.setLayout(new BoxLayout(panel_tetris2_game_border, BoxLayout.X_AXIS));
		
//		panel_tetris2_game_border.setBorder(Box.createHorizontalGlue());
		JPanel panel_tetris2_game = new JPanel();
		panel_tetris2_game_border.add(panel_tetris2_game);
	}
}
