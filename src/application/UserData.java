package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserData {

	private static final String FILE_NAME = "userdata.txt"; // file to store user data

	public static HashMap<String, String> loadData() {
		  HashMap<String, String> userData = new HashMap<>();
		  try {
		    BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
		    String line;
		    while ((line = reader.readLine()) != null) {
		      String[] values = line.split(",");
		      userData.put(values[0], values[1]);
		    }
		    reader.close();
		    return userData;
		  } catch (IOException e) {
		    e.printStackTrace();
		    return userData;
		  }
		}
	
	public static void saveData(HashMap<String, String> userData) {
	    try {
	      BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
	      for (Map.Entry<String, String> entry : userData.entrySet()) {
	        String line = entry.getKey() + "," + entry.getValue() + "\n";
	        writer.write(line);
	      }
	      writer.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}

}