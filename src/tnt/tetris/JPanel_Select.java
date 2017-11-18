package tnt.tetris;

import javax.swing.JPanel;

import tnt.GAME;
import tnt.GameFrame;
import tnt.Statics;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JPanel_Select extends JPanel{
	
	private JPanel_Tetris pnTetris;
	
	public JPanel_Select(JPanel_Tetris pnTetris) {
		this.pnTetris = pnTetris;
		
		setSize(Statics.FRAME_WIDTH, Statics.FRAME_HEIGHT);
		setLayout(null);
		
		setBackground(Statics.colorBackGround);
		
		JLabel lblTetrisTitle = new JLabel("");
		lblTetrisTitle.setIcon(new ImageIcon("image/tetris/title_tetris.png"));
		lblTetrisTitle.setBounds(436, 40, 255, 203);
		add(lblTetrisTitle);
		
		JButton btn1PMode = new JButton("     1인 모드");
		btn1PMode.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 22));
		btn1PMode.setHorizontalAlignment(SwingConstants.LEFT);
		btn1PMode.setIcon(new ImageIcon("image/tetris/block_long.png"));
		btn1PMode.setBounds(450, 282, 312, 75);
		btn1PMode.setBorderPainted(false); // 외곽선 없애줌
		btn1PMode.setContentAreaFilled(false);// 내용영역 채우기 않함
		btn1PMode.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btn1PMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel_Tetris.inst.changePanel_MAIN(GAME_MODE.ONE);
			}
		});
		add(btn1PMode);
		
		JButton btn2PMode = new JButton("        2인 모드");
		btn2PMode.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 22));
		btn2PMode.setHorizontalAlignment(SwingConstants.LEFT);
		btn2PMode.setIcon(new ImageIcon("image/tetris/block_squre.png"));
		btn2PMode.setBounds(450, 390, 312, 75);
		btn2PMode.setBorderPainted(false); // 외곽선 없애줌
		btn2PMode.setContentAreaFilled(false);// 내용영역 채우기 않함
		btn2PMode.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btn2PMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel_Tetris.inst.changePanel_MAIN(GAME_MODE.TWO);
			}
		});
		add(btn2PMode);
		
		JButton btnDynamicMode = new JButton("        다이나믹 모드");
		btnDynamicMode.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 22));
		btnDynamicMode.setHorizontalAlignment(SwingConstants.LEFT);
		btnDynamicMode.setIcon(new ImageIcon("image/tetris/block_rightup.png"));
		btnDynamicMode.setBounds(450, 493, 312, 75);
		btnDynamicMode.setBorderPainted(false); // 외곽선 없애줌
		btnDynamicMode.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnDynamicMode.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnDynamicMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel_Tetris.inst.changePanel_MAIN(GAME_MODE.DYNAMIC);
			}
		});
		add(btnDynamicMode);
		
		JButton btnQuit = new JButton("     나가기");
		btnQuit.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 22));
		btnQuit.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuit.setIcon(new ImageIcon("image/tetris/block_leftup.png"));
		btnQuit.setBounds(450, 592, 273, 75);
		btnQuit.setBorderPainted(false); // 외곽선 없애줌
		btnQuit.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnQuit.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameFrame.Inst.changePanel(GAME.FIRST);	
			}
		});
		add(btnQuit);

	}
}
