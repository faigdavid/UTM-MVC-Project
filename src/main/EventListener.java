package main;

public interface EventListener {
	public void changeBoardByBid(String bid);
	public void changeBoardByName(String bid);
	public void displayBoard();
	public void login();
	public void register(String userName, String password1,String password2);
	public void messageUser();
	public void post(String message);
	//public void getBoardsByTag();
	//public void Credits();
}
