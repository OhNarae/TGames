package tnt.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import tnt.tetris.JPanel_Game.Point;

class JPanel_Game extends JPanel {

	class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	Point[] blockPoint;

	
	
	JPanel_Game() {
		blockPoint = new Point[4];
		blockPoint[0] = new Point(0, 0);
		blockPoint[1] = new Point(0, 1);
		blockPoint[2] = new Point(0, 2);
		blockPoint[3] = new Point(0, 3);
	}

	public void keyProcessing(int vkDown) {
		int nowKey = vkDown;

		switch (nowKey) {
		case KeyEvent.VK_DOWN:
			for (Point points : blockPoint) {
				points.y += 1;
			}
			break;
		case KeyEvent.VK_UP:
			for (Point points : blockPoint) {
				points.y -= 1;
			}
			break;
		case KeyEvent.VK_LEFT:
			for (Point points : blockPoint) {
				points.y += 1;
			}
			break;
		case KeyEvent.VK_RIGHT:
			for (Point points : blockPoint) {
				points.y += 1;
			}
			break;	
		}

		repaint();
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