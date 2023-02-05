package application;

import java.io.File;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//This class extends the JavaFX Application class 
//and represents the Login Page of the application
public class LoginPage extends Application {

  //Define constants for window width, height, and title
  private static final int WIDTH = 900;
  private static final int HEIGHT = 600;
  private static final String TITLE = "Login Page";
  
  //Keeps track of the number of failed login attempts
  int failedAttempts = 0;

  //A HashMap to store the user data (username and password)
  private static HashMap<String, String> userData;

  //Main method to launch the application
  public static void main(String[] args) {
    launch(args);
  }

  //The start method is the entry point for the JavaFX Application
  @Override
  public void start(Stage primaryStage) {
    //Create a grid layout for the Login Page
    GridPane grid = new GridPane();
    //Align the grid layout to the center of the window
    grid.setAlignment(Pos.CENTER);
    //Set horizontal and vertical gaps between the grid cells
    grid.setHgap(10);
    grid.setVgap(10);
    //Set the padding around the grid layout
    grid.setPadding(new Insets(25, 25, 25, 25));

    //Create a label for the username field
    Label userName = new Label("User Name:");
    //Add the label to the grid layout
    grid.add(userName, 1, 1);

    //Create a text field for the username
    TextField userTextField = new TextField();
    //Add the text field to the grid layout
    grid.add(userTextField, 2, 1);

    //Create a label for the password field
    Label pw = new Label("Password:");
    //Add the label to the grid layout
    grid.add(pw, 1, 2);

    //Create a password field for the password
    PasswordField pwBox = new PasswordField();
    //Add the password field to the grid layout
    grid.add(pwBox, 2, 2);

    
  //Create a "Sign in" button and set an action for when it is clicked
    Button btnLogin = new Button("Sign in");
    btnLogin.setOnAction(event -> {
      //Get the entered username and password from the text fields
      String username = userTextField.getText();
      String password = pwBox.getText();

      //Load user data from some storage (the details of this storage are not specified in the code)
      userData = UserData.loadData();

      //Check if the entered username and password match a stored username and password
      if (userData.containsKey(username) && userData.get(username).equals(password)) {
        System.out.println("Sign in successful!..");
        //Create a WelcomePage with the entered username as a parameter
        WelcomePage welcomePage = new WelcomePage(username);
        //Close the primary stage (the main window)
        primaryStage.close();
      } else {
        System.out.println("Sign in failed!..");
        //Increment the failedAttempts counter
        failedAttempts++;
        //If the user has failed to sign in twice
        if (failedAttempts==2) {
          //Create an error alert to warn the user that they have one more chance
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Warning!");
          alert.setHeaderText("Last chance..");
          alert.setContentText("Becareful the app will close..");
          alert.showAndWait();
        //If the user has failed to sign in three or more times    
        }else if(failedAttempts >= 3) {
          //Create an error alert to inform the user that they have failed too many times and the app will close
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error!");
          alert.setHeaderText("Too many failed sign in attempts..");
          alert.setContentText("Closing the app..");
          alert.showAndWait();
          //Close the application
          System.exit(0);
        }
      }
    });

    //Add the "Sign in" button to the grid
    grid.add(btnLogin, 2, 3);

    //Create a "Register" button and set an action for when it is clicked
    Button btnRegister = new Button("Register");
    btnRegister.setOnAction(event -> {
      //Get the entered username and password from the text fields
      String username = userTextField.getText();
      String password = pwBox.getText();
      //Add the entered username and password to the user data map
      userData.put(username, password);
      //Save the updated user data map to some storage (the details of this storage are not specified in the code)
      UserData.saveData(userData);
    });

    //Add the "Register" button to the grid
    grid.add(btnRegister, 3, 3);

    //Create a "Quit" button and set an action for when it is clicked
    Button btnQuit = new Button("Quit");
    btnQuit.setOnAction(event -> System.exit(0));

    //Add the "Quit" button to the grid
    grid.add(btnQuit, 4, 3);
    
    //Create an ImageView object and set it to display the image located at "user.png" file
    ImageView imageView = new ImageView(new Image(new File("user.png").toURI().toString()));

    //Set the width of the image to half of the WIDTH constant value
    imageView.setFitWidth(WIDTH / 1.5);

    //Set the height of the image to the value of the HEIGHT constant
    imageView.setFitHeight(HEIGHT);

    //Add the imageView object to the grid at position (0,0) with a width of 1 and height of 5
    grid.add(imageView, 0, 0, 1, 5);

    Scene scene = new Scene(grid, WIDTH, HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.setTitle(TITLE);
    primaryStage.show();
  }
}