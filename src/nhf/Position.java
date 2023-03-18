package nhf;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y){
		setX(x);
		setY(y);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	/**K�t pont k�z�tti t�vols�got sz�molja
	 * @param targ c�lpont poz�ci�ja
	 * @return k�t pont k�z�tti t�vols�g
	 */
	public double distance(Position targ) {
		return Math.sqrt(Math.pow(targ.getX() - getX(), 2) + Math.pow(targ.getY() - getY(), 2));
	}
	
	public void setPos(Position p) {
		setX(p.getX());
		setY(p.getY());
	}
	
	@Override
	public boolean equals(Object o) {
		Position pos = (Position) o;
		return this.getX() == pos.getX() && this.getY() == pos.getY();
		}
}
