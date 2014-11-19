package GUIViews;

import java.io.IOException;
import java.util.Iterator;

import chatBoardsApp.*;
/**
 * This GUI will be act as the "startup"  for all the other GUIs.
 * Running this View should set up all the GUI listeners and immediately
 * load the first GUI.
 * 
 * The general idea of how GUI views works is that, rather than
 * just interacting with each other, they will also all be listeners
 * to the main program. This way, they are guaranteed to be synchronized.
 * 
 * @authors Henry, David
 *
 */
public class GUIMain implements ModelEventListener{
	
	private LoginGUI login = null;
	private GUIBoard board = null;
	private GUIDashBoard dashBoard = null;
	private RegisterGUI register = null;
	private ViewEventListener controller = null;
	
	public GUIMain() {
	}
	
	public ViewEventListener getController(){
		return this.controller;
	}
	
	@Override
	public void runView() throws IOException {
		this.changeStateLoggedOut();
	}
	
	//END GUI
	
	@Override
	public int printString(String text) {
		// TODO Auto-generated method stub
		System.out.println(text);
		return 1;
	}

	@Override
	public void changeStateLoggedIn() { //synchronizes all GUIs to this state
		this.dashBoard = new GUIDashBoard();

	}

	@Override
	public void changeStateLoggedOut() { //synchronizes all GUIs to this state
		this.login = new LoginGUI(this);
	}

	@Override
	public void changeStateInBoard() { //synchronizes all GUIs to this state
		this.board = new GUIBoard(this);//COPY THIS FOR OTHER ONES!!!!!!!!
	}

	@Override
	public void changeStateNoBoard() { //synchronizes all GUIs to this state
		// TODO Auto-generated method stub
	}

	@Override
	public void addViewEventListener(ViewEventListener listener) {
		this.controller = listener;

	}

	@Override
	public void removeViewEventListener(ViewEventListener listener) {
		this.controller = null;

	}

	@Override
	public void recieveBoardMessages(Iterator<Message> messages) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveBoards(Iterator<Board> boards) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveSubscribedBoards(Iterator<Board> boards) {
		// TODO Auto-generated method stub

	}


}
