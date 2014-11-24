package local;

import interfaces.SubscriptionDAOInterface;

import java.util.Iterator;

import model.*;

public class SubscriptionDAO implements SubscriptionDAOInterface{

	@Override
	public Iterator<String> getSubscriptionsByBoard(String bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Board> getBoardsBySubscriber(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int subUserToBoard(String user, String bid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unSubUserFromBoard(String user, String bid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
