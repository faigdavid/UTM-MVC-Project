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
	private DBConnection dbc = new DBConnection();
	private Connection con;
	private PreparedStatement pstmt = null; // use prepared statement
	public BoardDAO()
	{
		con = dbc.getConnection();
	}
	@Override
	public Board getBoard(String bid) {
		Board.Builder boardbuild = new Board.Builder();
		try {
			PreparedStatement pstmt = null; // use prepared statement
			String sqlText = "SELECT * FROM boards WHERE bid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setInt(1, Integer.parseInt(bid));
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
							.password(passwd).topic(rs.getString("topic"));
				} else {
					return null;
				}
			} else {
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
							.password(passwd).topic(rs.getString("topic"));
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return null;
		}
		return boardbuild.build();
	}
	@Override
	public int createBoard(String name, String topic) {
		Board board = this.getBoardByName(name);
		if (board != null) { // make sure same board does not exist
			return 0;
		}
		try {
			String sqlText = "INSERT INTO " + "boards (name, topic) " + "VALUES (?, ?)";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, name);
			pstmt.setString(2, topic);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
		return 1;
	}
	
	@Override
	public int createBoard(String name) {
		Board board = this.getBoardByName(name);
		if (board != null) { // make sure same board does not exist
			return 0;
		}
		try {
			String sqlText = "INSERT INTO " + "boards (name) " + "VALUES (?)";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteBoard(Board board) {
		try {
			String sqlText = "DELETE FROM boards WHERE bid = ?";
			pstmt = con.prepareStatement(sqlText);
			pstmt.setString(1, board.getBid());
			pstmt.executeUpdate();
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
							.password(passwd).topic(rs.getString("topic")).build();
					boards.add(brd);
				}
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return boards.iterator();
	}
	//returns all boards that match all tags given in the array list
	public Iterator<Board> getAllBoardsByTags(ArrayList<String> tags){
		if (tags.get(0).equals("")){
			return getAllBoards();
		}
		int count = 1;
		ArrayList<Board> boards = new ArrayList<Board>();
		try {
			
			String sqlText = "SELECT DISTINCT bid, name FROM tags "
					+ "NATURAL JOIN boards WHERE tag = ? ";
			
			for(int i = 2 ; i <= tags.size() ; i++){
				sqlText = sqlText+"INTERSECT SELECT DISTINCT bid, name"
						+ " FROM tags NATURAL JOIN boards WHERE tag = ? ";
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
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return boards.iterator();
	}

	
	public int addTags(ArrayList<String> tags, String bid) {
		//see super for implementation details
		PreparedStatement pstmt = null;
		try {
			for(String s : tags){
	
				String sqlText = "INSERT INTO " + "tags (bid, tag) "
						+ "VALUES (?, ?)";
				pstmt = con.prepareStatement(sqlText);
				//casts the bid to an integer
				pstmt.setInt(1, Integer.parseInt(bid));
				pstmt.setString(2, s);
				pstmt.executeUpdate();
				//Make it execute the statement for each tag.
			}
		}catch(Exception e){
			System.err.println("Exception: " + e.getMessage());
		}
		
		return 1;
	}
	public int changeTopic(String bid, String topic) {
		//see super for implementation details
		try {
			String sqlText = "UPDATE boards SET topic = ?";
			pstmt = con.prepareStatement(sqlText);
			//casts the bid to an integer
			pstmt.setString(1,topic);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.err.println("Exception: " + e.getMessage());
		}
		
		return 1;
	}

}
