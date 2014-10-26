package main;

public class Authenticator {
	private String inputName;
	private String inputPassword;
	private UserDAO userDAO;
	
	public Authenticator(String inputName, String inputPassword) {
		this.inputName = inputName;
		this.inputPassword = inputPassword;
		this.userDAO = new UserDAO();
	}
	
	public boolean Authenticate {
		boolean isAuthenticated = false; //assume false
		if(userDAO.getUserInformation() != null) {
			isAuthentcated = true;
		}
		return isAuthenticated;
	}
}
