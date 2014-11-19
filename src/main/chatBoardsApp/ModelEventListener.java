package chatBoardsApp;

import java.io.IOException;
import java.util.Iterator;

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
}

