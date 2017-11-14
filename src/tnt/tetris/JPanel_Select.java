package tnt.tetris;

import javax.swing.JPanel;

import tnt.GAME;
import tnt.GameFrame;
import tnt.Statics;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JPanel_Select extends JPanel  implements ActionListener {
	public JPanel_Select() {
		setSize(Statics.FRAME_WIDTH, Statics.FRAME_HEIGHT);
		setLayout(null);
		
		JLabel lblTetrisTitle = new JLabel("");
		lblTetrisTitle.setIcon(new ImageIcon(JPanel_Select.class.getResource("/tetris/title_tetris.png")));
		lblTetrisTitle.setBounds(472, 93, 255, 203);
		add(lblTetrisTitle);
		
		JButton btn1PMode = new JButton("     1인 모드");
		btn1PMode.setHorizontalAlignment(SwingConstants.LEFT);
		btn1PMode.setIcon(new ImageIcon(JPanel_Select.class.getResource("/tetris/block_long.png")));
		btn1PMode.setBounds(486, 336, 241, 75);
		btn1PMode.setBorderPainted(false); // 외곽선 없애줌
		btn1PMode.setContentAreaFilled(false);// 내용영역 채우기 않함
		btn1PMode.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btn1PMode.addActionListener(this);
		add(btn1PMode);
		
		JButton btn2PMode = new JButton("     2인 모드");
		btn2PMode.setIcon(new ImageIcon(JPanel_Select.class.getResource("/tetris/block_squre.png")));
		btn2PMode.setBounds(486, 551, 241, 75);
		btn2PMode.setBorderPainted(false); // 외곽선 없애줌
		btn2PMode.setContentAreaFilled(false);// 내용영역 채우기 않함
		btn2PMode.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btn2PMode.addActionListener(this);
		add(btn2PMode);
		
		JButton btnDynamicMode = new JButton("   다이나믹 모드");
		btnDynamicMode.setIcon(new ImageIcon(JPanel_Select.class.getResource("/tetris/block_rightup.png")));
		btnDynamicMode.setBounds(486, 651, 241, 75);
		btnDynamicMode.setBorderPainted(false); // 외곽선 없애줌
		btnDynamicMode.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnDynamicMode.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnDynamicMode.addActionListener(this);
		add(btnDynamicMode);
		
		JButton btnQuit = new JButton("    나가기");
		btnQuit.setIcon(new ImageIcon(JPanel_Select.class.getResource("/tetris/block_leftup.png")));
		btnQuit.setBounds(486, 440, 241, 75);
		btnQuit.setBorderPainted(false); // 외곽선 없애줌
		btnQuit.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnQuit.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnQuit.addActionListener(this);
		add(btnQuit);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == GAME.TARO.name()) {
			GameFrame.Inst.changePanel(GAME.TARO);
			
		} else if (e.getActionCommand() == GAME.TETRIS.name()) {
			GameFrame.Inst.changePanel(GAME.TETRIS);
			
		} else if(e.getActionCommand() == GAME.TRICK_PICTURE.name()) {
			GameFrame.Inst.changePanel(GAME.TRICK_PICTURE);
		}
		else if (e.getActionCommand() == "Exit") {
			GameFrame.Inst.changePanel(GAME.FIRST);
			
		}
	}
}
