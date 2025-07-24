package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Record {
	
	private static final String fileName = "records.txt";
	private LinkedHashMap<String, Integer> usersRecords = new LinkedHashMap<String, Integer>();
	private File recordFile = new File(fileName);
	
	public Map<String, Integer> getUsersRecords(){
		ordenarRecords();
		return Collections.unmodifiableMap(new LinkedHashMap<>(usersRecords));
	}
	
	public void leerRecords() {
		try {
			Scanner fileReader = new Scanner(recordFile);
			
			usersRecords = new LinkedHashMap<String, Integer>();
			while(fileReader.hasNext()) {
				String actualLine = fileReader.nextLine();
				
				String[] values = actualLine.split(" ");
				
				usersRecords.put(values[0], Integer.parseInt(values[1]));
			}
			
			fileReader.close();
			
		} catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + e.getMessage());
		}
	}
	
	public void ordenarRecords() {
		usersRecords = usersRecords.entrySet().stream()
			    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			    .collect(Collectors.toMap(
			        Map.Entry::getKey,
			        Map.Entry::getValue,
			        (e1, e2) -> e1,
			        LinkedHashMap::new
			    ));
	}
	
	public void updateUserRecord(String username, int record) {
		Integer recordActual = usersRecords.get(username);
		
		if(recordActual == null) {
			usersRecords.put(username, record);
		}
		else if(recordActual < record) {
			usersRecords.put(username, record);
		} 
	}
	
	public void escribirRecords() {
		try {
			FileWriter fWriter = new FileWriter(recordFile);
			
			for(Map.Entry<String, Integer> entry : usersRecords.entrySet()) {
				fWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
			}
			
			fWriter.close();
			
		} catch (IOException e) {
            System.out.println("Error escribiendo archivo: " + e.getMessage());
		}
	}
}
