package MAHLI;
import java.awt.event.*;
import java.io.File;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import json.ReadFromJSON;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ChartModel {
	private ArrayList<Long> data =  new ArrayList<Long>();
	private ArrayList<String> dataName = new ArrayList<String>();
	String imagePath = "";
	
	private transient Vector actionListeners;
	
	public ChartModel() {
	}
	
	public void setData(ArrayList<Long> data) {
		this.data = data;
	}
	
	public ArrayList<Long> getData() {
		return data;
	}
	
	public void setDataName(ArrayList<String> dataName) {
		this.dataName = dataName;
	}
	
	public ArrayList<String> getDataName() {
		return dataName;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListeners != null && actionListeners.contains(l)) {
			Vector v = (Vector) actionListeners.clone();
			v.removeElement(l);
			actionListeners = v;
		}
	}
	
	public synchronized void addActionListener(ActionListener l) {
		Vector v = actionListeners == null ? new Vector(2) : (Vector) actionListeners.clone();
		if (!v.contains(l)) {
			v.addElement(l);
			actionListeners = v;
		}
	}
	
	protected void fireActionPerformed(ActionEvent e) {
		if (actionListeners != null) {
			Vector listeners = actionListeners;
			int count = listeners.size();
			for (int i = 0; i < count; i++) {
				((ActionListener) listeners.elementAt(i)).actionPerformed(e);
			}
		}
	}
	
	public void setChartData(ArrayList<String> newDataName, ArrayList<Long> newData) {
	    dataName = newDataName;
	    data = newData;
	    // System.arraycopy(newData, 0, data, 0, newData.length);
	    fireActionPerformed(new ActionEvent(this,
	      ActionEvent.ACTION_PERFORMED, null));
	  }
}
