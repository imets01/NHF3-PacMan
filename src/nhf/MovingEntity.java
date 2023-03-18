package nhf;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class MovingEntity implements Constants{
	protected int offsetX = 3;
	protected int offsetY = 3;
	
	protected Position pos;
	protected Position base;
	protected Direction dir = Direction.LEFT;
	Direction nextdir = Direction.LEFT;
	protected int currentSpeed;
	protected int baseSpeed;
	protected int hitBox = 10;
	Image entity, entityRight, entityLeft, entityUp, entityDown, entityDeath;

	MovingEntity(){}
	
	MovingEntity(int speed, int x, int y){
		baseSpeed = speed;
		setCurrentSpeed(baseSpeed);
		entityRight = new ImageIcon("pacc_right.gif").getImage();
		entityLeft = new ImageIcon("pac_left.gif").getImage();
		entityUp = new ImageIcon("pac_up.gif").getImage();
		entityDown = new ImageIcon("pac_down.gif").getImage();
		entity = entityLeft;
		base = new Position(x,y);
		pos = new Position(base.getX(), base.getY());
	}
	
	/**
	 * A f�ggv�ny visszahelyezi az entit�st a kiindul� pozici�j�ra
	 */
	public void respawn() {
		pos.setPos(base);
	}
	
	public Position getPos() {
		return pos;
	}
	
	public Direction getDir() {
		return dir;
		}
	
	public void setDir(Direction d) {
		dir = d;
	}
	
	public Direction getNextDir() {
		return nextdir;
	}

	public void setNextDir(Direction d) {
		nextdir = d;
	}
	
	public void setCurrentSpeed(int s) {
		currentSpeed = s;
	}
	public int getCurrentSpeed() {
		return currentSpeed;
	}
	
	public int getBaseSpeed() {
		return baseSpeed;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		int xi = pos.getX();
		int yi = pos.getY();
		g2D.drawImage(entity, xi-offsetX, yi-offsetY, null);
		
	}
	
	protected void setImages(Image left, Image right , Image up, Image down) {
		entityLeft = left;
		entityRight = right;
		entityUp = up;
		entityDown = down;
	}
	
	protected void setImageD(Image death) {
		entityDeath = death;
	}
	
	/**A f�ggv�ny megn�zi, hogy melyik mez�n �ll a karakter
	 * @param list mez�k list�ja
	 * @return a mez� index�t
	 */
	public int onField(ArrayList<Field> list) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getFieldY() == pos.getY() && list.get(i).getFieldX() == pos.getX()) {
				return i;
			}
		}
		return -1;
	}
	
	/**A f�ggv�ny vizsg�lja, hogy az adott mez�n elkanyarodhat-e a karakter
	 * @param list mez�k list�ja
	 * @param dir a karakter aktu�lis ir�nya
	 * @return
	 */
	public boolean canTurn(ArrayList<Field> list, Direction dir) {
		int index = onField(list);
		return canTurn(list, dir, index);
	}
	
	public boolean canTurn(ArrayList<Field> list,Direction dir, int index) {
		if(index >= 0) {
			if(list.get(index).getNeighbor(dir) != null && !list.get(index).getNeighbor(dir).isWall()) {
				return true;
			}
		}
		return false;
	}
	

	
	public int eatPellet(int index,ArrayList<Field> list) {
		return index;
	}

	/**A karakterek mozg�s��rt felel�s f�ggv�ny
	 * @param list, mez�k list�ja
	 */
	public void move(ArrayList<Field> list) {
		int index = -1;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getFieldY() == pos.getY() && list.get(i).getFieldX() == pos.getX()) {
				index = eatPellet(i, list);
				break;
			}
		}
		if(canTurn(list, nextdir)) {
			dir = nextdir;
		}
		switch(dir) {
		case UP:
			if(index != -1) {
				if(list.get(index).getNeighbor(dir) != null &&!list.get(index).getNeighbor(dir).isWall()) {
					pos.setY(pos.getY() - currentSpeed);
					entity = entityUp;	
				}
			}
			else pos.setY(pos.getY() - currentSpeed);
			
			break;
		case DOWN: 
			if(index != -1) {
				if(list.get(index).getNeighbor(dir) != null &&!list.get(index).getNeighbor(dir).isWall()) {
					pos.setY(pos.getY() + currentSpeed);
					entity = entityDown;	
				}
			}
			else pos.setY(pos.getY() + currentSpeed);
			
			break;
		case LEFT:
			if(index != -1) {
				if(list.get(index).getNeighbor(dir) != null &&!list.get(index).getNeighbor(dir).isWall()) {
					pos.setX(pos.getX() - currentSpeed);
					entity = entityLeft;	
				}
			}
			else pos.setX(pos.getX() - currentSpeed);
			break;
		case RIGHT:
			if(index != -1) {
				if(list.get(index).getNeighbor(dir) != null &&!list.get(index).getNeighbor(dir).isWall()) {
					pos.setX(pos.getX() + currentSpeed);
					entity = entityRight;	
				}
			}
			else pos.setX(pos.getX() + currentSpeed);
			break;
		default:
			break;
		}
	}
	
	/**Az ir�nynak megfelel� k�pet �ll�tja be a karaktereknek
	 * @param dir
	 */
	public void changeDirection(Direction dir) {
		this.dir = dir;
		switch(dir) {
		case UP: entity = entityUp;		
			break;
		case DOWN: entity = entityDown;
			break;
		case LEFT: entity = entityLeft;
			break;
		case RIGHT: entity = entityRight;
			break;
		default:
			break;
		}
	}
}
