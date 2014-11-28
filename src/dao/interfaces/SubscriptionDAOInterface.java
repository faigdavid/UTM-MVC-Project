package interfaces;

import java.util.Iterator;

import model.Board;

public interface SubscriptionDAOInterface{
	/**
	 * get all subscribed Users of the board (by bid).
	 * 
	 * @param bid
	 *            String of board id of a board.
	 * @return Iterator<User> all subscribed Users of a given board (by bid)
	 */
	public Iterator<String> getSubscriptionsByBoard(String bid);

	/**
	 * get all boards that a User is subscribed to.
	 * 
	 * @param username
	 *            String of username.
	 * @return Iterator<Board> all Boards that a user is subscribed to.
	 */
	public Iterator<Board> getBoardsBySubscriber(String username);

	/**
	 * subscribe user to given board
	 * 
	 * @param user
	 *            User object who's subscribing.
	 * @param board
	 *            Board object that a user is subscribing.
	 * @return 0 on success, -1 on error
	 */
	public int subUserToBoard(String user, String bid);

	/**
	 * unsubscribe user from given board
	 * 
	 * @param user
	 *            User object who's unsubscribing.
	 * @param board
	 *            Board object that a user is unsubscribing.
	 * @return 1 on success, 0 on error
	 */
	public int unSubUserFromBoard(String user, String bid);

}
