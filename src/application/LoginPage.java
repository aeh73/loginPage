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

public class LoginPage extends Application {

  private static final int WIDTH = 900;
  private static final int HEIGHT = 600;
  private static final String TITLE = "Login Page";
  int failedAttempts = 0;

  private static HashMap<String, String> userData;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    Label userName = new Label("User Name:");
    grid.add(userName, 1, 1);

    TextField userTextField = new TextField();
    grid.add(userTextField, 2, 1);

    Label pw = new Label("Password:");
    grid.add(pw, 1, 2);

    PasswordField pwBox = new PasswordField();
    grid.add(pwBox, 2, 2);

    
    Button btnLogin = new Button("Sign in");
    btnLogin.setOnAction(event -> {
      String username = userTextField.getText();
      String password = pwBox.getText();
      userData = UserData.loadData();
      if (userData.containsKey(username) && userData.get(username).equals(password)) {
        System.out.println("Sign in successful!..");
        WelcomePage welcomePage = new WelcomePage(username);
        //welcomePage.WelcomePage(username);
        primaryStage.close();
      } else {
        System.out.println("Sign in failed!..");
        failedAttempts++;
        if (failedAttempts==2) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Warning!");
         alert.setHeaderText("Last chance..");
         alert.setContentText("Becareful the app will close..");
         alert.showAndWait();
        	
        }else if(failedAttempts >= 3) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error!");
          alert.setHeaderText("Too many failed sign in attempts..");
          alert.setContentText("Closing the app..");
          alert.showAndWait();
       	  System.exit(0);
          }
      }
        
    });
   
    grid.add(btnLogin, 2, 3);
    
    Button btnRegister = new Button("Register");
    btnRegister.setOnAction(event -> {
      String username = userTextField.getText();
      String password = pwBox.getText();
      userData.put(username, password);
      UserData.saveData(userData);
    });
    grid.add(btnRegister, 3, 3);
    
    Button btnQuit = new Button("Quit");
    btnQuit.setOnAction(event -> System.exit(0));
    grid.add(btnQuit, 4, 3);
    
    ImageView imageView = new ImageView(new Image(new File("user.png").toURI().toString()));
    imageView.setFitWidth(WIDTH / 2);
    imageView.setFitHeight(HEIGHT);
    grid.add(imageView, 0, 0, 1, 5);

    Scene scene = new Scene(grid, WIDTH, HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.setTitle(TITLE);
    primaryStage.show();
  }
}