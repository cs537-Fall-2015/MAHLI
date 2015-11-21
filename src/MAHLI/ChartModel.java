package MAHLI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ChartModel {
	private transient Vector actionListeners;

	public ChartModel() {
	}
	
	private Long[] data;
	
	public void setData(Long[] data){
		this.data = data;
	}
	
	public Long[] getData() {
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
	
	private String[] dataName;
	
	public void setDataName(String[] dataName){
		this.dataName = dataName;
	}
	
	public String[] getDataName() {
		return dataName;
	}
}
