package nhf;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Inky extends Ghost{
	
	protected Direction dir = Direction.UP;
	protected Direction nextdir = Direction.UP;
	protected Ghost targetGhost;
	Image inkyRight = new ImageIcon("i_right.gif").getImage();
	Image inkyLeft = new ImageIcon("i_left.gif").getImage();
	Image inkyUp = new ImageIcon("i_up.gif").getImage();
	Image inkyDown = new ImageIcon("i_down.gif").getImage();
	
	public Inky(int x, int y){
		super(x, y);
		entity = inkyLeft;
		setImages(inkyLeft, inkyRight, inkyUp, inkyDown);
		pos = new Position(x,y);
		basePos = new Position(20,640);
		targetPos = basePos;
	}
	
	public void respawn() {
		dir = Direction.LEFT;
		nextdir = Direction.LEFT;
		reachedCorner = false;
		super.respawn();
	}
	
	public void setTargetEnt(MovingEntity target, Ghost targetG) {
		targetEnt = target;
		targetGhost = targetG;
	}
	
	public void chase() {
		targetPos = targetEnt.getPos();
		setImages(inkyLeft, inkyRight, inkyUp, inkyDown);
	}
	
	/**Inky target-je a Blinky helyzetének Pac Man-re középpontosan tükrözött pozíciója
	 * @param target Pac Man pozíciója
	 * @param targetG Blinky pozíciója
	 */
	public void setTarget(Position target, Position targetG) {
		Position tempTarget = new Position(0, 0);
		int distanceX = 2*target.getX() - targetG.getX();
		int distanceY = 2*target.getY() - targetG.getY();
		tempTarget.setX(distanceX);
		tempTarget.setY(distanceY);
		targetPos = tempTarget;
	}
	
	
	
	public void move(ArrayList<Field> list) {
		if(isInCorner()) {
			setTarget(targetEnt.getPos(), targetGhost.getPos());
			reachedCorner = true;
			
		}
		if(reachedCorner) {
			setTarget(targetEnt.getPos(), targetGhost.getPos());
		}
		
		boolean isIntersection = isIntersection(list, dir);
		if(isIntersection) {
			shortestRoute(list, dir);
		}
		super.move(list);
	}
}
