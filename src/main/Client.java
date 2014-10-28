package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Client {

	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choice = new String ();
		
		//main loop
		while (true){
			//login print
			initPrint();
			choice = br.readLine();
			choice.trim();
			switch (choice){
				case "0":	System.out.println("You Chose To Login");
							//login function
							login();
							break;
							
				case "1":	System.out.println("You Chose To Register");
							//Register function
							register();
							break;
							
				case "2":	System.out.println("You Chose To See Credits");
							creditPrint();
							break;
							
				default: 	System.out.println("Invalid Choice");
							break;
			}
		}
	}

	public static void initPrint(){
		System.out.println("-------------------------SWAGGITY SWAG BOARD-------------------------");
		System.out.println("0 - LOGIN");
		System.out.println("1 - REGISTER");
		System.out.println("2 - SEE CREDITS");
		System.out.println("Please Enter The Corresponding Number:");
	}
	
	public static void creditPrint(){
		System.out.println("-------------------------SWAGGITY SWAG SQUAD-------------------------");
		System.out.println("-------------------------------Carlito-------------------------------");
		System.out.println("-------------------------------Dmitry--------------------------------");
		System.out.println("-------------------------------Shaun---------------------------------");
		System.out.println("-------------------------------Henry---------------------------------");
		System.out.println("-------------------------------David---------------------------------");
		System.out.println("-------------------------------BenNi---------------------------------");
	}
	
	
	private static void register() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserDAO userDao = new UserLocalDAO();
		User user;
		String username;  
		String password1;
		String password2;
		
		while (true){
			System.out.println("Enter A Unique Username:");
			username = br.readLine();
			System.out.println("Enter Your Password: ");
			password1 = br.readLine();
			System.out.println("Confirm Your Password: ");
			password2 = br.readLine();
			
		    if (password1.equals(password2)){
		    	break;
		    }else{
		    	System.out.println("The Passwords You Typed Are Different, Please Try Again");
			}
		}
		//*************create UserObject here 
	}
	
	public static void login() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserDAO userDao = new UserLocalDAO();
		BoardDAO boardDao = new BoardLocalDAO();
		MessageDAO messageDao = new MessageLocalDAO();
		User user;
		ArrayList <Board> boards = new ArrayList <Board> ();
		String username;  
		String password;
		String input;
		int choice;
		
		
		while (true){
			//Stdout ask for input
			System.out.println("Enter Your Username: ");
			username = br.readLine();
			System.out.println("Enter Your Password: ");
			password = br.readLine();
			Board cboard;
			
			//authenticate the username and password
			Authenticator auth = new Authenticator();
			user = auth.Authenticate(username, password);
			
			if (user != null){
				while (true){
					Iterator <Board> i = boardDao.getAllBoards();
					
					//Use Iterator to make our own arraylist
					while (i.hasNext()){
						boards.add(i.next());
					}
					
					//print all boards avaliable
					int counter = 0;
					for (Board b : boards){
						System.out.println(Integer.toString(counter)+ " - " + b.getTopic());
						counter++;
					}
					
					System.out.println("Enter The Number Of The Board You Would Like To Join");
					System.out.println("Or Type Quit To Return To the Login Menu");
					input = br.readLine();
					input.trim();
					
					//Type Quit To Return To the Login Menu
					if (input.equals("quit")){
						break;
					}
					
					//exception might be needed here
					choice = Integer.parseInt(input);
					
					if (choice >= 0 && choice <= (boards.size()-1)){
						cboard = boards.get(choice);
						
						while (true){
							Iterator<Message> j = messageDao.getMessages(cboard);
							while (j.hasNext()){
								//****************NEED FUNCTION HERE TO GET MID? SO I CAN GET THE ACTUAL TEXT? SO I CAN PRINT
							}
							System.out.println("Enter Text Here");
							input = br.readLine();
							//*****************MAKE MSG OBJECT HERE
						}
						
					}else{
						System.out.println("Index Out Of Bound, Please Try Again");
					}
				}
				
				
			}else{
				System.out.println("Password/Username Is Invalid");
				System.out.println("YOU SHALL NOT PASS");
			}
		}
	}

}
