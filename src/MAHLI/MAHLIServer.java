package MAHLI;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import javax.swing.JProgressBar;

import org.json.simple.JSONArray;

import MAHLI.junk.MAHLIClient;
import callback.CallBack;
import generic.RoverServerRunnable;
import generic.RoverThreadHandler;
import json.ReadFromJSON;

public class MAHLIServer extends RoverServerRunnable {

	public MAHLIServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		ObjectInputStream inputFromAnotherObject;
		ObjectOutputStream outputToAnotherObject;
		Boolean camOnStatus = false;
		Boolean nightIlluminationStatus = false;
		Boolean autoFocusStatus = false;
		Boolean videoStatus = false;
		Boolean imageCaptureStatus = false;
		Boolean imageStoreStatus = false;
		Boolean imageReadStatus = false;
		Boolean exitStatus = false;
		Boolean DustCoverStatus = false;
		Boolean InfraredStatus = false;
		File capturedFile = null;
		File imagesPath = new File("src/MAHLI/resources/images/");
		File dataPath = new File("src/MAHLI/resources/data/");
		int port_power = 9013;
		CallBack cb = new CallBack();
		ReadFromJSON readJSON = new ReadFromJSON();
		Random random = new Random();
		Integer number = 0;
		

		try {
			while (true) {
				System.out.println("MAHLI Server: Waiting for client request");
				int command;
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("MAHLI Server: Command Received from Client - " + message);
				if(message.equals("MAHLI_Camera_ON"))
					command = 1;
				else if(message.equals("MAHLI_Camera_OFF"))
					command = 2;
				else if(message.equals("MAHLI_NightIllumination_ON"))
					command = 3;
				else if(message.equals("MAHLI_NightIllumination_OFF"))
					command = 4;
				else if(message.equals("MAHLI_Infrared_ON"))
					command = 5;
				else if(message.equals("MAHLI_Infrared_OFF"))
					command = 6;
				else if(message.equals("MAHLI_AutoFocus_ON"))
					command = 7;
				else if(message.equals("MAHLI_AutoFocus_OFF"))
					command = 8;
				else if(message.equals("MAHLI_Video_ON"))
					command = 9;
				else if(message.equals("MAHLI_Video_OFF"))
					command = 10;
				else if(message.equals("MAHLI_Dust_Cover_OPEN"))
					command = 11;
				else if(message.equals("MAHLI_Dust_Cover_CLOSE"))
					command = 12;
				else if(message.equals("MAHLI_Image_CAPTURE"))
					command = 13;
				else if(message.equals("MAHLI_Image_READ"))
					command = 14;
				else if(message.equals("MAHLI_Image_VIEW"))
					command = 15;
				else if(message.equals("MAHLI_Image_ANALYZE"))
					command = 16;
				else if(message.equals("DAY_TIME"))
				{
					command = 1;
					command = 7;
				}
				else if(message.equals("NIGHT_TIME"))
					command = 18;
				else if(message.equals("EXIT"))
					command = 19;
				else 
					command = 20;
				
				// create ObjectOutputStream object
				outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				
				// getRoverServerSocket().closeSocket();
				
		        switch (command) {
		            case 1: // CAMERA_ON
		            		if(camOnStatus) {
		            			outputToAnotherObject.writeObject("Camera is already On");
		            		}
		            		else {
		            			camOnStatus = true;
		            			MAHLIClient clientMahli = new MAHLIClient(port_power, null);
		            			cb.done();
		            			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
		            			outputToAnotherObject.writeObject("Camera turned On ");
		            			client_3.start();
		            		}
		                    break;
		            case 2: // CAMERA_OFF
		            		if(camOnStatus) {
		            			camOnStatus = false;
		            			outputToAnotherObject.writeObject("Camera turned off");
		            			cb.done();
		            		}
		            		else {
		            			outputToAnotherObject.writeObject("Camera is already turned off");
		            		}
		                    break;
		            case 3: // NIGHTILLUMINATION_ON
		            		if(camOnStatus) {
		            			if(nightIlluminationStatus) {
		            				outputToAnotherObject.writeObject("Night Illumination is already tuned on");
		            			}
		            			else {
		            				nightIlluminationStatus = true;
		            				outputToAnotherObject.writeObject("Night Illumination turned On");
		            				cb.done();
		            			}
		            		}
		            		else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
		                    break;
		            case 4: // NIGHTILLUMINATION_OFF
		            		if(camOnStatus) {
		            			if(nightIlluminationStatus) {
		            				nightIlluminationStatus = false;
		            			    outputToAnotherObject.writeObject("Night Illumination is turned off");
		            			    cb.done();
		            			}
		            			else {
		            				outputToAnotherObject.writeObject("Night Illumination already turned off");
		            			}
	            			}
		            		else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
		                    break;
		            case 5: // INFRARED_ON
			            	if(camOnStatus) {
		            			if(InfraredStatus) {
			            			outputToAnotherObject.writeObject("Infrared is already turned on");
			            		}
			            		else {
			            			InfraredStatus = true;
			            			outputToAnotherObject.writeObject("Infrared turned on ");
			            			cb.done();
			            			}
			        		}
		        			else {
			        			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		        			}
	                     	break;
		            case 6: // INFRARED_OFF
			            	if(camOnStatus) {
				        		if(InfraredStatus) {
				        			InfraredStatus = false;
				        			outputToAnotherObject.writeObject("Infrared turned off");
				        			cb.done();
				        		}
				        		else {
				        			outputToAnotherObject.writeObject("Infrared is already turned off");
				        		}
			            	}
		        			else {
			        			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		        			}
			                break;
		            case 7: // AUTOFOCUS_ON
		            		if(camOnStatus) {
		            			if(autoFocusStatus){
		            				outputToAnotherObject.writeObject("Auto Focus is already turned ON");
		            			}
		            			else {
		            				outputToAnotherObject.writeObject("Auto Focus is now turned ON");
		            				cb.done();
		            				autoFocusStatus = true;
		            			}
		            		}
		            		else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
		                     break;
		            case 8: // AUTOFOCUS_OFF
		            		if(camOnStatus) {
		            			if(autoFocusStatus){
		            				autoFocusStatus = false;
		            				outputToAnotherObject.writeObject("Auto Focus is now turned OFF");
		            				cb.done();
		            			}
		            			else {
		            				outputToAnotherObject.writeObject("Auto Focus is already turned OFF");
		            			}
		            		}
		            		else {
		            		    outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
			                break;
		            case 9: // VIDEO_ON
		            		if(camOnStatus) {
		            			if(videoStatus){
		            				outputToAnotherObject.writeObject("Video is already turned ON");
		            			}
		            			else {
		            				outputToAnotherObject.writeObject("Video is now turned ON");
		            				cb.done();
		            				videoStatus = true;
		            			}	
		            		}
		            		else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
				           	break;
		            case 10: // VIDEO_OFF
		            		if(camOnStatus) {
		            			if(videoStatus){
		            				videoStatus = false;
		            				outputToAnotherObject.writeObject("Video is now turned OFF");
		            				cb.done();
		            			}
		            			else {
		            				System.out.println("Video is already turned OFF");
		            				outputToAnotherObject.writeObject("Video is already turned OFF");
		            			}
							}
							else {
								outputToAnotherObject.writeObject("Please turn on the camera to proceed");
							}
							break;
		            case 11: // DUST_COVERS_OPEN
		            		if(camOnStatus) {
		            			if(DustCoverStatus){
		            				outputToAnotherObject.writeObject("Dust Cover  is already turned OPEN");
		            			}
		            			else {
		            				outputToAnotherObject.writeObject("Dust Cover is now OPEN");
		            				cb.done();
		            				DustCoverStatus = true;
		            			}	
		            		}
		            		else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
		                    break;
		            case 12: // DUST_COVERS_CLOSE
		            		if(camOnStatus) {
				        		if(DustCoverStatus) {
				        			DustCoverStatus = false;
				        			outputToAnotherObject.writeObject("Dust Cover is close");
				        			cb.done();
				        		}
				        		else {
				        			outputToAnotherObject.writeObject("Dust Cover is already Close");
				        		}
					        }
			        		else {
			        			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
			        		}
		                 	break;
		            case 13: // IMAGE_CAPTURE
		            		if( camOnStatus ) {
		        				if(DustCoverStatus == true) {
					            	imageCaptureStatus = true;
					            	// System.out.println("Image Captured");
					            	random = new Random();
					            	number = random.nextInt(10) + 1;
					        		capturedFile = new File(imagesPath.list()[number]);
					        		System.out.println("MAHLI Server: image captured - " + capturedFile);
					            	outputToAnotherObject.writeObject("Image Captured: " + capturedFile.toString());
					            	cb.done();
		        				}
		        				else {
		                			outputToAnotherObject.writeObject("Open Dust Cover");
		                		}
		            		}
		            		else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
				            break;
		            case 14: // IMAGE_READ
		            		if(camOnStatus) {
				            	if(capturedFile != null) {
				            		File file = new File(dataPath + "/data" + number + ".json");
				            		readJSON.setJSONArray(file);
				            		outputToAnotherObject.writeObject("Reading image data");
				            		readJSON.printJSONArray();
				            		cb.done();
				            	}
				            	else
				            		outputToAnotherObject.writeObject("No image captured");
							}
							else {
								outputToAnotherObject.writeObject("Please turn on the camera to proceed");
							}
							break;
		            case 15: // IMAGE_VIEW
		            		if(camOnStatus) {
				            	if(imagesPath.listFiles().length > 0){
					            	
					            	cb.done();
					            }
				            	else
				            		outputToAnotherObject.writeObject("Image needs to be Captured First");
		            		}
				            else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
		                    break;
		            case 16: // IMAGE_ANALYZE
		            		if(camOnStatus) {
				            	if(dataPath.listFiles().length > 0){
					            	//File file = new File(destination.toString()+"/"+destination.list()[0]);
					            	File file = new File(dataPath + "/data" + number + ".json");
					            	System.out.println(file.toString());
					            	outputToAnotherObject.writeObject("Image data: " + file.getName() + " is analyzing.");
					            	
					            	progress(10);
					            	outputToAnotherObject.writeObject("Completed");
					            	readJSON.setJSONArray(file);
					            	/*JSONArray json = readJSON.getJSONArray();
					            	System.out.println(readJSON.getJSONArray().size() + " - " + json.size());
					            	JSONArray j = readJSON.getJSONArray();
					            	System.out.println(readJSON.getJSONArray().size() + " - " + j.size());*/
					            	ChartModel model = new ChartModel();
					            	model.setDataName(readJSON.getDataName());
					            	model.setData(readJSON.getData());
					            	/*System.out.println(model.getDataName().size() + " - " + model.getData().size());
					            	System.out.println(model.getData());
					            	System.out.println(model.getDataName());
					            	System.out.println(readJSON.getDataName().size() + " - " + readJSON.getData().size());
					            	BarChart bar = new BarChart();
					            	bar.setDataName(readJSON.getDataName());
					            	bar.setData(readJSON.getData());
					            	PieChart pie = new PieChart();
					            	pie.setDataName(readJSON.getDataName());
					            	pie.setData(readJSON.getData());
					            	System.out.println(pie.getData());
					            	System.out.println(pie.getDataName());
					            	System.out.println(bar.getData());
					            	System.out.println(bar.getDataName());*/
					            	MAHLIDisplayCharts displayCharts = new MAHLIDisplayCharts();
					            	model.setImagePath(capturedFile.getCanonicalPath());
					            	/*System.out.println(capturedFile);
					            	System.out.println(capturedFile.toString());
					            	System.out.println(capturedFile.getCanonicalPath());*/
					            	displayCharts.displayApplet(); // FIX DEFAULT CLOSE BUTTON
					            	cb.done();
					            }
				            	else
				            		outputToAnotherObject.writeObject("Image needs to be Captured First");
		            		}
				            else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
		                    break;
		       
		            		
		            case 19: // EXIT
			            	exitStatus = true;
		            		outputToAnotherObject.writeObject("Exit");
		            		cb.done();
		                    break;
		            case 20: 
		            		outputToAnotherObject.writeObject("Invalid Command");
                    		break;
		            default: 
		            		outputToAnotherObject.writeObject("Exit");
		            		System.out.println("SERVER: Shutting down Socket server MAHLI!!");
		            		cb.done();
		                    break;
		        }
		        
		        // terminate the server if client sends exit request
				if (exitStatus){
					System.out.println("SERVER: Performing System Shutdown");
					if(camOnStatus){
						System.out.println("Turning the Camera off ...");
					}
					if(nightIlluminationStatus){
						System.out.println("Turning the NightIllumination off ...");
					}
					if(autoFocusStatus){
						System.out.println("Turning the Autofocus off ...");
						progress(10);
					}
					if(videoStatus){
						System.out.println("Turning the Video off ...");
					}
					if(DustCoverStatus){
						System.out.println("Closing the Dust Covers ...");
						progress(50);
					}
					if(InfraredStatus){
						System.out.println("Turning the Infrared off ...");
					}
					System.out.println("SERVER: System Shutdown Complete");
					break;
				}
		        
				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
			}
			
			// close the ServerSocket object
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void progress(int n) throws InterruptedException {
        int x = 0;
        while(x <= n) {
        	System.out.print("#");
        	Thread.sleep(100);
            x++; // Setting incremental values
            if (x == n){
            	System.out.println(" Completed"); // End message
            }
        }
	}
}

