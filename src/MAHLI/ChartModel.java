package MAHLI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ChartModel {
	private transient Vector actionListeners;
	private double[] data = {294,3894,13,103,495,7053,64,23715,18,17245,950,180,2323,80,96,121,1538,6327,11,127,3318,56,66,53,2031,106,130,72,3478,17293,8,5559,7,2485,4,415,28,233,2088,15168,25,3451,6499,11445,3645,19};
	private String[] dataName = {"TALC","BORNITE","Andradite","BAUXITE","Pyroxene","CORUNDUM","Choride","Iron","PYRRHOTITE","EPIDOTE","CHLORITE","DOLOMITE","Beryl","Bismuth","Kaolinite","chlorotoid","Copper","QUARTZ","OLIVINE","SERPENTINE","PLAGIOCLASE","PYRITE","BIOTITE","Sodalite","GARNET","SPHALERITE","LIMONITE","ORTHOCLASE","GRAPHITE","APATITE","MUSCOVITE","PHLOGOPITE","HALITE","HEMATITE","Hornblende","GOETHITE","TOURMALINE","MAGNETITE","KAOLINITE","AUGITE","BARITE","Mica","GYPSUM","CALCITE","CHALCOPYRITE","FLUORITE"};
	
	int number;

	public ChartModel() {
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}	
	
	public void setData(double[] data){
		this.data = data;
	}
	
	public double[] getData() {
		return data;
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
	
	public void setDataName(String[] dataName){
		this.dataName = dataName;
	}
	
	public String[] getDataName() {
		return dataName;
	}
}
