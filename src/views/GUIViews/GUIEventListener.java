package GUIViews;

/**
 * All GUIs in the view need to implement this.
 * @author David
 *
 */

public interface GUIEventListener {
	public void closeGUI();
	public int displayString(String text);	
}
