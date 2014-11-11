package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Controller implements ViewEventListener{
	private User user;
	private ModelEventListener view;
	public Controller(ModelEventListener view){
		this.user = null;
		this.view = view;
	}
	
	@Override
	public void register(String userName, String password1, String password2){
		UserDAO userDao = new UserLocalDAO();

		if (password1.equals(password2)) {
			this.user = userDao.createUser(userName, password1);
			if (this.user != null) {
				// create success
				view.printString("User Created.");
				
			}else{
				view.printString("Failed to create new user.");
			}
		} else {
			view.printString("The passwords did not match!");
		}
	}
	@Override
	public void changeBoardByName(String name) {
		if (assertLoggedIn()){
			BoardLocalDAO DAO = new BoardLocalDAO();
			Board toChange = DAO.getBoardByName(name);
			this.user.joinBoard(toChange.getBid());
		}else{
			view.printString("You are not logged in.");
		}
		
	}
	@Override
	public void changeBoardByBid(String bid) {
		if (assertLoggedIn()){
			this.user.joinBoard(bid);
		}else{
			view.printString("You are not logged in.");
		}
		
		
	}
	@Override
	public void displayBoardMessages() {
		if (assertLoggedIn()){
			MessageLocalDAO DAO = new MessageLocalDAO();
			DAO.getMessages(this.user.getcurrentBoard().getBid());
		}else{
			view.printString("You are not logged in.");
		}
		
	}

	
	@Override
	public void login(String username, String password) {
		user = new Authenticator().authenticateUser(username, password);
		if (user == null){
			view.printString("FAILED TO LOG IN");
		return;	
		}
		view.changeMenuState("logged in");
		return;
		
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
