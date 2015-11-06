package MAHLI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import callback.CallBack;
import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

public class MAHLIServer extends RoverServerRunnable {

	public MAHLIServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Boolean camOnStatus = false;
		Boolean nightIlluminationStatus = false;
		Boolean autoFocusStatus = false;
		Boolean videoStatus = false;
		Boolean imageCaptureStatus = false;
		Boolean imageStoreStatus = false;
		Boolean imageReadStatus = false;
		Boolean exitStatus = false;
		Boolean DustCoverStatus = false;
		Boolean LEDStatus = false;
		File capturedFile = null;
		File source = new File("images");
		File destination = new File("captured");
		int port_power = 9013;
		CallBack cb = new CallBack();
		

		try {
			while (true) {
				System.out.println("MAHLI Server: Waiting for client request");
				int command;
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("MAHLI Server: Command Received from Client - "+ message);
				if(message.equals("MAHLI_Camera_ON"))
					command = 1;
				else if(message.equals("MAHLI_Camera_OFF"))
					command = 2;
				else if(message.equals("MAHLI_NightIllumination_ON"))
					command = 3;
				else if(message.equals("MAHLI_NightIllumination_OFF"))
					command = 4;
				else if(message.equals("MAHLI_AutoFocus_ON"))
					command = 5;
				else if(message.equals("MAHLI_AutoFocus_OFF"))
					command = 6;
				else if(message.equals("MAHLI_Video_ON"))
					command = 7;
				else if(message.equals("MAHLI_Video_OFF"))
					command = 8;
				else if(message.equals("MAHLI_Image_Capture"))
					command = 9;
				else if(message.equals("MAHLI_Image_store"))
					command = 10;
				else if(message.equals("MAHLI_Image_read"))
					command = 11;
				
				else if(message.equals("EXIT"))
					command = 12;
				else if(message.equals("Open_Dust_Cover"))
					command = 13;
				else if(message.equals("Close_Dust_Cover"))
					command = 14;
				else if(message.equals("Open_LED"))
					command = 15;
				else if(message.equals("Close_LED"))
					command = 16;
				else 
					command = 17;
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				
				// getRoverServerSocket().closeSocket();
				
		        switch (command) {
		            case 1:  
		            		if(camOnStatus){
		            			//System.out.println("Camera is already On");
		            			outputToAnotherObject.writeObject("Camera is already On");
		            		}
		            		else{
		            			//System.out.println("Camera turned On");
		            			camOnStatus = true;
		            			MAHLIClient clientMahli = new MAHLIClient(port_power, null);
		            			cb.done();
		            			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
		            			outputToAnotherObject.writeObject("Camera turned On ");
		            			client_3.start();
		            			}
		                     break;
		            case 2:  
		            		if(camOnStatus)
		            		{
		            			camOnStatus = false;
		            			//System.out.println("Camera turned off");
		            			outputToAnotherObject.writeObject("Camera turned off");
		            			cb.done();
		            		}
		            		else
		            		{
		            			//System.out.println("Camera is already turned off");
		            			outputToAnotherObject.writeObject("Camera is already turned off");
		            		}
		                     break;
		            case 3:  
		            	if(camOnStatus)
	            		{
	            			if(nightIlluminationStatus){
	            				//System.out.println("Night Illumination is already tuned on");
	            				outputToAnotherObject.writeObject("Night Illumination is already tuned on");
	            			}
	            			else
	            			{
	            				nightIlluminationStatus = true;
	            				//System.out.println("Night Illumination turned On");
	            				outputToAnotherObject.writeObject("Night Illumination turned On");
	            				cb.done();
	            			}
	            				
	            		}
	            		else
	            		{
	            			// System.out.println("Please turn on the camera to proceed");
	            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
	            		}
		                     break;
		            case 4:  if(camOnStatus)
            		{
            			if(nightIlluminationStatus){
            				nightIlluminationStatus = false;
            				// System.out.println("Night Illumination is turned off");
            			    outputToAnotherObject.writeObject("Night Illumination is turned off");
            			    cb.done();
            			}
            			else
            			{
            				// System.out.println("Night Illumination already turned off");
            				outputToAnotherObject.writeObject("Night Illumination already turned off");

            			}
            				
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		                     
		            case 5:  if(camOnStatus)
            		{
            			if(autoFocusStatus){
            				// System.out.println("Auto Focus is already turned ON");
            				outputToAnotherObject.writeObject("Auto Focus is already turned ON");
            			}
            			else
            			{
            				// System.out.println("Auto Focus is now turned ON");
            				outputToAnotherObject.writeObject("Auto Focus is now turned ON");
            				cb.done();
            				autoFocusStatus = true;
            			}
            				
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		            
		                     break;
		            case 6:  
		            	if(camOnStatus)
	            		{
	            			if(autoFocusStatus){
	            				autoFocusStatus = false;
	            				// System.out.println("Auto Focus is now turned OFF");
	            				outputToAnotherObject.writeObject("Auto Focus is now turned OFF");
	            				cb.done();
	            			}
	            			else
	            			{
	            				// System.out.println("Auto Focus is already turned OFF");
	            				outputToAnotherObject.writeObject("Auto Focus is already turned OFF");
	            			}
	            				
	            		}
	            		else
	            		{
	            			// System.out.println("Please turn on the camera to proceed");
	            		    outputToAnotherObject.writeObject("Please turn on the camera to proceed");

	            		}
		                     break;
		            case 7:  if(camOnStatus)
            		{
            			if(videoStatus){
            				// System.out.println("Video is already turned ON");
            				outputToAnotherObject.writeObject("Video is already turned ON");
            			}
            			else
            			{
            				// System.out.println("Video is now turned ON");
            				outputToAnotherObject.writeObject("Video is now turned ON");
            				cb.done();
            				videoStatus = true;
            			}	
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 8:  if(camOnStatus)
            		{
            			if(videoStatus){
            				videoStatus = false;
            				// System.out.println("Video is now turned OFF");
            				outputToAnotherObject.writeObject("Video is now turned OFF");
            				cb.done();
            			}
            			else
            			{
            				System.out.println("Video is already turned OFF");
            				outputToAnotherObject.writeObject("Video is already turned OFF");

            			}
            				
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 9:  if( camOnStatus )
            		
		            {
		            	
		            				if(DustCoverStatus == true)
		            				{
								            	imageCaptureStatus = true;
								            	// System.out.println("Image Captured");
								            	Random rand = new Random();
								            	int index = rand.nextInt(source.list().length-1) + 1;
								        		capturedFile = new File(source.list()[index]);
								        		System.out.println(capturedFile);
								            	outputToAnotherObject.writeObject("Image Captured - "+capturedFile.toString());
								            	cb.done();

		            				}
		            				else
		                    		{
		                    			// System.out.println("Please turn on the camera to proceed");
		                    			outputToAnotherObject.writeObject("Open Dust Cover");
		                    		}
		            }
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 10: if(camOnStatus)
            		{
		            	imageStoreStatus = true;
		            	// System.out.println("Image Stored");
		            	if(capturedFile != null){
		            		System.out.println(capturedFile);
		            		Files.copy(new File(source.toString()+"/"+capturedFile.toString()).toPath(), 
		            				new File(destination.toString()+"/"+ capturedFile.toString()).toPath(), 
		            				StandardCopyOption.REPLACE_EXISTING);
		            		outputToAnotherObject.writeObject("Image Stored - "+capturedFile.toString());
		            		capturedFile = null;
		            		cb.done();
		            		}
		            	else
		            		outputToAnotherObject.writeObject("No image captured");
            		}
		            else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 11: if(camOnStatus)
            		{
		            	if(destination.list().length>0){
			            	File file = new File(destination.toString()+"/"+destination.list()[0]);
			    		    ProcessImage objprocessImage=new ProcessImage();
			            	imageReadStatus = true;
			            	System.out.println(file.toString());
			            	//outputToAnotherObject.writeObject("Image Read");
			            	outputToAnotherObject.writeObject("Image Detected "+file.toString()+" and Processed.");
			            	objprocessImage.getImageColor(file);
			            	file.delete();
			            	cb.done();
			            }
		            	else
		            		outputToAnotherObject.writeObject("Image needs to be Captured First");
		            		
            		}
		            else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 12: exitStatus = true;
		            	outputToAnotherObject.writeObject("Exit");
		            	cb.done();
		                     break;
		                     
		            case 13:  if(camOnStatus)
            		{
            			if(DustCoverStatus){
            				// System.out.println("Video is already turned ON");
            				outputToAnotherObject.writeObject("Dust Cover  is already turned OPEN");
            			}
            			else
            			{
            				// System.out.println("Video is now turned ON");
            				outputToAnotherObject.writeObject("Dust Cover is now OPEN");
            				cb.done();
            				DustCoverStatus = true;
            			}	
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
                    
		            case 14:  
		            	if(camOnStatus)
	            		{
		        		if(DustCoverStatus)
		        		{
		        			DustCoverStatus = false;
		        			//System.out.println("Camera turned off");
		        			outputToAnotherObject.writeObject("Dust Cover is close");
		        			cb.done();
		        		}
		        		else
		        		{
		        			//System.out.println("Camera is already turned off");
		        			outputToAnotherObject.writeObject("Dust Cover is already Close");
		        		}
		        }
        		else
        		{
        			// System.out.println("Please turn on the camera to proceed");
        			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
        		}
		                 break; 
		                 
		                 
		          
		            case 15:  
	            		if(camOnStatus ){
	            			
	            			if(LEDStatus){
	            			//System.out.println("Camera is already On");
	            			outputToAnotherObject.writeObject("LED is already turned on");
	            		}
	            		else{
	            			//System.out.println("Camera turned On");
	            			LEDStatus = true;
	            			MAHLIClient clientMahli = new MAHLIClient(port_power, null);
	            			cb.done();
	            			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
	            			outputToAnotherObject.writeObject("LED turned on ");
	            			client_3.start();
	            			}
		        		}
        			else
        			{
        			// System.out.println("Please turn on the camera to proceed");
        			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
        			}
	                     break;  
		                 
		            case 16:  
		            	if(camOnStatus ){
		        		if(LEDStatus)
		        		{
		        			LEDStatus = false;
		        			//System.out.println("Camera turned off");
		        			outputToAnotherObject.writeObject("LED turned off");
		        			cb.done();
		        		}
		        		else
		        		{
		        			//System.out.println("Camera is already turned off");
		        			outputToAnotherObject.writeObject("LED is already turned off");
		        		}
		            	}
	        			else
	        			{
	        			// System.out.println("Please turn on the camera to proceed");
	        			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
	        			}
		                 break;    
		                     
		                     
		                     
		                     
		            case 17: 
		            	outputToAnotherObject.writeObject("Invalid Command");
                    		 break;
		            default: 
		            		outputToAnotherObject.writeObject("Exit");
		            		cb.done();
		                     break;
		        }
		        
		        
		        
		        
		     // terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
		        
		     // close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
			}
			System.out.println("Server: Shutting down Socket server MAHLI!!");
			// close the ServerSocket object
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
