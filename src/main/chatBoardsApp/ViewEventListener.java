package chatBoardsApp;


public interface ViewEventListener {
	public void changeBoardByBid(String bid);
	public void changeBoardByName(String bid);
	public void login(String username, String password);
	public void register(String userName, String password1,String password2);
	public void post(String message);
	public void createBoard(String name);
	public void requestBoardMessages();
	public void requestBoards();
	public void requestSubscribedBoards();
	//Carlito added these abstract methods()
	public void subscribeUserToBoard(Board board);
	public void unsubscribeUserFromBoard(Board board);
	public void addModelEventListener(ModelEventListener e);
	public void removeModelEventListener(ModelEventListener e);
}
