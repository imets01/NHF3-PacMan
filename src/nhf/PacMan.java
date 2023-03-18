package nhf;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PacMan extends MovingEntity{
	
	private int basePowerDuration = 1800;
	private int powerDuration;
	private int health = 5;
	private int wait = 0;
	private int score = 0;
	
	public PacMan(int x, int y){
		super(2, x, y);
		Image pacmanRight = new ImageIcon("pac_right.gif").getImage();
		Image pacmanLeft = new ImageIcon("pac_left.gif").getImage();
		Image pacmanUp = new ImageIcon("pac_up.gif").getImage();
		Image pacmanDown = new ImageIcon("pac_down.gif").getImage();
		Image pacmanDeath = new ImageIcon("pac_death.gif").getImage();
		entity = pacmanLeft;
		powerDuration = 0;
		setImages(pacmanLeft, pacmanRight, pacmanUp, pacmanDown);
		setImageD(pacmanDeath);
	}
	
	public void respawn() {
		dir = Direction.LEFT;
		nextdir = Direction.LEFT;
		super.respawn();
	}
	
	public boolean hasPower() {
		if(powerDuration > 0) {
			return true;
		}
		return false;
	}
	
	public int getPowerDuration() {
		return powerDuration;
	}

	public void setPowerDuration(int powerDuration) {
		this.powerDuration = powerDuration;
	}
	
	/**Vizsg�lja, hogy Pac Man �tk�z�tt-e egy szellemmel
	 * @param ghost a vizsg�lt szellem
	 * @return igazzal vagy hamissal t�r vissza
	 */
	public boolean isHit(Ghost ghost) {
		double distance = pos.distance(ghost.pos);
		return (distance < hitBox+ghost.hitBox);
		
	}
	
	/**
	 * Cs�kkenti eggyel Pac Man �let�t �s respawnolja
	 */
	public void death() {
		health -= 1;
		respawn();
	}
	
	/**Az �tvett szellem respawn-nol a kiindul� hely�re
	 * @param ghost
	 */
	public void eat(Ghost ghost) {
		ghost.respawn();
		score += 200;
	}
	
	/**Elt�nteti a mez�r�l a megevett pelletet
	 * @param index mez� indexe
	 * @param list mez�k list�ja
	 * @return annak a mez�nek az indexe, amelyr�l megett�k a pelletet
	 */
	@Override 
	public int eatPellet(int index, ArrayList<Field> list) {
		if(list.get(index).isHasPowerPellet()) {
			powerDuration = basePowerDuration;
			score += 50;
		}
		if(list.get(index).isHasPellet()) {
			score += 10;
		}
		list.get(index).setHasPellet(false);
		list.get(index).setHasPowerPellet(false);
		return index;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
		entity = entityDeath;
	}

	public int getScore() {
		return score;
	}

	public int getHealth() {
		return health;
	}	
	
	public void setHealth(int h) {
		health = h;
	}
}
