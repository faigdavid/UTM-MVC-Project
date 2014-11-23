package GUIViews;

import javax.swing.*;

import model.*;
import mvc.ViewEventListener;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.Iterator;
// HOW TO CALL GUI
//new GUI();
public class LoginGUI extends JFrame implements ActionListener, GUIEventListener{
	private GUIController GUIMain = null;
	private ViewEventListener controller = null;
	private static final long serialVersionUID = 1L;
	private JPanel pane = new JPanel();
	private JLabel LB_username = new JLabel ("Username: ");
	private JLabel LB_password = new JLabel ("Password: ");
	private JTextField TA_username = new JTextField(15);
	private JTextField TA_password = new JTextField(15);
	
	JButton BT_register = new JButton("Register");
	JButton BT_signin = new JButton("Sign In");
	int i = 0;
	
	public LoginGUI(GUIController listener){
		
		super("Login Screen");
		GUIMain = listener;
		controller = GUIMain.getController();
		
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    setBounds(100,100,280,150); //From upper left corner (right, down, width, height)
	    this.add(pane); 
	    pane.setLayout(new FlowLayout());
	    
	    LB_username.setBounds(0, 50, 300, 30);
	    pane.add(LB_username);
	        
	    TA_username.setBounds(100,50,100,30);
	    pane.add(TA_username);
	    
	    LB_password.setBounds(0, 50, 300, 30);
	    pane.add(LB_password);
	    
	    TA_password.setBounds(0,50,100,30);
	    pane.add(TA_password);
	    
	    BT_register.setSize(100,20);
	    BT_register.setLocation(10,10);
	    BT_register.addActionListener(this);
	    pane.add(BT_register);
	    
	    BT_signin.setSize(100,20);
	    BT_signin.setLocation(10,10);
	    BT_signin.addActionListener(this);
	    pane.add(BT_signin);
	    
	    //we called GUI but we're not logged in yet (we need to authenticate ourselves)
	} //end constructor

	
	public void actionPerformed(ActionEvent event){
		if (event.getSource() == BT_signin) {
			controller.login(LB_username.getText(), LB_password.getText());
		}
		else if(event.getSource() == BT_register) {
			GUIMain.changeStateRegister();
		}
	}



	@Override
	public int closeGUI() {
		this.dispose();
		return 1;
	}


	@Override
	public int displayString(String text) {
		//RETURN 1 FOR SUCCESS!!!!
		return 0;
	}

	
}
