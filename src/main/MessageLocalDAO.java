package main;

import java.sql.Timestamp;
import java.util.Iterator;

public class MessageLocalDAO implements MessageDAO{

	@Override
	public Iterator<Message> getMessages(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getMessage(String mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Message> getMessagesSinceTime(Board board, Timestamp time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMessage(Board board, Message msg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveMessage(Board board, Message msg) {
		// TODO Auto-generated method stub
		return 0;
	}



}
