package sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Message;
import model.User;
import interfaces.UserDAOInterface;

public class UserDAO implements UserDAOInterface {

	@Override
	public User getUser(String username) {
		User.Builder usrbuild = new User.Builder();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "SELECT username, passwd FROM users WHERE "
					+ "username = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				System.out.println("rs not null");
				if (rs.next()) {
					System.out.println("userfound; name: "
							+ rs.getString("username"));
					usrbuild.password(rs.getString("passwd"));
					usrbuild.username(rs.getString("username"));
					usrbuild.currentBoard(null);
				} else { // user does not exist
					dbc.disconnect();
					return null;
				}
			} else {
				dbc.disconnect();
				return null;
			}
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Get Exception: " + e.getMessage());
		}
		return usrbuild.build();
	}

	@Override
	public int saveUser(User user) {
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "UPDATE users SET username = ?, passwd = ? WHERE "
					+ "username = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			if (pstmt.execute()) {
				return 1;
			} else {
				dbc.disconnect();
				return 0;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
	}

	@Override
	public User createUser(String username, String password) {
		System.out.println("createUser in DAO");
		User user = this.getUser(username);
		if (user != null) {
			return null;
		}
		user = new User.Builder().password(password).username(username).build();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "INSERT INTO users (username, passwd) VALUES"
					+ "(?, ?)";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
			System.out.println("user created");
			dbc.disconnect();
			return user;
		} catch (Exception e) {
			System.err.println("Create Exception: " + e.getMessage());
			return null;
		}
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
