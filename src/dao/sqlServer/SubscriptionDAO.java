package sqlServer;

import interfaces.SubscriptionDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import model.Board;

public class SubscriptionDAO implements SubscriptionDAOInterface {
	private DBConnection dbc = null;
	private Connection con = null;
	private PreparedStatement pstmt = null; // use prepared statement

	public SubscriptionDAO() {
		dbc = DBConnection.getInstance();
		con = dbc.getConnection();
	}

	@Override
	// change this function name to getSubscribersByBoard(String bid)
	public Iterator<String> getSubscriptionsByBoard(String username) {
		ArrayList<String> users = new ArrayList<String>();

		try {
			String sqlText = "SELECT username FROM subscriptions WHERE bid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(rs.getString("username"));
			}
			;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return users.iterator();
	}

	@Override
	public Iterator<Board> getBoardsBySubscriber(String username) {

		ArrayList<Board> boards = new ArrayList<Board>();

		try {
			String sqlText = "SELECT bid FROM subscriptions WHERE username = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			BoardDAO dao = new BoardDAO();
			while (rs.next()) {
				boards.add(dao.getBoard(rs.getString("bid")));

			}
			;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return boards.iterator();
	}

	@Override
	public int subUserToBoard(String user, String bid) {

		try {

			String sqlText = "INSERT INTO subscriptions "
					+ " (username, bid) VALUES (? , ?)";

			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, user);
			pstmt.setInt(2, Integer.parseInt(bid));
			if (pstmt.execute()) {

				return 1;
			}
			;
			return 0;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}

	}

	@Override
	public int unSubUserFromBoard(String user, String bid) {

		try {
			String sqlText = "DELETE FROM subscriptions WHERE username = ?, "
					+ "bid = ?";

			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, user);
			pstmt.setInt(2, Integer.parseInt(bid));
			if (pstmt.execute()) {
				return 1;
			}
			;
			return 0;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}

	}

}
