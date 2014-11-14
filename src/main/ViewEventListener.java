package main;
/**
 * These methods are a list of view events that the controller will
 * be able to respond to.
 * @author David
 *
 */
public interface ViewEventListener {
	/**
	 * Call this to change the Board.
	 * On success or failure, the function will ask the view to
	 * print the according
	 * @param bid
	 */
	public void changeBoardByBid(String bid);
	public void changeBoardByName(String bid);
	public void login(String username, String password);
	public void register(String userName, String password1,String password2);
	public void post(String message);
	public void requestBoardMessages();
	public void requestBoards();
	public void requestSubscribedBoards();
}
