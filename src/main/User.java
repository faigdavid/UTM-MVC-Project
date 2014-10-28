+package main;

public class User {
	private String username;
	private String password;
	private Board current_board;
	private Board home_board;
	
	public static class Builder {
		private String username;
		private String password;
		private Board current_board;
		private Board home_board;
		
		public Builder username(String username) {
		}
		
		public Builder password(String password) {
		}
		
		public Builder current_board(Board current_board) {
		}
		
		public Builder home_board(Board home_board) {
		}
		
		public User build() {
			return new User(this);
		}
	}
	
	
	private User(Builder builder) {
	}

	int postTo(this, board, string){} //Does not need to be implented atm
	
	int post(this, string) {}

	int joinBoard(board ID){}
		//joins a board	
	
	int leaveBoard(board ID){}
		//leaves a board. cannot leave if not joined to a board

	Board getCurrentBoard(){}
		//returns current_board
		
	int deleteUser(){}
		//deletes current user from DAO and then exits program

	//all getters and setters ommitted for now
}
