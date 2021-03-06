package model;

import java.util.ArrayList;

public class Message {
	private String mid;
	private String username;
	private String bid;
	private String text;
	private String date;
	private ArrayList<String> whitelist;

	/*
	 * IMPORTANT: The builder cannot make a date or mid, since those are
	 * pre-determined for each message.
	 */
	private Message(Builder builder) {
		this.mid = builder.mid;
		this.date = builder.date;
		this.username = builder.username;
		this.bid = builder.bid;
		this.text = builder.text;
		this.whitelist = builder.whitelist;
	}

	public String formatMessage() {
		String msg = null;
		msg = String.format("[%s] [%s]: %s\n", date, this.username, text);

		if (whitelist == null) { // No whitelist == public message.
			return msg; // NOTE: all messages are public... for now.
		}
		return "<Permission Denied>";
	}

	public String getMid() {
		return mid;
	}

	public String getUsername() {
		return username;
	}

	public String getBid() {
		return bid;
	}

	public String getText() {
		return text;
	}

	/* Changed this.Date is now a string. */
	public String getDate() {
		return date;
	}

	public void setText(String contents) {
		// used for editing a message
		this.text = contents;
		return;
	}

	// make sure to check variable names with actual implementation
	// calling setmid(String) is not required. The DAO will handle that.
	public static class Builder {
		private String mid = "-1"; // Gets changed by the DAO.
		private String username;
		private String bid;
		private String text;
		private String date;
		private ArrayList<String> whitelist;

		public Builder setMid(String mid) {
			this.mid = mid;
			return this;
		}

		public Builder setusername(String username) {
			this.username = username;
			return this;
		}

		public Builder setBid(String Bid) {
			this.bid = Bid;
			return this;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}

		public Builder setDate(String date) {
			this.date = date;
			return this;
		}

		public Builder setWhitelist(ArrayList<String> list) {
			this.whitelist = list;
			return this;
		}

		public Message build() {

			return new Message(this);
		}

	}
}
