package main;

import java.util.Iterator;

public interface SubscriptionDAO {
	/**
	 * get all subscribed Users of the board (by bid).
	 * 
	 * @param bid
	 *            String of board id of a board.
	 * @return Iterator<User> all subscribed Users of a given board (by bid)
	 */
	public Iterator<User> getSubscriptionsByBoard(String bid);

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
	public int subUserToBoard(User user, Board board);

	/**
	 * unsubscribe user from given board
	 * 
	 * @param user
	 *            User object who's unsubscribing.
	 * @param board
	 *            Board object that a user is unsubscribing.
	 * @return 0 on success, -1 on error
	 */
	public int unSubUserFromBoard(User user, Board board);

}
