package main;

import java.util.ArrayList;
import java.util.Date;

public class Message {
	private String mid;
	private String username;
	private String bid;
	private String text;
	private String date;
	private ArrayList<String> whitelist;
	
	/*IMPORTANT: The builder cannot make a date or mid, since those
	 are pre-determined for each message.*/
	private Message(Builder builder){
		this.mid = builder.mid;
		this.date = builder.date;
		this.username = builder.username;
		this.bid = builder.bid;
		this.text = builder.text;
		this.whitelist = builder.whitelist;
	}
	
	public String printMessage(String username) {
		String msg = null;
		msg = String.format("[%s][%s][%s][%s][%s]",
				mid, bid, username, date, text);
		
		if(whitelist == null){ //No whitelist == public message.
			return msg;
		}else{
			for (String name : whitelist){
				if (name == username){
					return msg;
				}
			}
		}
		return "You do not have permission to see this message.";
	}

	public String getmid(){
		return mid;
	}
	public String getusername() {
		return username;
	}
	public String getBid() {
		return bid;
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
	//calling setmid(String) is not required. The DAO will handle that.
	public static class Builder{
		private String mid = "-1"; //Gets changed by the DAO.
		private String username;
		private String bid;
		private String text;
		private String date;
		private ArrayList<String> whitelist;
		
		public Builder setMid(String mid){
			this.mid = mid;
			return this;
		}
		
		public Builder setusername(String username){
			this.username = username;
			return this;
		}
		
		public Builder setBid(String Bid){
			this.bid = Bid;
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

