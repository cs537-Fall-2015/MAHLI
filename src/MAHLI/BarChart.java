package MAHLI;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.Random;

public class BarChart extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	BorderLayout borderLayout1 = new BorderLayout();
	private ChartModel model;

	public BarChart() {
		this.setLayout(borderLayout1);
	}

	// generate random color
	public Color RandomColor() {
		Random random = new Random();

		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);

		Color randomColor = new Color(r, g, b);

		return randomColor;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (model == null)
			return;

		String[] dataName = model.getDataName();
		double[] data = model.getData();

		// Find the maximum value in the data
		double max = data[0];
		for (int i = 1; i < data.length; i++){
			max = Math.max(max, data[i]);
		}

		int barWidth = (int)((getWidth() - 10.0) / data.length - 10);
		int maxBarHeight = getHeight() - 30;
		g.drawLine(5, getHeight() - 10, getWidth() - 5, getHeight() - 10);

		int x = 15;
		for (int i = 0; i < data.length; i++) {
			g.setColor(RandomColor());
			int newHeight = (int)(maxBarHeight * data[i] / max);
			int y = getHeight() - 10 - newHeight;
			g.fillRect(x, y, barWidth, newHeight);
			g.setColor(Color.black);
			g.drawString(dataName[i], x, y - 7);
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
