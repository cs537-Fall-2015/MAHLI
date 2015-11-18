package MAHLI;

import java.awt.BorderLayout;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import generic.RoverClientRunnable;

public class MAHLIGuiApp extends RoverClientRunnable {

	public MAHLIGuiApp(int port, InetAddress host)
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
		
		protected JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14 , b15, b16, b17;
		protected JTextArea ta, sta;
		protected JScrollPane sp, ssp;

		public GUILayout() {
			// set the window layout manager
			setLayout(new FlowLayout());
			// set the window location when it starts
	        setLocation(0, 0);
			
	        // set the dimensions for the panels
			Dimension leftDimension = new Dimension(200,700);
			Dimension rightDimension = new Dimension(500,700);
			Dimension topDimension = new Dimension(500,350);
			Dimension bottomDimension = new Dimension(500,350);
			
			// create the panel to hold the buttons and output from the server
			JPanel buttonPanel = new JPanel(new GridLayout(17, 1, 2, 2));
			JPanel outputPanel = new JPanel();
			JPanel clientPanel = new JPanel();
			JPanel serverPanel = new JPanel();
	        
			// set the preferred panel size
	        buttonPanel.setPreferredSize(leftDimension);
	        outputPanel.setPreferredSize(rightDimension);
	        clientPanel.setPreferredSize(topDimension);
	        serverPanel.setPreferredSize(bottomDimension);
	        
	        // set the panel layout manager
	        buttonPanel.setLayout(new GridLayout(17, 1, 2, 2));
	        outputPanel.setLayout(new GridLayout(2, 1));
	        clientPanel.setLayout(new GridLayout(1, 1));
	        serverPanel.setLayout(new GridLayout(1, 1));
	        
	        // set the panel to be visible
	        buttonPanel.setVisible(true);
	        outputPanel.setVisible(true);
	        clientPanel.setVisible(true);
	        serverPanel.setVisible(true);
	        
	        // set the buttons parameters
			b1 = new JButton("MAHLI_Camera_ON");
			b1.setMnemonic(KeyEvent.VK_E);
			b1.setActionCommand("MAHLI_Camera_ON");
			
			b2 = new JButton("MAHLI_Camera_OFF");
			b2.setMnemonic(KeyEvent.VK_E);
			b2.setActionCommand("MAHLI_Camera_OFF");

			b3 = new JButton("MAHLI_NightIllumination_ON");
			b3.setMnemonic(KeyEvent.VK_E);
			b3.setActionCommand("MAHLI_NightIllumination_ON");

			b4 = new JButton("MAHLI_NightIllumination_OFF");
			b4.setMnemonic(KeyEvent.VK_E);
			b4.setActionCommand("MAHLI_NightIllumination_OFF");
			
			b5 = new JButton("MAHLI_Infrared_ON");
			b5.setMnemonic(KeyEvent.VK_E);
			b5.setActionCommand("MAHLI_Infrared_ON");
			
			b6 = new JButton("MAHLI_Infrared_OFF");
			b6.setMnemonic(KeyEvent.VK_E);
			b6.setActionCommand("MAHLI_Infrared_OFF");

			b7 = new JButton("MAHLI_AutoFocus_ON");
			b7.setMnemonic(KeyEvent.VK_E);
			b7.setActionCommand("MAHLI_AutoFocus_ON");

			b8 = new JButton("MAHLI_AutoFocus_OFF");
			b8.setMnemonic(KeyEvent.VK_E);
			b8.setActionCommand("MAHLI_AutoFocus_OFF");

			b9 = new JButton("MAHLI_Video_ON");
			b9.setMnemonic(KeyEvent.VK_E);
			b9.setActionCommand("MAHLI_Video_ON");

			b10 = new JButton("MAHLI_Video_OFF");
			b10.setMnemonic(KeyEvent.VK_E);
			b10.setActionCommand("MAHLI_Video_OFF");
			
			b11 = new JButton("MAHLI_Dust_Cover_OPEN");
			b11.setMnemonic(KeyEvent.VK_E);
			b11.setActionCommand("MAHLI_Dust_Cover_OPEN");
			
			b12 = new JButton("MAHLI_Dust_Cover_CLOSE");
			b12.setMnemonic(KeyEvent.VK_E);
			b12.setActionCommand("MAHLI_Dust_Cover_CLOSE");

			b13 = new JButton("MAHLI_Image_CAPTURE");
			b13.setMnemonic(KeyEvent.VK_E);
			b13.setActionCommand("MAHLI_Image_CAPTURE");

			b14 = new JButton("MAHLI_Image_READ");
			b14.setMnemonic(KeyEvent.VK_E);
			b14.setActionCommand("MAHLI_Image_READ");

			b15 = new JButton("MAHLI_Image_VIEW");
			b15.setMnemonic(KeyEvent.VK_E);
			b15.setActionCommand("MAHLI_Image_VIEW");
			
			b16 = new JButton("MAHLI_Image_ANALYZE");
			b16.setMnemonic(KeyEvent.VK_E);
			b16.setActionCommand("MAHLI_Image_ANALYZE");
			
			b17 = new JButton("EXIT");
			b17.setMnemonic(KeyEvent.VK_E);
			b17.setActionCommand("EXIT");			

			// set and listen for button actions
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);
			b5.addActionListener(this);
			b6.addActionListener(this);
			b7.addActionListener(this);
			b8.addActionListener(this);
			b9.addActionListener(this);
			b10.addActionListener(this);
			b11.addActionListener(this);
			b12.addActionListener(this);
			b13.addActionListener(this);
			b14.addActionListener(this);
			b15.addActionListener(this);
			b16.addActionListener(this);
			b17.addActionListener(this);
			
			// set the text area parameters
			ta = new JTextArea(50, 100);
	    	ta.setLineWrap(true);
	        ta.setWrapStyleWord(true);
	        sp = new JScrollPane(ta);
	        
	        // set the text area parameters
 			sta = new JTextArea(50, 100);
 	    	sta.setLineWrap(true);
 	        sta.setWrapStyleWord(true);
 	        ssp = new JScrollPane(sta);

			// add all the buttons to the left panel
	        buttonPanel.add(b1);
	        buttonPanel.add(b2);
	        buttonPanel.add(b3);
	        buttonPanel.add(b4);
	        buttonPanel.add(b5);
	        buttonPanel.add(b6);
	        buttonPanel.add(b7);
	        buttonPanel.add(b8);
	        buttonPanel.add(b9);
	        buttonPanel.add(b10);
	        buttonPanel.add(b11);
	        buttonPanel.add(b12);
	        buttonPanel.add(b13);
	        buttonPanel.add(b14);
	        buttonPanel.add(b15);
	        buttonPanel.add(b16);
	        buttonPanel.add(b17);
	        
	        // add the text area to the right panel
	        clientPanel.add(sp);
	        serverPanel.add(ssp);
	        
	        // add the panel to the main GUI panel
	        add(buttonPanel);
	        outputPanel.add(serverPanel);
	        outputPanel.add(clientPanel);
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
				reply = String.valueOf(inputFromAnotherObject.readObject());
				ta.append("MAHLI SERVER: " + reply);
				ta.append("\n");
				sta.append("MAHLI: " + command);
				sta.append("\n");
				
				
				if(reply.equalsIgnoreCase("exit")){
					Thread.sleep(10000);
					System.exit(0);
				}
			} catch (ClassNotFoundException | IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		/**
		 * Create the GUI and show it. For thread safety, this method should be
		 * invoked from the event-dispatching thread.
		 */
		private void createAndShowGUI() {

			// Create and set up the window.
			JFrame guiFrame = new JFrame("MAHLI Simulator App");
			guiFrame.setDefaultCloseOperation(guiFrame.DO_NOTHING_ON_CLOSE);

			// Create and set up the content pane.
			GUILayout newContentPane = new GUILayout();
			newContentPane.setOpaque(true); // content panes must be opaque
			guiFrame.setContentPane(newContentPane);

			// Display the window.
			guiFrame.pack();
			guiFrame.setVisible(true);
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
