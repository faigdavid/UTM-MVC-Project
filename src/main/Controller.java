package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller implements EventListener{
	private User user;
	
	public Controller(){
		this.user = null;
	}
	
	@Override
	public void register(String userName, String password1, String password2){
		UserDAO userDao = new UserLocalDAO();

		if (password1.equals(password2)) {
			if (userDao.createUser(userName, password1) != null) {
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


	@Override
	public void changeBoard(String bid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayBoard() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void messageUser() {
		// TODO Auto-generated method stub
		
	}
	
	/*NON-OVERRIDES*/
	
	/*ALways call this to check that you've logged in.*/
	private boolean assertLoggedIn(){
		return this.user != null;
	}

	
	
}
