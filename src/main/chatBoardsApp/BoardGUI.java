package chatBoardsApp;

import interfaces.BoardDAOInterface;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;

import local.*;
import mvcInterfaces.ViewEventListener;

/*Assumption: We can open multiple messaging apps so I'm not going to make it into a singleton */
public class BoardGUI extends JFrame implements ActionListener {
	
	GUIView mdl_listener = GUIView.newMessagingApp();
	private ViewEventListener control = mdl_listener.getViewEventListener();
	
	private JPanel pane = new JPanel();
	
	private JComboBox subscribedBoards = new JComboBox(); //ComboBox is the drop down menu
	private JComboBox notSubscribedBoards = new JComboBox();
	
	private JTextField board = new JTextField(); //this text field shows the board.
	private JTextField message = new JTextField();
	
	private JButton subscribeButton = new JButton("Subscribe");
	private JButton unsubscribeButton = new JButton("Unsubscribe");
	private JButton postButton = new JButton("Post");
	
	public BoardGUI() {
		super("4chinz.gov");
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
			control.subscribeUserToBoard(dao.getBoardByName(selectedBoard));			
		}
		else if(event.getSource() == unsubscribeButton) {
			BoardDAOInterface dao = new BoardDAO();
			control.unsubscribeUserFromBoard(dao.getBoardByName(selectedBoard));			
		}
		else if(event.getSource() == postButton) {
			control.post(message.getText());
		}
		else if(event.getSource() == subscribedBoards ) {
			selectedBoard = (String) subscribedBoards.getSelectedItem();
		}
		else if(event.getSource() == notSubscribedBoards) {
			selectedBoard = (String) notSubscribedBoards.getSelectedItem();
		}
		
	}
	
}
