package services;

import models.Rocket;

public class Automacao {
	
	public static double corrigeAnguloAsa(Rocket foguete, double altitudeObjetivo){
		
		double altitudeEstimada;
		double erro;

		RocketService auxService = new RocketService();

		auxService.initialize(foguete);

		while(auxService.getAltitude_k_metros() > 200){

			auxService.atualizaMovimentoFoguete(foguete);
			/*tempo = count * foguete.getDelta_tempo_segundos();

			x = service.getX_k_metros();
			y = service.getAltitude_k_metros();
			
			System.out.println("Tempo:" + tempo + " / X:" + x + " / Y:" + y);

			count++;*/
		}

		altitudeEstimada = auxService.getAltitudeMaxima();

		erro = altitudeEstimada - altitudeObjetivo;
		
		System.out.println("OPAAAAAAAAAAAAAA:" + altitudeEstimada);

		/*ESBOÇO DA FUNÇÃO DE AUTOMAÇÃO VINDA DO ARDUINO
		double acp_var;
		double ainst;
		double aci_var;
		double acd_var;
		double ac_var;
		double d_erro;
		double erro_ant;
		double kp;
		double ki;
		double kd;
		
		//Ação de controle proporcional
		acp_var = kp * erro;

		/*AQUI TINHA UM IF ESQUISITO
		//Ação de controle integral
		ainst = ki * erro;
		aci_var += ainst;

		//Ação de controle derivativa
		d_erro = erro - erro_ant;
		erro_ant = erro;
		/*AQUI TERMINA O IF ESQUISITO
		
		acd_var = (kd / 0.02) * d_erro;

		//Ação de controle
		ac_var = acp_var + aci_var + acd_var;

		if (ac_var > 100)
			ac_var = 100;
		else if(ac_var < 0)
			ac_var = 0;

		return ac_var;*/
		
		return 0;
	}

}
