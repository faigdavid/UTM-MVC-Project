package DAOsqls;

import java.util.Iterator;

import main.Message;
import DAOinterfaces.MessageDAOInterface;

public class MessageDAO implements MessageDAOInterface {

	@Override
	public Iterator<Message> getMessages(String bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getMessage(String bid, String mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Message> getMessagesSinceTime(String bid, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMessage(String mid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addMessage(String username, String bid, String text) {
		// TODO Auto-generated method stub
		return 0;
	}

}
