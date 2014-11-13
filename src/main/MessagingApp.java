package main;

import java.io.IOException;

public class MessagingApp implements ModelEventListener {

	private boolean isLoggedIn; 
	private boolean isInBoard; 
	
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public boolean getIsInBoard() {
		return isInBoard;
	}
	
	@Override
	public void runView() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int printString(String text) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeStateLoggedIn() {
		// TODO Auto-generated method stub
		isLoggedIn = true;
	}

	@Override
	public void changeStateLoggedOut() {
		// TODO Auto-generated method stub
		isLoggedIn = false;
	}

	@Override
	public void changeStateInBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeStateNoBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub
		
	}

}
