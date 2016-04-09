package controllers;

import frames.LineChart;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import models.Rocket;
import services.Automacao;
import services.RocketService;

public class Simulacao {

	public static void run(double massa_foguete_kilogramas, double diametro_tubo_metros, 
			double coeficiente_drag_fuselagem, double impulso_motor_newton_segundo,
			double tempo_queima_segundos, double coeficiente_lift,
			double aceleracao_gravidade, double densidade_do_ar_kg_por_m3, double altitude_objetivo,
			double comprimento_da_asa_metro, double corda_media_da_asa_metro, double delta_tempo_segundos,
			double angulo_velocidade_inicial_rad, double coeficiente_lift_max,
			double coeficiente_drag_asa, double coeficiente_drag_max, double ki,
			double kp, double kd){

		double tempo, x, y, anguloAutomacao;
		int count;
		boolean corrigirAsa = true;
		
		List<Double> xValues = new ArrayList<Double>();
		List<Double> yValues = new ArrayList<Double>();

		RocketService service = new RocketService();
		
		count = 0;

        final double x_inicial_metros = 0;
		
		Rocket foguete = new Rocket(massa_foguete_kilogramas, aceleracao_gravidade, angulo_velocidade_inicial_rad,
				x_inicial_metros, densidade_do_ar_kg_por_m3, corda_media_da_asa_metro,
				comprimento_da_asa_metro, delta_tempo_segundos, coeficiente_drag_fuselagem,
				impulso_motor_newton_segundo, tempo_queima_segundos, diametro_tubo_metros, altitude_objetivo,
				coeficiente_lift_max, coeficiente_drag_asa, coeficiente_drag_max);
		
		service.initialize(foguete);
 
		while(service.getAltitude_k_metros() > -10){

			service.atualizaMovimentoFoguete(foguete);
			tempo = count * foguete.getDelta_tempo_segundos();

			if((service.getAltitude_k_metros() >= 400) && corrigirAsa){
				corrigirAsa = false;
				anguloAutomacao = Math.toRadians(-15);
				foguete.setCoeficiente_lift(-41.397 * Math.pow(anguloAutomacao, 3) + 0.0049 * Math.pow(anguloAutomacao, 2) + 7.2957 * anguloAutomacao - 0.00006);
				foguete.setCoeficiente_drag_asa(0.6999 * Math.pow(anguloAutomacao, 2) + 0.00005 * anguloAutomacao + 0.0093);
			}

			if(corrigirAsa){
				anguloAutomacao = Math.toRadians(Automacao.corrigeAnguloAsa(foguete, 400, ki, kp, kd));
				foguete.setCoeficiente_lift(-41.397 * Math.pow(anguloAutomacao, 3) + 0.0049 * Math.pow(anguloAutomacao, 2) + 7.2957 * anguloAutomacao - 0.00006);
				foguete.setCoeficiente_drag_asa(0.6999 * Math.pow(anguloAutomacao, 2) + 0.00005 * anguloAutomacao + 0.0093);
			}else{
				anguloAutomacao = Math.toRadians(-15);
				foguete.setCoeficiente_lift(-41.397 * Math.pow(anguloAutomacao, 3) + 0.0049 * Math.pow(anguloAutomacao, 2) + 7.2957 * anguloAutomacao - 0.00006);
				foguete.setCoeficiente_drag_asa(0.6999 * Math.pow(anguloAutomacao, 2) + 0.00005 * anguloAutomacao + 0.0093);
			}

			x = service.getX_k_metros();
			y = service.getAltitude_k_metros();
			
			xValues.add(x);
			yValues.add(y);
			
			System.out.println("Tempo:" + tempo + " / X:" + x + " / Y:" + y);
			if(corrigirAsa)
				System.out.println("Retorno da função de automação:" + anguloAutomacao);

			count++;
		}
                
        LineChart chart = new LineChart("Simulador de Vôo","Simulação","Distância","Altitude",xValues,yValues);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );

	}
}