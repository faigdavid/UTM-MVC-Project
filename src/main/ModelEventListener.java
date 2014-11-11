package main;

import java.io.IOException;

public interface ModelEventListener {
	public int printString(String text);
	public void changeMenuState(String state);
}
