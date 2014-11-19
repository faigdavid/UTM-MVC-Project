package GUIViews;

import java.io.IOException;
import java.util.Iterator;

import chatBoardsApp.*;
import exceptions.StateException;
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
	
	private GUIEventListener currentState = null;
	private ViewEventListener controller = null;
	
	public GUIMain() { }
	
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
		return currentState.displayString(text);
	}

	@Override
	public void changeStateRegister() {
		currentState.closeGUI();
		this.currentState = new RegisterGUI(this);
	}
	@Override
	public void changeStateLoggedIn() {
		currentState.closeGUI();
		this.currentState = new DashBoardGUI(this);
	}

	@Override
	public void changeStateLoggedOut() {
		currentState.closeGUI();
		this.currentState = new LoginGUI(this);
	}

	@Override
	public void changeStateInBoard() {
		currentState.closeGUI();
		this.currentState = new BoardGUI(this);
	}

	@Override
	public void changeStateNoBoard() { 
		currentState.closeGUI();
		this.currentState = new DashBoardGUI(this);
	}

	@Override
	public void addViewEventListener(ViewEventListener controller) {
		this.controller = controller;

	}

	@Override
	public void removeViewEventListener(ViewEventListener controller) {
		this.controller = null;

	}

	@Override
	public void recieveBoardMessages(Iterator<Message> messages) throws StateException {
		if (this.currentState instanceof BoardGUI) {
			((BoardGUI) currentState).recieveMessages(messages);
		}
		else {
			throw new StateException("GUI state is out of sync.");
		}

	}

	@Override
	public void recieveBoards(Iterator<Board> boards) throws StateException {
		if (this.currentState instanceof DashBoardGUI) {
			((DashBoardGUI) currentState).recieveBoards(boards);
		}
		else {
			throw new StateException("GUI state is out of sync.");
		}

	}

	@Override
	public void recieveSubscribedBoards(Iterator<Board> boards) throws StateException {
		if (this.currentState instanceof DashBoardGUI) {
			((DashBoardGUI) currentState).recieveBoards(boards);
		}
		else {
			throw new StateException("GUI state is out of sync.");
		}


	}



}
