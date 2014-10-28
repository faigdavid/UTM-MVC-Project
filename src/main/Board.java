package main;

public class Board {
	private int BID;
	private String Name;
	private string password;
	
	private Board(int id, String name, String password){
		this.bid = bid;
		this.name = name;
		this.password = password;
	}
	
	//Deletes a message
	public int deleteMessage(int mid){
		MessageDAO mdao = new MessageLocalDAO();
		Message msg = mdao.getMessage(mid);
		return mdao.deleteMessage(this,msg);
	}

	public int postMessage(Message msg) {
		MessageDAO mdao = new MessageLocalDAO();
		return mdao.addMessage(this, msg);
	}

}
