package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class BoardLocalDAO implements BoardDAO {
	private String linuxPath = "/student/ekelundh/git/Proj-UTM-Team6-repo/src/database/BOARDS/";
	@Override
	
	public Board getBoard(String bid) {
		BufferedReader reader = null;
		String filename = linuxPath +bid;
		String name;
		String password = null;
		
		try{
			reader = new BufferedReader(new FileReader(filename));
			name = reader.readLine();
			password = reader.readLine();
			
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			   try {reader.close();} catch (Exception ex) {}
		}
		return new Board(bid, name, password);
	

	}

	@Override
	public Board createBoard(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Board> getAllBoards() {
		// TODO Auto-generated method stub
		return null;
	}

}
