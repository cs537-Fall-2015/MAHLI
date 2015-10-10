package mahli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
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
		ButtonDemo bd = new ButtonDemo();
		bd.mainRun();

	}

	public class ButtonDemo extends JPanel implements ActionListener {
		protected JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;
		protected JTextArea ta;
		protected JScrollPane sp;

		public ButtonDemo() {
			
			Dimension d=new Dimension(1500,500);
	    	setPreferredSize(d);
	    	setLocation(0, 0);
	    	setBorder(BorderFactory.createLineBorder(Color.black));
	    	ta=new JTextArea(8,100);
	    	ta.setLineWrap(true);
	        ta.setWrapStyleWord(true);
	        ta.setLocation(300, 300);
	        sp = new JScrollPane(ta);  

			b1 = new JButton("MAHLI_Camera_ON");
			b1.setVerticalTextPosition(AbstractButton.CENTER);
			b1.setHorizontalTextPosition(AbstractButton.LEADING); // aka LEFT,
																	// for
																	// left-to-right
																	// locales
			b1.setMnemonic(KeyEvent.VK_D);
			b1.setActionCommand("MAHLI_Camera_ON");

			b2 = new JButton("MAHLI_NightIllumination_ON");
			b2.setVerticalTextPosition(AbstractButton.BOTTOM);
			b2.setHorizontalTextPosition(AbstractButton.CENTER);
			b2.setMnemonic(KeyEvent.VK_M);

			b3 = new JButton("MAHLI_NightIllumination_OFF");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b3.setMnemonic(KeyEvent.VK_E);
			b3.setActionCommand("MAHLI_NightIllumination_OFF");
			// b3.setEnabled(false);

			b4 = new JButton("MAHLI_AutoFocus_ON");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b4.setMnemonic(KeyEvent.VK_E);
			b4.setActionCommand("MAHLI_AutoFocus_ON");
			// b4.setEnabled(false);

			b5 = new JButton("MAHLI_AutoFocus_OFF");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b5.setMnemonic(KeyEvent.VK_E);
			b5.setActionCommand("MAHLI_AutoFocus_OFF");
			// b5.setEnabled(false);

			b6 = new JButton("MAHLI_Video_ON");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b6.setMnemonic(KeyEvent.VK_E);
			b6.setActionCommand("MAHLI_Video_ON");
			// b6.setEnabled(false);

			b7 = new JButton("MAHLI_Video_OFF");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b7.setMnemonic(KeyEvent.VK_E);
			b7.setActionCommand("MAHLI_Video_OFF");
			// b7.setEnabled(false);

			b8 = new JButton("MAHLI_Image_Capture");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b8.setMnemonic(KeyEvent.VK_E);
			b8.setActionCommand("MAHLI_Image_Capture");
			// b8.setEnabled(false);

			b9 = new JButton("MAHLI_Image_store");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b9.setMnemonic(KeyEvent.VK_E);
			b9.setActionCommand("MAHLI_Image_store");
			// b9.setEnabled(false);

			b10 = new JButton("MAHLI_Image_read");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b10.setMnemonic(KeyEvent.VK_E);
			b10.setActionCommand("MAHLI_Image_read");
			// b10.setEnabled(false);

			b11 = new JButton("MAHLI_Camera_OFF");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b11.setMnemonic(KeyEvent.VK_E);
			b11.setActionCommand("MAHLI_Camera_OFF");
			// b11.setEnabled(false);

			b12 = new JButton("EXIT");
			// Use the default text position of CENTER, TRAILING (RIGHT).
			b12.setMnemonic(KeyEvent.VK_E);
			b12.setActionCommand("EXIT");
			// b12.setEnabled(false);

			// Listen for actions on buttons 1 and 3.
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

			b1.setToolTipText("Click this button to disable the middle button.");
			b2.setToolTipText("This middle button does nothing when you click it.");
			b3.setToolTipText("Click this button to enable the middle button.");

			// Add Components to this container, using the default FlowLayout.
			add(b1);
			add(b11);
			add(b2);
			add(b3);
			add(b4);
			add(b5);
			add(b6);
			add(b7);
			add(b8);
			add(b9);
			add(b10);
			add(b12);
			add(sp);
		}

		public void actionPerformed(ActionEvent e) {
			ObjectOutputStream outputToAnotherObject = null;
			ObjectInputStream inputFromAnotherObject = null;

			String command;
			String reply = "";

			command = e.getActionCommand();
			//System.out.println(command);

			try {
				outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
				outputToAnotherObject.writeObject(command);
				inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
				reply = (String) inputFromAnotherObject.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			//System.out.println("MAHLI CLIENT : Response from server - "+reply);
			ta.append("MAHLI CLIENT : Response from server - "+reply);
			ta.append("\n");
			
			if(reply.equalsIgnoreCase("exit"))
				System.exit(0);

		}

		/** Returns an ImageIcon, or null if the path was invalid. */
		protected  ImageIcon createImageIcon(String path) {
			java.net.URL imgURL = ButtonDemo.class.getResource(path);
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
			ButtonDemo newContentPane = new ButtonDemo();
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
