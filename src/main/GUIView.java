package main;


import java.io.IOException;
import java.util.Iterator;

public class GUIView implements ModelEventListener {
	private static GUIView singleton;
	private ViewEventListener controller;
	private enum stateType {LOGGEDOUT, REGISTER, LOGGEDIN, INBOARD};
	private stateType state;
	
	private GUIView() {
		super();
	}
	
	/**
	 * Instantiates the MessagingApp. All further calls to newMessagingApp 
	 * will return the same instance. MessagingApp acts as a singleton.
	 * @return a MessagingApp instance.
	 */
	public static GUIView newMessagingApp() {
		if (singleton == null)
			singleton = new GUIView();
		return singleton;
	}
	
	public boolean getIsLoggedIn() {
		return state == stateType.LOGGEDIN;
	}
	public boolean getIsInBoard() {
		return state == stateType.INBOARD;
	}
	
	@Override
	public void runView() throws IOException {
		// TODO Auto-generated method stub
		new LoginGUI();
		
	}

	@Override
	public int printString(String text) {
		// TODO Auto-generated method stub
		System.out.println(text); // just for testing
		return 0;
	}

	@Override
	public void changeStateLoggedIn() {
		// TODO Auto-generated method stub
		this.state = stateType.LOGGEDIN;
	}

	@Override
	public void changeStateLoggedOut() {
		// TODO Auto-generated method stub
		this.state = stateType.LOGGEDOUT;
	}

	@Override
	public void changeStateInBoard() {
		// TODO Auto-generated method stub
		this.state = stateType.INBOARD;
	}

	@Override
	public void changeStateNoBoard() {
		// TODO Auto-generated method stub
		this.state = stateType.LOGGEDIN;
		
	}

	@Override
	public void addViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub
		this.controller = listener;
	}

	@Override
	public void removeViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub
		this.controller = null;
	}

	/*--------------------------Methods used only in GUI's--------------------------*/
	
	public ViewEventListener getViewEventListener(){
		return this.controller;
	}

	@Override
	public void recieveBoardMessages(Iterator<Message> messages) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieveBoards(Iterator<Message> messages) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieveSubscribedBoards(Iterator<Message> messages) {
		// TODO Auto-generated method stub
		
	}

}
