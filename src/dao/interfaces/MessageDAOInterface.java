package interfaces;

import java.util.Iterator;

import model.Message;

public interface MessageDAOInterface {
	/**
	 * get all messages of a given board from the database.
	 * 
	 * @param board
	 *            Board object where messages are posted.
	 * @return Iterator<Message> of a given board.
	 */
	public Iterator<Message> getMessages(String bid);

	/**
	 * get specific message with given mid from the database.
	 * 
	 * @param mid
	 *            String of message id.
	 * @return Message object which given mid.
	 */
	public Message getMessage(String bid, String mid);

	/**
	 * get all new messages of a given board since given time from the database.
	 * 
	 * @param board
	 *            Board object where messages are posted.
	 * @param time
	 *            Timestamp object to search for new messages after this time.
	 * @return Iterator<Message> of a given board since given time.
	 */
	public Iterator<Message> getMessagesSinceTime(String bid, String date);

	/**
	 * delete a message from the given board.
	 * 
	 * @param board
	 *            Board object where message is posted.
	 * @param msg
	 *            Message object which to be deleted.
	 * @return 1 on success, 0 on error.
	 */
	public int deleteMessage(String mid);

	/**
	 * add a message to the given board.
	 * 
	 * @param board
	 *            Board object where message is going to be posted.
	 * @param user
	 *  		  The user who is posting
	 * @param text
	 *           text which going to be added.
	 * @return 1 on success, 0 on error.
	 */
	public int addMessage(String username, String bid, String text);
}
