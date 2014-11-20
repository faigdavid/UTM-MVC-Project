package GUIViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;


public class DashBoardGUI extends JFrame implements ActionListener, GUIEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DashBoardGUI(GUIMain Listener) {
		super("4chinz.gov");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 60, 198, 20);
		contentPane.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(10, 146, 198, 28);
		contentPane.add(comboBox_1);
		
		JButton btnUnsubscribe = new JButton("Unsubscribe");
		btnUnsubscribe.setBounds(236, 60, 121, 20);
		contentPane.add(btnUnsubscribe);
		
		JButton btnSusbscribe = new JButton("Susbscribe");
		btnSusbscribe.setBounds(236, 149, 121, 23);
		contentPane.add(btnSusbscribe);
		
		JLabel lblSusbscribedBoards = new JLabel("Susbscribed Boards");
		lblSusbscribedBoards.setBounds(10, 17, 163, 32);
		contentPane.add(lblSusbscribedBoards);
		
		JLabel lblBoards = new JLabel("Boards");
		lblBoards.setBounds(10, 121, 46, 14);
		contentPane.add(lblBoards);
	}

	@Override
	public int closeGUI() {
		// TODO Auto-generated method stub
		return 0;
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
}
