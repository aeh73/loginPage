package application;

import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WelcomePage {

	  private static final int WIDTH = 600;
	  private static final int HEIGHT = 400;
	  //private static final String TITLE = "Welcome Page";

	  public WelcomePage(String username) {
	    BorderPane root = new BorderPane();
	    root.setPadding(new Insets(20, 20, 20, 20));

	    Label welcomeLabel = new Label("Welcome " + username);
	    root.setTop(welcomeLabel);

	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(20, 20, 20, 20));

	    Label nameLabel = new Label("Name:");
	    TextField nameField = new TextField();
	    Label categoryLabel = new Label("Category:");
	    ComboBox<String> categoryComboBox = new ComboBox<>();
	    categoryComboBox.getItems().addAll("Sticker", "Case", "Capsule", "Weapon", "Misc");
	    Label priceLabel = new Label("Price:");
	    TextField priceField = new TextField();
	    Label quantityLabel = new Label("Quantity:");
	    TextField quantityField = new TextField();
	      
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
	        displayPane.setPadding(new Insets(20, 20, 20, 20));
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
	        Label contentLabel = new Label(content);
	        grid.add(contentLabel, 2, 5);
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
	    
	    
	    grid.add(nameLabel, 0, 0);
	    grid.add(nameField, 1, 0);
	    grid.add(categoryLabel, 0, 1);
	    grid.add(categoryComboBox, 1, 1);
	    grid.add(priceLabel, 0, 2);
	    grid.add(priceField, 1, 2);
	    grid.add(quantityLabel, 0, 3);
	    grid.add(quantityField, 1, 3);
	    grid.add(addButton, 1, 4);
	    grid.add(loadButton, 2, 4);
	    grid.add(saveButton, 3, 4);

	    root.setCenter(grid);
	    

	    
	    Stage stage = new Stage();
	    stage.setTitle("Welcome " + username + "!!");
	    stage.setScene(new Scene(root, WIDTH, HEIGHT));
	    stage.show();
	  }
	}
//	    BorderPane displayPane = new BorderPane();
//	    displayPane.setPadding(new Insets(20, 20, 20, 20));
//	    Label displayLabel = new Label();
//	    displayPane.setCenter(displayLabel);
//	    root.setBottom(displayPane);
//	    
//	    nameField.setOnAction(event -> {
//	      String name = nameField.getText();
//	      String category = categoryComboBox.getValue();
//	      String price = priceField.getText();
//	      String quantity = quantityField.getText();
//	      displayLabel.setText("Name: " + name + "\nCategory: " + category + "\nPrice: " + price + "\nQuantity: " + quantity);
//	    });