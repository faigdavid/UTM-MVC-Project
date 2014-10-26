package main;

import java.util.Iterator;

public interface BoardMsgDAOInt {
	public void storeMessage(Message message);
	public void removeMessage(Message message);
	public Iterator<Message> getMessages();
}