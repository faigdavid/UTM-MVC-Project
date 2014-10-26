package main;

import java.util.Iterator;

public interface SuperBoardDAOInt {
	public void storeBoard(Board board);
	public void removeBoard(Board board);
	public Iterator<Board> getAllBoards();
}
