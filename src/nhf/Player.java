package nhf;

import java.io.Serializable;

public class Player implements Comparable, Serializable{
	String name;
	Integer score;
	
	Player(){
		name = "anonymus";
		score = 0;
	}
	
	Player(String n, int s){
		name = n;
		score = s;
	}
	
	Player(String[] cmd) {
		name = cmd[1];
		score = Integer.parseInt(cmd[2]);
	}
	
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	public void setName(String n) {
		name = n;
	}
	public void setScore(int s) {
		score = s;
	}

	@Override
	public int compareTo(Object o) {
		Player p = (Player) o;
		return p.score - this.score;
	}
}
