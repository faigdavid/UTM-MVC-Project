package GUIViews;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import exceptions.DataException;
import exceptions.StateException;
import mvc.ViewEventListener;
// HOW TO CALL GUI
//new GUI();
public class RegisterGUI extends JFrame implements GUIEventListener, ActionListener {	
	private GUIController GUIMain = null;
	private ViewEventListener controller = null;
	
	private static final long serialVersionUID = 1L;
    private JButton BT_register = new JButton("Register");
    private JButton BT_cancel = new JButton("Cancel");
    
    private JLabel JL_username = new JLabel ("Username: ");
    private JLabel JL_password = new JLabel ("Password: ");
    private JLabel JL_passwordConfirm = new JLabel ("Confirm Password: ");
    
	private JTextField TA_username = new JTextField(15);
	private JPasswordField PF_password = new JPasswordField(15);
	private JPasswordField PF_passwordConfirm = new JPasswordField(15);
    
    public RegisterGUI(GUIController listener) {
        super("Register");
		GUIMain = listener;
		controller = GUIMain.getController();


		
        //Create and set up the window.
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                
        //Set up the content pane.
        this.preparePanel(this.getContentPane());
        //Display the window.
        this.pack();
        
        centreWindow(this);
        this.setVisible(true);
        
    }
    
    public void preparePanel(Container pane) {
        GridLayout inputLayout = new GridLayout(0,2);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(inputLayout);
        
        GridLayout controlLayout = new GridLayout(0,2);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(controlLayout);
         
        //Add buttons to experiment with Grid Layout
        inputPanel.add(JL_username);
        inputPanel.add(TA_username);
        inputPanel.add(JL_password);
        inputPanel.add(PF_password);
        inputPanel.add(JL_passwordConfirm);
        inputPanel.add(PF_passwordConfirm);
        inputPanel.add(Box.createVerticalStrut(4));
        inputLayout.setVgap(10);
         
        //Add controls to set up horizontal and vertical gaps
        controlPanel.add(BT_register);
        controlPanel.add(BT_cancel);
        controlLayout.setHgap(10);
        
         
        //Process the Apply gaps button press
        BT_cancel.addActionListener(this);
        BT_register.addActionListener(this);
        pane.add(inputPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controlPanel, BorderLayout.SOUTH);
        
        addWindowListener(exitListener);
        
        
	    PF_password.addKeyListener(new KeyListener (){
			@Override
			public void keyPressed(KeyEvent key) {
				// TODO Auto-generated method stub
				if (key.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						controller.register(TA_username.getText(), new String (PF_password.getPassword()), new String (PF_passwordConfirm.getPassword()));
					} catch (DataException e) {
						ErrorGUI.showError("Registration Error", e.getMessage());
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent key) {}
			@Override
			public void keyTyped(KeyEvent key) {}
        	
        });
	    
	    PF_passwordConfirm.addKeyListener(new KeyListener (){

			@Override
			public void keyPressed(KeyEvent key) {
				// TODO Auto-generated method stub
				if (key.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						controller.register(TA_username.getText(), new String (PF_password.getPassword()), new String (PF_passwordConfirm.getPassword()));
					} catch (DataException e) {
						ErrorGUI.showError("Registration Error", e.getMessage());
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent key) {}
			@Override
			public void keyTyped(KeyEvent key) {}
        	
        });
	    
	    TA_username.addKeyListener(new KeyListener (){

			@Override
			public void keyPressed(KeyEvent key) {
				// TODO Auto-generated method stub
				if (key.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						controller.register(TA_username.getText(), new String (PF_password.getPassword()), new String (PF_passwordConfirm.getPassword()));
					} catch (DataException e) {
						ErrorGUI.showError("Registration Error", e.getMessage());
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent key) {}
			@Override
			public void keyTyped(KeyEvent key) {}
        	
        });
    }
   
    
	@Override
	public int displayString(String text) {
		//RETURN 1 FOR SUCCESS!!!!
		return 0;
	}

	@Override
	public void closeGUI() {
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == BT_register) {
			try {
				controller.register(TA_username.getText(), new String (PF_password.getPassword()), new String (PF_passwordConfirm.getPassword()));
			} catch (DataException e) {
				ErrorGUI.showError("Registration Error", e.getMessage());
			}
		}
		else if(event.getSource()==BT_cancel)
		{
			GUIMain.changeStateLoggedOut();
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

	@Override
	public void refresh() {
		//Do Nothing for login or register.
		
	}


} //end class
