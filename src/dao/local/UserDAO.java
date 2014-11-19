package local;

import interfaces.UserDAOInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import model.*;

public class UserDAO implements UserDAOInterface {
	//this string should work on my computer
	private String linuxPath = "/student/ekelundh/git/Proj-UTM-Team6-repo/src/database/USERS/";
	private String davidsPath = (System.getProperty("user.dir")+"/src/database/USERS/");
	@Override
	public User getUser(String username) {
		String password;
		BufferedReader reader = null;
		String filename = davidsPath + username+ ".txt";
		
		try{
			reader = new BufferedReader(new FileReader(filename));
			password = reader.readLine();
			
		}catch (IOException e) {
			return null;
		} finally {
			   try {reader.close();} catch (Exception ex) {}
		}
		return new User.Builder().password(password).username(username).build();
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		//needs to create files in the format username.txt
		

		String filename = davidsPath + user.getUsername()+ ".txt";
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "utf-8"));
			
			writer.write(String.format("%s\n", user.getPassword()));
			//anything after here can be added to make more information
			// for the user to be made with
	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			   try {writer.close();} catch (Exception ex) {}
		}
		
		
		
		return 0;
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
