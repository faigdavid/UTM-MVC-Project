package model;

import sqlServer.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import exceptions.DataException;
import exceptions.StateException;
import mvc.ModelEventListener;
import mvc.ViewEventListener;

/**
 * THIS IS HOW YOU TALK TO THE PROGRAM. IT ACTS AS A "middle man" between view
 * and model.
 * 
 * @author David
 *
 */
public class ModelController implements ViewEventListener {
	private User user;
	private HashSet<ModelEventListener> views = new HashSet<ModelEventListener>();

	public ModelController() {
		this.user = null;

	}

	/*-------------State-Change requests---------*/
	@Override
	public void register(String userName, String password1, String password2)
			throws DataException {
		UserDAO userDao = new UserDAO();
		if (password1.equals(password2)) {
			this.user = userDao.createUser(userName, password1);
			if (this.user != null) {
				for (ModelEventListener view : views) {
					view.changeStateLoggedIn();
				}

			} else {// failed to register
				throw new DataException("Failed to create user.");
			}
		} else { // passwords don't match.
			throw new DataException("Password missmatch.");

		}
	}

	@Override
	public void changeBoardByName(String name) throws StateException,
			DataException {
		if (assertLoggedIn()) {
			BoardDAO DAO = new BoardDAO();
			Board toChange = DAO.getBoardByName(name);
			if (this.user.joinBoard(toChange.getBid()) == 1) {
				for (ModelEventListener view : views) {
					view.changeStateInBoard(user.getcurrentBoard());
				}

			} else {// Board does not exist, or the DAO failed.
				throw new DataException();
			}
		} else {// failed to join the board since we're not logged in
			throw new StateException();
		}

	}

	@Override
	public void changeBoardByBid(String bid) throws StateException,
			DataException {
		if (assertLoggedIn()) {
			if (this.user.joinBoard(bid) == 1) {
				for (ModelEventListener view : views) {
					view.changeStateInBoard(user.getcurrentBoard());
				}
			} else {// Board does not exist, or DAO failed.
				throw new DataException();

			}
		} else {// failed to join the board since we're not logged in
			throw new StateException();
		}

	}

	@Override
	public void login(String username, String password) throws DataException,
			StateException {
		if (user != null) {
			throw new StateException("User must be logged out.");
		}
		user = new Authenticator().authenticateUser(username, password);
		if (user == null) {
			throw new DataException();
		} else {
			for (ModelEventListener view : views) {
				view.changeStateLoggedIn();
			}

		}
		return;

	}

	@Override
	public void logout() {
		this.user = null;
		for (ModelEventListener view : views) {
			view.changeStateLoggedOut();
		}

	}

	/*-----------------DATA REQUESTS-------------------*/

	@Override
	public void requestBoardMessages() throws StateException {
		if (assertLoggedIn()) {
			MessageDAO DAO = new MessageDAO();
			Iterator<Message> msgs = DAO.getMessages(this.user
					.getcurrentBoard().getBid());
			for (ModelEventListener view : views) {
				view.recieveBoardMessages(msgs);
			}
		} else {
			throw new StateException("Not logged in.");
		}

	}

	@Override
	public void requestBoards() throws StateException {
		if (assertLoggedIn()) {
			BoardDAO DAO = new BoardDAO();
			Iterator<Board> boards = DAO.getAllBoards();
			for (ModelEventListener view : views) {
				view.recieveBoards(boards);
			}
		} else {
			throw new StateException("Wrong state to recieve boards.");
		}
	}
	@Override
	public void requestSubbedBoards() throws StateException {
		if (assertLoggedIn()) {
			SubscriptionDAO DAO = new SubscriptionDAO();
			Iterator<Board> boards = DAO.getBoardsBySubscriber(
					user.getUsername());
			for (ModelEventListener view : views) {
				view.recieveBoards(boards);
			}
		} else {
			throw new StateException("Wrong state to recieve boards.");
		}
	}
	@Override
	public void requestBoardsByTag(ArrayList<String> tags) throws StateException {
		if (assertLoggedIn()) {
			BoardDAO DAO = new BoardDAO();
			Iterator<Board> boards = DAO.getAllBoardsByTags(tags);
			for (ModelEventListener view : views) {
				view.recieveBoards(boards);
			}
		} else {
			throw new StateException("Wrong state to recieve boards.");
		}
	}

	@Override
	public void requestSubscribedBoards() {
		// TODO Auto-generated method stub

	}

	@Override
	public void subscribeUserToBoard() {
		// TODO Auto-generated method stub
		SubscriptionDAO dao = new SubscriptionDAO();
		dao.subUserToBoard(user.getUsername(), user.getcurrentBoard().getBid());

	}

	@Override
	public void unsubscribeUserFromBoard(Board board) {
		// TODO Auto-generated method stub
		SubscriptionDAO dao = new SubscriptionDAO();
		dao.unSubUserFromBoard(user.getUsername(), board.getBid());
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
	public void createBoard(String name, String topic) throws DataException {
		// TODO Auto-generated method stub
		BoardDAO BDAO = new BoardDAO();
		if (topic == "" || topic == null){
			if (BDAO.createBoard(name) != 1) {
				throw new DataException("Board already exists.");
			}
		} else {
			if (BDAO.createBoard(name, topic) != 1) {
				throw new DataException("Board already exists.");
			}
		}
		
	}

	@Override
	public void post(String message) throws DataException {
		if (message == null){
			throw new DataException();
		}
		if (user.post(message) != 1) {// failed to post.
			throw new DataException();
		}
	}
	@Override
	public void addTagsToBoard(ArrayList<String> tags) throws DataException,
	StateException  {
		// TODO Auto-generated method stub
		if (assertLoggedIn()) {
			BoardDAO DAO = new BoardDAO();
			if(DAO.addTags(tags, user.getcurrentBoard().getBid()) != 1){
				throw new DataException("Failed to add tags.");
			}
		} else {
			throw new StateException("Not logged in.");
		}
	}
	/*-------------NON-OVERRIDES-------------*/

	/* ALways call this to check that you've logged in. */
	private boolean assertLoggedIn() {
		return this.user != null;
	}

	
}
