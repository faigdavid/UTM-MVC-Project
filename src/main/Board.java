package main;

public class Board {
	private int id;
	private String topic;
	
	private Board(int id, String topic){
		this.id = id;
		this.topic = topic;
	}
	
	public writeMessage(int userid, String text){
		BoardMsgDAO.storeMessage();
	}
}
