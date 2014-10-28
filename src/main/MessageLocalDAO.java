package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Iterator;

public class MessageLocalDAO implements MessageDAO{
	/*Note: Messages will be stored by mId, board, username, date, and text,
	 separated by newlines "\n" */
	
	/*IMPORTANT!! This class was made for windows file-systems.
	 * If you wish to change it for linux, then change the path names in
	 the  methods to linuxpath.*/
	private String linuxpath = "/CSC301/Messages";
	private String windowspath = "Libraries\\Documents\\Messages";
	
	@Override
	public Iterator<Message> getMessages(Board board) {
		
		
		return null;
	}

	@Override
	public Message getMessage(String mid) {
		String filename = String.format("%s/%s.txt", windowspath, mid);
		BufferedReader reader = null;
		Message.Builder msgbuild = new Message.Builder();
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = null;
		    for (int i=1;i<6;i++) {
		    	line = reader.readLine();
		    	if (line == null){
		    		//Corrupted message.
		    		return null;
		    	}
		    	else if (i == 1){
		    		msgbuild.setmId(line);
		    	}
		    	else if (i == 2){
		    		msgbuild.setBoardId(line);
		    	}
		    	else if (i == 3){
		    		msgbuild.setusername(line);
		    	}
		    	else if (i == 4){
		    		msgbuild.setDate(line);
		    	}
		    	else if (i == 5){
		    		msgbuild.setText(line);
		    	}
		    }
		} catch (IOException ex) {
		  // report
		} finally {
		   try {reader.close();} catch (Exception ex) {}
		}
		return msgbuild.build();
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
	String filename = String.format("%s/%s.txt", windowspath, msg.getmId());
	Writer writer = null;
	try {
		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename), "utf-8"));
		writer.write(String.format("%d \n", msg.getmId()));
		writer.write(String.format("%s \n", msg.getboardId()));
		writer.write(String.format("%s \n", msg.getusername()));
		writer.write(String.format("%s \n", msg.getDate()));
		writer.write(String.format("%s \n", msg.getText()));
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		   try {writer.close();} catch (Exception ex) {}
	}
	
	return 0;
	}



}
