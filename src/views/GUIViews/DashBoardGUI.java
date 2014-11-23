package GUIViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.Board;


public class DashBoardGUI extends JFrame implements ActionListener, GUIEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DashBoardGUI(GUIController Listener) {
		super("4chinz.gov");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> listOfSubscribedBoards = new JComboBox<String>();
		listOfSubscribedBoards.setBounds(10, 60, 198, 20);
		contentPane.add(listOfSubscribedBoards);
		
		JComboBox<String> listOfBoards = new JComboBox<String>();
		listOfBoards.setBounds(10, 146, 198, 28);
		contentPane.add(listOfBoards);
		
		JButton unsubscribeButton = new JButton("Unsubscribe");
		unsubscribeButton.setBounds(236, 60, 121, 20);
		contentPane.add(unsubscribeButton);
		
		JButton subscribeButton = new JButton("Susbscribe");
		subscribeButton.setBounds(236, 149, 121, 23);
		contentPane.add(subscribeButton);
		
		JLabel labelSusbscribedBoards = new JLabel("Susbscribed Boards");
		labelSusbscribedBoards.setBounds(10, 17, 163, 32);
		contentPane.add(labelSusbscribedBoards);
		
		JLabel labelBoards = new JLabel("Boards");
		labelBoards.setBounds(10, 121, 46, 14);
		contentPane.add(labelBoards);
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

	public void recieveBoards(Iterator<Board> boards) {
		// TODO Auto-generated method stub
		
	}
}
