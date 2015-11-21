package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import MAHLI.MAHLIGuiApp;
import MAHLI.MAHLIServer;

public class MasterMain {

	public static void main(String[] args) {
		// client port number
		int port_client = 9010;
		
		try {
			// start the MAHLI client module
			MAHLIServer serverMahli = new MAHLIServer(port_client);

			// create the MAHLI client thread
			Thread server_mahli = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverMahli);
			
			// start the MAHLI client gui
			MAHLIGuiApp mahliGUI = new MAHLIGuiApp(port_client, null);
			
			// create a thread for the MAHLI client gui
			Thread client_mahli = RoverThreadHandler.getRoverThreadHandler().getNewThread(mahliGUI);
						
			// each server begins listening
			server_mahli.start();
			client_mahli.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
