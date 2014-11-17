package views.GUIViews;

import java.io.IOException;
import java.util.Iterator;

import chatBoardsApp.*;

public class GUIBoard implements ModelEventListener {

	@Override
	public void runView() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int printString(String text) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeStateLoggedIn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStateLoggedOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStateInBoard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStateNoBoard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeViewEventListener(ViewEventListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveBoardMessages(Iterator<Message> messages) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveBoards(Iterator<Board> boards) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveSubscribedBoards(Iterator<Board> boards) {
		// TODO Auto-generated method stub

	}

}
