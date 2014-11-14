package main;

import java.io.IOException;
import java.util.Iterator;

public interface ModelEventListener {
	public void addViewEventListener(ViewEventListener listener);
	public void removeViewEventListener(ViewEventListener listener);
	
	/**
	 * Runs the View implementation. Acts as a "main" for each View.
	 * @throws IOException
	 */
	public void runView() throws IOException;
	
	/**
	 * Prints text on the View.
	 * @return 1 for success, 0 for failure.
	 */
	public int printString(String text);
	
	/*------STATE CHANGERS--------*/
	
	/**
	 * Become notified that the model is now "logged in",
	 * In some Views, this is no different from "no board".
	 */
	public void changeStateLoggedIn();
	
	/**
	 * Become notified that the model is now "logged out",
	 * Meaning you may register or login.
	 */
	public void changeStateLoggedOut();
	
	/**
	 * Become notified that the model is now "in a board",
	 * Meaning you may post/receive messages from it.
	 */
	public void changeStateInBoard();
	
	/**
	 * Become notified that the model is now "not in a board".
	 */
	public void changeStateNoBoard();
	
	/*-------DATA RECIEVERS-------*/
	
	/** 
	 * Receive a board's messages and alter the view accordingly.
	 * Usually called as a response to requestBoardMessages().
	 * */
	public void recieveBoardMessages(Iterator<String> messages);
	
	/** 
	 * Receive information from all boards 
	 * and alter the view accordingly.
	 * Usually called as a response to requestBoards().
	 * */
	public void recieveBoards(Iterator<String> Boards);
	
	/** 
	 * Receive information from all subscribed boards,
	 * and alter the view accordingly.
	 * Usually called as a response to requestSubscribedBoards().
	 * */
	public void recieveSubscribedBoards(Iterator<String> Boards);
}
