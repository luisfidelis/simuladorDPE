package util;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class TestaGrafico {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(40.5, "maximo", "dia 1");
		ds.addValue(38.2, "maximo", "dia 2");
		ds.addValue(37.3, "maximo", "dia 3");
		ds.addValue(31.5, "maximo", "dia 4");
		ds.addValue(35.7, "maximo", "dia 5");
		ds.addValue(42.5, "maximo", "dia 6");
		
		JFreeChart grafico = ChartFactory.createLineChart("Meu Grafico", "Dia", 
			    "Valor", ds, PlotOrientation.VERTICAL, true, true, false);
		
		//OutputStream arquivo = new FileOutputStream("grafico.png");
		//ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
		//fos.close();
	}

}
