package nhf;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Clyde extends Ghost{
	
	Image clydeRight = new ImageIcon("c_right.gif").getImage();
	Image clydeLeft = new ImageIcon("c_left.gif").getImage();
	Image clydeUp = new ImageIcon("c_up.gif").getImage();
	Image clydeDown = new ImageIcon("c_down.gif").getImage();
	
	public Clyde(int x, int y){
		super(x, y);
		entity = clydeRight;
		setImages(clydeLeft, clydeRight, clydeUp, clydeDown);
		pos = new Position(x,y);
		basePos = new Position(520,640);
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
	
	
	/**Clyde Pac Man-t üldözi, azonban ha 12 mezõ sugarán belül van, akkor a bázisa lesz a célpont
	 *@param target Pac Man pozíciója
	 */
	public void setTarget(Position target) {
		if(pos.distance(target) < 240) {
			super.setTarget(basePos);
		}
		else super.setTarget(target);
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
	
	public void chase() {
		targetPos = targetEnt.getPos();
		setImages(clydeLeft, clydeRight, clydeUp, clydeDown);
	}

}
