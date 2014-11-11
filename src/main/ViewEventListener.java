package main;

public interface ViewEventListener {
	public void changeState(String state);
	public void changeBoardByBid(String bid);
	public void changeBoardByName(String bid);
	public void displayBoardMessages();
	public void login(String username, String password);
	public void register(String userName, String password1,String password2);
	public void post(String message);
	//public void getBoardsByTag();
	//public void Credits();
	//public void messageUser();
}
