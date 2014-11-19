package GUIViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;

import model.*;
import mvc.ViewEventListener;

public class DashBoardGUI implements ActionListener, GUIEventListener{
	
	private GUIMain GUIMain = null;
	private ViewEventListener controller = null;
	
	public DashBoardGUI(GUIMain listener) { //being in dash board implies we're logged in.
		GUIMain = listener;
		controller = GUIMain.getController();
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void recieveBoards(Iterator<Board> boards) {
		//Display the boards.
		
	}
	
	@Override
	public int displayString(String text) {
		//RETURN 1 FOR SUCCESS!!!!
		return 0;
	}
	
	@Override
	public int closeGUI() {
		return JFrame.EXIT_ON_CLOSE;
		
	}

}
