package GUIViews;

import interfaces.BoardDAOInterface;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import local.BoardDAO;
import chatBoardsApp.*;

public class BoardGUI extends JFrame implements ActionListener , GUIEventListener{
	
	private GUIMain GUIMain = null;
	private ViewEventListener controller = null;
	
	private JPanel pane = new JPanel();
	
	private JComboBox subscribedBoards = new JComboBox(); //ComboBox is the drop down menu
	private JComboBox notSubscribedBoards = new JComboBox();
	
	private JTextField board = new JTextField(); //this text field shows the board.
	private JTextField message = new JTextField();
	
	private JButton subscribeButton = new JButton("Subscribe");
	private JButton unsubscribeButton = new JButton("Unsubscribe");
	private JButton postButton = new JButton("Post");
	
	public BoardGUI(GUIMain listener) {
		super("4chinz.gov");
		this.GUIMain = listener;
		this.controller = GUIMain.getController();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(pane);
		setSize(640, 480); //640x480 Pixels
		pane.setLayout(new FlowLayout());
		
		//added entities to panel, just set the bounds/size, etc. to whatever you wish.
		pane.add(subscribedBoards);
		pane.add(notSubscribedBoards);
		pane.add(board);
		pane.add(subscribeButton);
		pane.add(unsubscribeButton);
		pane.add(postButton);
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		String selectedBoard = null;
		
		if(event.getSource() == subscribeButton) {
			BoardDAOInterface dao = new BoardDAO();
			controller.subscribeUserToBoard(dao.getBoardByName(selectedBoard));			
		}
		else if(event.getSource() == unsubscribeButton) {
			BoardDAOInterface dao = new BoardDAO();
			controller.unsubscribeUserFromBoard(dao.getBoardByName(selectedBoard));			
		}
		else if(event.getSource() == postButton) {
			controller.post(message.getText());
		}
		else if(event.getSource() == subscribedBoards ) {
			selectedBoard = (String) subscribedBoards.getSelectedItem();
		}
		else if(event.getSource() == notSubscribedBoards) {
			selectedBoard = (String) notSubscribedBoards.getSelectedItem();
		}
		
	}


	@Override
	public int closeGUI() {
		return JFrame.EXIT_ON_CLOSE;
		
	}
	
}
