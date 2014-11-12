package main;

import java.io.IOException;

public interface ModelEventListener {
	public void runView() throws IOException;
	public int printString(String text);
	public void changeStateLoggedIn();
	public void changeStateLoggedOut();
	public void changeStateInBoard();
	public void changeStateNoBoard();
	public void addViewEventListener(ViewEventListener listener);
	public void removeViewEventListener(ViewEventListener listener);
}
