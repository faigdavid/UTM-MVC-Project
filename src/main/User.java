package main;

public class User {
	private String username;
	private String password;
	private Board current_board;
	private Board home_board;
	
	//The builder is at the bottom.
	private User(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.current_board = builder.current_board;
		this.home_board = builder.home_board;
	}
	
	/*GETTERS*/
	public String getusername(){
		return this.username;
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
	public int postTo(Board board, String text){
		return 1;
	}
	
	//posts to current_board.
	public int post(String text) {
		return 1;
	}
	
	//joins a board	
	public int joinBoard(String boardId){
		return 1;
	}
		
	//leaves current_board. cannot leave if not joined to a board
	public int leaveBoard(){
		this.current_board = null;
		return 1;
	}
		
	//returns current_board
	public Board getCurrentBoard(){
		return current_board;
	}
		
	//deletes current user from DAO and then exits program	
	public int deleteUser(){
		return 1;
	}
		

	//all getters and setters omitted for now
	public static class Builder {
		private String username;
		private String password;
		private Board current_board = null; //defaults to null.
		private Board home_board = null; //defaults to null.
		
		public Builder username(String username) {
			this.username = username;
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
