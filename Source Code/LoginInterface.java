package Interface;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


import Login.LoginController;
import java.io.Console;

/**
 * LoginInterface is a boundary class which prompts the user to enter his login 
 * informations and passes control to the control class LoginController. <p>
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class LoginInterface extends javax.swing.JPanel{
	
	private String username, password;
	private String choice;
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Displays the login menu and prompts user to choose if he wants to login as student or admin.
	 */
	public void loginAs() //Enter from mySTARSApp
	{

		while(true) {
			System.out.println("WELCOME TO NTU STARS");
			System.out.println("****CHOOSE DOMAIN****");
			System.out.println("1. Student \n2. Admin \n3. Exit");
			System.out.printf("Enter choice: ");
			choice = sc.next();
			if(choice.equals("1") || choice.equals("2")) //Choosing Admin or Student
			{
				int choice = Integer.parseInt(this.choice);
				adminOrStudent(choice); //Enter AdminOrStudent 
			}
			else if(choice.equals("3")){
				System.out.println("Exiting System...");
				System.exit(0);
            }
			else {
				System.out.println("Please Enter a Valid Number!\n");
			}
		}
	}
	
	/**
	 * Prompts user to enter his username and password, passes control to LoginController for
	 * password validation.
	 * 
	 * @param choice login choice of user (Student/Admin)
	 */
	private void adminOrStudent(int choice)
	{	
		System.out.println("*****LOGIN*****");

		System.out.printf("Please enter Username: ");
		username = sc.next();
      
		//password = consoleReadPass();
      
		//System.out.printf("Please Enter Password: ");
		//password = sc.next();
			
		password = masking();
		
		LoginController loginController = new LoginController(); //Enter loginController
		loginController.validateLogin(username, password, choice);
	}
	
	private String consoleReadPass()
	{
		 Console console = System.console();
	      if(console == null){
	         System.err.println("No console found");
	         return "null";
	         //System.exit(1);
	      }
	      
	      char[] pass = console.readPassword("Please Enter password:");
	      return String.valueOf(pass);
	}
	
	/**
	 * Masks password during login
	 * 
	 * @return a key. If key = null, the login check fails, otherwise the login check passes.
	 */
	public static String masking() {
		final String password, message = "Enter password";
		if( System.console() == null ) 
		{ // inside IDE like Eclipse or NetBeans
		  final JPasswordField pf = new JPasswordField(); 
		  return password = JOptionPane.showConfirmDialog( null, pf, message,
		    JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
		      new String( pf.getPassword() ) : "";
		}
		else 
			return password = new String( System.console().readPassword( "%s> ", message ) );
		
	}
}
