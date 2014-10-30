package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Client {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choice = new String();

		// main loop
		while (true) {
			// login print
			initPrint();
			choice = br.readLine();
			choice.trim();
			switch (choice) {
			case "0":
				System.out.println("You Chose To Login");
				// login function
				login();
				break;

			case "1":
				System.out.println("You Chose To Register");
				// Register function
				register();
				break;

			case "2":
				System.out.println("You Chose To See Credits");
				creditPrint();
				break;

			default:
				System.out.println("Invalid Choice");
				break;
			}
		}
	}

	public static void initPrint() {
		System.out
				.println("-------------------------SWAGGITY SWAG BOARD-------------------------");
		System.out.println("0 - LOGIN");
		System.out.println("1 - REGISTER");
		System.out.println("2 - SEE CREDITS");
		System.out.println("Please Enter The Corresponding Number:");
	}

	public static void creditPrint() {
		System.out
				.println("-------------------------SWAGGITY SWAG SQUAD-------------------------");
		System.out
				.println("-------------------------------Carlito-------------------------------");
		System.out
				.println("-------------------------------Dmitry--------------------------------");
		System.out
				.println("-------------------------------Shaun---------------------------------");
		System.out
				.println("-------------------------------Henry---------------------------------");
		System.out
				.println("-------------------------------David---------------------------------");
		System.out
				.println("-------------------------------BenNi---------------------------------");
	}

	private static void register() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserDAO userDao = new UserLocalDAO();
		String username;
		String password1;
		String password2;

		while (true) {
			System.out.println("Enter A Unique Username:");
			username = br.readLine();
			username.trim();
			System.out.println("Enter Your Password: ");
			password1 = br.readLine();
			System.out.println("Confirm Your Password: ");
			password2 = br.readLine();

			if (password1.equals(password2)) {
				if (userDao.createUser(username, password1) != null) {
					// create success
					break;
				} else {
					System.out.println("Failed to create new user.");
				}
			} else {
				System.out
						.println("The Passwords You Typed Are Different, Please Try Again");
			}
		}
		// *************create UserObject here

	}

	public static void login() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BoardDAO boardDao = new BoardLocalDAO();
		MessageDAO messageDao = new MessageLocalDAO();
		User user;
		String username;
		String password;
		String input;
		int choice;

		while (true) {
			// Stdout ask for input
			System.out.println("Enter Your Username: ");
			username = br.readLine();
			System.out.println("Enter Your Password: ");
			password = br.readLine();
			Board cboard;

			// authenticate the username and password
			user = new Authenticator().authenticateUser(username, password);

			if (user != null) {
				while (true) {
					Iterator<Board> allBoards = boardDao.getAllBoards();

					// print all boards avaliable
					while (allBoards.hasNext()) {
						Board temp = allBoards.next();
						System.out.println(temp.getBid()
								+ " - " + temp.getName());
					}

					System.out
							.println("Enter The Board ID of the Board You Would Like to Join");
					System.out
							.println("Or Type 'Quit' to Return to the Main Menu");
					input = br.readLine();
					input.trim();
					// Type Quit To Return To the Login Menu
					if (input.equals("quit")) {
						return;
					}
					if (user.joinBoard(input) != 0){//May need try/catch block.
						System.out.println("Invalid Board choice");
					}else{
						while (true) {
							Iterator<Message> j = messageDao.getMessages(user.getcurrentBoard().getBid());
							while (j.hasNext()) {
								j.next().printMessage(user.getUsername());
							}
							System.out.println("Enter Text Here");
							user.post(br.readLine());
							// *****************MAKE MSG OBJECT HERE
						}
					}
				}
				} else {
				System.out.println("Password/Username Is Invalid");
				System.out.println("YOU SHALL NOT PASS");
			}
		}
	}

}
