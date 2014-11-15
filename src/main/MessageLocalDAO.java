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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class MessageLocalDAO implements MessageDAO{
	/*Note: Messages will be stored by mId, board, username, date, and text,
	 separated by newlines "\n" */
	
	/*IMPORTANT!! This class was made for windows file-systems.
	 * If you wish to change it for linux, then change the path names in
	 the  methods to linuxpath.*/
	private String linuxpath = "/CSC301/Messages/";
	private String davidsPath = (System.getProperty("user.dir")+"/src/database/messages/");
	private String defaultPath = davidsPath;
	private ArrayList<Message> messages = null;
	private Iterator<Message> allmessages = null;

	@Override
	public Iterator<Message> getMessages(String bid) {
		ArrayList<Message> messages = new ArrayList<Message>();
		
		for (int i=1;i<Integer.parseInt(getMID());i++){
			Message msg = getMessage(bid, Integer.toString(i));
			if (msg != null){
				if (msg.getBid().equals(bid)){
					messages.add(msg);
				}
			}
		}
		Iterator<Message> allmessages = messages.iterator();
		return allmessages;
	}

	@Override
	public Message getMessage(String bid, String mid) {
		String filename = String.format("%s%s%s.txt", defaultPath, bid, mid);
		BufferedReader reader = null;
		Message.Builder msgbuild = new Message.Builder();
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = null;
		    line = reader.readLine();
		    if (line == null){
		    	//Corrupted or deleted message.
		    	return null;
		    }
    		msgbuild.setBid(line);
    		msgbuild.setusername(reader.readLine());
    		msgbuild.setDate(reader.readLine());
    		msgbuild.setText(reader.readLine());
	    	msgbuild.setMid(mid);
		} catch (IOException ex) {
			return null; //Message not found.
		} finally {
		   try {reader.close();} catch (Exception ex) {}
		}
		return msgbuild.build();
	}

	@Override
	public Iterator<Message> getMessagesSinceTime(String bid, String date) {
		//Do in another sprint.
		return null;
	}

	@Override
	public int deleteMessage(String mid) {
		
		return 0;
	}

	@Override
	public int addMessage(String bid, String username, String text) {
	String MID = getMID();
	incrementMID();
	Date date = new Date();
	String filename = String.format("%s%s%s.txt", defaultPath, bid, MID);
	Writer writer = null;
	try {
		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename), "utf-8"));
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
	private int incrementMID(){
		String filename = String.format("%smId.txt", defaultPath);
		Writer writer = null;
		String newMid;
		newMid = Integer.toString(Integer.parseInt(getMID()) + 1);
		try {
			writer = new PrintWriter(filename);
			writer.write(String.format("%s", newMid));
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			   try {writer.close();} catch (Exception ex) {}
		}
		return 1;
	}
	//Gets the next MID. Increments the MID right after.
	private String getMID(){
		BufferedReader reader = null;
		String filename = String.format("%smId.txt", defaultPath);
		int mid = 1;
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line = null;
			line = reader.readLine();
			if (line != null){ //null = first message.
				mid = Integer.parseInt(line);
			}
		}catch (IOException e) {
			
		} finally {
			   try {reader.close();} catch (Exception ex) {}
		}
		return String.valueOf(mid);
		
	}
}
