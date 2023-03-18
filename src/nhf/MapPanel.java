package nhf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Graphics2D;

public class MapPanel extends JPanel implements KeyListener{
	private final int maze[][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,2,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,2,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},
			{0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,3,0,0,3,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,0,0,0,3,0,0,3,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,3,3,3,3,3,3,3,3,3,3,0,0,1,0,0,0,0,0,0},	
			{0,0,0,0,0,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,3,0,0,0,3,3,0,0,0,3,0,0,1,0,0,0,0,0,0},
			{3,3,3,3,3,0,1,3,3,3,0,0,3,3,3,3,0,0,3,3,3,1,0,3,3,3,3,3},
			{0,0,0,0,0,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,3,3,3,3,3,3,3,3,3,3,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,2,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,2,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
			{0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	Timer timer;
	Image map;
	Image pacman;
	PacMan pacMan = new PacMan(270, 520);
	Inky inky = new Inky(240, 280);
	Pinky pinky = new Pinky(270, 280);
	Blinky blinky = new Blinky(270,280);
	Clyde clyde = new Clyde(300, 280);
	ArrayList<Field> list = new ArrayList<>();
	//JTextField points;
	
	public MapPanel(){
		map = new ImageIcon("PacMan_background.png").getImage();
		setPreferredSize(new Dimension(560,720));
		addKeyListener(this);
		//points = new JTextField("Type here!");
		timer = new Timer(10, event->{
			pacMan.move(list);
			blinky.move(list);
			pinky.move(list);
			inky.move(list);
			clyde.move(list);
			
			if(pacMan.hasPower()) {
				pinky.frightened();
				inky.frightened();
				blinky.frightened();
				clyde.frightened();
				if(pacMan.getPowerDuration() == 4) {
					pinky.chase();
					inky.chase();
					blinky.chase();
					clyde.chase();
				}
				if(pacMan.getPowerDuration() < 600 && pacMan.getPowerDuration() > 4) {
					pinky.frightenedEnd();
					inky.frightenedEnd();
					blinky.frightenedEnd();
					clyde.frightenedEnd();
				}
			}
			collision(pinky);
			collision(blinky);
			collision(inky);
			collision(clyde);
			repaint();
		});
		timer.start();
		blinky.setTargetEnt(pacMan);
		pinky.setTargetEnt(pacMan);
		inky.setTargetEnt(pacMan, blinky);
		clyde.setTargetEnt(pacMan);
	}
	
	/**A függvény dönti el, hogy szellemmel való ütközés esetén mi fog történni
	 * @param ghost az szellem amivel Pac Man ütközött
	 */
	public void collision(Ghost ghost) {
		if(pacMan.isHit(ghost)) {
			if(pacMan.hasPower()) {
				pacMan.eat(ghost);
			}
			else{
				pacMan.setWait(310);
				}
		}
		if(pacMan.getPowerDuration() > 0) {
			pacMan.setPowerDuration(pacMan.getPowerDuration() - 1);
		}
		if(pacMan.getWait() > 0) {
			pinky.disappear();
			blinky.disappear();
			inky.disappear();
			clyde.disappear();
			
			pacMan.setWait(pacMan.getWait() - 1);
			ghost.setCurrentSpeed(0);
			pacMan.setCurrentSpeed(0);
			if(pacMan.getWait() == 1) {
				pinky.respawn();
				blinky.respawn();
				inky.respawn();
				clyde.respawn();
				pacMan.death();
				pacMan.setCurrentSpeed(pacMan.getBaseSpeed());
				pinky.setCurrentSpeed(pinky.getBaseSpeed());
				blinky.setCurrentSpeed(blinky.getBaseSpeed());
				inky.setCurrentSpeed(inky.getBaseSpeed());
				clyde.setCurrentSpeed(clyde.getBaseSpeed());
				pacMan.setWait(0);
				}
		}
	}
	
	public PacMan getPacMan() {
		return pacMan;
	}

	
	/**Ellenörzi Pac Man életét, ennek megfelelõen igazzal vagy hamissal tér vissza
	 * @return
	 */
	public boolean endGame() {
		if(pacMan.getHealth() == 0) {
			return true;
		}
		return false;
	}

	/*A függvény festi a pályát, karaktereket, egyéb elemeket
	 */
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(map, 0, 0, null);
		
		for(Field f: list) {
			if(f.isHasPowerPellet()) {
				g2D.setPaint(Color.pink);
				g2D.fillOval(f.getFieldX()+2, f.getFieldY()+2, 15, 15);
			}
			else if(f.isHasPellet()) {
				g2D.setPaint(Color.yellow);
				g2D.fillOval(f.getFieldX()+7, f.getFieldY()+7, 5, 5);
			}
		}
		/*
		g2D.setPaint(Color.red);
		g2D.fillOval(blinky.getTargetPos().getX()+7, blinky.getTargetPos().getY()+7, 6,6);
		g2D.setPaint(Color.pink);
		g2D.fillOval(pinky.getTargetPos().getX()+7, pinky.getTargetPos().getY()+7, 6,6);
		g2D.setPaint(Color.blue);
		g2D.fillOval(inky.getTargetPos().getX()+7, inky.getTargetPos().getY()+7, 6,6);
		g2D.setPaint(Color.orange);
		g2D.fillOval(clyde.getTargetPos().getX()+7, clyde.getTargetPos().getY()+7, 6,6);
		*/
		pacMan.paint(g);
		blinky.paint(g);
		inky.paint(g);
		pinky.paint(g);
		clyde.paint(g);

		g2D.setPaint(Color.white);
		g2D.setFont(new Font("Arial", Font.BOLD, 30));
		g2D.drawString("Score: " + pacMan.getScore(), 200, 30);
		g2D.drawString("Health: " + pacMan.getHealth(), 20, 710);
	}
	
	/**Ez a függvény generálja a mezõket, valamint beállítja a mezõ szomszédait
	 */
	public void generateMap() {
		for(int i = 0; i < 31; i++) {
			int posY = i*20+60;
			
			for(int j = 0; j < 28; j++) {
				int posX = j*20;
				Field f = new Field(maze[i][j], posX, posY);
				list.add(f);
			}
		}
		for(Field field1 : list) {
			for(Field field2 : list) {
				if(field1.getFieldX() == field2.getFieldX() && field1.getFieldY() == field2.getFieldY() + 20)
					field1.setNeighbor(Direction.UP, field2);
				else if(field1.getFieldX() == field2.getFieldX() && field1.getFieldY() == field2.getFieldY() - 20)
					field1.setNeighbor(Direction.DOWN, field2);
				else if(field1.getFieldX() == field2.getFieldX() + 20 && field1.getFieldY() == field2.getFieldY())
					field1.setNeighbor(Direction.LEFT, field2);
				else if(field1.getFieldX() == field2.getFieldX() - 20 && field1.getFieldY() == field2.getFieldY())
					field1.setNeighbor(Direction.RIGHT, field2);
			}
		}			
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 *A nyilakkal való irányításért felelõs függvény
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP: 
			pacMan.setNextDir(Direction.UP);
			break;
		case KeyEvent.VK_DOWN: 
			pacMan.setNextDir(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT: 
			pacMan.setNextDir(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			pacMan.setNextDir(Direction.RIGHT);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public ArrayList<Field> getList(){
		return list;
	}
}

