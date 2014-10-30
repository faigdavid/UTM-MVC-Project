package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserLocalDAO implements UserDAO {
	//this string should work on my computer
	private String linuxPath = "/student/ekelundh/git/Proj-UTM-Team6-repo/src/database/USERS";
	@Override
	public User getUser(String username) {
		
		BufferedReader reader = null;
		String filename = String.format(linuxPath + username);
		
		try{
			reader = new BufferedReader(new FileReader(filename));
			String password = reader.readLine();
			return new User.Builder().password(password).username(username).build();
		}catch (IOException e) {
			return null;
		} finally {
			   try {reader.close();} catch (Exception ex) {}
		}
		 
		
		return user
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
