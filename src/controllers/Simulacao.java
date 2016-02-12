package controllers;

//import org.jfree.data.category.DefaultCategoryDataset;

import models.Rocket;
import services.RocketService;

public class Simulacao {
    
	public static void run(){

		double tempo, x, y;
		int count;

		count = 0;

		// Provisório
        final double massa_foguete_kilogramas = 0.1;
        final double aceleracao_gravidade = 9.81;
        double angulo_velocidade_inicial_rad = Math.toRadians(45);
        final double x_inicial_metros = 0;
        final double densidade_do_ar_kg_por_m3 = 1.2922;
        final double corda_media_da_asa_metro = 0.025;
        final double comprimento_da_asa_metro = 0.05;
        final double delta_tempo_segundos = 0.01;
        final double coeficiente_drag_fuselagem = 0.5;
        final double impulso_motor_newton_segundo=15;
        final double tempo_queima_segundos =3;
        final double diametro_tubo_metros = 0.018;
        final double altitude_objetivo = 400;
        
        //DefaultCategoryDataset dados = new DefaultCategoryDataset();
		
		Rocket foguete = new Rocket(massa_foguete_kilogramas, aceleracao_gravidade, angulo_velocidade_inicial_rad,
				x_inicial_metros, densidade_do_ar_kg_por_m3, corda_media_da_asa_metro,
				comprimento_da_asa_metro, delta_tempo_segundos, coeficiente_drag_fuselagem,
				impulso_motor_newton_segundo, tempo_queima_segundos, diametro_tubo_metros, altitude_objetivo);
		
		RocketService.initialize(foguete);

		while(RocketService.getAltitude_k_metros() > 200){
			RocketService.atualizaMovimentoFoguete(foguete);
			tempo = count * foguete.getDelta_tempo_segundos();
			x = RocketService.getX_k_metros();
			y = RocketService.getAltitude_k_metros();
			//dados.addValue(x, "Trajetória", y);
			System.out.println("Tempo:" + tempo + " / X:" + x + " / Y:" + y);
			count++;
		}
	}
	
}
