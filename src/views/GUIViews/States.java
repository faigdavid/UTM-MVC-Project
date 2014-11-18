package GUIViews;

/*This interface holds possible states and a function that synchronizes all states within all GUIs
 * so if you want to add/modify/remove a state, just do it here
 * 
 * author Carlito
 * 
 * */
public interface States {
	public enum states {
		LOGGED_IN, LOGGED_OUT, IN_BOARD, NOT_IN_BOARD
	}
	
	public void updateCurrentState(states currentState);
}
