package tnt;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static GameFrame Inst;

	CardLayout cardLayout = new CardLayout();
	
	public void init() {
		Inst = this;
		
		setTitle("Tarot aNd Tetris");
		setSize(Statics.FRAME_WIDTH, Statics.FRAME_HEIGHT);
		
		getContentPane().setLayout(cardLayout);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		setLocation((int) (screen.getWidth() / 2 - Statics.FRAME_WIDTH / 2), (int) (screen.getHeight() / 2 - Statics.FRAME_HEIGHT / 2));

		getContentPane().add(GAME.FIRST.name(), new JPanel_First());
		getContentPane().add(GAME.TARO.name(), new tnt.taro.JPanel_Taro());
		getContentPane().add(GAME.TETRIS.name(), new tnt.tetris.JPanel_Tetris());	
		getContentPane().add(GAME.TRICK_PICTURE.name(), new tnt.trick_picture.JPanel_TrickPicture());

		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void changePanel(GAME panelType) {
		switch (panelType) {
		case FIRST:
			cardLayout.show(this.getContentPane(), GAME.FIRST.name());
			getContentPane().add(GAME.TARO.name(), new tnt.taro.JPanel_Taro());			
			getContentPane().add(GAME.TETRIS.name(), new tnt.tetris.JPanel_Tetris());
			getContentPane().add(GAME.TRICK_PICTURE.name(), new tnt.trick_picture.JPanel_TrickPicture());
			break;
		case TARO:
			cardLayout.show(this.getContentPane(), GAME.TARO.name());
			break;
		case TETRIS:
			cardLayout.show(this.getContentPane(), GAME.TETRIS.name());
			break;	
		case TRICK_PICTURE:
			cardLayout.show(this.getContentPane(), GAME.TRICK_PICTURE.name());
			break;		
		default:
		}
	}
}