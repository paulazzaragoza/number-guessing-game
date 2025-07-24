package players;

import java.util.Random;
import commands.Shell;
import utils.Difficulty;

public class Opponent {
	
	//atributes
	int number; 
	
	//constructor
	public Opponent() {
	}
	
	//getter
	public int getNumber() {
		return number;
	}
	
	//methods
	public void generateRandomNumber(Shell sh) {
		Random rnd = new Random();
		
		Difficulty mode = sh.getMode();
		switch(mode) {
			case Difficulty.EASY:
				number = rnd.nextInt(1, 11);
				sh.setTries(5);
				break;
				
			case Difficulty.MEDIUM:
				number = rnd.nextInt(1, 21);
				sh.setTries(3);
				break;
				
			case Difficulty.HARD:
				number = rnd.nextInt(1, 51);
				sh.setTries(1);
				break;
		}
	}
}
