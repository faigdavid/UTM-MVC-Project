package main;

public interface EventListener {
	public void changeBoard(String bid);
	public void displayBoard();
	public void login();
	public void register(String userName, String password1,String password2);
	public void messageUser();
	
	//public void getBoardsByTag();
	//public void Credits();
}
