package application;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class LoginPageTest {

  @Test
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