package main;

import java.util.Iterator;

public interface BoardUserDAOInt{
	public void storeUser(User user);
	public void removeUser(User user);
	public void markUserAdmin(User user);
	public void markUserModerator(User user);
	public void banUser(User user);
	public Iterator<User> getAllUsers();
}
