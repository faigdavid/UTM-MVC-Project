package main;
import java.sql.Timestamp;

public class Message {
	private String userId;
	private String boardId;
	private String text;
	private Timestamp date;
	
	private Message(Builder builder){
		this.userId = builder.userId;
		this.boardId = builder.boardId;
		this.text = builder.text;
		this.date = builder.date;
	}
	
	
	
	public String getUserId() {
		return userId;
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



	public static class Builder{
		private String userId;
		private String boardId;
		private String text;
		private Timestamp date;
		
		public Builder userId(String userId){
			this.userId = userId;
			return this;
		}
		
		public Builder boardId(String boardId){
			this.boardId = boardId;
			return this;
		}
		
		public Builder text(String text){
			this.text = text;
			return this;
		}
		
		public Builder date(Timestamp date){
			this.date = date;
			return this;
		}
		
		public Message build(){
			// We can validate the arguments, before creating a CanadianAddress instance. 
			return new Message(this);
		}
		
	}
}

