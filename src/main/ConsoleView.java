package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleView implements ModelEventListener{
	private ViewEventListener controller;
	private String state;
		
	public void runConsoleView() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = new String();
		String choice = new String();
		String username;
		String password;
		String password2;
		
		this.state = "logged out";
		printString("Welcome to Team6's Chat Boards!");
		// main loop
		while (true) {
			
			// login print
			printString("Please Input: ");
			input = br.readLine();
			input.trim();
			choice = inputInterpreter(input, state);
			switch (choice) {
			case "login":
				if (state != "logged out"){
					printString("You're already logged in.");
					break;
				}
				username = getInput("Please enter your"
						+ "username", br);
			    password = getInput("Please input your password", br);
				controller.login(username, password);
				break;

			case "register":
				if (state != "logged out"){
					printString("You're already logged in.");
					break;
				}
				printString("You Chose To Register");
				// Register function
				// Gets the 3 needed inputs then put them in
				//if register fails it will make the view print something
				//informing the user
				username = getInput("Please enter a unique "
						+ "Username", br);
				password = getInput("Please input a password", br);
				password2 = getInput("Please repeat the "+ 
				"same password", br); 
				
				controller.register(username, password, password2);
				break;

			case "credits":
				printString("You Chose To See Credits");
				//make this do shit
				break;
				
			case "refresh":
				//make this update the current screen (ie get new messages)
				break;
			
			case "post":
				if (state != "in board"){
					printString("You're already logged in.");
					break;
				}
				controller.post(input);
				break;
				
			case "private message":
				//the (very temporary) format will be /w username MESSAGE
				//this case will get the input into a proper form.
				//controller.messageUser();
			case "change board by name":
				if (state == "logged out"){
					printString("You are not logged in.");
					break;
				}
				controller.changeBoardByName(input);
				break;
				
			case "change board by bid":
				if (state == "logged out"){
					printString("You are not logged in.");
					break;
				}
				controller.changeBoardByBid(input);
				break;
				
			default:
				printString("Invalid Choice");
				break;
			}
		}
	}

	@Override
	public int printString(String string){
		
		System.out.println(string);
		return 1;
		
	}
	@Override
	public void addViewEventListener(ViewEventListener listener) {
		this.controller = listener;
		
	}

	@Override
	public void removeViewEventListener(ViewEventListener listener) {
		this.controller = null;
		
	}

	@Override
	public void changeStateLoggedIn() {
		state = "logged in";
		
	}

	@Override
	public void changeStateLoggedOut() {
		state = "logged out";
		
	}

	@Override
	public void changeStateInBoard() {
		state = "in board";
		
	}

	@Override
	public void changeStateNoBoard() {
		state = "logged in";
		
	}
	
	/*--------------------------Private Methods--------------------------*/

	private static String inputInterpreter(String input, String state){
		if (state.equals("logged out")){
			
			if(input.equals("login")){
				return "login";
			}
			
			if(input.equals("register")){
				return "register";
			}
			return "bad input";
		}
		else if(state.equals("logged in") || state.equals("in board")){
			
			return "post";
		}
		return "bad input";
	}
	
	private String getInput(String message, BufferedReader br){
		System.out.println(message);
		String input;
		try {
			input = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			input = null;
			System.out.println("FAILED TO GET INPUT");
		}
		input.trim();
		return input;
	}
}

