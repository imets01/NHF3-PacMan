package nhf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerList implements Serializable{
	
	ArrayList<Player> players = new ArrayList<>();

	public ArrayList<Player> getList() {
		return players;
	}
	
	protected void add(Player p) {
		Player temp = new Player(p.getName(), p.getScore());
		players.add(temp);
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public Player get(int index) {
		return players.get(index);
	}
	
	public int getSize() {
		return players.size();
	}

	/**
	 * Fájlba menti a játékoslistát
	 */
	protected void save() {
		try {
			File wd = new File(System.getProperty("user.dir"));
			File outputFile = new File(wd, "players.txt");
			outputFile.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(outputFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(players);
			objectOut.close();
			fileOut.close();
			System.out.println("Success");
			} catch(IOException e) {
				System.out.println("Unable to save players");
			}
		}

	/**Beolvassa fájlból a játékoslistát
	 * @throws FileNotFoundException
	 */
	protected void load() throws FileNotFoundException {
		File source = null;
		File wd = new File(System.getProperty("user.dir"));
		for(File file : wd.listFiles()) {
			if(file.getName().equals("players.txt")) {
				source = file;
			}	
		}
		if (source == null) {
            throw new FileNotFoundException();
        }
		try {
			FileInputStream fileIn = new FileInputStream(source);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			ArrayList<Player> tmp = (ArrayList<Player>) objectIn.readObject();
			players = tmp;
			objectIn.close();
			fileIn.close();
			System.out.println("Success");
		}catch(IOException | ClassNotFoundException e) {
			System.out.println("Unable to load players");
		}
	}
	
	protected void delete(int index) {
		players.remove(index);
	}
}
