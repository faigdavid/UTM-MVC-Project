package main;

public class Authenticator {
	/**
	 * Authenticates a user with username and password in the Authenticator
	 * object
	 * 
	 * @return true if such user with password exists, false otherwise.
	 */
	public boolean Authenticate(String username,String password) {
		UserDAO udao = new UserLocalDAO();
		boolean isAuthenticated = false; // assume false

		User actualUser = udao.GetUser(username);
		
		if (actualUser != null) {
			if(password.equals(actualUser.getPassword())){
				isAuthenticated = true;
			}
		}
		return isAuthenticated;
	}
}
