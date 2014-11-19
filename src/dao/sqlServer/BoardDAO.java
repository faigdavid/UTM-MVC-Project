package sqlServer;

import interfaces.BoardDAOInterface;

import java.util.Iterator;

import model.Board;

public class BoardDAO implements BoardDAOInterface{

	@Override
	public Board getBoard(String bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board getBoardByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBoard(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Board> getAllBoards() {
		// TODO Auto-generated method stub
		return null;
	}

}
