package interfaces;

import java.util.ArrayList;
import java.util.Iterator;

import model.Board;

public interface BoardDAOInterface {
	/**
	 * get Board object with given bid from the database.
	 * 
	 * @param bid
	 *            String of board id.
	 * @return Board object with given bid.
	 */
	public Board getBoard(String bid);

	/**
	 * create given board on the database.
	 * 
	 * @param board
	 *            Board object that will be created on the database.
	 * @return Board object that got created; null on error.
	 */
	public Board getBoardByName(String name);
	
	public int createBoard(String name);
	public int createBoard(String name, String topic);
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
	/**
	 * 
	 * returns all boards in an Iterator
	 * @return the iterator of all boards
	 */

	public int addTags(ArrayList<String> tags, String bid);
	/**
	 * 
	 * adds all tags in the array list to the board, so we can find
	 * them by that tag.
	 * @return an int: 1 = worked, 0 = failed.
	 */
}
