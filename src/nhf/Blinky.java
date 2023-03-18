package nhf;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Blinky extends Ghost{
	
	Image blinkyRight = new ImageIcon("b_right.gif").getImage();
	Image blinkyLeft = new ImageIcon("b_left.gif").getImage();
	Image blinkyUp = new ImageIcon("b_up.gif").getImage();
	Image blinkyDown = new ImageIcon("b_down.gif").getImage();

	public Blinky(int x, int y){
		super(x, y);
		entity = blinkyLeft;
		setImages(blinkyLeft, blinkyRight, blinkyUp, blinkyDown);
		pos = new Position(x,y);
		basePos = new Position(20,80);
		targetPos = basePos;
	}
	
	public void respawn() {
		dir = Direction.LEFT;
		nextdir = Direction.LEFT;
		reachedCorner = false;
		super.respawn();
	}
	
	public void chase() {
		targetPos = targetEnt.getPos();
		setImages(blinkyLeft, blinkyRight, blinkyUp, blinkyDown);
	}

}
