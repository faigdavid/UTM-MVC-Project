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
	
	private GUIEventListener currentState = null;
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
		
		//Take a string and display it.
		
		return 1;
	}

	@Override
	public void changeStateLoggedIn() { //synchronizes all GUIs to this state
		this.currentState = new DashBoardGUI(this);

	}

	@Override
	public void changeStateLoggedOut() { //synchronizes all GUIs to this state
		this.currentState = new LoginGUI(this);
	}

	@Override
	public void changeStateInBoard() { //synchronizes all GUIs to this state
		this.currentState = new BoardGUI(this);//COPY THIS FOR OTHER ONES!!!!!!!!
	}

	@Override
	public void changeStateNoBoard() { //synchronizes all GUIs to this state
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
	public void recieveBoardMessages(Iterator<Message> messages) {
		//Send board messages to the GUIboard. So it can display them.

	}

	@Override
	public void recieveBoards(Iterator<Board> boards) {
		//Send the boards to the GUIDashBoard. So it can display them.

	}

	@Override
	public void recieveSubscribedBoards(Iterator<Board> boards) {
		//Send the boards to the GUIDashBoard. So it can display them.

	}


}
