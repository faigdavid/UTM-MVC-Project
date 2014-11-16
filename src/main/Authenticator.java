package main;

import DAOinterfaces.UserDAO;
import DAOlocals.UserLocalDAO;

public class Authenticator {
	/**
	 * Authenticates a user with username and password in the Authenticator
	 * object
	 * 
	 * @return User object with given username and password; null if
	 *         authenticate fails.
	 */
	public User authenticateUser(String username, String password) {
		UserDAO udao = new UserLocalDAO();

		User actualUser = udao.getUser(username);

		if (actualUser != null) {
			if (password.equals(actualUser.getPassword())) {
				return actualUser;
			}
		}
		return null;
	}
}
