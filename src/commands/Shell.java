package commands;

import java.util.Scanner;

import players.Opponent;
import players.User;
import utils.PrinterThread;
import utils.Difficulty;

public class Shell {
	
	//atributes
	private Scanner reader;
	private int userNumber;
	private Difficulty mode;
	private int tries;
	private boolean guessed; 
	private Opponent op;
	private User usr;
	
	//constructor
	public Shell() {
		reader = new Scanner(System.in);
		guessed = false;
		op = new Opponent();
	}
	
	//getters
	public int getUserNumber() {
		return userNumber;
	}
	
	public Difficulty getMode() {
		return mode;
	}
	
	public int getTries() {
		return tries;
	}
	
	public boolean isGuessed() {
		return guessed;
	}
	
	public Opponent getOpponent() {
		return op;
	}
	
	public Scanner getReader() {
		return reader;
	}
	
	public User getUser() {
		return usr;
	}
	
	//setters
	public void setTries(int tries){
		this.tries = tries;
	}
	
	
	//methods
	public void readNumber() throws InterruptedException{
		selectDifficulty();
		
		PrinterThread printer = new PrinterThread("\nEstoy pensando un número", "Hecho! Intenta adivinarlo.");
		printer.start();
		
		printer.join();
		
		op.generateRandomNumber(this);
		while(tries != 0){
			
			System.out.print("\nInserta el número que piensas: ");
			userNumber = reader.nextInt();
			
			if(userNumber != op.getNumber()) {
				tries--;
				
				if(!mode.equals(Difficulty.HARD)) {
					if(userNumber < op.getNumber()) System.out.println("El número en el que estoy pensando es más grande.");
					
					else System.out.println("El número en el que estoy pensando es más pequeño.");
				}
			} else {
				guessed = true;
				tries = 0;
			}
			
		}
	}
	
	public void selectDifficulty() {
		boolean difficultySelected = false;
		
		int modeIndx = 0;
		while(!difficultySelected) {
			System.out.print(
					"\n1-Easy [1-10] 5 intentos.\n" +
					"2-Medium [1-20] 3 intentos.\n" +
					"3-Hard [1-50] 1 intento.\n" +
					"Selecciona la dificultad: ");
			
			modeIndx= reader.nextInt();
			reader.nextLine();
			
			Difficulty diffChoosen = Difficulty.values()[modeIndx - 1];
			if(modeIndx > -1 && modeIndx < 4) {
				System.out.print("La dificultad elegida es " + diffChoosen + ". ¿Es correcto?(S/N): ");
				String accepted = reader.nextLine();
				
				if(accepted.equals("S")) difficultySelected = true;
			}
			
			else System.err.println("El número no está comprendido entre el [1, 3].");
			
		}
		
		if(difficultySelected) mode = Difficulty.values()[modeIndx - 1];
	}
	
	
	public boolean continueGame() {
		System.out.print("¿Quiere volver a jugar?(S/N): ");
		reader.nextLine();
		String accepted = reader.nextLine();
		
		return accepted.equals("S");
	}
	
	public void loginUser() {
		boolean userLogged = false; 
		
		while(!userLogged) {
			System.out.print("Elige un nombre de usuario entre [3-10] caracteres: ");
			String username = reader.nextLine();
			
			
			if (username.length() < 3) System.out.println("El nombre es demasiado corto.\n");
			
			else if(username.length() > 10) System.out.println("El nombre es demasiado largo.\n");
			
			else {
				System.out.print("El nombre introducido es \"" + username + "\". ¿Es correcto?(S/N): ");
				String accepted = reader.nextLine();
				
				if(accepted.equals("S")) {
					usr = new User(username);
					userLogged = true;
				}
			}
		}
	}
	

}
