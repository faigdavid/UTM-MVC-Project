package GUIViews;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exceptions.DataException;
import exceptions.StateException;
import model.Board;
import model.Message;
import mvc.ViewEventListener;

public class DashBoardGUI extends JFrame implements ActionListener,
		GUIEventListener {

	private static final long serialVersionUID = 1L;
	private GUIController GUIMain = null;
	private ViewEventListener controller = null;

	// -- THIS STRING LIST IS TEMPERORY
	private String[] temp = {};

	private JComboBox<String> JCB_boardList = new JComboBox<String>(temp);
	private JButton BT_subsOnly = new JButton("Subscribed Boards");
	private JButton BT_create = new JButton("Create New");
	private JButton BT_join = new JButton("Join");
	private JButton BT_cancel = new JButton("Logout");
	private JButton BT_refresh = new JButton("Show All");
	private JButton BT_search = new JButton("Search by Tags: ");
	private JTextField TA_tags = new JTextField(0);

	/**
	 * Create the frame.
	 */
	public DashBoardGUI(GUIController listener) {
		super("DashBoard");
		GUIMain = listener;
		controller = GUIMain.getController();

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.preparePanel(this.getContentPane());

		this.setVisible(true);

		this.pack();

		centreWindow(this);

		addWindowListener(exitListener);
	}

	public void preparePanel(Container pane) {

		// buttons
		GridLayout controlLayout = new GridLayout(0, 4);
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(controlLayout);

		// boards
		GridLayout inputLayout = new GridLayout(0, 1);
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(inputLayout);

		// search stuff
		GridLayout searchLayout = new GridLayout(0, 2);
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(searchLayout);

		// Add controls to set up horizontal and vertical gaps
		controlPanel.add(BT_create);
		controlPanel.add(BT_join);
		controlPanel.add(BT_cancel);
		controlPanel.add(BT_refresh);
		controlLayout.setHgap(10);

		// Add buttons to experiment with Grid Layout
		inputPanel.add(BT_subsOnly);
		inputPanel.add(JCB_boardList);
		// inputLayout.setVgap(10);

		// add the search text field to the search layout
		searchPanel.add(BT_search);
		// searchPanel.add(LB_tags);
		searchPanel.add(TA_tags);

		// add action listener
		BT_refresh.addActionListener(this);
		BT_cancel.addActionListener(this);
		BT_join.addActionListener(this);
		BT_create.addActionListener(this);
		BT_search.addActionListener(this);
		BT_subsOnly.addActionListener(this);
		// Process the Apply gaps button press
		// inputPanel.add(TA_username);
		pane.add(inputPanel, BorderLayout.SOUTH);
		pane.add(controlPanel, BorderLayout.NORTH);
		pane.add(searchPanel, BorderLayout.CENTER);

		TA_tags.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String tagString = new String(TA_tags.getText());
						ArrayList<String> tags = new ArrayList<String>(Arrays
								.asList(tagString.split(" ")));
						controller.requestBoardsByTag(tags);

					} catch (StateException e) {
						ErrorGUI.showError("DashBoard Error", e.getMessage());
						e.printStackTrace();
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

		});
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
			String name = JOptionPane
					.showInputDialog("Type in your board name");
			String topic = JOptionPane.showInputDialog("Type in your topic");
			try {
				controller.createBoard(name, topic);
			} catch (DataException e) {
				ErrorGUI.showError("DashBoard Error", e.getMessage());
			}
			tagRefresh(new String(TA_tags.getText()));
		} else if (event.getSource() == BT_cancel) {
			// GUIMain.changeStateRegister();
			controller.logout();
		} else if (event.getSource() == BT_join) {
			try {
				String toJoin = String.valueOf(JCB_boardList.getSelectedItem());
				if (toJoin != null) {
					controller.changeBoardByName(toJoin);
				} else {
					ErrorGUI.showError("DashBoard Error", "No board selected.");
				}
			} catch (StateException | DataException e) {
				ErrorGUI.showError("DashBoard Error", e.getMessage());
				e.printStackTrace();
			}
		} else if (event.getSource() == BT_search) {
			try {
				String tagString = new String(TA_tags.getText());
				ArrayList<String> tags = new ArrayList<String>(
						Arrays.asList(tagString.split(" ")));
				controller.requestBoardsByTag(tags);

			} catch (StateException e) {
				ErrorGUI.showError("DashBoard Error", e.getMessage());
				e.printStackTrace();
			}
		} else if (event.getSource() == BT_subsOnly) {

			try {
				controller.requestSubbedBoards();
			} catch (StateException e) {
				ErrorGUI.showError("DashBoard Error", e.getMessage());
				e.printStackTrace();
			}

		} else if (event.getSource() == BT_refresh) {

			refresh();

		}
	}

	public void recieveBoards(Iterator<Board> boards) {
		JCB_boardList.removeAllItems();
		try {
			while (boards.hasNext()) {
				JCB_boardList.addItem(boards.next().getName());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	WindowListener exitListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			int confirm = JOptionPane.showOptionDialog(null, "Are you sure?",
					"Exit Confirmation", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);
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

	private void tagRefresh(String tags) {
		ArrayList<String> tagsList = new ArrayList<String>(Arrays.asList(tags
				.split(" ")));
		try {
			controller.requestBoardsByTag(tagsList);
		} catch (StateException e) {
			ErrorGUI.showError("DashBoard Error", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void refresh() {
		TA_tags.setText("");
		try {
			controller.requestBoards();
		} catch (StateException e) {
			ErrorGUI.showError("DashBoard Error", e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void recieveMessages(Iterator<Message> messages) {
		// TODO Auto-generated method stub
		
	}
}
