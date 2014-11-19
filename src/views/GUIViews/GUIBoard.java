package GUIViews;

import java.io.IOException;
import java.util.Iterator;

import GUIViews.States.states;
import chatBoardsApp.*;

public class GUIBoard implements GUIEventListener {
	private GUIMain GUIMain = null;
	private ViewEventListener controller = null;
	
	public GUIBoard(GUIMain listener) {
		GUIMain = listener;
		controller = GUIMain.getController();
	}
	
	@Override
	public void closeGUI() {
		// TODO Auto-generated method stub
		
	}
	
	

}
