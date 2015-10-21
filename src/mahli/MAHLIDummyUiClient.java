package mahli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import generic.RoverClientRunnable;

public class MAHLIDummyUiClient extends RoverClientRunnable {

	public MAHLIDummyUiClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		GUILayout gl = new GUILayout();
		gl.mainRun();

	}
	
	/**
	 * Create the GUI buttons, text area and layout parameters
	 */
	public class GUILayout extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		
		protected JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;
		protected JTextArea ta;
		protected JScrollPane sp;

		public GUILayout() {
			// set the window layout manager
			setLayout(new FlowLayout());
			// set the window location when it starts
	        setLocation(0, 0);
			
	        // set the dimensions for the panels
			Dimension leftDimension = new Dimension(200,700);
			Dimension rightDimension = new Dimension(500,700);
			
			// create the panel to hold the buttons and output from the server
			JPanel buttonPanel = new JPanel(new GridLayout(15, 1, 5, 5));
			JPanel outputPanel = new JPanel(new GridLayout(1, 1));
	        
			// set the preferred panel size
	        buttonPanel.setPreferredSize(leftDimension);
	        outputPanel.setPreferredSize(rightDimension);
	        
	        // set the panel layout manager
	        buttonPanel.setLayout(new GridLayout(15, 2, 5, 5));
	        outputPanel.setLayout(new GridLayout());
	        
	        // set the panel background color
	        buttonPanel.setBackground(Color.DARK_GRAY);
	        outputPanel.setBackground(Color.YELLOW);
	        
	        // set the panel to be visible
	        buttonPanel.setVisible(true);
	        outputPanel.setVisible(true);
	        
	        // set the buttons parameters
			b1 = new JButton("MAHLI_Camera_ON");
			b1.setVerticalTextPosition(AbstractButton.CENTER);
			b1.setHorizontalTextPosition(AbstractButton.LEADING); 
			b1.setMnemonic(KeyEvent.VK_D);
			b1.setActionCommand("MAHLI_Camera_ON");

			b2 = new JButton("MAHLI_NightIllumination_ON");
			b2.setVerticalTextPosition(AbstractButton.BOTTOM);
			b2.setHorizontalTextPosition(AbstractButton.CENTER);
			b2.setMnemonic(KeyEvent.VK_M);

			b3 = new JButton("MAHLI_NightIllumination_OFF");
			b3.setMnemonic(KeyEvent.VK_E);
			b3.setActionCommand("MAHLI_NightIllumination_OFF");

			b4 = new JButton("MAHLI_AutoFocus_ON");
			b4.setMnemonic(KeyEvent.VK_E);
			b4.setActionCommand("MAHLI_AutoFocus_ON");

			b5 = new JButton("MAHLI_AutoFocus_OFF");
			b5.setMnemonic(KeyEvent.VK_E);
			b5.setActionCommand("MAHLI_AutoFocus_OFF");

			b6 = new JButton("MAHLI_Video_ON");
			b6.setMnemonic(KeyEvent.VK_E);
			b6.setActionCommand("MAHLI_Video_ON");

			b7 = new JButton("MAHLI_Video_OFF");
			b7.setMnemonic(KeyEvent.VK_E);
			b7.setActionCommand("MAHLI_Video_OFF");

			b8 = new JButton("MAHLI_Image_Capture");
			b8.setMnemonic(KeyEvent.VK_E);
			b8.setActionCommand("MAHLI_Image_Capture");

			b9 = new JButton("MAHLI_Image_store");
			b9.setMnemonic(KeyEvent.VK_E);
			b9.setActionCommand("MAHLI_Image_store");

			b10 = new JButton("MAHLI_Image_read");
			b10.setMnemonic(KeyEvent.VK_E);
			b10.setActionCommand("MAHLI_Image_read");

			b11 = new JButton("MAHLI_Camera_OFF");
			b11.setMnemonic(KeyEvent.VK_E);
			b11.setActionCommand("MAHLI_Camera_OFF");

			b12 = new JButton("EXIT");
			b12.setMnemonic(KeyEvent.VK_E);
			b12.setActionCommand("EXIT");

			// set and listen for button actions
			b1.addActionListener(this);
			b11.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);
			b5.addActionListener(this);
			b6.addActionListener(this);
			b7.addActionListener(this);
			b8.addActionListener(this);
			b9.addActionListener(this);
			b10.addActionListener(this);
			b12.addActionListener(this);
			
			// set the text area parameters
			ta = new JTextArea(50, 100);
	    	ta.setLineWrap(true);
	        ta.setWrapStyleWord(true);
	        sp = new JScrollPane(ta);

			// add all the buttons to the left panel
	        buttonPanel.add(b1);
	        buttonPanel.add(b11);
	        buttonPanel.add(b2);
	        buttonPanel.add(b3);
	        buttonPanel.add(b4);
	        buttonPanel.add(b5);
	        buttonPanel.add(b6);
	        buttonPanel.add(b7);
	        buttonPanel.add(b8);
	        buttonPanel.add(b9);
	        buttonPanel.add(b10);
	        buttonPanel.add(b12);
	        
	        // add the text area to the right panel
	        outputPanel.add(sp);
	        
	        // add the panel to the main GUI panel
	        add(buttonPanel);
	        add(outputPanel);
		}

		public void actionPerformed(ActionEvent e) {
			ObjectOutputStream outputToAnotherObject = null;
			ObjectInputStream inputFromAnotherObject = null;

			String command;
			String reply = "";

			command = e.getActionCommand();

			try {
				outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
				outputToAnotherObject.writeObject(command);
				inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
				reply = (String) inputFromAnotherObject.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			ta.append("MAHLI CLIENT : Response from server - " + reply);
			ta.append("\n");
			
			if(reply.equalsIgnoreCase("exit"))
				System.exit(0);

		}

		/** Returns an ImageIcon, or null if the path was invalid. */
		protected  ImageIcon createImageIcon(String path) {
			java.net.URL imgURL = GUILayout.class.getResource(path);
			if (imgURL != null) {
				return new ImageIcon(imgURL);
			} else {
				System.err.println("Couldn't find file: " + path);
				return null;
			}
		}

		/**
		 * Create the GUI and show it. For thread safety, this method should be
		 * invoked from the event-dispatching thread.
		 */
		private void createAndShowGUI() {

			// Create and set up the window.
			JFrame frame = new JFrame("MAHLI Simulator App");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Create and set up the content pane.
			GUILayout newContentPane = new GUILayout();
			newContentPane.setOpaque(true); // content panes must be opaque
			frame.setContentPane(newContentPane);

			// Display the window.
			frame.pack();
			frame.setVisible(true);
		}

		public void mainRun() {
			// Schedule a job for the event-dispatching thread:
			// creating and showing this application's GUI.
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
				}
			});
		}
	}

}
