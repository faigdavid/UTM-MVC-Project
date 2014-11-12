package main;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
// HOW TO CALL GUI
//new GUI();
public class RegisterGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pane = new JPanel();
	JLabel LB_username = new JLabel        ("Username: ");
	JLabel LB_password = new JLabel        ("Password: ");
	JLabel LB_confirmpassword = new JLabel ("   Confirm: ");
	JLabel LB_result = new JLabel ("");
	JTextField TF_username = new JTextField(15);
	JTextField TF_password = new JTextField(15);
	JTextField TF_confirmpassword = new JTextField(15);
	JButton BT_register = new JButton("Register");
	JButton BT_cancel = new JButton("Cancel");
	
	int i = 0;
	RegisterGUI(){
	    super("Register Screen");
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    setBounds(100,100,280,170); //From upper left corner (right, down, width, height)
	    this.add(pane); 
	    pane.setLayout(new FlowLayout());
	    
	    LB_username.setBounds(0, 50, 300, 30);
	    pane.add(LB_username);
	        
	    TF_username.setBounds(100,50,100,30);
	    pane.add(TF_username);
	    
	    LB_password.setBounds(0, 50, 300, 30);
	    pane.add(LB_password);
	    
	    TF_password.setBounds(0,50,100,30);
	    pane.add(TF_password);
	    
	    LB_confirmpassword.setBounds(0, 50, 300, 30);
	    pane.add(LB_confirmpassword);
	    
	    TF_confirmpassword.setBounds(0,50,100,30);
	    pane.add(TF_confirmpassword);
	    
	    BT_register.setSize(100,20);
	    BT_register.setLocation(10,10); 
	    BT_register.addActionListener(this);
	    pane.add(BT_register);
	    
	    BT_cancel.setSize(100,20);
	    BT_cancel.setLocation(10,10); 
	    BT_cancel.addActionListener(this);
	    pane.add(BT_cancel);
	    

	} //end constructor

	public void actionPerformed(ActionEvent event){
		if (event.getSource() == BT_cancel){
			//GO TO MAIN GUI, set GUI as visible
			JOptionPane.showMessageDialog(pane,"You are Signing in");
				
		}
		if (event.getSource() == BT_register){
			//go to register GUI, set GUI as visible
			JOptionPane.showMessageDialog(pane,"You are registering");
		}

	}   
} //end class