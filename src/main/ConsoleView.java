package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleView implements ModelEventListener{
	private ViewEventListener controller;
	private String state;
	private String username;
	
	public void runView() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = new String();
		String choice = new String();
		String password;
		String password2;
		int toStrip;
		this.state = "logged out";
		printString("Welcome to Team6's Chat Boards!");
		// main loop
		while (true) {
			
			// login print
			printString("Please Input: ");
			if (state.equals("logged out"))
			{
				printString("Please enter l to login, or r to register");
			}
			else if (state.equals("logged in"))
			{
				printString("To join a board type j follwed by bid or name of the board");
			}
			else if (state.equals("in board"))
			{
				printString("Type j and the bid or name to enter a board, /r to refresh the board, and /p followed by message to post a message");
			}
			input = br.readLine();
			input.trim();
			choice = inputInterpreter(input, state);
			switch (choice) {
			case "login":
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

			case "credits": //carlito coded this
				printString("You Chose To See Credits");
				System.out.println("-------------------------------");
				System.out.println("Henry Ekelund");
				System.out.println("David Faig");
				System.out.println("Seung Hyun");
				System.out.println("Carlito Llena");
				System.out.println("Dmitry 'Vladimir Putin' Vasin");
				System.out.println("Ben");

				break;
				
			case "refresh": //Carlito coded this
				//make a request for boards or messages depending on state.
				if(this.state.equals("logged in")) {
					controller.requestBoards();				
				}
				else if(this.state.equals("in board")) {
					controller.requestBoardMessages();
				}
				
				break;
			
			case "post":
				controller.post(input);
				break;
				
			case "private message":
				//the (very temporary) format will be /w username MESSAGE
				//this case will get the input into a proper form.
				//controller.messageUser();
			case "join board by name":
				toStrip = input.indexOf(' ');
				controller.changeBoardByName(input.substring(toStrip).trim());
				break;
				
			case "join board by bid":
				toStrip = input.indexOf(' ');
				controller.changeBoardByBid(input.substring(toStrip).trim());
				break;
				
			case "create board":
				toStrip = input.indexOf(' ');
				controller.createBoard(input.substring(toStrip).trim());
				break;
				
			default:
				printString("Invalid Choice");
				break;
			}
		}
	}
	@Override
	public void recieveBoardMessages(Iterator<Message> messages) {
		//PRINT OUT ALL THE MESSAGES ON THE CURRENT BOARD.
			while (messages.hasNext()) {
				Message msg = messages.next();
				System.out.println(msg.formatMessage(username));
			}
		
	}
	@Override
	public void recieveBoards(Iterator<Board> boards) {
		//PRINT OUT ALL THE BOARDS.
		Board b;
		System.out.println("List of all boards:");
		while (boards.hasNext()){
			b = boards.next();
			System.out.println(b.getBid() + " : " + b.getName() );
		}
	}
	@Override
	public void recieveSubscribedBoards(Iterator<Board> boards) {
		//PRINT OUT ALL BOARDS.
		while(boards.hasNext()) {
			Board nextBoard = boards.next();
			System.out.println(nextBoard.getName());
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
		if(Pattern.matches("^credits$", input)){
			return "credits";
		}
		else if (state.equals("logged out")){
			if(Pattern.matches("^l(ogin)?$", input)){
				
				return "login";
			}
			if(Pattern.matches("^r(egister)?$", input)){
				return "register";
			}
			return "bad input";
		}
		else if(state.equals("logged in")){
			
			if(Pattern.matches("^/?j(oinboard)?[ \t]+[0-9]+$", input)){
				System.out.println("state=logged in");
				return "join board by bid";
			}
			if(Pattern.matches("^/?j(oinboard)?[ \t]+.+$", input)){
				System.out.println("state=logged in");
				return "join board by name";
			}
			return "bad input";
		}
		else if(state.equals("in board")){
			if(Pattern.matches("^/j(oinboard)?[ \t]+[0-9]+$", input)){
				return "join board by bid";
			}
			if(Pattern.matches("^/j(oinboard)?[ \t]+.*$", input)){
				return "join board by name";
			}
			if(Pattern.matches("^/r(efresh)?.*$", input)){
				return "refresh";
			}
			if(Pattern.matches("^/w[ \t]+.*$", input)){
				return "private message";
			}
			if(Pattern.matches("^/p[ \t]+.*$", input)){
				return "post";
			}
			return "bad input";
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


