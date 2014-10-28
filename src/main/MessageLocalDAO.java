package main;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Iterator;

public class MessageLocalDAO implements MessageDAO{

	@Override
	public Iterator<Message> getMessages(Board board) {
		
		
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
	Writer writer = null;
	try {
		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("filename.txt"), "utf-8"));
		writer.write(msg.getboardId());
		writer.write(msg.getusername());
		writer.write(msg.getDate());
		writer.write(msg.getText());
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		   try {writer.close();} catch (Exception ex) {}
	}
	
	return 0;
	}



}
