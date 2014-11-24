package GUIViews;

import javax.swing.*;

import com.sun.xml.internal.ws.api.server.Container;

import exceptions.DataException;
import exceptions.StateException;
import model.*;
import mvc.ViewEventListener;

import java.awt.*;
import java.awt.event.*;


public class LoginGUI extends JFrame implements ActionListener, GUIEventListener{
	private GUIController GUIMain = null;
	private ViewEventListener controller = null;
	
	private static final long serialVersionUID = 1L;

	private JLabel LB_username = new JLabel ("Username: ");
	private JLabel LB_password = new JLabel ("Password: ");
	private JTextField TA_username = new JTextField(15);
	private JPasswordField PF_password = new JPasswordField(15);
	
	private JButton BT_register = new JButton("Register");
	private JButton BT_signin = new JButton("Sign In");
	private JButton BT_exit = new JButton("Exit");
	int i = 0;
	
	public LoginGUI(GUIController listener){
		
		super("Login Screen");
		GUIMain = listener;
		controller = GUIMain.getController();
		
		//maybe look and feel --insert here--
		
		
		this.preparePanel(this.getContentPane());
		
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);     
	    	    
	    this.pack();
	    
	    centreWindow(this);
	    
	    this.addWindowListener(exitListener);
	    
	    
	    //we called GUI but we're not logged in yet (we need to authenticate ourselves)
	} //end constructor

	public void preparePanel(java.awt.Container pane) {
		GridLayout inputLayout = new GridLayout(0,2);
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(inputLayout);
		inputLayout.setVgap(10);
		
        GridLayout controlLayout = new GridLayout(0,3);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(controlLayout);
        controlLayout.setVgap(10);
        //---------inputPanel-------------------------
        inputPanel.add(LB_username);
        inputPanel.add(TA_username);
        inputPanel.add(LB_password);
	    inputPanel.add(PF_password);
	    inputPanel.add(Box.createVerticalStrut(4));
	    //---------controlPanel-------------------------
	    controlPanel.add(BT_register);
	    controlPanel.add(BT_signin);
	    controlPanel.add(BT_exit);
	    BT_exit.addActionListener(this);
	    BT_register.addActionListener(this);
	    BT_signin.addActionListener(this);
        
        
		pane.add(inputPanel, BorderLayout.NORTH);
		pane.add(new JSeparator(), BorderLayout.CENTER);
		pane.add(controlPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent event){
		if (event.getSource() == BT_signin) {
			try {
				controller.login(new String(TA_username.getText()), new String(PF_password.getPassword()));
			} catch (StateException e) {
				ErrorGUI.showError("Login Error", "You are already logged in.");
			} catch (DataException e) {
				ErrorGUI.loginError();
			}
		}
		else if(event.getSource() == BT_register) {
			GUIMain.changeStateRegister();
		}
		else if(event.getSource() == BT_exit) {
			System.exit(0);
		}
	}

	@Override
	public void closeGUI() {
		this.dispose();
	}


	@Override
	public int displayString(String text) {
		//RETURN 1 FOR SUCCESS!!!!
		return 0;
	}
	
	//Stackoverflow --- listener for X
	WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Quit the Program Completly?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
               System.exit(0);
            }
        }
    };
    
    //function for centering the frame
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
	
}
