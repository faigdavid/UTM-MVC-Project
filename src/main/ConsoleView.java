package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		
		this.controller = new Controller(this);
		this.state = "initial";
		// main loop
		while (true) {
		
			// login print
			initPrint();
			input = br.readLine();
			input.trim();
			choice = inputInterpreter(input, state);
			switch (choice) {
			case "login":
				printString("You Chose To Login");
				username = getInput("Please enter your"
						+ "username", br);
			    password = getInput("Please input your password", br);
				controller.login(username, password);
				break;

			case "register":
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
				controller.post(input);
					
			case "private message":
				//the (very temporary) format will be /w username MESSAGE
				//this case will get the input into a proper form.
				//controller.messageUser();
			case "change board by name":
				//
				controller.changeBoardByName(input);
			
			case "change board by bid":
				//
				controller.changeBoardByBid(input);
			default:
				printString("Invalid Choice");
				break;
			}
		}
	}
	
	public static String inputInterpreter(String login, String state){

		return null;
}

	public static int initPrint() {
		
		
		return 1;
		
	}
	@Override
	public int printString(String string){
		
		System.out.println(string);
		return 1;
		
	}
	public void changeMenuState(String state){
		this.state = state;
		return;
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

