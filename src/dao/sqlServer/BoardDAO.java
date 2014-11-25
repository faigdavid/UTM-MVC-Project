package sqlServer;

import interfaces.BoardDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import model.Board;

public class BoardDAO implements BoardDAOInterface {

	@Override
	public Board getBoard(String bid) {
		Board.Builder boardbuild = new Board.Builder();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "SELECT * FROM boards WHERE bid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, bid);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					boardbuild.bid(rs.getString("bid"))
							.name(rs.getString("name"))
							.password(rs.getString("passwd"));
					dbc.disconnect();
				} else {
					dbc.disconnect();
					return null;
				}
			} else {
				dbc.disconnect();
				return null;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return null;
		}
		return boardbuild.build();
	}

	@Override
	public Board getBoardByName(String name) {
		Board.Builder boardbuild = new Board.Builder();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "SELECT * FROM boards WHERE name = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			String passwd = null;
			if (rs != null) {
				if (rs.next()) {
					try {
					passwd = rs.getString("passwd");
					} catch (Exception e){
						passwd = null;
					}
					boardbuild.bid(rs.getString("bid"))
							.name(rs.getString("name"))
							.password(passwd);
					dbc.disconnect();
				} else {
					dbc.disconnect();
					return null;
				}
			} else {
				dbc.disconnect();
				return null;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return null;
		}
		return boardbuild.build();
	}

	@Override
	public int createBoard(String name) {
		Board board = this.getBoardByName(name);
		if (board != null) { // make sure same board does not exist
			return 0;
		}
		System.out.println("asd123");
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "INSERT INTO " + "boards (name) " + "VALUES (?)";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteBoard(Board board) {
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "DELETE FROM boards WHERE bid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, board.getBid());
			pstmt.executeUpdate();
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
		return 1; // **** WHAT DO I RETURN ON SUCCESS?
	}

	@Override
	public Iterator<Board> getAllBoards() {
		ArrayList<Board> boards = new ArrayList<Board>();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			Statement sql = con.createStatement();

			String sqlText = "SELECT * FROM boards ORDER BY bid";
			ResultSet rs = sql.executeQuery(sqlText);
			String passwd = null;
			if (rs != null) {
				while (rs.next()) {
					try {
						passwd = rs.getString("passwd");
						} catch (Exception e){
							passwd = null;
						}
					Board brd = new Board.Builder().bid(rs.getString("bid"))
							.name(rs.getString("name"))
							.password(passwd).build();
					boards.add(brd);
				}
			}
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return boards.iterator();
	}
	//returns all boards that match all tags given in the array list
	public Iterator<Board> getAllBoardsByTags(ArrayList<String> tags){
		if (tags.size() == 0){
			return getAllBoards();
		}
		int count = 1;
		ArrayList<Board> boards = new ArrayList<Board>();
		try {
			DBConnection dbc = new DBConnection();
			dbc.connect();
			Connection con = dbc.getConnection();
			PreparedStatement pstmt = null;
			
			String sqlText = "SELECT DISTINCT bid FROM tags WHERE tag = ? ";
			
			for(int i = 2 ; i <= tags.size() ; i++){
				sqlText = sqlText + "INTERSECT SELECT DISTINCT bid FROM tags WHERE tag = ? ";
			}
			pstmt = con.prepareStatement(sqlText);
			
			for(String s : tags){
				pstmt.setString(count, s);
				count++;
			}
			ResultSet rs = pstmt.executeQuery();
			String passwd = null;
			if (rs != null) {
				while (rs.next()) {
					try {
						passwd = rs.getString("passwd");
						} catch (Exception e){
							passwd = null;
						}
					Board brd = new Board.Builder().bid(rs.getString("bid"))
							.name(rs.getString("name"))
							.password(passwd).build();
					boards.add(brd);
				}
			}
			dbc.disconnect();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return boards.iterator();
	}
}
