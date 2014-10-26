public class User extends UserDAO implements User_DAO {

	public static class Builder {
		private String userName;
		private String userID;
		private UserDAO userDAO;
		
		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}
		public Builder userID(String userID) {
			this.userID = userID;
			return this;
		}
		public Builder userDAO(String userDAO) {
			this.userDAO = userDAO;
			return this;
		}
		public Builder SuperBoardDAO(String SuperBoardDAO) {
			this.SuperBoardDAO = SuperBoardDAO;
			return this;
		}
		public User build() {
			return new User(this);
		}
	}
	
	private String userName;
	private String userID;
	private UserDAO userDAO;
	private SuperBoardDAO superBoardDAO;
	
	private User(Builder builder) {
		this.userName = builder.userName;
		this.userID = builder.userID;
		this.userDAO = builder.userDAO;
		this.SuperBoardDAO = builder.superBoardDAO;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getUserID() {
		return userID;
	}
	
	public void storeUserInformation() {
		userDAO.storeUserInformation(getUserName(), getUserID());
		return null;
	}
	
	public void changeUserInformation() {
		userDAO.changeUserInformation(getUserName(), getUserID());
		return null;	
	}
	
	public void createBoard(String boardName) {
		Board board = new Board(userID, boardName);
		superBoardDAO.createBoard(board);
		return null;
	}
	
	public void writeToBoard(Board board, Message message) {
		board.writeMessage(message);
		return null;
	}
}
