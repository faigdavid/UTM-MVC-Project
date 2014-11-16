package main;

import DAOlocals.*;
import java.util.HashSet;
import java.util.Iterator;
import DAOinterfaces.UserDAO;
import DAOlocals.MessageLocalDAO;
import DAOlocals.SubscriptionLocalDAO;
import DAOlocals.UserLocalDAO;

public class Controller implements ViewEventListener{
	private User user;
	private HashSet<ModelEventListener> views = new HashSet<ModelEventListener>();
	public Controller(){
		this.user = null;

	}
	
	/*-------------State-Change requests---------*/
	@Override
	public void register(String userName, String password1, String password2){
		UserDAO userDao = new UserLocalDAO();

		if (password1.equals(password2)) {
			this.user = userDao.createUser(userName, password1);
			if (this.user != null) {
				for (ModelEventListener view : views){
					view.changeStateLoggedIn();
					view.printString("User Created.");
				}
				
			}else{
				for (ModelEventListener view : views){
					view.printString("Failed to create new user.");
				}
				
			}
		} else {
			for (ModelEventListener view : views){
				view.printString("The passwords did not match!");
			}
			
		}
	}
	@Override
	public void changeBoardByName(String name) {
		if (assertLoggedIn()){
			BoardDAO DAO = new BoardDAO();
			Board toChange = DAO.getBoardByName(name);
			if (this.user.joinBoard(toChange.getBid()) == 1){
				for (ModelEventListener view : views){
					view.changeStateInBoard();
				}
				
			} else {
				for (ModelEventListener view : views){
					view.printString("Failed to join the board.");
				}
				
			}
		}else{
			for (ModelEventListener view : views){
				view.printString("You are not logged in.");
			}
		}
		
	}
	@Override
	public void changeBoardByBid(String bid) {
		if (assertLoggedIn()){
			if (this.user.joinBoard(bid) == 1){
				for (ModelEventListener view : views){
					view.changeStateInBoard();
				}
			} else {
				for (ModelEventListener view : views){
					view.printString("Failed to join the board.");
				}
				
			}
		}else{
			for (ModelEventListener view : views){
				view.printString("You are not logged in.");
			}
		}
		
		
	}


	
	@Override
	public void login(String username, String password) {
		user = new Authenticator().authenticateUser(username, password);
		if (user == null){
			for (ModelEventListener view : views){
				view.printString("FAILED TO LOG IN");
			}
			
		} else {
			for (ModelEventListener view : views){
				view.changeStateLoggedIn();
			}
			
		}
		return;
		
	}
	
	@Override
	public void post(String message) {
		if (user.post(message) != 1){
			for (ModelEventListener view : views){
				view.printString("Message failed to post.");
			}
		}
	}

	/*-----------------DATA REQUESTS-------------------*/
	
	@Override
	public void requestBoardMessages() {
		if (assertLoggedIn()){
			MessageLocalDAO DAO = new MessageLocalDAO();
			Iterator<Message> msgs = 
					DAO.getMessages(this.user.getcurrentBoard().getBid());
			for (ModelEventListener view : views){
				view.recieveBoardMessages(msgs);
			}
			
		}else{
			for (ModelEventListener view : views){
				view.printString("You are not logged in.");
			}
		}
		
	}
	
	@Override
	public void requestBoards() {
		if (assertLoggedIn()){
			BoardDAO DAO = new BoardDAO();
			Iterator<Board> boards = DAO.getAllBoards();
			for (ModelEventListener view : views){
				view.recieveBoards(boards);
			}
		}else{
			for (ModelEventListener view : views){
				view.printString("You are not logged in.");
			}
	}
	}

	@Override
	public void requestSubscribedBoards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeUserToBoard(Board board) {
		// TODO Auto-generated method stub
		SubscriptionLocalDAO dao = new SubscriptionLocalDAO();
		dao.subUserToBoard(user, board);
		
		
	}
	@Override
	public void unsubscribeUserFromBoard(Board board) {
		// TODO Auto-generated method stub
		SubscriptionLocalDAO dao = new SubscriptionLocalDAO();
		dao.unSubUserFromBoard(user, board);
	}
	
	@Override
	public void addModelEventListener(ModelEventListener e) {
		this.views.add(e);
		
	}

	@Override
	public void removeModelEventListener(ModelEventListener e) {
		this.views.remove(e);
		
	}

	@Override
	public void createBoard(String name) {
		// TODO Auto-generated method stub
	        BoardDAO BDAO = new BoardDAO();
		if (BDAO.createBoard(name) != 1){
			//nothing for now.
		}
	}
	/*-------------NON-OVERRIDES-------------*/
	
	/*ALways call this to check that you've logged in.*/
	private boolean assertLoggedIn(){
		return this.user != null;
	}



	
}
