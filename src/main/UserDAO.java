package main;

public interface UserDAO {
	/**
	 * Grabs the User object with given username from the database.
	 * 
	 * @param username
	 *            String of username.
	 * @return User object with given username; null if no such user exist.
	 */
	public User GetUser(String username);

	/**
	 * Save User object to the database.
	 * 
	 * @param user
	 *            User object to be saved.
	 * @return User object that got saved; null on error.
	 */
	public User SaveUser(User user);

	/**
	 * Create given User object in the database.
	 * 
	 * @param user
	 *            User object to be created.
	 * @return User object that got saved; null on error.
	 */
	public User CreateUser(User user);

	/**
	 * delete given User object from the database.
	 * 
	 * @param user
	 *            User object that will be deleted.
	 * @return 0 on success, -1 on error.
	 */
	public int deleteUser(User user);
}
