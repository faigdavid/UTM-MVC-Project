package mvc;

import java.io.IOException;
import java.util.Iterator;

import model.Board;
import model.Message;
import exceptions.StateException;

public interface ModelEventListener {
	public void runView() throws IOException;
	public int printString(String text);
	public void changeStateLoggedIn();
	public void changeStateLoggedOut();
	public void changeStateInBoard();
	public void changeStateNoBoard();
	public void changeStateRegister();
	public void recieveBoardMessages(Iterator<Message> messages) throws StateException;
	public void recieveBoards(Iterator<Board> boards) throws StateException;
	public void recieveSubscribedBoards(Iterator<Board> boards) throws StateException;
	public void addViewEventListener(ViewEventListener listener);
	public void removeViewEventListener(ViewEventListener listener);
	
	/*
	 * Added error pop up messages to replace the error messages that prints to the console.
	 * I did this since we're doing a GUI.
	 * @author Carlito Llena
	 */
	public void loginError();
	public void registerError();
	public void boardError();
	public void messageError();
	public void dataError();
}

