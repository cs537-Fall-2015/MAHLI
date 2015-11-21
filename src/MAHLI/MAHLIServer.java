package MAHLI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import callback.CallBack;
import generic.RoverServerRunnable;
import generic.RoverThreadHandler;
import json.ReadFromJSON;
import MAHLI.ZoomLabel;

public class MAHLIServer extends RoverServerRunnable implements Serializable {
	private static final long serialVersionUID = 1L;

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
		Boolean imageAnalyzeStatus = false;
		Boolean exitStatus = false;
		Boolean DustCoverStatus = false;
		Boolean InfraredStatus = false;
		File capturedFile = null;
		File dataFile = null;
		File imagesPath = new File("src/MAHLI/resources/images/");
		File dataPath = new File("src/MAHLI/resources/data/");
		int port_client = 9010;
		CallBack cb = new CallBack();
		ReadFromJSON readJSON = new ReadFromJSON();
		Random random = new Random();
		Integer number = 0;

		try {
			while (true) {
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
				else if(message.equals("EXIT"))
					command = 17;
				else 
					command = 18;
				
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
		            			MAHLIClient clientMahli = new MAHLIClient(port_client, null);
		            			cb.done();
		            			Thread client_thread = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
		            			outputToAnotherObject.writeObject("Camera turned On ");
		            			client_thread.start();
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
		            		if(camOnStatus) {
		        				if(DustCoverStatus == true) {
					            	imageCaptureStatus = true;
					            	random = new Random();
					            	number = random.nextInt(10) + 1;
					            	progress(25);
					        		capturedFile = new File(imagesPath + "/" + number + ".jpg");
					        		dataFile = new File(dataPath + "/data" + number + ".json");
					        		imageAnalyzeStatus = true;
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
				            		readJSON.setJSONArray(dataFile);
				            		outputToAnotherObject.writeObject("Reading image data");
				            		progress(25);
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
				            	if(imageAnalyzeStatus){
				            		outputToAnotherObject.writeObject("Viewing Image: " + capturedFile.toString());
				            		final ZoomLabel label = new ZoomLabel();
					                try{
					                    Image img = javax.imageio.ImageIO.read(new File(capturedFile.toString()));
					                    label.setImage(img);
					                }catch(Exception e) {
					                    System.err.println("Couldn't load image for demonstration");
					                    e.printStackTrace();
					                    System.exit(0);
					                }

					                JFrame frame = new JFrame();

					                frame.add(new JScrollPane(label));

					                JSlider slider = new JSlider(0,1000,100);
					                slider.setPaintLabels(true);
					                slider.addChangeListener(new ChangeListener() {
					                    public void stateChanged(ChangeEvent e) {
					                        int val = ((JSlider) e.getSource()).getValue();
					                        label.setScale(val * .01f, val * .01f);
					                    }
					                });

					                frame.add(slider, java.awt.BorderLayout.SOUTH);
					                frame.setSize(400,400);
					                frame.setLocationRelativeTo(null);
					                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					                frame.setVisible(true);
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
				            	if(imageAnalyzeStatus){
					            	System.out.println(dataFile.toString());
					            	outputToAnotherObject.writeObject("Image data: " + dataFile.getName() + " is analyzing.");
					            	
					            	progress(10);
					            	outputToAnotherObject.writeObject("Completed");
					            	readJSON.setJSONArray(dataFile);
					            	
					            	MAHLIDisplayCharts displayCharts = new MAHLIDisplayCharts();
					            	ChartModel chartModel = new ChartModel();
					            	PieChart pieChart = new PieChart();
					            	BarChart barChart = new BarChart();
					            	chartModel.setData(readJSON.getData());
					            	chartModel.setDataName(readJSON.getDataName());
					            	pieChart.setModel(chartModel);
					        		barChart.setModel(chartModel);
					        		
					            	JFrame frame = new JFrame();
					        	    frame.setTitle("MAHLI Charts");
					        	    frame.getContentPane().add(displayCharts, BorderLayout.CENTER);
					        	    displayCharts.init();
					        	    displayCharts.start();
					        	    frame.setSize(400,400);
					        	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        	    frame.setLocationRelativeTo(null); // Center the frame
					        	    frame.setVisible(true);
					            		
					            	cb.done();
					            }
				            	else
				            		outputToAnotherObject.writeObject("Image needs to be Captured First");
		            		}
				            else {
		            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
		            		}
		                    break;
		            case 17: // EXIT
			            	exitStatus = true;
		            		outputToAnotherObject.writeObject("Exit");
		            		cb.done();
		                    break;
		            case 18: 
		            		outputToAnotherObject.writeObject("Invalid Command");
		            		cb.done();
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
					outputToAnotherObject.writeObject("SERVER: Performing System Shutdown");
					if(camOnStatus){
						System.out.println("Turning the Camera off ...");
						outputToAnotherObject.writeObject("Turning the Camera off ...");
					}
					if(nightIlluminationStatus){
						System.out.println("Turning the NightIllumination off ...");
						outputToAnotherObject.writeObject("Turning the NightIllumination off ...");
					}
					if(autoFocusStatus){
						System.out.println("Turning the Autofocus off ...");
						outputToAnotherObject.writeObject("Turning the Autofocus off ...");
						progress(10);
					}
					if(videoStatus){
						System.out.println("Turning the Video off ...");
						outputToAnotherObject.writeObject("Turning the Video off ...");
					}
					if(DustCoverStatus){
						System.out.println("Closing the Dust Covers ...");
						outputToAnotherObject.writeObject("Closing the Dust Covers ...");
						progress(50);
					}
					if(InfraredStatus){
						System.out.println("Turning the Infrared off ...");
						outputToAnotherObject.writeObject("Turning the Infrared off ...");
					}
					System.out.println("SERVER: System Shutdown Complete");
					outputToAnotherObject.writeObject("SERVER: System Shutdown Complete");
					break;
				}
		        
				
				cb.done();
			}
			// close resources
			inputFromAnotherObject.close();
			outputToAnotherObject.close();
			
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
            	System.out.println(" Completed\n"); // End message
            }
        }
	}
}
