package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Controller implements ViewEventListener{
	private User user;
	
	public Controller(){
		this.user = null;
	}
	
	@Override
	public void register(String userName, String password1, String password2){
		UserDAO userDao = new UserLocalDAO();

		if (password1.equals(password2)) {
			this.user = userDao.createUser(userName, password1);
			if (this.user != null) {
				// create success
				System.out.println("User Created.");
			} else {
				System.out.println("Failed to create new user.");
			}
		} else {
			System.out
					.println("The Passwords You Typed Are Different, Please Try Again");
		}
	}
		// *************create UserObject her


	@Override
	public void changeBoardByName(String name) {
		if (assertLoggedIn()){
			BoardLocalDAO DAO = new BoardLocalDAO();
			Board toChange = DAO.getBoardByName(name);
			this.user.joinBoard(toChange.getBid());
		}else{
			System.out.println("You are not logged in.");
		}
		
	}
	@Override
	public void changeBoardByBid(String bid) {
		if (assertLoggedIn()){
			this.user.joinBoard(bid);
		}else{
			System.out.println("You are not logged in.");
		}
		
		
	}
	@Override
	public void displayBoardMessages() {
		if (assertLoggedIn()){
			MessageLocalDAO DAO = new MessageLocalDAO();
			DAO.getMessages(this.user.getcurrentBoard().getBid());
		}else{
			System.out.println("You are not logged in.");
		}
		
	}

	
	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void post(String message) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void changeState(String state) {
		// TODO Auto-generated method stub
		
	}
	/*NON-OVERRIDES*/
	
	/*ALways call this to check that you've logged in.*/
	private boolean assertLoggedIn(){
		return this.user != null;
	}


	
	

	
	
}
