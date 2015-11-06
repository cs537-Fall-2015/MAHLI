package MAHLI;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.Random;

public class PieChart extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	BorderLayout borderLayout1 = new BorderLayout();
	private ChartModel model;
	
	public PieChart() {
		this.setLayout(borderLayout1);
	}
	
	// generate random color
	public Color RandomColor(){
		Random random = new Random();

		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		Color randomColor = new Color(r, g, b);
		
		return randomColor;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (model == null) return;
		
		int radius = (int)(Math.min(getWidth(), getHeight()) * 0.5 * 0.9);
		int x = getWidth() / 2 - radius;
		int y = getHeight()/2 - radius;
		
		String[] dataName = model.getDataName();
		double[] data = model.getData();
		
		double total = 0;
		for (int i = 0; i < data.length; i++)
		total += data[i];
		
		int angle1 = 0;
		int angle2 = 0;
		for (int i = 0; i < data.length; i++){
			angle1 = angle1 + angle2;
			angle2 = (int)Math.ceil(360 * data[i] / total);
			g.setColor(RandomColor());
			g.fillArc(x, y, 2 * radius, 2 * radius, angle1, angle2);
			g.setColor(Color.black);
			g.drawString(dataName[i],
					(int)(getWidth() / 2 + radius * Math.cos((angle1 + angle2 / 2) * 2 * Math.PI / 360)),
					(int)(getHeight() / 2 - radius * Math.sin((angle1+angle2 / 2) * 2 * Math.PI / 360)));
		}
	}
	
	public void setModel(ChartModel newModel){
		model = newModel;
		model.addActionListener(this);
	}
	
	public ChartModel getModel(){
		return model;
	}
	
	public void actionPerformed(ActionEvent e){
		repaint();
	}
}
