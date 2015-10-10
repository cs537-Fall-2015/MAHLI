package mahli;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;
import generic.RoverThreadHandler;

public class MAHLIDummyClient extends RoverClientRunnable{

	public MAHLIDummyClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		    String command;
		    String reply;
		    while (true) {
				System.out.println("Following is the list of MAHLI Commands : ");
				System.out.println("1. MAHLI_Camera_ON");
				System.out.println("2. MAHLI_Camera_OFF");
				System.out.println("3. MAHLI_NightIllumination_ON");
				System.out.println("4. MAHLI_NightIllumination_OFF");
				System.out.println("5. MAHLI_AutoFocus_ON");
				System.out.println("6. MAHLI_AutoFocus_OFF");
				System.out.println("7. MAHLI_Video_ON");
				System.out.println("8. MAHLI_Video_OFF");
				System.out.println("9. MAHLI_Image_Capture");
				System.out.println("10. MAHLI_Image_store");
				System.out.println("11. MAHLI_Image_read");
				System.out.println("12. EXIT");
				System.out.println("Enter Command : ");
				command = br.readLine();
				//System.out.println(command);
				outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
				outputToAnotherObject.writeObject(command);
			    inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            reply = (String) inputFromAnotherObject.readObject();
	            System.out.println(reply);
	            if(reply.equalsIgnoreCase("exit"))
	            	break;
		    }
		        
		    
		    /*File file = new File("CS540ColorTest.jpg");
		    ProcessImage objprocessImage=new ProcessImage();
		    //System.out.println(objprocessImage.getImageColor(file));
		    
		    
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    outputToAnotherObject.writeObject("COLOR OF IMAGE IS "+objprocessImage.getImageColor(file));
		    inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
            String message = (String) inputFromAnotherObject.readObject();
            System.out.println("Module 1 Client: Message from Server - " + message.toUpperCase());*/
            
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
