package sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import interfaces.UserDAOInterface;

public class UserDAO implements UserDAOInterface {
	private DBConnection dbc = null;
	private Connection con = null;
	private PreparedStatement pstmt = null; // use prepared statement
	public UserDAO()
	{
		dbc =  new DBConnection();
		con = dbc.getConnection();
	}
	
	@Override
	public User getUser(String username) {
		User.Builder usrbuild = new User.Builder();
		try {
			
			String sqlText = "SELECT username, passwd FROM users WHERE "
					+ "username = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					usrbuild.password(rs.getString("passwd"));
					usrbuild.username(rs.getString("username"));
					usrbuild.currentBoard(null);
				} else { // user does not exist
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("Get Exception: " + e.getMessage());
		}
		return usrbuild.build();
	}

	@Override
	public int saveUser(User user) {
		try {
			String sqlText = "UPDATE users SET username = ?, passwd = ? WHERE "
					+ "username = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			if (pstmt.execute()) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
	}

	@Override
	public User createUser(String username, String password) {
		User user = this.getUser(username);
		if (user != null) {
			return null;
		}
		user = new User.Builder().password(password).username(username).build();
		try {
			String sqlText = "INSERT INTO users (username, passwd) VALUES"
					+ "(?, ?)";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
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
