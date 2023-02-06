package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserData {

	// A constant string representing the name of the file to store user data
	private static final String FILE_NAME = "userdata.txt"; 

	// A method to load user data from the file into a HashMap
	public static HashMap<String, String> loadData() {
		  // Create a new HashMap to store the user data
		  HashMap<String, String> userData = new HashMap<>();
		  try {
		    // Create a BufferedReader object to read from the file
		    BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
		    // Read each line from the file
		    String line;
		    while ((line = reader.readLine()) != null) {
		      // Split the line by comma and store the key-value pair in the HashMap
		      String[] values = line.split(",");
		      userData.put(values[0], values[1]);
		    }
		    // Close the reader and return the HashMap
		    reader.close();
		    return userData;
		  } catch (IOException e) {
		    // In case of an error, print the stack trace and return the empty HashMap
		    e.printStackTrace();
		    return userData;
		  }
		}
	
	// A method to save user data from a HashMap to the file
	public static void saveData(HashMap<String, String> userData) {
		  try {
		    File file = new File(FILE_NAME);
		    // Create a BufferedWriter object to write to the file, set the second argument to "true" to enable append mode
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); 
		    // Write each key-value pair from the HashMap to a separate line in the file
		    for (Map.Entry<String, String> entry : userData.entrySet()) {
		      String line = entry.getKey() + "," + entry.getValue() + "\n";
		      writer.write(line);
		    }
		    // Close the writer
		    writer.close();
		  } catch (IOException e) {
		    // In case of an error, print the stack trace
		    e.printStackTrace();
		  }
		}

} 