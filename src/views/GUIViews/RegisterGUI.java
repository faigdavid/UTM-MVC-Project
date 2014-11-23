package GUIViews;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import mvc.ViewEventListener;
// HOW TO CALL GUI
//new GUI();
public class RegisterGUI extends JFrame implements GUIEventListener, ActionListener {
	
	private GUIController GUIMain = null;
	private ViewEventListener controller = null;
	
	private static final long serialVersionUID = 1L;
	private static RegisterGUI registerGUIReference = null;
    final static int maxGap = 20;
    JButton BT_register = new JButton("Register");
    JButton BT_cancel = new JButton("cancel");
    
	private JTextField TA_username = new JTextField(15);
	private JTextField TA_password = new JTextField(15);
	private JTextField TA_passwordConfirm = new JTextField(15);
    
    public RegisterGUI(GUIController listener) {
        super("Register");
		GUIMain = listener;
		controller = GUIMain.getController();
        
        
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
        BT_cancel.addActionListener(this);
        BT_register.addActionListener(this);
        pane.add(inputPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }
   
    
	@Override
	public int displayString(String text) {
		//RETURN 1 FOR SUCCESS!!!!
		return 0;
	}

	@Override
	public int closeGUI() {
		this.dispose();
		return 1;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == BT_register) {
			controller.register(TA_username.getText(), TA_password.getText(), TA_passwordConfirm.getText());
		}
		else if(event.getSource()==BT_cancel)
		{
			GUIMain.changeStateLoggedOut();
		}
	}

	



} //end class
