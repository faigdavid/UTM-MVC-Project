package GUIViews;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorGUI {
	/**
	 * Added error pop up messages to replace the error messages that prints to the console.
	 * I did this since we're doing a GUI.
	 * @author Carlito Llena
	 */
	public static void loginError() { //produces a pop up if we input the wrong credentials
		String message = "Wrong username or password, please try again!";
	    JOptionPane.showMessageDialog(new JFrame(), message, "Authentication Error",
	            JOptionPane.ERROR_MESSAGE);
	}
	public static void registerError() { //if for some reason a user failed to register
		String message = "Failed to register, please try again!";
	    JOptionPane.showMessageDialog(new JFrame(), message, "Registration Error",
	            JOptionPane.ERROR_MESSAGE);
	}
	public static void boardError() { //if we failed to join a board
		String message = "Failed to join a board, please try again!";
	    JOptionPane.showMessageDialog(new JFrame(), message, "Failed to join a board",
	            JOptionPane.ERROR_MESSAGE);	
	}
	public static void messageError() { //failed to post messages
		String message = "Failed to post message, please try again!";
	    JOptionPane.showMessageDialog(new JFrame(), message, "Message not posted",
	            JOptionPane.ERROR_MESSAGE);			
	}
	public static void dataError() { //failed to retrieve data
		String message = "Failed to get contents, please try again!";
	    JOptionPane.showMessageDialog(new JFrame(), message, "Contents not retrieved",
	            JOptionPane.ERROR_MESSAGE);		
	}
	
	public static void showError(String name, String message){//General purpose error pane.
		 JOptionPane.showMessageDialog(new JFrame(), message, name,
		            JOptionPane.ERROR_MESSAGE);	
	}
}
