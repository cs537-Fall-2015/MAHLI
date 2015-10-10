package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import mahli.MAHLIClient;
import mahli.MAHLIDummyClient;
import mahli.MAHLIDummyUiClient;
import mahli.MAHLIServer;
import module1.ModuleOneClient;
import module1.ModuleOneServer;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = 9897;
		int port_two = 9898;
		int port_mahli = 9010;
		int port_power = 9013;
		
		try {
			
			// create a thread for module one
			ModuleOneServer serverOne = new ModuleOneServer(port_power);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);
						
						
			//create a thread for module mahli
			MAHLIServer serverMahli = new MAHLIServer(port_mahli);
			Thread server_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverMahli);
						
			MAHLIDummyUiClient clientMahli = new MAHLIDummyUiClient(port_mahli, null);
			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
						
			// each server begins listening
			server_1.start();
			server_3.start();
			client_3.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
