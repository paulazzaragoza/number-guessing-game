package players;

import utils.Difficulty;

public class User {
	
	//atributes
	String username; 
	int points; 
	
	//constructor
	public User(String username) {
		this.username = username; 
		points = 0;
	}
	
	//getters
	public String getUsername() {
		return username; 
	}
	
	public int getPoints() {
		return points;
	}
	
	//methods
	public void incrementPoints(Difficulty mode) {
		switch(mode) {
			case Difficulty.EASY:
				points += 5;
				break;
				
			case Difficulty.MEDIUM:
				points += 15;
				break;
				
				
			case Difficulty.HARD:
				points += 200;
				break;
		}
	}
	
	public void setPointsZero() {
		points = 0;
	}
}
