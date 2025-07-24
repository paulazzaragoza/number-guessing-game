package application;

import java.util.Iterator;
import java.util.Map;

import commands.Shell;
import utils.Record;

public class Guesser {
	
	public static void main(String[] args) {
		Shell sh = new Shell();
		Record rd = new Record();
		boolean continueGuessing = true;
		
		sh.loginUser();
		rd.leerRecords();
		
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
				rd.updateUserRecord(sh.getUser().getUsername(), sh.getUser().getPoints());
				System.out.println("Lo siento, no has acertado :(. El número en el que estaba pensando era el " + sh.getOpponent().getNumber() + ".\n");
				sh.getUser().setPointsZero();
			}
		
			if (!sh.continueGame()) {
				rd.updateUserRecord(sh.getUser().getUsername(), sh.getUser().getPoints());
				sh.getReader().close();
				continueGuessing = false;
			}
		}
		
		Map<String, Integer> records = rd.getUsersRecords();
		System.out.println(records.toString());
		int myRecord = records.get(sh.getUser().getUsername());
		Iterator<Map.Entry<String, Integer>> iterator = records.entrySet().iterator();
		
		System.out.println("Tu récord es de " + myRecord + " puntos.\n");
		
		System.out.println("En el TOP 3 están:");
		
		int counter = 1; 
		while(counter < 4) {
			
			if(iterator.hasNext()) {
				Map.Entry<String, Integer> entry = iterator.next();
				System.out.println("Top " + counter + ":\n\t" + entry.getKey() +
						" has " + entry.getValue() + " points.");
			}
				counter++;
		}

		rd.escribirRecords();
	}

}
