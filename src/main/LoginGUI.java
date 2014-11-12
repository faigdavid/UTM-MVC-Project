package main;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;
// HOW TO CALL GUI
//new GUI();
public class LoginGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pane = new JPanel();
	JLabel LB_username = new JLabel ("Username: ");
	JLabel LB_password = new JLabel ("Password: ");
	JLabel LB_result = new JLabel ("");
	JTextField TA_username = new JTextField(15);
	JTextField TA_password = new JTextField(15);
	
	JButton BT_register = new JButton("Register");
	JButton BT_signin = new JButton("Sign In");
	int i = 0;
	LoginGUI(){
	    super("Login Screen");
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
	    

	} //end constructor

	public void actionPerformed(ActionEvent event){
		if (event.getSource() == BT_signin){
			//GO TO MAIN GUI, set GUI as visible
			JOptionPane.showMessageDialog(pane,"You are Signing in");
				
		}
		if (event.getSource() == BT_register){
			new RegisterGUI();
			//JOptionPane.showMessageDialog(pane,"You are registering");
		}

	}   
} //end class