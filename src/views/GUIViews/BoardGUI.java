package GUIViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import model.Message;


public class BoardGUI extends JFrame implements ActionListener, GUIEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userInput;

	/**
	 * Create the frame.
	 */
	public BoardGUI(GUIController listener) {
		super("4chinz.gov");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane board = new JTextPane();
		board.setEditable(false);
		board.setBounds(35, 36, 397, 214);
		contentPane.add(board);
		
		JLabel boardTitle = new JLabel("<Insert Board Name Here>");
		boardTitle.setBounds(35, 11, 179, 14);
		contentPane.add(boardTitle);
		
		userInput = new JTextField();
		userInput.setBounds(35, 269, 397, 20);
		contentPane.add(userInput);
		userInput.setColumns(10);
		
		JButton postButton = new JButton("Post");
		postButton.setBounds(35, 300, 89, 23);
		contentPane.add(postButton);
	}

	@Override
	public void closeGUI() {
		this.dispose();
	}

	@Override
	public int displayString(String text) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void recieveMessages(Iterator<Message> messages) {
		// TODO Auto-generated method stub
		
	}
}
