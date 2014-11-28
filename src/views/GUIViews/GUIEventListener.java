package GUIViews;

import java.util.Iterator;

import model.Message;

/**
 * All GUIs in the view need to implement this.
 * @author David
 *
 */

public interface GUIEventListener {
	public void closeGUI();
	public int displayString(String text);
	public void refresh();
	public void recieveMessages(Iterator<Message> messages);
}
