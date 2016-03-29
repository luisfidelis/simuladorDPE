package controllers;

import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

//import org.jfree.data.category.DefaultCategoryDataset;

import models.Rocket;
import services.RocketService;
import util.Comunicacao; 

public class Simulacao {

	public static void run(double massa_foguete_kilogramas, double diametro_tubo_metros, 
			double coeficiente_drag_fuselagem, double impulso_motor_newton_segundo,
			double tempo_queima_segundos, double coeficiente_lift,
			double aceleracao_gravidade, double densidade_do_ar_kg_por_m3, double altitude_objetivo,
			double comprimento_da_asa_metro, double corda_media_da_asa_metro, double delta_tempo_segundos,
			double angulo_velocidade_inicial_rad, double coeficiente_lift_max,
			double coeficiente_drag_asa, double coeficiente_drag_max){

		double tempo, x, y;
		int count;

		long startTime;
		long currentTime;
		
		List<Double> xValues = new ArrayList<Double>();
		List<Double> yValues = new ArrayList<Double>();
		
		count = 0;

		/********************************************
		LEMBRAR DE VERIFICAR SE É NECESSÁRIO TRATAR OS SEGUINTES
		DADOS VINDOS DO UF: delta_tempo_segundos, diametro_tubo_metros,
		angul_velocidade_inicial_rad...
		********************************************/

        final double x_inicial_metros = 0;
		
		Rocket foguete = new Rocket(massa_foguete_kilogramas, aceleracao_gravidade, angulo_velocidade_inicial_rad,
				x_inicial_metros, densidade_do_ar_kg_por_m3, corda_media_da_asa_metro,
				comprimento_da_asa_metro, delta_tempo_segundos, coeficiente_drag_fuselagem,
				impulso_motor_newton_segundo, tempo_queima_segundos, diametro_tubo_metros, altitude_objetivo,
				coeficiente_lift_max, coeficiente_drag_asa, coeficiente_drag_max);
		
		RocketService.initialize(foguete);

<<<<<<< HEAD
=======
		//------>Declara e inicializa a classe de comunicação
	//	Comunicacao comm = new Comunicacao();
	//	comm.initialize();

		//------> Inicializa uma thread que aguarda os eventos de receber dados
		Thread t = new Thread(){
			public void run(){
			try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		
		//------->Starta a thread e avisa no console que iniciou
		t.start();
		System.out.println("Started");

>>>>>>> 5e4dbcb7585f5885bb003adf36e147963b2c1dd8
		while(RocketService.getAltitude_k_metros() > 200){

			RocketService.atualizaMovimentoFoguete(foguete);
			tempo = count * foguete.getDelta_tempo_segundos();

			x = RocketService.getX_k_metros();
			y = RocketService.getAltitude_k_metros();
			
			xValues.add(x);
			yValues.add(y);

			//dados.addValue(x, "Trajetória", y);
			
			System.out.println("Tempo:" + tempo + " / X:" + x + " / Y:" + y);

			count++;
		}
		
		/*LineChart grafico = new LineChart("Simulação Trajetória", "Simulação");
		
		grafico.pack( );
	      RefineryUtilities.centerFrameOnScreen( grafico );
	      grafico.setVisible( true );*/

	}
}