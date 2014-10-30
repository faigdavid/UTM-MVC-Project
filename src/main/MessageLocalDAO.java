package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

public class MessageLocalDAO implements MessageDAO{
	/*Note: Messages will be stored by mId, board, username, date, and text,
	 separated by newlines "\n" */
	
	/*IMPORTANT!! This class was made for windows file-systems.
	 * If you wish to change it for linux, then change the path names in
	 the  methods to linuxpath.*/
	private String linuxpath = "/CSC301/Messages/";
	private String windowspath = "Libraries\\Documents\\Messages\\";
	

	
	@Override
	public Iterator<Message> getMessages(String bid) {
		
		
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
	public Iterator<Message> getMessagesSinceTime(String bid, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMessage(String mid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addMessage(String username, String bid, String text) {
	String MID = getMID();
	Date date = new Date();
	String filename = String.format("%s%s.txt", windowspath, MID);
	Writer writer = null;
	try {
		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename), "utf-8"));
		writer.write(String.format("%d\n", MID));
		writer.write(String.format("%s\n", bid));
		writer.write(String.format("%s\n", username));
		writer.write(String.format("%s\n", date.toString()));
		writer.write(String.format("%s\n", text));
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		   try {writer.close();} catch (Exception ex) {}
	}
	
	return 0;
	}
	
	/*PRIVATE FUNCTIONS*/
	//Updates the MID that the next post will have.
	private int incrementMID(int oldMID){
		String filename = String.format("%s/mId.txt", windowspath);
		Writer writer = null;
		
		try {
			writer = new PrintWriter(filename);
			writer.write(String.format("%d", oldMID));
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			   try {writer.close();} catch (Exception ex) {}
		}
		return 1;
	}
	//Gets the next MID.
	private String getMID(){
		BufferedReader reader = null;
		String filename = String.format("%s/mId.txt", windowspath);
		int mid = 1;
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line = null;
			line = reader.readLine();
			if (line != null){ //null = first message.
				mid = Integer.parseInt(line);
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			   try {reader.close();} catch (Exception ex) {}
		}
		incrementMID(mid);
		return String.valueOf(mid);
		
	}
}
