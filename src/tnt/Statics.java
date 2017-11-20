package tnt;

import java.awt.Color;
import javax.swing.ImageIcon;

public interface Statics {
	ImageIcon imgBackground = new ImageIcon("image/first_background.png");
	ImageIcon imgTaro = new ImageIcon("image/taro.png");
	ImageIcon imgTetris = new ImageIcon("image/tetris.png");
	ImageIcon imgExit = new ImageIcon("image/exit.png");
	ImageIcon imgPen = new ImageIcon("image/pen.png");
	
	//Tetris

	int FRAME_WIDTH = 1200; // 화면의 넓이
	int FRAME_HEIGHT = 750; // 화면의 높이
	
	Color colorBackGround = new Color(255, 214, 109);

//	Font fontKorean = new Font("나눔고딕 ExtraBold", Font.PLAIN, 40);	//한글 쓸 때 적용할 글씨체
//	Font fontEnglish = new Font("Showcard Gothic", Font.PLAIN, 40);	//영어 쓸 때 적용할 글씨체
	String fontKorean = "나눔고딕 ExtraBold";
	String fontEnglish = "Showcard Gothic";	
}
