package application;

import commands.Shell;

public class Guesser {
	
	public static void main(String[] args) {
		Shell sh = new Shell();
		boolean continueGuessing = true;
		
		while(continueGuessing) {
			try {
				sh.readNumber();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			
			if(sh.isGuessed()) System.out.println("Enhorabuena! Has acertado!\n");
			
			else System.out.println("Lo siento, no has acertado :(. El número en el que estaba pensando era el " + sh.getOpponent().getNumber() + ".\n");
		
			if (!sh.continueGame()) {
				sh.getReader().close();
				continueGuessing = false;
			}
		}
	}

}
