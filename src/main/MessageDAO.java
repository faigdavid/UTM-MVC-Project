package main;

import java.sql.Timestamp;
import java.util.Iterator;

public interface MessageDAO {
	/**
	 * get all messages of a given board from the database.
	 * 
	 * @param board
	 *            Board object where messages are posted.
	 * @return Iterator<Message> of a given board.
	 */
	public Iterator<Message> getMessages(Board board);

	/**
	 * get specific message with given mid from the database.
	 * 
	 * @param mid
	 *            String of message id.
	 * @return Message object which given mid.
	 */
	public Message getMessage(String mid);

	/**
	 * get all new messages of a given board since given time from the database.
	 * 
	 * @param board
	 *            Board object where messages are posted.
	 * @param time
	 *            Timestamp object to search for new messages after this time.
	 * @return Iterator<Message> of a given board since given time.
	 */
	public Iterator<Message> getMessagesSinceTime(Board board, Timestamp time);

	/**
	 * delete a message from the given board.
	 * 
	 * @param board
	 *            Board object where message is posted.
	 * @param msg
	 *            Message object which to be deleted.
	 * @return 0 on success, -1 on error.
	 */
	public int deleteMessage(Board board, Message msg);

	/**
	 * add a message to the given board.
	 * 
	 * @param board
	 *            Board object where message is going to be posted.
	 * @param msg
	 *            Message object which going to be added.
	 * @return 0 on success, -1 on error.
	 */
	public int saveMessage(Board board, Message msg);
}
