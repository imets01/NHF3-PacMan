package nhf;
import javax.swing.*;


import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;

public class GameFrame extends JFrame implements Constants{
	
	MapPanel map;
	Timer timer;
	ImageIcon icon;
	int wait = 300;
	
	public void add(String[] cmd, PlayerList list) {
		Player p = new Player(cmd);
		list.add(p);
	}
	
	GameFrame(Player player, PlayerList list) {
		map = new MapPanel();
		icon = new ImageIcon("logo.png");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		timer = new Timer(10, event->{
			if(map.endGame()) {
				timer.stop();
				player.setScore(map.getPacMan().getScore());
				
				list.add(player);
				list.getList().sort(null);
				
				
				System.out.println(list.getSize());
				if(list.getSize() > 10) {
					list.delete(list.getSize()-1);
				}
				list.save();
				new OverFrame(list);
				dispose();
			}
		});
		timer.start();
		add(map);
		setIconImage(icon.getImage());
		addKeyListener(map);
		setTitle("Pac-Man");
		setSize(MAP_WIDTH,MAP_HEIGHT);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		map.generateMap();
		//System.out.println(map.getList().get(29).getFieldX() == 20);
	}
}
	
	
	
	
	
	

	
	

