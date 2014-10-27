package main;

import java.util.Iterator;

public class SubscriptionLocalDAO implements SubscriptionDAO{

	@Override
	public Iterator<User> getSubscriptionsByBoard(String bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Board> getBoardsBySubscriber(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int subUserToBoard(User user, Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unSubUserFromBoard(User user, Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
