package model;

import java.util.HashSet;
import java.util.Iterator;

import exceptions.DataException;
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
	public void register(String userName, String password1, String password2) throws DataException{
		UserDAO userDao = new UserDAO();

		if (password1.equals(password2)) {
			this.user = userDao.createUser(userName, password1);
			if (this.user != null) {
				for (ModelEventListener view : views){
					view.changeStateLoggedIn();
				}
				
			}else{//failed to register
				throw new DataException();
			}
		} else { //passwords don't match.
			throw new DataException();
			
		}
	}
	@Override
	public void changeBoardByName(String name) throws StateException, DataException {
		if (assertLoggedIn()){
			BoardDAO DAO = new BoardDAO();
			Board toChange = DAO.getBoardByName(name);
			if (this.user.joinBoard(toChange.getBid()) == 1){
				for (ModelEventListener view : views){
					view.changeStateInBoard();
				}
				
			} else {//Board does not exist, or the DAO failed.
					throw new DataException();
			}
		}else{//failed to join the board since we're not logged in
			throw new StateException();
		}
		
	}
	@Override
	public void changeBoardByBid(String bid) throws StateException, DataException {
		if (assertLoggedIn()){
			if (this.user.joinBoard(bid) == 1){
				for (ModelEventListener view : views){
					view.changeStateInBoard();
				}
			} else {//Board does not exist, or DAO failed.
				throw new DataException();
				
			}
		}else{//failed to join the board since we're not logged in
			throw new StateException(); 
		}
		
		
	}


	
	@Override
	public void login(String username, String password) throws StateException {
		user = new Authenticator().authenticateUser(username, password);
		if (user == null){
			throw new StateException();
		} else {
			for (ModelEventListener view : views){
				view.changeStateLoggedIn();
			}
			
		}
		return;
		
	}
	
	@Override
	public void logout() {
		this.user = null;
		for (ModelEventListener view : views){
			view.changeStateLoggedOut();
		}
		
	}
	


	/*-----------------DATA REQUESTS-------------------*/
	
	@Override
	public void requestBoardMessages() throws StateException {
		if (assertLoggedIn()){
			MessageDAO DAO = new MessageDAO();
			Iterator<Message> msgs = 
					DAO.getMessages(this.user.getcurrentBoard().getBid());
			for (ModelEventListener view : views){
				view.recieveBoardMessages(msgs);
			}
		}else{
			throw new StateException();
		}
		
	}
	
	@Override
	public void requestBoards() throws StateException {
		if (assertLoggedIn()){
			BoardDAO DAO = new BoardDAO();
			Iterator<Board> boards = DAO.getAllBoards();
			for (ModelEventListener view : views){
				view.recieveBoards(boards);
			}
		}else{
			throw new StateException();
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
	public void createBoard(String name) throws DataException {
		// TODO Auto-generated method stub
	        BoardDAO BDAO = new BoardDAO();
		if (BDAO.createBoard(name) != 1){
			throw new DataException();
		}
	}
	@Override
	public void post(String message) throws StateException {
		if (user.post(message) != 1){//failed to post.
			throw new StateException();
		}
	}

	/*-------------NON-OVERRIDES-------------*/
	
	/*ALways call this to check that you've logged in.*/
	private boolean assertLoggedIn(){
		return this.user != null;
	}	
}
