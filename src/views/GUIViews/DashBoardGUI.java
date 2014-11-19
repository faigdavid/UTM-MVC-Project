package GUIViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;

import chatBoardsApp.*;

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
	@Override
	public int closeGUI() {
		return JFrame.EXIT_ON_CLOSE;
		
	}
	

}
