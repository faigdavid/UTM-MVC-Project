package main;

import java.util.Iterator;

public interface SubscriptionDAO{
	public Iterator<User> getSubscriptionsByBoard(String BID);

	public Iterator<Board> getBoardsBySubscriber(String username);

	public int subUserToBoard(User user, Board board);

	public int unSubUserFromBoard(User user,Board board);
	
}
