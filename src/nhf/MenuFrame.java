package nhf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame implements ActionListener, Constants{
	
	JButton button, board;
	ImageIcon startButton;
	JLabel label, name;
	JTextField textField;
	ImageIcon logo, icon;
	Player player;
	PlayerList list = new PlayerList();
	
	
	MenuFrame(PlayerList list2){
		list = list2;
		startButton = new ImageIcon("startbutton2.png");
		label = new JLabel();
		name = new JLabel();
		textField = new JTextField();
	
		player = new Player();
		player.setName(textField.getText());
		if(player.getName() == null || player.getName() == "") {
			player.setName("anonymus");
		}
		
		logo = new ImageIcon("pac-man-logo.gif");
		icon = new ImageIcon("logo.png");
		button = new JButton();
		button.setBounds(128,420,320,90);
		button.setBackground(Color.black);
		button.addActionListener(this);
		button.setFocusable(false);
		button.setIcon(startButton);
		
		board = new JButton("LEADERBOARD");
		board.setBounds(205,530,150,50);
		board.setForeground(Color.yellow);
		board.setBackground(Color.black);
		board.addActionListener(this);
		board.setFocusable(false);
		
		textField.setBounds(205,350,250,30);
		textField.setForeground(Color.yellow);
		textField.setBackground(Color.black);
		textField.setCaretColor(Color.white);
		
		label.setIcon(logo);
		label.setBounds(38,40,500,300);
		
		name.setText("Player name:");
		name.setForeground(Color.yellow);
		name.setBounds(120,350,100,30);
		
		setTitle("Pac-Man");
		setIconImage(icon.getImage());
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(MAP_WIDTH+16,MAP_HEIGHT+32);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(button);
		add(board);
		add(label);
		add(textField);
		add(name);
	}
	
	MenuFrame() throws FileNotFoundException{
		startButton = new ImageIcon("startbutton2.png");
		label = new JLabel();
		name = new JLabel();
		textField = new JTextField();
		
		logo = new ImageIcon("pac-man-logo.gif");
		icon = new ImageIcon("logo.png");
		button = new JButton();
		button.setBounds(128,420,320,90);
		button.setBackground(Color.black);
		button.addActionListener(this);
		button.setFocusable(false);
		button.setIcon(startButton);
		
		board = new JButton("LEADERBOARD");
		board.setBounds(205,530,150,50);
		board.setForeground(Color.yellow);
		board.setBackground(Color.black);
		board.addActionListener(this);
		board.setFocusable(false);
		
		textField.setBounds(205,350,250,30);
		textField.setForeground(Color.yellow);
		textField.setBackground(Color.black);
		textField.setCaretColor(Color.white);
		label.setIcon(logo);
		label.setBounds(38,40,500,300);
		
		name.setText("Player name:");
		name.setForeground(Color.yellow);
		name.setBounds(120,350,100,30);
		
		setTitle("Pac-Man");
		setIconImage(icon.getImage());
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(MAP_WIDTH+16,MAP_HEIGHT+32);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(button);
		add(board);
		add(label);
		add(textField);
		add(name);
		list.load();
	}
	
	/**
	 *A megfelelõ gomb megnyomására elindul a játék, vagy megnyílik a top 10 játékos listája
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		player = new Player();
		player.setName(textField.getText());
		if(player.getName() == null || player.getName() == "") {
			player.setName("anonymus");
		}
		if(e.getSource() == button) {
			new GameFrame(player, list);
			dispose();
		}
		if(e.getSource() == board) {
			new LeaderBoard(list);
			dispose();
		}
	}
}
