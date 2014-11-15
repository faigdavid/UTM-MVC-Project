package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class BoardLocalDAO implements BoardDAO {
	private String linuxPath = "/student/ekelundh/git/Proj-UTM-Team6-repo/src/database/BOARDS/";
	private String davidsPath = (System.getProperty("user.dir")+"/src/database/BOARDS/");
	@Override
	
	public Board getBoard(String bid) {
		BufferedReader reader = null;
		String filename = davidsPath +bid+ ".txt";
		String name;
		String password = null;
		
		try{
			reader = new BufferedReader(new FileReader(filename));
			name = reader.readLine();
			password = reader.readLine();
			
		}catch (IOException e) {
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
		ArrayList<Board> boards = new ArrayList<Board>();
		
		for (int i=1;i<=10;i++){ //I AM ONLY READING 10 FILES FOR NOW.
			Board b = getBoard(Integer.toString(i));
			if (b != null){
				boards.add(b);
			}
		}
		Iterator<Board> allboards = boards.iterator();
		return allboards;
	}
	
	@Override
	public Board getBoardByName(String name){
		Iterator<Board> boards = getAllBoards();
		Board toJoin = null;
		while (boards.hasNext()){
			Board temp = boards.next();
			if (temp.getName() == name){
				toJoin = temp;
			}
		}
		return toJoin;
	}

}
