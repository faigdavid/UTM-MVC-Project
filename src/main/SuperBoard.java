package main;

import java.util.ArrayList;
import java.util.Iterator;

public class SuperBoard{
	ArrayList<Board> Superboard_ArrayList = new ArrayList<Board>();
	Iterator<Board> i = Superboard_ArrayList.iterator(); 
	
	private SuperBoard(ArrayList<Board> Superboard_ArrayList) {
		this.Superboard_ArrayList = Superboard_ArrayList;
	}

	public Iterator<Board> getIterator() {
		return this.i;
	}
}