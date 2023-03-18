import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import nhf.*;

public class Test1 {
	
	MapPanel map;
	PacMan pacMan = new PacMan(20,80);
	Blinky blinky = new Blinky(270,280);

	@BeforeClass
	public void init() throws Exception{
		map.generateMap();
	}
	
	@Test
	public void getFieldTest() {
		assertTrue("error",map.getList().get(29).getFieldX() == 20);
		assertTrue("error",map.getList().get(29).getFieldY() == 80);
		assertTrue("error",map.getList().get(29).isHasPellet());
		assertFalse("error",map.getList().get(29).isHasPowerPellet());
	}
	
	@Test
	public void getNeighborTest() {
		assertEquals("error", map.getList().get(29).getNeighbor(Direction.RIGHT) == map.getList().get(30));
	}
	
	@Test
	public void pacManTest() {
		pacMan.setCurrentSpeed(2);
		assertEquals("error", pacMan.getCurrentSpeed(), 2);
		assertEquals("error",pacMan.getPos().equals(new Position(20,80)));
		
	}
	
	@Test
	public void ghostTest() {
		assertEquals("error",blinky.getPos().equals(new Position(270,280)));
	}
	
	@Test
	public void ghostTargetTest() {
		assertEquals("error",blinky.getTargetEnt() == pacMan);
	}
	
	@Test
	public void distanceTest() {
		Position a = new Position(0,40);
		Position b = new Position(30,0);
		assertTrue("error",a.distance(b) == 50);
	}
	
	@Test
	public void mapPanelTest() {
		pacMan.setHealth(0);
		assertEquals("error",pacMan.getHealth(), 0);
		assertTrue("error",map.endGame());
	}

}
