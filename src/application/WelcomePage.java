package application;

import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WelcomePage {

	  private static final int WIDTH = 900;
	  private static final int HEIGHT = 600;
	  //private static final String TITLE = "Welcome Page";

	  public WelcomePage(String username) {
		/*
		 * Here a new BorderPane is created and its padding is set to 20 pixels on all
		 * sides. The BorderPane is used as the root layout for the application.
		 */
	    BorderPane root = new BorderPane();
	    root.setPadding(new Insets(20, 20, 20, 20));

		/*
		 * A Label with the text "Welcome " concatenated with the username argument is
		 * created, and set as the top component in the BorderPane.
		 */
	    String capitalizedUsername = username.substring(0,1).toUpperCase() + username.substring(1);
	    Label welcomeLabel = new Label("Welcome " + capitalizedUsername);
	    root.setTop(welcomeLabel);
	    
		
		 /* A new GridPane is created and its horizontal and vertical gaps are set to 10
		  pixels each. The padding is set to 20 pixels on all sides.*/
		 
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));
	    
		/*
		 * These lines create new Label and TextField components for the name, category,
		 * price, and quantity fields. The ComboBox for the  category field is also
		 * created and pre-populated with the options "Sticker", "Case", "Capsule",
		 * "Weapon", and "Misc"
		 */
	    Label nameLabel = new Label("Name:");
	    grid.add(nameLabel, 0, 0);
	    TextField nameField = new TextField();
	    grid.add(nameField, 1, 0);
	    Label categoryLabel = new Label("Category:");
	    grid.add(categoryLabel, 0, 1);
	    ComboBox<String> categoryComboBox = new ComboBox<>();
	    categoryComboBox.getItems().addAll("Sticker", "Case", "Capsule", "Weapon", "Misc");
	    grid.add(categoryComboBox, 1, 1);
	    Label priceLabel = new Label("Price:");
	    grid.add(priceLabel, 0, 2);
	    TextField priceField = new TextField();
	    grid.add(priceField, 1, 2);
	    Label quantityLabel = new Label("Quantity:");
	    grid.add(quantityLabel, 0, 3);
	    TextField quantityField = new TextField();  
	    grid.add(quantityField, 1, 3);
	    
	    Button addButton = new Button("Add");
	    addButton.setOnAction(e -> {
	      String name = nameField.getText();
		  String category = categoryComboBox.getValue();
		  String price = priceField.getText();
		  String quantity = quantityField.getText();
	      if (name.isEmpty() || category == null || price.isEmpty() || quantity.isEmpty()) {
	        // show an error message if any of the fields are empty
	        Label errorLabel = new Label("Error: All fields must be filled.");
	        grid.add(errorLabel, 2, 4);
	      } else {
	        // display the inputs in a separate pane
	        VBox displayPane = new VBox();
	        displayPane.setSpacing(10);
	        displayPane.setPadding(new Insets(30, 30, 30, 30));
	        displayPane.getChildren().add(new Label("Name: " + name));
	        displayPane.getChildren().add(new Label("Category: " + category));
	        displayPane.getChildren().add(new Label("Price: " + price));
	        displayPane.getChildren().add(new Label("Quantity: " + quantity));
	        root.setRight(displayPane);
	      }
	    }); 
	    
	    Button loadButton = new Button("Load");
	    loadButton.setOnAction(e -> {
	      try {
	        File file = new File(username+".txt");
	        Scanner reader = new Scanner(file);
	        String content = "";
	        while (reader.hasNextLine()) {
	          content += reader.nextLine() + "\n";
	        }
	        TextArea contentArea = new TextArea(content);
	        ScrollPane contentPane = new ScrollPane(contentArea);
	        grid.add(contentPane, 2, 7);
	      } catch (FileNotFoundException ex) {
	        System.out.println("Error: Could not read the file.");
	      }
	    });
	    
	    Button saveButton = new Button("Save");
	    saveButton.setOnAction(e -> {
	    String name = nameField.getText();
		String category = categoryComboBox.getValue();
		String price = priceField.getText();
		String quantity = quantityField.getText();
	    	try {
	    	      File file = new File(username+".txt");
	    	      //We pass a second argument true to the FileOutputStream constructor, which indicates that we want to append to the file if it 				   		          exists, and create it if it does not
	    	      PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
	    	      writer.println("Name: " + name + "\nCategory: " + category + "\nPrice: " + price + "\nQuantity: " + quantity + "\n");
	    	      writer.close();
	    	    } catch (FileNotFoundException ex) {
	    	      System.out.println("Error: Could not save the file.");
	    	    }
	    });
	    
	    grid.add(addButton, 1, 4);
	    grid.add(loadButton, 1, 14);
	    grid.add(saveButton, 2, 14);

	    root.setCenter(grid);
 
	    Stage stage = new Stage();
	    stage.setTitle("Welcome " + capitalizedUsername + "!!");
	    stage.setScene(new Scene(root, WIDTH, HEIGHT));
	    stage.show();
	  }
	}
