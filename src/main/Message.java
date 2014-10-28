package main;

import java.util.ArrayList;
import java.util.Date;

public class Message {
	private String mId;
	private String username;
	private String boardId;
	private String text;
	private String date;
	private ArrayList<String> whitelist;
	
	/*IMPORTANT: The builder cannot make a date or mId, since those
	 are pre-determined for each message.*/
	private Message(Builder builder){
		this.mId = builder.mId;
		this.date = builder.date;
		this.username = builder.username;
		this.boardId = builder.boardId;
		this.text = builder.text;
		this.whitelist = builder.whitelist;
	}
	
	public String printMessage(User user) {
		String msg = null;
		msg = String.format("[%s][%s][%s][%s][%s]",
				mId, boardId, username, date, text);
		
		if(whitelist == null){ //No whitelist == public message.
			return msg;
		}else{
			for (String username : whitelist){
				if (username == user.getusername()){
					return msg;
				}
			}
		}
		return "You do not have permission to see this message.";
	}
	public String getmId(){
		return mId;
	}
	public String getusername() {
		return username;
	}
	public String getboardId() {
		return boardId;
	}
	public String getText() {
		return text;
	}
	/*Changed this.Date is now a string. */
	public String getDate() {
		return date;
	}
	
	public void setText(String contents){
		//used for editing a message
		this.text = contents;
		return;
	}

	//make sure to check variable names with actual implementation
	public static class Builder{
		private String mId;
		private String username;
		private String boardId;
		private String text;
		private String date;
		private ArrayList<String> whitelist;
		
		public Builder setmId(String mid){
			this.mId = mid;
			return this;
		}
		public Builder setusername(String username){
			this.username = username;
			return this;
		}
		
		public Builder setBoardId(String boardId){
			this.boardId = boardId;
			return this;
		}
		
		public Builder setText(String text){
			this.text = text;
			return this;
		}
		public Builder setDate(String date){
			this.text = date;
			return this;
		}
		
		public Builder setWhitelist(ArrayList<String> list){
			this.whitelist = list;
			return this;
		}
		public Message build(){
			 
			return new Message(this);
		}
		
	}
}

