package main;

import java.sql.Timestamp;
import java.util.Iterator;

public interface MessageDAO{
	public Iterator<Message> getMessages(Board board);
	
	public Message getMessage(String mid);
	
	public Iterator<Message> getMessagesSinceTime(Board board, Timestamp time);

	public int deleteMessage(Board board, Message msg);
	
	public int addMessage(Board board, Message msg);
}
