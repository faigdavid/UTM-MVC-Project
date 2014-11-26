package model;

import sqlServer.*;

public class Board {
	private String bid;
	private String name;
	private String password;
	private String topic = null;
	
	public Board(String id, String name, String password) {
		this.bid = id;
		this.name = name;
		this.password = password;
	}

	private Board(Builder builder) {
		this.bid = builder.bid;
		this.name = builder.name;
		this.password = builder.password;
		this.setTopic(builder.topic);
	}

	// Deletes a message
	public int deleteMessage(String mid) {
		MessageDAO mdao = new MessageDAO();
		Message msg = mdao.getMessage(this.bid, mid);
		return mdao.deleteMessage(msg.getMid());
	}

	public int postMessage(User user, String text) {
		// david is responsible for making sure this works
		return new MessageDAO().addMessage(this.getBid(), user.getUsername(),
				text);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBid() {
		return bid;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public static class Builder {
		private String topic;
		private String bid;
		private String name;
		private String password; // defaults to null.

		public Builder bid(String bid) {
			this.bid = bid;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder topic(String topic){
			this.topic = topic;
			return this;
		}
		
		public Board build() {
			return new Board(this);
		}
		

	}
}
