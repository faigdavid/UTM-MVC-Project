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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import exceptions.DataException;
import exceptions.StateException;
import model.Board;
import model.Message;
import mvc.ViewEventListener;


public class BoardGUI extends JFrame implements ActionListener, GUIEventListener {
	
	private GUIController GUIMain = null;
	private ViewEventListener controller = null;
	private static final long serialVersionUID = 1L;
	
	private JLabel LB_boardTitle;
	private JTextArea TA_boardMsgs = new JTextArea(10,30);
	private JTextField TA_userInput  = new JTextField(30);
	private JButton BT_post = new JButton("Post");
	private JButton BT_subscribe = new JButton ("Subscribe");
	private JButton BT_tag = new JButton ("Tag This Board!");
	private JButton BT_back = new JButton("Leave Board");
	private String topic = null;
	/**
	 * Create the frame.
	 */
	public BoardGUI(GUIController listener, Board board) {
		super(board.getName());
		this.topic = board.getTopic();
		this.GUIMain = listener;
		this.controller = GUIMain.getController();
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.setVisible(true);
		
		preparePanel(this.getContentPane());
		
		centreWindow(this);
		
		this.pack();
		addWindowListener(exitListener);
	}
	
	public void preparePanel(java.awt.Container pane) {
		LB_boardTitle = new JLabel(topic);
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
        
        //Scroll bar, and messages.
        JScrollPane scroll = new JScrollPane(TA_boardMsgs);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        displayPanel.add(scroll);
       
        //---------inputPanel-------------------------
        inputLayout.setVgap(3);
        inputPanel.add(TA_userInput);
        inputPanel.add(BT_post);
	    inputPanel.add(BT_tag);
	    inputPanel.add(BT_subscribe);
	    inputPanel.add(BT_back);
	    
	    //Add Action listeners.
	    BT_post.addActionListener(this);
	    BT_back.addActionListener(this);
	    BT_subscribe.addActionListener(this);
        BT_tag.addActionListener(this);
	    //Gaps.
	    
	    pane.add(LB_boardTitle, BorderLayout.NORTH);
	    pane.add(displayPanel,BorderLayout.CENTER);
		pane.add(inputPanel, BorderLayout.SOUTH);
		//pane.add(new JSeparator(), BorderLayout.CENTER);
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
		if (event.getSource() == BT_post) {
			try {
				//this is throwing a null pointer exception for some reason
				//when we attempt to post
				if (TA_userInput.getText() == null){
					ErrorGUI.showError("Post", "Nothing to post...");
				} else {
					controller.post(new String (TA_userInput.getText()));
				}
			} catch (DataException e) {
				ErrorGUI.messageError();
			}
			TA_userInput.setText("");
			refresh();
		}
		else if(event.getSource() == BT_back) {
			//GUIMain.changeStateRegister();
			GUIMain.changeStateNoBoard();
		}
		else if(event.getSource() == BT_subscribe) {
			controller.subscribeUserToBoard();
		}
		else if (event.getSource() == BT_tag) {
			String tagString = JOptionPane.showInputDialog("Type in your tags"
					+ ", seperated by a space");
			ArrayList<String> tags = new ArrayList<String>(
					Arrays.asList(tagString.split(" ")));
			try {
				controller.addTagsToBoard(tags);
			} catch (DataException | StateException e) {
				ErrorGUI.showError("DashBoard Error", e.getMessage());
			}
			refresh();
		}
	}

	public void recieveMessages(Iterator<Message> messages) {
		TA_boardMsgs.setText("");
		while(messages.hasNext()) {
			Message msg = messages.next();
			TA_boardMsgs.append(msg.formatMessage());
		}
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
    @Override
    public void refresh(){
    	try {
			controller.requestBoardMessages();
		} catch (StateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
