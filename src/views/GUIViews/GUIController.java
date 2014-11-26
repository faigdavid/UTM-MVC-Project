package GUIViews;

import java.io.IOException;
import java.util.Iterator;

import model.*;
import mvc.ModelEventListener;
import mvc.ViewEventListener;
import exceptions.StateException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This GUI will be act as the "startup"  for all the other GUIs.
 * Running this View should set up all the GUI listeners and immediately
 * load the first GUI.
 * 
 * The general idea of how GUI views works is that, rather than
 * just interacting with each other, they will also all be listeners
 * to the main program. This way, they are guaranteed to be synchronized.
 * 
 * @authors Henry, David
 *
 */
public class GUIController implements ModelEventListener{
	
	private GUIEventListener currentState = null;
	private GUIEventListener tempCurrentState = null;
	private ViewEventListener controller = null;
	
	public GUIController() { }
	
	public ViewEventListener getController(){
		return this.controller;
	}
	
	@Override
	public void runView() throws IOException {
        /* Use an appropriate Look and Feel*/
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
           //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        
        /* Turn off metal's use of bold fonts */
        //UIManager.put("swing.boldMetal", Boolean.TRUE);		
		
		this.currentState = new LoginGUI(this);
	}
	
	//END GUI
	
	@Override
	public int printString(String text) {
		return currentState.displayString(text);
	}

	@Override
	public void changeStateRegister() {
		this.tempCurrentState=this.currentState;
		this.currentState = new RegisterGUI(this);
		tempCurrentState.closeGUI();
		
	}
	@Override
	public void changeStateLoggedIn() {
		this.tempCurrentState=this.currentState;
		this.currentState = new DashBoardGUI(this);
		tempCurrentState.closeGUI();
		currentState.refresh();//To populate the list.
	}

	@Override
	public void changeStateLoggedOut() {
		this.tempCurrentState=this.currentState;
		this.currentState = new LoginGUI(this);
		tempCurrentState.closeGUI();
	}

	@Override
	public void changeStateInBoard(Board board) {
		this.tempCurrentState=this.currentState;
		this.currentState = new BoardGUI(this, board);
		tempCurrentState.closeGUI();
		currentState.refresh();
	}

	@Override
	public void changeStateNoBoard() { 
		this.tempCurrentState=this.currentState;
		this.currentState = new DashBoardGUI(this);
		tempCurrentState.closeGUI();
        currentState.refresh();//To populate the list.
	}

	@Override
	public void addViewEventListener(ViewEventListener controller) {
		this.controller = controller;

	}

	@Override
	public void removeViewEventListener(ViewEventListener controller) {
		this.controller = null;

	}

	@Override
	public void recieveBoardMessages(Iterator<Message> messages) throws StateException {
		if (this.currentState instanceof BoardGUI) {
			((BoardGUI) currentState).recieveMessages(messages);
		}
		else {
			ErrorGUI.showError("State Error", "GUI is out of sync");
		}

	}

	@Override
	public void recieveBoards(Iterator<Board> boards) throws StateException {
		if (this.currentState instanceof DashBoardGUI) {
			((DashBoardGUI) currentState).recieveBoards(boards);
		}
		else {
			ErrorGUI.showError("State Error", "Must be in DashBoard to recieve boards.");
		}

	}

	@Override
	public void recieveSubscribedBoards(Iterator<Board> boards) throws StateException {
		if (this.currentState instanceof DashBoardGUI) {
			((DashBoardGUI) currentState).recieveBoards(boards);
		}
		else {
			ErrorGUI.showError("State Error", "Must be in DashBoard to recieve boards.");
		}


	}
	
}
