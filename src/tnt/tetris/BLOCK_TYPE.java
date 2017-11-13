package tnt.tetris;

import java.awt.Color;

enum BLOCK_TYPE {
	SQUARE(Color.YELLOW), LONG(Color.BLUE), LEFT_UP(Color.GREEN), RIGHT_UP(Color.RED);
	
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
		default://3 포함
			return RIGHT_UP;
		} 
	}
}
