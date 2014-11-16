package main;

import DAOlocals.*;
import java.util.Iterator;



public class User {
	private String username;
	private String password;
	private Board currentBoard;
	private UserLocalDAO userLocalDAO;
	private BoardDAO BoardDAO;

	// The builder is at the bottom.
	private User(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.currentBoard = builder.currentBoard;
		this.BoardDAO = new BoardDAO();
		this.userLocalDAO = new UserLocalDAO();
	}

	/* GETTERS */
	public String getUsername() {
		return this.username;
	}

	public Board getcurrentBoard() {
		return this.currentBoard;
	}


	/* METHODS */
	// Does not need to be implemented for now.
	public int postTo(String bid, String text) {
		BoardDAO BDAO = new BoardDAO();
		Board B = BDAO.getBoard(bid);
		return B.postMessage(this, text);
	}

	// posts to currentBoard.
	public int post(String text) {
		if (currentBoard != null) {
			return postTo(currentBoard.getBid(), text);
		}
		return 1;
	}

	// joins a board
	public int joinBoard(String bid) {
		int status = 0; // assume we fail
		Board current;
		Iterator<Board> allBoards = BoardDAO.getAllBoards();
		while (allBoards.hasNext()) {
			current = allBoards.next();
			if (current.getBid().equals(bid)) {
				this.currentBoard = current;
				status = 1;
			}
		}
		return status;
	}

	// leaves currentBoard. cannot leave if not joined to a board
	public int leaveBoard() {
		int status = 0; // assume it's false
		if (this.currentBoard != null) {
			this.currentBoard = null;
			status = 1;
		}
		return status;
	}

	// deletes current user from DAO and then exits program
	public int deleteUser() {
		return userLocalDAO.deleteUser(this);
	}

	public String getPassword() {
		return this.password;
	}

	// all getters and setters omitted for now
	public static class Builder {
		private String username;
		private String password;
		private Board currentBoard = null; // defaults to null.

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


		public User build() {
			return new User(this);
		}
	}
}
