package tnt.tetris;

import java.awt.Color;


class Point implements Cloneable{
	int x;
	int y;
	Color color;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	Point(int x, int y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
}

public class TBlock{

	BLOCK_TYPE type;
	Point[] points;
	
	int width; //블럭 하나 당 넓이
	int height; //블럭 하나 당 높이
	
	enum BLOCK_STATE{NORTH, WEST, EAST, SOUTH};
	BLOCK_STATE state;
	
	TBlock(int x0, int y0, int width, int height){
		this(BLOCK_TYPE.GetBlockType((int)(Math.random()*(BLOCK_TYPE.values().length))), x0, y0 , width, height);
	}
	
	TBlock(BLOCK_TYPE type, int x0, int y0, int width, int height){
		points = new Point[4];
		
		this.type = type;
		this.width = width;
		this.height = height;
		SetBlockPoint(x0, y0, width, height);
	}
	
	void SetBlockPoint(int x0, int y0, int width, int height) {
		switch(type) {
		case SQUARE:
			points[0] = new Point(x0 + width	, y0 + height);
			points[1] = new Point(x0 + width * 2, y0 + height);
			points[2] = new Point(x0 + width	, y0 + height * 2);
			points[3] = new Point(x0 + width * 2, y0 + height * 2);
			break;
		case LONG:
			points[0] = new Point(x0 + (int)(width * 1.5), y0);
			points[1] = new Point(x0 + (int)(width * 1.5), y0 + height * 1);
			points[2] = new Point(x0 + (int)(width * 1.5), y0 + height * 2);
			points[3] = new Point(x0 + (int)(width * 1.5), y0 + height * 3);
			break;
		case LEFT_UP:
			points[0] = new Point(x0 + width	, y0 + (int)(height * 0.5));
			points[1] = new Point(x0 + width * 2, y0 + (int)(height * 1.5));
			points[2] = new Point(x0 + width	, y0 + (int)(height * 1.5));
			points[3] = new Point(x0 + width * 2, y0 + (int)(height * 2.5));
			break;
		case RIGHT_UP:
			points[0] = new Point(x0 + width	, y0 + (int)(height * 1.5));
			points[1] = new Point(x0 + width * 2, y0 + (int)(height * 0.5));
			points[2] = new Point(x0 + width	, y0 + (int)(height * 2.5));
			points[3] = new Point(x0 + width * 2, y0 + (int)(height * 1.5));
			break;
		case LEFT_BOOT:	
			points[0] = new Point(x0 + width * 2, y0 + (int)(height * 0.5));
			points[1] = new Point(x0 + width * 2, y0 + (int)(height * 1.5));
			points[2] = new Point(x0 + width * 2, y0 + (int)(height * 2.5));
			points[3] = new Point(x0 + width 	, y0 + (int)(height * 2.5));
			break;
		case RIGHT_BOOT:	
			points[0] = new Point(x0 + width	, y0 + (int)(height * 0.5));
			points[1] = new Point(x0 + width 	, y0 + (int)(height * 1.5));
			points[2] = new Point(x0 + width	, y0 + (int)(height * 2.5));
			points[3] = new Point(x0 + width * 2, y0 + (int)(height * 2.5));
			break;	
		}
	}
	
	void ChangeBlockPoint(int x_diff, int y_diff) 
	{
		for(Point point : points) {
			point.x += x_diff;
			point.y += y_diff;
		}
	}
	
	TBlock(BLOCK_TYPE type, int x0, int y0){
		points = new Point[4];
		
		this.type = type;
		this.state = BLOCK_STATE.NORTH;
		SetBlockPoint(x0, y0);
	}
	
	void SetBlockPoint(int xIndex, int yIndex) {
		switch(type) {
		case SQUARE:
			points[0] = new Point(xIndex	, yIndex);
			points[1] = new Point(xIndex + 1, yIndex);
			points[2] = new Point(xIndex	, yIndex + 1);
			points[3] = new Point(xIndex + 1, yIndex + 1);
			break;
		case LONG:
			points[0] = new Point(xIndex, yIndex);
			points[1] = new Point(xIndex, yIndex + 1);
			points[2] = new Point(xIndex, yIndex + 2);
			points[3] = new Point(xIndex, yIndex + 3);
			break;
		case LEFT_UP:
			points[0] = new Point(xIndex	, yIndex);
			points[1] = new Point(xIndex + 1, yIndex + 1);
			points[2] = new Point(xIndex	, yIndex + 1);
			points[3] = new Point(xIndex + 1, yIndex + 2);
			break;
		case RIGHT_UP:
			points[0] = new Point(xIndex	, yIndex + 1);
			points[1] = new Point(xIndex + 1, yIndex);
			points[2] = new Point(xIndex	, yIndex + 2);
			points[3] = new Point(xIndex + 1, yIndex + 1);
			break;
		case LEFT_BOOT:
			points[0] = new Point(xIndex + 1, yIndex);
			points[1] = new Point(xIndex + 1, yIndex + 1);
			points[2] = new Point(xIndex + 1, yIndex + 2);
			points[3] = new Point(xIndex 	, yIndex + 2);
			break;
		case RIGHT_BOOT:
			points[0] = new Point(xIndex	, yIndex);
			points[1] = new Point(xIndex	, yIndex + 1);
			points[2] = new Point(xIndex	, yIndex + 2);
			points[3] = new Point(xIndex + 1, yIndex + 2);
			break;	
		}
	}
	
	public void goDown() {
		for (Point points : points) {
			points.y += 1;
		}
	}
	
	public void goLeft() {
		for (Point points : points) {
			points.x -= 1;
		}
	}
	
	public void goRight() {
		for (Point points : points) {
			points.x += 1;
		}
	}
	
	public Point[] predictChange() {
		Point[] points = new Point[4];
		points[0] = new Point(this.points[0].x, this.points[0].y);
		points[1] = new Point(this.points[1].x, this.points[1].y);
		points[2] = new Point(this.points[2].x, this.points[2].y);
		points[3] = new Point(this.points[3].x, this.points[3].y);
		
		change(this.state, points);
		
		return points;
	}
	
	public void goChange() {
		if(!change(this.state, this.points)) return;
		
		switch(this.state){
			case NORTH:
				this.state = BLOCK_STATE.WEST; break;
			case WEST:
				this.state = BLOCK_STATE.SOUTH; break;
			case SOUTH:
				this.state = BLOCK_STATE.EAST; break;
			case EAST:
				this.state = BLOCK_STATE.NORTH; break;
		}
	}
	
	private boolean change(BLOCK_STATE state, Point[] points) {
//		System.out.println("****************");
//		for(Point point : points)
//			System.out.println("point.x " + point.x + ", point.y" + point.y);
		
		switch(type) {
		case SQUARE:
			break;
		case LONG:
			switch(state) {
			case NORTH:
			case SOUTH:	
				points[1].x = points[0].x + 1;	points[1].y = points[0].y;
				points[2].x = points[0].x + 2;	points[2].y = points[0].y;
				points[3].x = points[0].x + 3;	points[3].y = points[0].y;
				break;
			case EAST:
			case WEST:	
				points[1].x = points[0].x;	points[1].y = points[0].y + 1;
				points[2].x = points[0].x;	points[2].y = points[0].y + 2;
				points[3].x = points[0].x;	points[3].y = points[0].y + 3;
				break;
			}
			break;
		case LEFT_UP:
			switch(state) {
			case NORTH :
			case SOUTH:	
				points[1].x = points[0].x + 1; 	points[1].y = points[0].y;
				points[2].x = points[0].x - 1; 	points[2].y = points[0].y + 1;
				points[3].x = points[0].x; 		points[3].y = points[0].y + 1;
				break;
			case EAST:
			case WEST:	
				points[1].x = points[0].x;		points[1].y = points[0].y + 1;
				points[2].x = points[0].x + 1;	points[2].y = points[0].y + 1;
				points[3].x = points[0].x + 1;	points[3].y = points[0].y + 2;
				break;
			}
			break;
		case RIGHT_UP:
			switch(state) {
			case NORTH :
			case SOUTH:	
				points[1].x = points[0].x - 1; 	points[1].y = points[0].y;
				points[2].x = points[0].x; 		points[2].y = points[0].y + 1;
				points[3].x = points[0].x + 1; 	points[3].y = points[0].y + 1;
				break;
			case EAST:
			case WEST:
				points[1].x = points[0].x;		points[1].y = points[0].y + 1;
				points[2].x = points[0].x - 1;	points[2].y = points[0].y + 1;
				points[3].x = points[0].x - 1;	points[3].y = points[0].y + 2;
				break;	
			}
			break;
		case LEFT_BOOT:
			switch(state) {
			case NORTH :
				points[1].x = points[0].x;	 	points[1].y = points[0].y - 1;
				points[2].x = points[0].x + 1;	points[2].y = points[0].y;
				points[3].x = points[0].x + 2; 	points[3].y = points[0].y;
				break;
			case WEST:	
				points[1].x = points[0].x + 1; 	points[1].y = points[0].y;
				points[2].x = points[0].x; 		points[2].y = points[0].y + 1;
				points[3].x = points[0].x; 		points[3].y = points[0].y + 2;
				break;
			case SOUTH:
				points[1].x = points[0].x - 2; 	points[1].y = points[0].y;
				points[2].x = points[0].x - 1; 	points[2].y = points[0].y;
				points[3].x = points[0].x;	 	points[3].y = points[0].y + 1;
				break;
			case EAST:
				points[1].x = points[0].x;		points[1].y = points[0].y - 2;
				points[2].x = points[0].x;		points[2].y = points[0].y - 1;
				points[3].x = points[0].x - 1;	points[3].y = points[0].y;
				break;	
			}
			break;
		case RIGHT_BOOT:
			switch(state) {
			case NORTH :
				points[1].x = points[0].x;	 	points[1].y = points[0].y + 1;
				points[2].x = points[0].x + 1; 	points[2].y = points[0].y;
				points[3].x = points[0].x + 2; 	points[3].y = points[0].y;
				break;
			case WEST:	
				points[1].x = points[0].x - 1; 	points[1].y = points[0].y;
				points[2].x = points[0].x; 		points[2].y = points[0].y + 1;
				points[3].x = points[0].x;	 	points[3].y = points[0].y + 2;
				break;
			case SOUTH:
				points[1].x = points[0].x - 2; 	points[1].y = points[0].y;
				points[2].x = points[0].x - 1; 	points[2].y = points[0].y;
				points[3].x = points[0].x; 		points[3].y = points[0].y - 1;
				break;
			case EAST:
				points[1].x = points[0].x;		points[1].y = points[0].y - 1;
				points[2].x = points[0].x;		points[2].y = points[0].y - 2;
				points[3].x = points[0].x + 1;	points[3].y = points[0].y;
				break;	
			}
			break;
		}
		
//		for(Point point : points)
//			System.out.println("point.x " + point.x + ", point.y" + point.y);
//		System.out.println("****************");
		return true;
	}
}