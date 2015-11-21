package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import MAHLI.MAHLIGuiApp;
import MAHLI.MAHLIServer;
import MAHLI.ModuleOneServer;

public class MasterMain {

	public static void main(String[] args) {
		// Each module has its own port no.
		int port_mahli = 9010;
		int port_power = 9013;

		try {
			// create a thread for module one and starting the
			ModuleOneServer serverOne = new ModuleOneServer(port_power);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);

			// create a thread for module MAHLI and start thread
			MAHLIServer serverMahli = new MAHLIServer(port_mahli);

			// start the module MAHLI
			Thread server_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverMahli);

			MAHLIGuiApp clientMahli = new MAHLIGuiApp(port_mahli, null);
			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);

			// each server begins listening
			server_1.start();
			server_3.start();
			client_3.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
