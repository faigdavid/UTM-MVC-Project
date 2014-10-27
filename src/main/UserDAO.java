package main;

public interface UserDAO{

	public User GetUser(String Username);

	public User SaveUser(User user);

	public User CreateUser(User user);

	public int deleteUser(User user);
}
