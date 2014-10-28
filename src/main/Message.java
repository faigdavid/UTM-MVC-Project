package main;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Message {
	private String username;
	private String boardId;
	private String text;
	private Timestamp date;
	private ArrayList<String> whitelist;
	
	private Message(Builder builder){
		this.username = builder.username;
		this.boardId = builder.boardId;
		this.text = builder.text;
		this.date = builder.date;
		this.whitelist = builder.whitelist;
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
	public Timestamp getDate() {
		return date;
	}
	
	public void setText(String contents){
		//used for exiting a message
		this.text = contents;
		return;
	}

	//make sure to check variable names with actual implementation
	public static class Builder{
		private String username;
		private String boardId;
		private String text;
		private Timestamp date;
		private ArrayList<String> whitelist;
		
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
		
		public Builder setDate(Timestamp date){
			this.date = date;
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

