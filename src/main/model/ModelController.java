package model;

import java.util.HashSet;
import java.util.Iterator;

import exceptions.StateException;
import local.*;
import mvc.ModelEventListener;
import mvc.ViewEventListener;
/**
 * THIS IS HOW YOU TALK TO THE PROGRAM. IT ACTS AS A "middle man" between
 * view and model.
 * @author David
 *
 */
public class ModelController implements ViewEventListener{
	private User user;
	private HashSet<ModelEventListener> views = new HashSet<ModelEventListener>();
	public ModelController(){
		this.user = null;

	}
	
	/*-------------State-Change requests---------*/
	@Override
	public void register(String userName, String password1, String password2){
		UserDAO userDao = new UserDAO();

		if (password1.equals(password2)) {
			this.user = userDao.createUser(userName, password1);
			if (this.user != null) {
				for (ModelEventListener view : views){
					view.changeStateLoggedIn();
				}
				
			}else{
				for (ModelEventListener view : views){ //failed to regiister
					view.registerError();
				}
				
			}
		} else {
			for (ModelEventListener view : views){ //passwords don't match
				view.registerError();
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
				for (ModelEventListener view : views){ //failed to join the board
					view.boardError();
				}
				
			}
		}else{
			for (ModelEventListener view : views){ //failed to join the board since we're not logged in
				view.boardError();
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
				for (ModelEventListener view : views){ //failed to join the board
					view.boardError();
				}
				
			}
		}else{
			for (ModelEventListener view : views){ //failed to join the board since we're not logged in
				view.boardError();
			}
		}
		
		
	}


	
	@Override
	public void login(String username, String password) {
		user = new Authenticator().authenticateUser(username, password);
		if (user == null){
			for (ModelEventListener view : views){
				view.loginError();
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
			for (ModelEventListener view : views){ //failed to post message
				view.messageError();
			}
		}
	}

	/*-----------------DATA REQUESTS-------------------*/
	
	@Override
	public void requestBoardMessages() {
		if (assertLoggedIn()){
			MessageDAO DAO = new MessageDAO();
			Iterator<Message> msgs = 
					DAO.getMessages(this.user.getcurrentBoard().getBid());
			for (ModelEventListener view : views){
				try {
					view.recieveBoardMessages(msgs);
				} catch (StateException e) {
					e.printStackTrace();
				}
			}
			
		}else{
			for (ModelEventListener view : views){ //failed to retrieve board messages since user is not logged in
				view.dataError();
			}
		}
		
	}
	
	@Override
	public void requestBoards() {
		if (assertLoggedIn()){
			BoardDAO DAO = new BoardDAO();
			Iterator<Board> boards = DAO.getAllBoards();
			for (ModelEventListener view : views){
				try {
					view.recieveBoards(boards);
				} catch (StateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			for (ModelEventListener view : views){ //failed to retrieve boards since user is not logged in
				view.dataError();
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
		SubscriptionDAO dao = new SubscriptionDAO();
		dao.subUserToBoard(user, board);
		
		
	}
	@Override
	public void unsubscribeUserFromBoard(Board board) {
		// TODO Auto-generated method stub
		SubscriptionDAO dao = new SubscriptionDAO();
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
	
	@Override
	public void logout() {
		this.user = null;
		for (ModelEventListener view : views){
			view.changeStateLoggedOut();
		}
		
	}
	
	/*-------------NON-OVERRIDES-------------*/
	
	/*ALways call this to check that you've logged in.*/
	private boolean assertLoggedIn(){
		return this.user != null;
	}	
}
