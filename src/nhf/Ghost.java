package nhf;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Ghost extends  MovingEntity{
	
	protected Position targetPos;
	protected Position basePos;
	private boolean canGoUp;
	private boolean canGoDown;
	private boolean canGoLeft;
	private boolean canGoRight;
	private int fieldIndex = -1;
	protected MovingEntity targetEnt;
	protected boolean reachedCorner = false;
	Image frightened = new ImageIcon("g_frightened.png").getImage();
	Image frightenedEnd = new ImageIcon("g_frightened_end.gif").getImage();
	Image ghostRight = new ImageIcon("b_right.gif").getImage();
	Image ghostLeft = new ImageIcon("b_left.gif").getImage();
	Image ghostUp = new ImageIcon("b_up.gif").getImage();
	Image ghostDown = new ImageIcon("b_down.gif").getImage();
	

	Ghost(){}
	
	Ghost(int x, int y){
		super(2, x, y);
		entity = ghostLeft;
		setImages(ghostLeft, ghostRight, ghostUp, ghostDown);
		pos = new Position(x,y);
		basePos = new Position(20,80);
		targetPos = basePos;
		
	}
	
	public void respawn() {
		super.respawn();
		targetPos = basePos;
	}
	
	public void disappear() {
		pos.setPos(new Position(570,0));
	}
	
	public void setTargetEnt(MovingEntity target) {
		targetEnt = target;
	}
	
	public MovingEntity getTargetEnt() {
		return targetEnt;
	}
	
	/**ez a függvény gyakorlatilag Blinky üldözési módszerét írja le
	 * @param target Pac Man pozíciója
	 */
	public void setTarget(Position target) {
		
		targetPos = target;
	}
	
	public void setFrTarget(Position target) {
		targetPos = target;
	}
	
	public Position getTargetPos() {
		return targetPos;
	}
	
	public void chase() {
		targetPos = targetEnt.getPos();
		setImages(ghostLeft, ghostRight, ghostUp, ghostDown);
	}
	
	/**Kiszámolja, hogy melyik irányba menjen a szellem, ha a legrövidebb úton akarja elérni a célját
	 * @param list, mezõk listája
	 * @param dir
	 */
	public void shortestRoute(ArrayList<Field> list, Direction dir) {
		Double distance = Double.POSITIVE_INFINITY;
		
		if(isIntersection(list, dir)) {
			if(canGoUp && dir != Direction.DOWN) {
				double tempDist = list.get(fieldIndex).getNeighbor(Direction.UP).pos.distance(targetPos);
				if(tempDist < distance) {
					distance = tempDist;
					nextdir = Direction.UP;
				}
			}
			if(canGoDown && dir != Direction.UP) {
				double tempDist = list.get(fieldIndex).getNeighbor(Direction.DOWN).pos.distance(targetPos);
				if(tempDist < distance) {
					distance = tempDist;
					nextdir = Direction.DOWN;
				}
			}
			if(canGoLeft && dir != Direction.RIGHT) {
				double tempDist = list.get(fieldIndex).getNeighbor(Direction.LEFT).pos.distance(targetPos);
				if(tempDist < distance) {
					distance = tempDist;
					nextdir = Direction.LEFT;
				}
			}
			if(canGoRight && dir != Direction.LEFT) {
				double tempDist = list.get(fieldIndex).getNeighbor(Direction.RIGHT).pos.distance(targetPos);
				if(tempDist < distance) {
					distance = tempDist;
					nextdir = Direction.RIGHT;
				}
			}
		}
	}
	
	/**
	 * Beálltja a szellemek "ijedt" képét, valamint a bázisukat állítja be célpontként
	 */
	public void frightened() {
		setImages(frightened,frightened,frightened,frightened);
		setFrTarget(basePos);
	}
	
	public void frightenedEnd() {
		setImages(frightenedEnd,frightenedEnd,frightenedEnd,frightenedEnd);
	}
	
	/**Vizsgálja, hogy keresztezõdés-e az adott mezõ
	 * @param list, mezõk listája
	 * @param dir
	 * @return igazzal vagy hamissal tér vissza
	 */
	public boolean isIntersection(ArrayList<Field> list, Direction dir) {
		fieldIndex = onField(list);
		
		canGoUp = canTurn(list, Direction.UP, fieldIndex);
		canGoDown = canTurn(list, Direction.DOWN, fieldIndex);
		canGoLeft = canTurn(list, Direction.LEFT, fieldIndex);
		canGoRight = canTurn(list, Direction.RIGHT, fieldIndex);

		if(fieldIndex >= 0) {
			switch(dir) {
			case UP:
				return(canGoLeft || canGoRight || !canGoUp);
			case DOWN:
				return(canGoLeft || canGoRight || !canGoDown);
			case LEFT:
				return(canGoUp || canGoDown || !canGoLeft);
			case RIGHT:
				return(canGoUp || canGoDown || !canGoRight);
			}
		}
		return false;
	}
	
	/**Vizsgálja, hogy a szellemek elérték-e a bázis pozíciójukat
	 * @return
	 */
	public boolean isInCorner() {
		return pos.equals(basePos);
	}
	
	/**
	 *A szellemek a játék elején elmennek a bázispozíciójukra, majd amint elérték elkezdik üldözni saját stratégia szerint Pac Man-t
	 */
	public void move(ArrayList<Field> list) {
		if(isInCorner()) {
			setTarget(targetEnt.getPos());
		}
		boolean isIntersection = isIntersection(list, dir);
		if(isIntersection) {
			shortestRoute(list, dir);
		}
		super.move(list);
	}
}
