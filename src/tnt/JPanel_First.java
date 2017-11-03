package tnt;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

public class JPanel_First extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanel_First() {
		setSize(Statics.FRAME_WIDTH, Statics.FRAME_HEIGHT);
		setLayout(null);

		JButton btnTaroButton = new JButton(GAME.TARO.name(), Statics.imgTaro);
		btnTaroButton.setBounds(12, 280, 378, 151);
		btnTaroButton.setBorderPainted(false); // 외곽선 없애줌
		btnTaroButton.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnTaroButton.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnTaroButton.addActionListener(this);
		btnTaroButton.setFont(Statics.fontEnglish);
		add(btnTaroButton);

		JButton btnTetrisButton = new JButton(GAME.TETRIS.name(), Statics.imgTetris);
		btnTetrisButton.setBounds(58, 518, 333, 118);
		btnTetrisButton.setBorderPainted(false); // 외곽선 없애줌
		btnTetrisButton.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnTetrisButton.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnTetrisButton.addActionListener(this);
		btnTetrisButton.setFont(Statics.fontEnglish);
		add(btnTetrisButton);
		
		JButton btnPenButton = new JButton(GAME.TRICK_PICTURE.name(), Statics.imgPen);
		btnPenButton.setBounds(12, 61, 588, 135);
		btnPenButton.setBorderPainted(false); // 외곽선 없애줌
		btnPenButton.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnPenButton.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnPenButton.addActionListener(this);
		btnPenButton.setFont(Statics.fontEnglish);
		add(btnPenButton);

		JButton btnExitButton = new JButton("Exit", Statics.imgExit);
		btnExitButton.setBounds(1054, 665, Statics.imgExit.getIconWidth(), Statics.imgExit.getIconHeight());
		btnExitButton.setBorderPainted(false); // 외곽선 없애줌
		btnExitButton.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnExitButton.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnExitButton.addActionListener(this);
		add(btnExitButton);
		

	}

	public void paintComponent(Graphics g) {
		g.drawImage(Statics.imgBackground.getImage(), 0, 0, getWidth(), getHeight(), null);
		setOpaque(false); // 그림을 표시하게 설정,투명하게 조절

		super.paintComponent(g);
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
