package mahli;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class CopyOfMAHLIClient extends RoverClientRunnable{

	public CopyOfMAHLIClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		    /*
		    //Send 5 messages to the Server
	        for(int i = 0; i < 5; i++){
	            //write to socket using ObjectOutputStream
	            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
	            
	            System.out.println("=================================================");
	            System.out.println("Module MAHLI Client: Sending request to MAHLI SERVER");
	            System.out.println("=================================================");
	            
	            if(i == 4){
	            	outputToAnotherObject.writeObject("exit");
	            }
	            else {
	            	outputToAnotherObject.writeObject("Message #" + i + " from module MAHLI.");
	            }
	            
	            //read the server response message
	            inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            String message = (String) inputFromAnotherObject.readObject();
	            System.out.println("Module 1 Client: Message from Server - " + message.toUpperCase());
	            
	            //close resources
	            inputFromAnotherObject.close();
	            outputToAnotherObject.close();
	            Thread.sleep(5000);
	        }
	        */
		    
		    File file = new File("CS540ColorTest.jpg");
		    ProcessImage objprocessImage=new ProcessImage();
		    //System.out.println(objprocessImage.getImageColor(file));
		    
		    
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    outputToAnotherObject.writeObject("COLOR OF IMAGE IS ");
		    objprocessImage.getImageColor(file);
		    inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
            String message = (String) inputFromAnotherObject.readObject();
            System.out.println("Module 1 Client: Message from Server - " + message.toUpperCase());
            
            //close resources
            inputFromAnotherObject.close();
            outputToAnotherObject.close();
            Thread.sleep(5000);
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}
		
	}

}
