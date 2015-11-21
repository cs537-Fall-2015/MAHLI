package MAHLI;

import javax.swing.JApplet;

public class MAHLIDisplayCharts extends JApplet {
	private static final long serialVersionUID = 1L;
	
	private ChartModel chartModel;
	private BarChart barChart;
	private PieChart pieChart;
	
	public MAHLIDisplayCharts() {
		chartModel = new ChartModel();
		barChart = new BarChart();
		pieChart = new PieChart();
		
		getContentPane().setLayout(new java.awt.GridLayout(1, 2));
		
		barChart.setModel(chartModel);
		getContentPane().add(barChart);
		
		pieChart.setModel(chartModel);
		getContentPane().add(pieChart);
	}
}
