package MAHLI.junk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import generic.RoverServerRunnable;

public class CopyOfMAHLIServer extends RoverServerRunnable {

	public CopyOfMAHLIServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {

		try {
			while (true) {

				final JTextArea mytextArea = null;

				System.out.println("Module MAHLI Server: Waiting for client request");

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						mytextArea.append("Module MAHLI Server: Waiting for client request");
					}
				});

				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();

				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(
						getRoverServerSocket().getSocket().getInputStream());

				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Module 1 Server: Message Received from Client - " + message.toUpperCase());

				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(
						getRoverServerSocket().getSocket().getOutputStream());

				// write object to Socket
				outputToAnotherObject.writeObject("Module MAHLI Server response Hi Client - " + message);

				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();

				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
			}
			System.out.println("Server: Shutting down Socket server 1!!");
			// close the ServerSocket object
			closeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		}

	}

}
