package nhf;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Field {
	
	private boolean hasPellet = false;
	private boolean hasPowerPellet = false;
	private boolean wall = false;
	Position pos;
	private Map<Direction, Field> neighbors;
	
	public Field(int maze, int x, int y) {
		neighbors = new HashMap<Direction, Field>();
		pos = new Position(x,y);
		switch(maze) {
		case 0:
			setHasPellet(false);
			setHasPowerPellet(false);
			setWall(true);
			break;
		case 1:
			setHasPellet(true);
			setHasPowerPellet(false);
			break;
		case 2:
			setHasPellet(true);
			setHasPowerPellet(true);
			break;
		}
	}
	
	public void setNeighbor(Direction dir, Field field) {
		neighbors.put(dir, field);
	}
	
	public Field getNeighbor(Direction d) {
        if (neighbors.containsKey(d))
            return neighbors.get(d);
        return null;
    }
	
	public Map<Direction, Field> getNeighbors() {
		return neighbors;
	}
	
	public int getFieldX() {
		return pos.getX();
	}
	
	public int getFieldY() {
		return pos.getY();
	}

	public boolean isWall() {
		return wall;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}

	public boolean isHasPellet() {
		return hasPellet;
	}

	public void setHasPellet(boolean hasPellet) {
		this.hasPellet = hasPellet;
	}

	public boolean isHasPowerPellet() {
		return hasPowerPellet;
	}

	public void setHasPowerPellet(boolean hasPowerPellet) {
		this.hasPowerPellet = hasPowerPellet;
	}
}
