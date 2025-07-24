package application;

import commands.Shell;

public class Guesser {
	
	public static void main(String[] args) {
		Shell sh = new Shell();
		boolean continueGuessing = true;
		
		sh.loginUser();
		
		while(continueGuessing) {
			try {
				sh.readNumber();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			
			if(sh.isGuessed()) {
				sh.getUser().incrementPoints(sh.getMode());
				System.out.println("Enhorabuena! Has acertado!\nTienes " + sh.getUser().getPoints() + " puntos.\n");
			}
			
			else {
				System.out.println("Lo siento, no has acertado :(. El número en el que estaba pensando era el " + sh.getOpponent().getNumber() + ".\n");
				sh.getUser().setPointsZero();
			}
		
			if (!sh.continueGame()) {
				sh.getReader().close();
				continueGuessing = false;
			}
		}
	}

}
