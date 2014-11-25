package mvc;

import java.util.ArrayList;

import exceptions.StateException;
import exceptions.DataException;
import model.Board;


public interface ViewEventListener {
	public void changeBoardByBid(String bid) throws StateException, DataException;
	public void changeBoardByName(String bid) throws StateException, DataException;
	public void login(String username, String password) throws DataException, StateException;
	public void logout();
	public void register(String userName, String password1,String password2) throws DataException;
	public void post(String message) throws DataException;
	public void createBoard(String name) throws DataException;
	public void requestBoardMessages() throws StateException;
	public void requestBoards() throws StateException;
	public void requestBoardsByTag(ArrayList<String> tags) throws StateException;
	public void requestSubscribedBoards();
	//Carlito added these abstract methods()
	public void subscribeUserToBoard(Board board);
	public void unsubscribeUserFromBoard(Board board);
	public void addModelEventListener(ModelEventListener e);
	public void removeModelEventListener(ModelEventListener e);
	
	public void addTagsToBoard(ArrayList<String> tags) throws DataException, StateException;
}
