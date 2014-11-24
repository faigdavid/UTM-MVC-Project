package sqlServer;

import interfaces.MessageDAOInterface;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import model.Message;

public class MessageDAO implements MessageDAOInterface {

	@Override
	public Iterator<Message> getMessages(String bid) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			Statement sql = con.createStatement();

			String sqlText = "SELECT * FROM messages ORDER BY mid";
			ResultSet rs = sql.executeQuery(sqlText);

			if (rs != null) {
				while (rs.next()) {
					Message msg = new Message.Builder()
							.setBid(rs.getString("bid"))
							.setusername(rs.getString("username"))
							.setDate(rs.getString("date_posted"))
							.setText(rs.getString("contents"))
							.setMid(rs.getString("mid")).build();
					messages.add(msg);
				}
			}
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return messages.iterator();
	}

	@Override
	public Message getMessage(String bid, String mid) {
		Message.Builder msgbuild = new Message.Builder();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "SELECT * FROM messages WHERE bid = ? AND mid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, bid);
			pstmt.setString(2, mid);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				msgbuild.setBid(rs.getString("bid"))
						.setusername(rs.getString("username"))
						.setDate(rs.getString("date_posted"))
						.setText(rs.getString("contents")).setMid(mid);
				dbc.disconnect();
			} else {
				dbc.disconnect();
				return null;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return msgbuild.build();
	}

	@Override
	public Iterator<Message> getMessagesSinceTime(String bid, String date) {
		// String date must be in postgreSQL timestamp format (i.e.
		// "yyyy-MM-dd HH:mm:ss")

		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "SELECT mid FROM messages WHERE date_posted > ? ORDER BY mid";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, date);
			ResultSet rs = pstmt.executeQuery(sqlText);

			if (rs != null) {
				while (rs.next()) {
					Message msg = getMessage(bid, rs.getString("mid"));
					messages.add(msg);
				}
			}
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		return messages.iterator();
	}

	@Override
	public int deleteMessage(String mid) {
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "DELETE FROM messages WHERE mid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
		return 1; // **** WHAT DO I RETURN ON SUCCESS?
	}

	@Override
	public int addMessage(String username, String bid, String text) {

		// get current time in postgreSQL timestamp format
		String psqlTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "INSERT INTO "
					+ "messages (bid, date_posted, username, contents) "
					+ "VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, bid);
			pstmt.setString(2, psqlTimestamp);
			pstmt.setString(3, username);
			pstmt.setString(4, text);

			pstmt.executeUpdate();
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
		return 1; // **** WHAT DO I RETURN ON SUCCESS?
	}

}
