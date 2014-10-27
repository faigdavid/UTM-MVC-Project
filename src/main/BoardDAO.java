package main;

import java.util.Iterator;

public interface BoardDAO {
	public Board GetBoard(String BID);

	public Board CreateBoard(Board board);

	public int deleteBoard(Board board);

	public Iterator<Board> getAllBoards();
}
