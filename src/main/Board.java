package main;

public class Board {
	private int BID;
	private String Name;
	private String password;
	
	private Board(int id, String name, String password){
		this.BID = id;
		this.Name = name;
		this.password = password;
	}
	
	//Deletes a message
	public int deleteMessage(String mid){
		MessageDAO mdao = new MessageLocalDAO();
		Message msg = mdao.getMessage(mid);
		return mdao.deleteMessage(this,msg);
	}

	public int postMessage(User user, String text) {
	
		return new MessageLocalDAO().addMessage(this, user, text);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBID() {
		return BID;
	}

}
