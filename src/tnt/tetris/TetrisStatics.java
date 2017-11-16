package tnt.tetris;

import java.awt.Color;

import javax.swing.ImageIcon;

public interface TetrisStatics {
	ImageIcon imgMinionGuitar = new ImageIcon("image/tetris/minion-guitar.gif");
	
	int MaxLevel = 10;
}

enum BLOCK_TYPE {
	SQUARE(Color.YELLOW), LONG(Color.BLUE), LEFT_UP(Color.GREEN), RIGHT_UP(Color.RED), LEFT_BOOT(Color.MAGENTA), RIGHT_BOOT(Color.CYAN);
	
	Color color;
	BLOCK_TYPE(Color color){
		this.color = color;
	}
	
	public static BLOCK_TYPE GetBlockType(int n){
		switch(n) {
		case 0:
			return SQUARE;
		case 1:
			return LONG;
		case 2:
			return LEFT_UP;
		case 3:
			return RIGHT_UP;
		case 4:
			return LEFT_BOOT;
		case 5:	
		default: //
			return RIGHT_BOOT;	
		} 
	}
}

enum TETRIS_MENU{
	SELECT, MAIN
}

enum GAME_STATUS {
	READY, RUNNING, STOP, END;
}

enum GAME_MODE{
	ONE, TWO, DYNAMIC
}

