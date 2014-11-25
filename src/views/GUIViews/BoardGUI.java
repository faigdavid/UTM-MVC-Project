package GUIViews;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import exceptions.StateException;
import model.Message;
import mvc.ViewEventListener;


public class BoardGUI extends JFrame implements ActionListener, GUIEventListener {

	private GUIController GUIMain = null;
	private ViewEventListener controller = null;
	private static final long serialVersionUID = 1L;
	
	private JLabel LB_boardTitle;
	private JTextArea TA_boardMsgs = new JTextArea(10,30);
	private JTextField TA_userInput  = new JTextField();
	private JButton BT_post = new JButton("Post");
	private JButton BT_subscribe = new JButton ("Subscribe");
	private JButton BT_back = new JButton("Back");


	/**
	 * Create the frame.
	 */
	public BoardGUI(GUIController listener) {
		super("Board");
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.setVisible(true);
		
		preparePanel(this.getContentPane());
		
		centreWindow(this);
		
		this.pack();
		addWindowListener(exitListener);
	}
	
	public void preparePanel(java.awt.Container pane) {
		LB_boardTitle = new JLabel("<-INSERT BOARD NAME HERE->");
		
		/*
		if (User sub){
			BT_subscribe.setText("Unsubscribe");
		}
		*/
				
		GridLayout inputLayout = new GridLayout(0,1);
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(inputLayout);
		inputLayout.setVgap(3);
		
		GridLayout displayLayout = new GridLayout(0,1);
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(displayLayout);
		displayLayout.setVgap(10);
		
        GridLayout controlLayout = new GridLayout(0,1);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(controlLayout);
        controlLayout.setVgap(10);
        //---------displayPanel-------------------------
        TA_boardMsgs.setEditable(false);
        
        //displayPanel.add(LB_boardTitle);
        displayPanel.add(TA_boardMsgs);
        inputPanel.add(Box.createVerticalStrut(4));
        //---------inputPanel-------------------------
        inputPanel.add(TA_userInput);
        
	    inputPanel.add(BT_post);
	    inputPanel.add(BT_subscribe);
	    inputPanel.add(BT_back);
	    BT_post.addActionListener(this);
	    BT_back.addActionListener(this);
	    BT_subscribe.addActionListener(this);
        
	    pane.add(LB_boardTitle, BorderLayout.NORTH);
	
	    pane.add(displayPanel,BorderLayout.CENTER);
		//pane.add(new JSeparator(), BorderLayout.CENTER);
		pane.add(inputPanel, BorderLayout.SOUTH);
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
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == BT_post) {
			try {
				controller.post(TA_userInput.getText());
			} catch (StateException e) {
				ErrorGUI.messageError();
			}
			TA_userInput.setText("");
		}
		else if(event.getSource() == BT_back) {
			//GUIMain.changeStateRegister();
			GUIMain.changeStateNoBoard();
		}
		else if(event.getSource() == BT_subscribe) {
			//controller.subscribeUserToBoard(BOARD)
		}
	}

	public void recieveMessages(Iterator<Message> messages) {
		// TODO Auto-generated method stub
		
	}
	
    //function for centering the frame
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2- 100);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2 - 100);
        frame.setLocation(x, y);
    }
	
	WindowListener exitListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(null, "Are you sure?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
               System.exit(0);
            }
        }
    };
}
