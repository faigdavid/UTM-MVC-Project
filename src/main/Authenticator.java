package main;

public class Authenticator {
	/**
	 * Authenticates a user with username and password in the Authenticator
	 * object
	 * 
	 * @return User object with given username and password; null if
	 *         authenticate fails.
	 */
	public User Authenticate(String username, String password) {
		UserDAO udao = new UserLocalDAO();

		User actualUser = udao.GetUser(username);

		if (actualUser != null) {
			if (password.equals(actualUser.getPassword())) {
				return actualUser;
			}
		}
		return null;
	}
}
