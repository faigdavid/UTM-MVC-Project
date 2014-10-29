//Carlito is responsible for this, don't touch!
package main;

public class User {

	public static class Builder {
		private String username;
		private String password;
		private Board currentBoard;
		private Board homeBoard;
		
		public Builder username(String username) {
			this.username = username;
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
	
	private String username;
	private String password;
	private Board currentBoard;
	private Board homeBoard = null; //for now
	private UserDAO userDAO;
	
	private User(Builder builder) {
		this.userName = builder.userName;
		this.userDAO = new UserLocalDAO();
	}
	
	public String getUserName() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	public Board getCurrentBoard(){
		return this.currentBoard;
	}
	//returns current_board
	public void setUserName(String userName) {
		this.userName = userName;
		return null;
	}

	//int postTo(this, board, string){} //Does not need to be implented atm
	int post(String message) {
		return this.currentBoard.writeMessage(message);
	}
	
	int joinBoard(Board board) {
		int status = -1;
		if(board != null) {
			this.currentBoard = board;
			status = 0;
		}	
		return status;
	}
	
	//joins a board
	
	int leaveBoard(){
		int status = -1;
		if(this.currentBoard != null) {
			this.currentBoard = null;
			status = 0;
		}	
		return status;	
	}
	//leaves a board. cannot leave if not joined to a board

	int deleteUser(){
		return userDAO.deleteUser(this);	
	}
	//deletes current user from DAO and then exits program
}
