package main;
public interface BoardMsg_DAO {
	public void storeMessage(String message);
	public void removeMessage(String message);
	public Iterator<String> getMessages();
}

public class BoardMsgDAO implements BoardMsg_DAO {

}
