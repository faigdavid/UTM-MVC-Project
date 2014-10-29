package main;

public class User {
	private String userName;
	private String password;
	private Board currentBoard;
	private Board homeBoard;
	private UserLocalDAO userLocalDAO;
	private BoardLocalDAO boardLocalDAO;
	
	//The builder is at the bottom.
	private User(Builder builder) {
		this.userName = builder.userName;
		this.password = builder.password;
		this.currentBoard = builder.currentBoard;
		this.homeBoard = builder.homeBoard;
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
	public Board getcurrentBoard(){
		return this.currentBoard;
	}
	public Board gethomeBoard(){
		return this.homeBoard;
	}
	
	/*METHODS*/
	public int postTo(String bid, String text){
		Board board = boardLocalDAO.GetBoard(bid);
		return board.postMessage(this, text);
	}
	
	//posts to currentBoard.
	public int post(String text) {
		return 1;
	}
	
	//joins a board	
	public int joinBoard(String boardID) {
		int status = 1; //assume we fail
		Board current;
		while(boardLocalDAO.getAllBoards().hasNext()) {
			current = boardLocalDAO.getAllBoards().next();
			if(current.getBID() == boardID) {
				this.currentBoard = current;
				status = 0;
			}
		}
		return status;
	}
		
	//leaves currentBoard. cannot leave if not joined to a board
	public int leaveBoard(){
		int status = 1; //assume it's false
		if(this.currentBoard != null) {
			this.currentBoard = null;
			status = 0;
		}
		return status;
	}
		
	//deletes current user from DAO and then exits program	
	public int deleteUser(){
		return userLocalDAO.deleteUser(this);
	}
		

	//all getters and setters omitted for now
	public static class Builder {
		private String userName;
		private String password;
		private Board currentBoard = null; //defaults to null.
		private Board homeBoard = null; //defaults to null.
		
		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder currentBoard(Board currentBoard) {
			this.currentBoard = currentBoard;
			return this;
		}
		
		public Builder homeBoard(Board homeBoard) {
			this.homeBoard = homeBoard;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
}
