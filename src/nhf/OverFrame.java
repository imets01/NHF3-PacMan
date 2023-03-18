package nhf;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OverFrame extends JFrame implements Constants, ActionListener{
	
	ImageIcon icon;
	JButton button;
	JLabel gameOver;
	ImageIcon gameOverIm;
	PlayerList listtemp = new PlayerList();
	
	OverFrame(PlayerList list){
		listtemp = list;
		icon = new ImageIcon("logo.png");
		gameOverIm = new ImageIcon("gameover.png");
		gameOver = new JLabel();
		gameOver.setIcon(gameOverIm);
		gameOver.setBounds(160,180,500,300);
		
		button = new JButton("MENU");
		button.setBounds(205,400,150,50);
		button.setBackground(Color.black);
		button.setForeground(Color.yellow);
		button.addActionListener(this);
		button.setFocusable(false);
		
		setTitle("Pac-Man");
		setIconImage(icon.getImage());
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(MAP_WIDTH+16,MAP_HEIGHT+32);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(gameOver);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			
			new MenuFrame(listtemp);
			dispose();
		}
	}
}
