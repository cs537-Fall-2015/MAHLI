package MAHLI;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class BarChart extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	BorderLayout borderLayout1 = new BorderLayout();
	private ChartModel model;
	private ArrayList<Long> data =  new ArrayList<Long>();
	private ArrayList<String> dataName = new ArrayList<String>();

	public BarChart() {
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
		ArrayList<String> dn = new ArrayList<String>();
		ArrayList<Long> d = new ArrayList<Long>();

		if (model == null) return;

		dn = model.getDataName();
		d = model.getData();

		// Find the maximum value in the data
		Long max = Long.valueOf(0);
		for (int i = 1; i < d.size(); i++){
			max = Math.max(max, d.get(i));
		}

		int barWidth = (int)((getWidth() - 10.0) / d.size() - 10);
		int maxBarHeight = getHeight() - 30;

		g.drawLine(5, getHeight() - 10, getWidth() - 5, getHeight() - 10);

		int x = 15;
		for (int i = 0; i < d.size(); i++) {
			g.setColor(RandomColor());
			int newHeight = (int)(maxBarHeight * d.get(i) / max);
			int y = getHeight() - 10 - newHeight;
			g.fillRect(x, y, barWidth, newHeight);
			g.setColor(Color.black);
			g.drawString(dn.get(i), x, y - 7);
			x += barWidth + 10;
		}
	}

	public void setModel(ChartModel newModel) {
		model = newModel;
		model.addActionListener(this);
	}

	public ChartModel getModel() {
		return model;
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
