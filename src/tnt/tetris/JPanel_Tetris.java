package tnt.tetris;

import java.awt.CardLayout;

import javax.swing.JPanel;

import tnt.*;

enum TETRIS_MENU{
	SELECT, MAIN
}

public class JPanel_Tetris extends JPanel {
	private CardLayout cardLayout = new CardLayout();
	
	private JPanel_Select pnSelect =  new JPanel_Select(this);
	private JPanel_Tetris_Main pnMain =  new JPanel_Tetris_Main();
	
	public static JPanel_Tetris inst;
	
	public JPanel_Tetris() {
		setSize(Statics.FRAME_WIDTH, Statics.FRAME_HEIGHT);
		
		setLayout(cardLayout);
		
		add(TETRIS_MENU.SELECT.name(), pnSelect);
		add(TETRIS_MENU.MAIN.name(), pnMain);
		
		inst = this;
	}
	
	public void changePanel_SELECT() {
		cardLayout.show(this, TETRIS_MENU.SELECT.name());
	}
	
	public void changePanel_MAIN(GAME_MODE gameMode) {
		cardLayout.show(this, TETRIS_MENU.MAIN.name());
		pnMain.init(gameMode);
	}
}
