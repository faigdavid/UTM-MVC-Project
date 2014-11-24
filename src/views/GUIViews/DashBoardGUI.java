package GUIViews;

import java.awt.BorderLayout;
import java.awt.Container;
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
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import exceptions.DataException;
import exceptions.StateException;
import model.Board;
import mvc.ViewEventListener;


public class DashBoardGUI extends JFrame implements ActionListener, GUIEventListener {

	private static final long serialVersionUID = 1L;
	private GUIController GUIMain = null;
	private ViewEventListener controller = null;
	
	//list of board names
	private List<String> boardList = new ArrayList<>();
	
	//-- THIS STRING LIST IS TEMPERORY
	private String[] temp = {"c","c","c","c","c","c","c","c","c","c","c","c"};
	
	private JComboBox JCB_boardList = new JComboBox(temp);
	private JButton BT_create = new JButton("Create New");
	private JButton BT_join = new JButton("Join");
	private JButton BT_cancel = new JButton("Cancel");
	
	
	/**
	 * Create the frame.
	 */
	public DashBoardGUI(GUIController listener) {
		super("DashBoard");
		GUIMain = listener;
		controller = GUIMain.getController();
		
		//----- NEED THIS THING TO RETURN ARRAY OF BOARD NAMES
		//boardList = controller.requestBoards();
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.preparePanel(this.getContentPane());
		
		this.setVisible(true);
		
		this.pack();
		
		centreWindow(this);
		
		addWindowListener(exitListener);
	}
	
    public void preparePanel(Container pane) {
        GridLayout inputLayout = new GridLayout(0,1);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(inputLayout);
        
        GridLayout controlLayout = new GridLayout(0,3);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(controlLayout);
         
        //Add buttons to experiment with Grid Layout
        inputPanel.add(JCB_boardList);
        inputLayout.setVgap(10);
         
        //Add controls to set up horizontal and vertical gaps
        controlPanel.add(BT_create);
        controlPanel.add(BT_join);
        controlPanel.add(BT_cancel);
        
        
        controlLayout.setHgap(10);
        
        
        //add action listener
        BT_cancel.addActionListener(this); 
        BT_join.addActionListener(this); 
        BT_create.addActionListener(this); 
        
        //Process the Apply gaps button press
        pane.add(inputPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controlPanel, BorderLayout.SOUTH);
        
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
		if (event.getSource() == BT_create) {
		
			JTextField TA_boardName = new JTextField();
			String create = JOptionPane.showInputDialog("Type in your board name");

			try {
				controller.createBoard(create);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if(event.getSource() == BT_cancel) {
			//GUIMain.changeStateRegister();
			controller.logout();
		}
		else if(event.getSource() == BT_join) {
			try {
				controller.changeBoardByName(String.valueOf(JCB_boardList.getSelectedItem()));
			} catch (StateException | DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

	public void recieveBoards(Iterator<Board> boards) {
		// TODO Auto-generated method stub
		boardList.add(boards.next().getName());
		while (boards.hasNext()){
			boardList.add(boards.next().getName());
		}
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
    
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

}
