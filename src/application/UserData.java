package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
		      if (!userData.containsKey(values[0])) {
		        userData.put(values[0], values[1]);
		      }
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
		    // Load the existing data from the file
		    HashMap<String, String> existingData = loadData();

		    // Loop through each key-value pair in the input HashMap
		    for (Map.Entry<String, String> entry : userData.entrySet()) {
		      // Get the key and value of the current pair
		      String key = entry.getKey();
		      String value = entry.getValue();
		      // Check if the key doesn't exist in the existing data
		      if (!existingData.containsKey(key)) {
		        // If it doesn't, add it to the existing data
		        existingData.put(key, value);
		      }
		    }

		    // Create a File object for the file name
		    File file = new File(FILE_NAME);
		    // Create a BufferedWriter object to write to the file, with the second argument set to "false" to overwrite the file
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file, false)); 
		    // Loop through each key-value pair in the updated HashMap
		    for (Map.Entry<String, String> entry : existingData.entrySet()) {
		      // Format the key-value pair as a string and add a newline character at the end
		      String line = entry.getKey() + "," + entry.getValue() + "\n";
		      // Write the line to the file
		      writer.write(line);
		    }
		    // Close the writer
		    writer.close();
		  } catch (IOException e) {
		    // In case of an error, print the stack trace
		    e.printStackTrace();
		  }
		}

	public static HashMap<String, String> readData() {
		  HashMap<String, String> userData = new HashMap<>();
		  // Code to read data from file and populate the userData map goes here
		  return userData;
		}
} 