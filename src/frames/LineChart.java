/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.util.List;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame
{
   public LineChart( String applicationTitle , String chartTitle,String xAxis, String yAxis , List<Double> xValues, List<Double> yValues, Double yMax )
   {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         xAxis,yAxis,
         createDataset(xValues,yValues,yMax),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
      
   }

   private DefaultCategoryDataset createDataset(List<Double> xValues, List<Double> yValues, Double yMax )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      for(int i = 0; i < xValues.size(); i++){
        dataset.addValue( yValues.get(i) , "trajetória" , xValues.get(i) );
        dataset.addValue( yMax , "Altura Máxima" , xValues.get(i) );
      }
      return dataset;
   }
//   public static void main( String[ ] args ) 
//   {
//      LineChart chart = new LineChart(
//      "School Vs Years" ,
//      "Numer of Schools vs years");
//
//      chart.pack( );
//      RefineryUtilities.centerFrameOnScreen( chart );
//      chart.setVisible( true );
//   }
}