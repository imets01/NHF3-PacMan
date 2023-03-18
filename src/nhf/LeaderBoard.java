package nhf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LeaderBoard extends JFrame implements Constants, ActionListener{

	ImageIcon icon, backbutton;
	JLabel name;
	JButton back;
	PlayerList listtemp = new PlayerList();
	
	LeaderBoard(PlayerList list){
		listtemp = list;
		icon = new ImageIcon("logo.png");
		backbutton = new ImageIcon("backbutton.png");
		back = new JButton();
		back.setBounds(0,0,50,50);
		back.setBackground(Color.black);
		back.addActionListener(this);
		back.setFocusable(false);
		back.setIcon(backbutton);
		
		add(back);
		setTitle("Pac-Man");
		setIconImage(icon.getImage());
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(MAP_WIDTH+16,MAP_HEIGHT+32);
		setLayout(new GridLayout(11,1));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		for(int i = 0; i < list.getSize(); i++) {
			String playerName = list.get(i).getName();
			int score = list.get(i).getScore();
			name = new JLabel();
			name.setForeground(Color.yellow);
			name.setText(i+1 + ". " + playerName + "  SCORE:  " + score);
			name.setHorizontalAlignment(JLabel.CENTER);
			add(name);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			new MenuFrame(listtemp);
			dispose();
		}
	}
}
