package main;

public class Board {
	private int id;
	private String topic;
	private BoardMsgDAO dao;
	
	private Board(int id, String topic){
		this.id = id;
		this.topic = topic;
	}
	
	public void writeMessage(Message message){
		dao.storeMessage(message);
	}

	public String getTopic() {
		return topic;
	}

	public int getId() {
		return id;
	}
}
