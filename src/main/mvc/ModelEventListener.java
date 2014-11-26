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
	public void changeStateInBoard(Board board);//changed to provide board being joined.
	public void changeStateNoBoard();
	public void changeStateRegister();
	public void recieveBoardMessages(Iterator<Message> messages) throws StateException;
	public void recieveBoards(Iterator<Board> boards) throws StateException;
	public void recieveSubscribedBoards(Iterator<Board> boards) throws StateException;
	public void addViewEventListener(ViewEventListener listener);
	public void removeViewEventListener(ViewEventListener listener);
	
	
	/*
	 * Changed this. Now GUI errors will be sent through exception catch
	 * blocks. Since those error messages are meant for the GUI and not any
	 * other views, there is no point to force other views to implement them.
	 * 
	 * This also allows the GUI to handle failures any way it wants.
	 * -David.
	 */
	//public void loginError();
	//public void registerError();
	//public void boardError();
	//public void messageError();
	//public void dataError();
}

