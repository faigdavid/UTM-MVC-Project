package main;

import DAOlocals.*;

public class Board {
	private String bid;
	private String name;
	private String password;
	
	public Board(String id, String name, String password){
		this.bid = id;
		this.name = name;
		this.password = password;
	}
	
	//Deletes a message
	public int deleteMessage(String mid){
		MessageDAO mdao = new MessageDAO();
		Message msg = mdao.getMessage(this.bid, mid);
		return mdao.deleteMessage(msg.getMid());
	}

	public int postMessage(User user, String text) {
		//david is responsible for making sure this works
		return new MessageDAO().addMessage(
				this.getBid(), user.getUsername(), text);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBid() {
		return bid;
	}

}
