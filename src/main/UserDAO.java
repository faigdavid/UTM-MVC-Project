package main;

public interface UserDAO {
	/**
	 * Grabs the User object with given username from the database.
	 * 
	 * @param username
	 *            String of username.
	 * @return User object with given username; null if no such user exist.
	 */
	public User getUser(String username);

	/**
	 * Save User object to the database.
	 * 
	 * @param user
	 *            User object to be saved.
	 * @return User object that got saved; null on error.
	 */
	public int saveUser(User user);

	/**
	 * Create User object in the database with given username and password.
	 * Check whether there is any User with given username already exists
	 * 
	 * @param username
	 *            String of username of new user.
	 * @param password
	 *            String of password of new user.
	 * @return User object that got saved; null on error(i.e. duplicate or write
	 *         error)
	 */
	public User createUser(String username, String password);

	/**
	 * delete given User object from the database.
	 * 
	 * @param user
	 *            User object that will be deleted.
	 * @return 0 on success, -1 on error.
	 */
	public int deleteUser(User user);
}
