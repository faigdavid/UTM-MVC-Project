package main;

import java.util.Iterator;

public interface BoardDAO {
	/**
	 * get Board object with given bid from the database.
	 * 
	 * @param bid
	 *            String of board id.
	 * @return Board object with given bid.
	 */
	public Board GetBoard(String bid);

	/**
	 * create given board on the database.
	 * 
	 * @param board
	 *            Board object that will be created on the database.
	 * @return Board object that got created; null on error.
	 */
	public Board CreateBoard(Board board);

	/**
	 * delete given board from the database.
	 * 
	 * @param board
	 *            Board object that will be deleted.
	 * @return 0 on success, -1 on error.
	 */
	public int deleteBoard(Board board);

	/**
	 * get all Board objects in the database.
	 * 
	 * @return Iterator<Board> all the boards in the database.
	 */
	public Iterator<Board> getAllBoards();
}