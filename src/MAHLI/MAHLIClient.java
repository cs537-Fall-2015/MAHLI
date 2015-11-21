package MAHLI;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class MAHLIClient extends RoverClientRunnable {

	public MAHLIClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream outputToAnotherObject = null;

			outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
			outputToAnotherObject.writeObject("POWER_CAL 100");

			outputToAnotherObject.close();
			Thread.sleep(5000);
			closeAll();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}

	}

}
