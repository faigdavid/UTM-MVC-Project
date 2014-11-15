package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
// HOW TO CALL GUI
//new GUI();
public class RegisterGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	
    final static int maxGap = 20;
    JButton BT_register = new JButton("Register");
    JButton BT_cancel = new JButton("cancel");
    
	private JTextField TA_username = new JTextField(15);
	private JTextField TA_password = new JTextField(15);
	private JTextField TA_passwordConfirm = new JTextField(15);
    
    public RegisterGUI() {
        super("Register");
        //setResizable(false);
        
        
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        this.addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
        this.setVisible(true);
        
        
    }
	
    public void addComponentsToPane(final Container pane) {
        GridLayout inputLayout = new GridLayout(0,2);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(inputLayout);
        
        GridLayout controlLayout = new GridLayout(0,2);
        JPanel controls = new JPanel();
        controls.setLayout(controlLayout);
         
        //Add buttons to experiment with Grid Layout
        inputPanel.add(new JLabel ("Username: "));
        inputPanel.add(TA_username);
        inputPanel.add(new JLabel ("Password: "));
        inputPanel.add(TA_password);
        inputPanel.add(new JLabel ("Confirm Password: "));
        inputPanel.add(TA_passwordConfirm);
        inputPanel.add(Box.createVerticalStrut(4));
        inputLayout.setVgap(10);
         
        //Add controls to set up horizontal and vertical gaps
        controls.add(BT_register);
        controls.add(BT_cancel);
        controlLayout.setHgap(10);
        
         
        //Process the Apply gaps button press
        BT_register.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	//System.out.println("Asdfadsf");
            	
            	
                //Set up the horizontal gap value
            	//textLayout.setHgap(10);
                //Set up the vertical gap value
            	//textLayout.setVgap(10);
                //Set up the layout of the buttons
            	//textLayout.layoutContainer(compsToExperiment);
            }
        });
        pane.add(inputPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }
} //end class