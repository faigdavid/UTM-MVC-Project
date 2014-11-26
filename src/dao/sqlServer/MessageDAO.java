package sqlServer;

import interfaces.MessageDAOInterface;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import model.Message;

public class MessageDAO implements MessageDAOInterface {
	private DBConnection dbc = null;
	private Connection con = null;
	private PreparedStatement pstmt = null; // use prepared statement
	public MessageDAO()
	{
		dbc =  new DBConnection();
		con = dbc.getConnection();
	}
	@Override
	public Iterator<Message> getMessages(String bid) {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			String sqlText = "SELECT * FROM messages WHERE bid = ? ORDER BY mid";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, bid);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Message msg = getMsgBuilderFromRS(rs).build();
					messages.add(msg);
				}
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return messages.iterator();
	}

	@Override
	public Message getMessage(String bid, String mid) {
		Message.Builder msgbuild = new Message.Builder();
		try {

			String sqlText = "SELECT * FROM messages WHERE bid = ? AND mid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, bid);
			pstmt.setString(2, mid);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				msgbuild = getMsgBuilderFromRS(rs);
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return msgbuild.build();
	}

	@Override
	public Iterator<Message> getMessagesSinceTime(String bid, String date) {
		// String date must be in postgreSQL timestamp format in EST (i.e.
		// "yyyy-MM-dd HH:mm:ss")

		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			String sqlText = "SELECT * FROM messages WHERE date_posted > ? ORDER BY mid";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, date);
			ResultSet rs = pstmt.executeQuery(sqlText);

			if (rs != null) {
				while (rs.next()) {
					Message msg = getMsgBuilderFromRS(rs).build();
					messages.add(msg);
				}
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		return messages.iterator();
	}

	@Override
	public int deleteMessage(String mid) {
		try {
			String sqlText = "DELETE FROM messages WHERE mid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
		return 1; // **** WHAT DO I RETURN ON SUCCESS?
	}

	@Override
	public int addMessage(String bid, String username, String text) {

		// get current time in postgreSQL timestamp format
		try {

			String sqlText = "INSERT INTO "
					+ "messages (bid, username, contents) "
					+ "VALUES (?, ?, ?)";
			pstmt = con.prepareStatement(sqlText);
			int bidi = Integer.parseInt(bid);
			pstmt.setInt(1, bidi);
			pstmt.setString(2, username);
			pstmt.setString(3, text);

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 1; // **** WHAT DO I RETURN ON SUCCESS?
	}

	// ========== PRIVATE METHODS
	private Message.Builder getMsgBuilderFromRS(ResultSet rs)
			throws SQLException {
		return new Message.Builder().setBid(rs.getString("bid"))
				.setusername(rs.getString("username"))
				.setDate(rs.getString("date_posted"))
				.setText(rs.getString("contents")).setMid(rs.getString("mid"));
	}
}
