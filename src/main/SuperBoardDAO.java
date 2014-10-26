package main;
public interface SuperBoard_DAO {
	public void storeBoard(Board board);
	public void removeBoard(Board board);
	public Iterator<Board> getAllBoards();
}

public class SuperBoardDAO implements SuperBoard_DAO {

}
