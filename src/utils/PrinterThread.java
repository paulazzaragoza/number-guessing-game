package utils;

public class PrinterThread extends Thread {
	
	private String message1; 
	private String message2;
	
	public PrinterThread(String m1, String m2) {
		message1 = m1; 
		message2 = m2;
	}
	
	
	public void run() {
		
		try {
			print();
		} catch (InterruptedException e) {
			System.err.println("La espera fue interrumpida");
			
		}
	}
	
	private void print() throws InterruptedException {
		System.out.print(message1);
		PrinterThread.sleep(400);
		
		for(int i = 0; i < 3; i++) {			
			System.out.print(".");
			PrinterThread.sleep(800);
		}
		
		
		
		System.out.println("\n" + message2);
		PrinterThread.sleep(800);
	}
}