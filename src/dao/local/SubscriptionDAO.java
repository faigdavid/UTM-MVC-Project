package local;

import interfaces.SubscriptionDAOInterface;

import java.util.Iterator;

import chatBoardsApp.*;

public class SubscriptionDAO implements SubscriptionDAOInterface{

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
