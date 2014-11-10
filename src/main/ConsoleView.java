package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = new String();
		String choice = new String();
		String state = "initial";
		Controller controller = new Controller();
		
		// main loop
		while (true) {
			
			// login print
			initPrint();
			input = br.readLine();
			input.trim();
			choice = inputInterpreter(input, state);
			switch (choice) {
			case "login":
				System.out.println("You Chose To Login");
				// login function
				controller.login();
				break;

			case "register":
				System.out.println("You Chose To Register");
				// Register function
				//change the 3 states to a way to get input, does not work atm
				controller.register(state, state, state);
				break;

			case "credits":
				System.out.println("You Chose To See Credits");
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
				controller.messageUser();
			case "change board by name":
				//
				controller.changeBoardByName(input);
			
			case "change board by bid":
				//
				controller.changeBoardByBid(input);
			default:
				System.out.println("Invalid Choice");
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
	
	public static int printString(String string){
		
		System.out.println(string);
		return 1;
		
	}
}

