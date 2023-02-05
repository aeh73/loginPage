package application;

import org.junit.Test;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class LoginPageTest {

	
  @Test
  //Tests correct username + password
  public void testSignInButton() {
    //Create an instance of the LoginPage class
    LoginPage loginPage = new LoginPage();
    //Create a HashMap to store the user data
    HashMap<String, String> userData = new HashMap<>();
    //Add some test data to the HashMap
    userData.put("testUser", "testPassword");
    //Set the userData member of the LoginPage class to the test data
    LoginPage.userData = userData;
    //Get the entered username and password from the text fields
    String username = "testUser";
    String password = "testPassword";
    //Check if the sign in button returns true
    assertTrue(loginPage.signInButton(username, password));
  }

  @Test
  //Tests wrong username 
  public void testSignInButtonWrongUsername() {
    //Create an instance of the LoginPage class
    LoginPage loginPage = new LoginPage();
    //Create a HashMap to store the user data
    HashMap<String, String> userData = new HashMap<>();
    //Add some test data to the HashMap
    userData.put("testUser", "testPassword");
    //Set the userData member of the LoginPage class to the test data
    LoginPage.userData = userData;
    //Get the entered username and password from the text fields
    String username = "wrongUser";
    String password = "testPassword";
    //Check if the sign in button returns false
    assertFalse(loginPage.signInButton(username, password));
  }

  @Test
  //Tests wrong password
  public void testSignInButtonWrongPassword() {
    //Create an instance of the LoginPage class
    LoginPage loginPage = new LoginPage();
    //Create a HashMap to store the user data
    HashMap<String, String> userData = new HashMap<>();
    //Add some test data to the HashMap
    userData.put("testUser", "testPassword");
    //Set the userData member of the LoginPage class to the test data
    LoginPage.userData = userData;
    //Get the entered username and password from the text fields
    String username = "testUser";
    String password = "wrongPassword";
    //Check if the sign in button returns false
    assertFalse(loginPage.signInButton(username, password));
  }
  
	
}