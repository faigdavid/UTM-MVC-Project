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

	public int postMessage(Message msg) {
		MessageDAO mdao = new MessageLocalDAO();
		return mdao.addMessage(this, msg);
	}

}
