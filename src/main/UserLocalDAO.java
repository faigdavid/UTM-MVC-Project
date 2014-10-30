package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserLocalDAO implements UserDAO {
	//this string should work on my computer
	private String linuxPath = "/student/ekelundh/git/Proj-UTM-Team6-repo/src/database/USERS/";
	@Override
	public User getUser(String username) {
		String password;
		BufferedReader reader = null;
		String filename = linuxPath + username;
		
		try{
			reader = new BufferedReader(new FileReader(filename));
			password = reader.readLine();
			
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			   try {reader.close();} catch (Exception ex) {}
		}
		return new User.Builder().password(password).username(username).build();
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		//needs to create files in the format username.txt
		return null;
	}

	@Override
	public User createUser(String username, String password) {
	
		User user = this.getUser(username);
		if(user != null){
			return null;
	
		}
		user = new User.Builder().password(password).username(username).build();
		this.saveUser(user);
		return user;
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
