package local;

import interfaces.BoardDAOInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import model.Board;

public class BoardDAO implements BoardDAOInterface {
	private String linuxPath = "/student/ekelundh/git/Proj-UTM-Team6-repo/src/database/BOARDS/";
	private String dimitriPath = (System.getProperty("user.dir")+"/src/database/BOARDS/");
	private String defaultPath = dimitriPath;
	
	@Override
	public Board getBoard(String bid) {
		BufferedReader reader = null;
		String filename = dimitriPath +bid+ ".txt";
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
	public int createBoard(String name) {
		// TODO Auto-generated method stub
		String BID = getBID();
		incrementBID();
		String filename = String.format("%s%s.txt", defaultPath, BID);
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "utf-8"));
			writer.write(String.format("%s\n", name));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			   try {writer.close();} catch (Exception ex) {}
		}
		
		return 1;
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
	private int incrementBID(){
		String filename = String.format("%sbId.txt", defaultPath);
		Writer writer = null;
		String newBid;
		newBid = Integer.toString(Integer.parseInt(getBID()) + 1);
		try {
			writer = new PrintWriter(filename);
			writer.write(String.format("%s", newBid));
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			   try {writer.close();} catch (Exception ex) {}
		}
		return 1;
	}
	//Gets the next MID. Increments the MID right after.
	private String getBID(){
		BufferedReader reader = null;
		String filename = String.format("%sbId.txt", defaultPath);
		int bid = 1;
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line = null;
			line = reader.readLine();
			if (line != null){ //null = first message.
				bid = Integer.parseInt(line);
			}
		}catch (IOException e) {
			
		} finally {
			   try {reader.close();} catch (Exception ex) {}
		}
		return String.valueOf(bid);
		
	}
}
