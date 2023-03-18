package nhf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Pinky extends Ghost{
	
	Image pinkyRight = new ImageIcon("p_right.gif").getImage();
	Image pinkyLeft = new ImageIcon("p_left.gif").getImage();
	Image pinkyUp = new ImageIcon("p_up.gif").getImage();
	Image pinkyDown = new ImageIcon("p_down.gif").getImage();
	
	public Pinky(int x, int y){
		super(x, y);
		entity = pinkyRight;
		setImages(pinkyLeft, pinkyRight, pinkyUp, pinkyDown);
		pos = new Position(x,y);
		basePos = new Position(520,80);
		targetPos = basePos;
		dir = Direction.RIGHT;
		nextdir = Direction.RIGHT;
	}
	
	public void respawn() {
		dir = Direction.RIGHT;
		nextdir = Direction.RIGHT;
		reachedCorner = false;
		super.respawn();
	}
	
	public void chase() {
		targetPos = targetEnt.getPos();
		setImages(pinkyLeft, pinkyRight, pinkyUp, pinkyDown);
	}
	
	
	/**Pinky célpontja mindig Pac Man elõtti negyedik mezõ
	 *@param target Pac Man pozíciója
	 */
	public void setTarget(Position target) {
		Direction entDir = targetEnt.getDir();
		Position temptargetPos = new Position(0,0);
		switch(entDir) {
		case UP:
			temptargetPos.setY(target.getY() - 80);
			temptargetPos.setX(target.getX() - 80);
			
			break;
		case DOWN:
			temptargetPos.setY(target.getY() + 80);
			temptargetPos.setX(target.getX());
			
			break;
		case LEFT:
			temptargetPos.setY(target.getY());
			temptargetPos.setX(target.getX() - 80);
			
			break;
		case RIGHT:
			temptargetPos.setY(target.getY());
			temptargetPos.setX(target.getX() + 80);
			break;
		}
		super.setTarget(temptargetPos);
	}
	
	public void move(ArrayList<Field> list) {
		if(isInCorner()) {
			setTarget(targetEnt.getPos());
			reachedCorner = true;
		}
		if(reachedCorner) {
			setTarget(targetEnt.getPos());
		}
		
		boolean isIntersection = isIntersection(list, dir);
		if(isIntersection) {
			shortestRoute(list, dir);
		}
		super.move(list);
	}
}
