package main;
public interface BoardUser_DAO {
	public void storeUser(User user);
	public void removeUser(User user);
	public void markUserAdmin(User user);
	public void markUserModerator(User user);
	public void banUser(User user);
	public Iterator<User> getAllUsers();
}

public class BoardUserDAO implements BoardUser_DAO {

}
