package main;

public class User {
	private String userName;
	private String password;
	private Board current_board;
	private Board home_board;
	private UserLocalDAO userLocalDAO;
	private BoardLocalDAO boardLocalDAO;
	
	//The builder is at the bottom.
	private User(Builder builder) {
		this.userName = builder.userName;
		this.password = builder.password;
		this.current_board = builder.current_board;
		this.home_board = builder.home_board;
		this.boardLocalDAO = new BoardLocalDAO();
		this.userLocalDAO = new UserLocalDAO();
	}
	
	/*GETTERS*/
	public String getuserName(){
		return this.userName;
	}
	public String getpassword(){
		return this.password;
	}
	public Board getcurrent_board(){
		return this.current_board;
	}
	public Board gethome_board(){
		return this.home_board;
	}
	
	/*METHODS*/
	//Does not need to be implemented for now.
public int postTo(int bid, String text){
BoardLocalDAO BDAO = new BoardLocalDAO();
Board B = BDAO.getBoard(bid);
return B.postMessage(this, text);
}
//posts to current_board.
public int post(String text) {
return postTo(current_board.getBID(), text);
}

	
	//joins a board	
	public int joinBoard(String boardID) {
		int status = 1; //assume we fail
		Board current;
		while(boardLocalDAO.getAllBoards().hasNext()) {
			current = boardLocalDAO.getAllBoards().next();
			if(current.getBID() == boardID) {
				this.current_board = current;
				status = 0;
			}
		}
		return status;
	}
		
	//leaves current_board. cannot leave if not joined to a board
	public int leaveBoard(){
		int status = 1; //assume it's false
		if(this.current_board != null) {
			this.current_board = null;
			status = 0;
		}
		return status;
	}
		
	//deletes current user from DAO and then exits program	
	public int deleteUser(){
		return userLocalDAO.deleteUser(this);
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}

	//all getters and setters omitted for now
	public static class Builder {
		private String userName;
		private String password;
		private Board current_board = null; //defaults to null.
		private Board home_board = null; //defaults to null.
		
		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder current_board(Board current_board) {
			this.current_board = current_board;
			return this;
		}
		
		public Builder home_board(Board home_board) {
			this.home_board = home_board;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
}
