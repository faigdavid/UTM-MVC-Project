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
public class GUIMain implements ModelEventListener, States {

	private static GUIMain main = null;
	
	private LoginGUI login = null;
	private GUIBoard board = null;
	private GUIDashBoard dashBoard = null;
	private RegisterGUI register = null;
	
	private states currentState;
	
	private GUIMain() {
		this.currentState = states.LOGGED_OUT; //we initially are not in the chat board, therefore we're logged out
	}
	
	public static GUIMain instantiateGUIMain() {
		if(GUIMain.main == null) {
			GUIMain.main = new GUIMain();
		}
		return GUIMain.main;
	}
	
	@Override
	public void runView() throws IOException {

	}

	@Override
	public int printString(String text) {
		// TODO Auto-generated method stub
		System.out.println(text);
		return 1;
	}

	@Override
	public void changeStateLoggedIn() { //synchronizes all GUIs to this state
		login.updateCurrentState(states.LOGGED_IN);
		board.updateCurrentState(states.LOGGED_IN);
		dashBoard.updateCurrentState(states.LOGGED_IN);
		register.updateCurrentState(states.LOGGED_IN);
	}

	@Override
	public void changeStateLoggedOut() { //synchronizes all GUIs to this state
		login.updateCurrentState(states.LOGGED_OUT);
		board.updateCurrentState(states.LOGGED_OUT);
		dashBoard.updateCurrentState(states.LOGGED_OUT);
		register.updateCurrentState(states.LOGGED_OUT);
	}

	@Override
	public void changeStateInBoard() { //synchronizes all GUIs to this state
		login.updateCurrentState(states.IN_BOARD);
		board.updateCurrentState(states.IN_BOARD);
		dashBoard.updateCurrentState(states.IN_BOARD);
		register.updateCurrentState(states.IN_BOARD);
	}

	@Override
	public void changeStateNoBoard() { //synchronizes all GUIs to this state
		// TODO Auto-generated method stub
		login.updateCurrentState(states.NOT_IN_BOARD);
		board.updateCurrentState(states.NOT_IN_BOARD);
		dashBoard.updateCurrentState(states.NOT_IN_BOARD);
		register.updateCurrentState(states.NOT_IN_BOARD);
	}

	@Override
	public void addViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub

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

	@Override
	public void updateCurrentState(states currentState) {
		this.currentState = currentState;
	}

}
