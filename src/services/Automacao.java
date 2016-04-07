package services;

import models.Rocket;

public class Automacao {
	
	//------>Salva o erro da última vez que o método corrigeAnguloAsa foi chamada
	static double erroAnterior;
	//------>Salva o somatório dos erros ao longo da execução da simulação
	static double erroAcumulado;

	public static double corrigeAnguloAsa(Rocket foguete, double altitudeObjetivo, double ti
											double kp, double td){

		double altitudeEstimada;
		double altitudeAnterior;

		double erro;

		//----> Variáveis do cálculo das ações de automação
		double acaoProporcional;
		double acaoDerivativa;
		double acaoIntegral;
		double acaoFinal;

		//----> Inicializa uma nova "simulação paralela", para verificar qual a altitude estimada
		RocketService auxService = new RocketService();
		auxService.initialize(foguete);

		while(auxService.getAltitude_k_metros() > -10){

			/*Encerra a simulação quando a altitude do foguete começa a diminuir, pois precisamos
			somente da altitude máxima que ele atingirá*/
			if(auxService.getAltitude_k_metros < altitudeAnterior)
				break;

			altitudeAnterior = auxService.getAltitude_k_metros();

			auxService.atualizaMovimentoFoguete(foguete);
		}

		//---> A altitude máxima será a maior altitude alcançada na "simulação paralela"
		altitudeEstimada = auxService.getAltitudeMaxima();

		erro = altitudeEstimada - altitudeObjetivo;

		erroAcumulado += erro;
		
		//System.out.println("OPAAAAAAAAAAAAAA:" + altitudeEstimada);

		//-----> Calcula as ações de automação
		acaoProporcional = calculaAcaoProporcional(kp, erro);
		acaoDerivativa = calculaAcaoDerivativa(kp, td, erro, erroAnterior, foguete.getDelta_tempo_segundos);
		acaoIntegral = calculaAcaoIntegral(ti, foguete.getDelta_tempo_segundos)

		acaoFinal = acaoProporcional + acaoDerivativa + acaoIntegral;

		erroAnterior = erro;

		//---> Limita o retorno da função, para impedir alterações muito bruscas
		if(acaoFinal > 15)
			acaoFinal = 15;
		else if(acaoFinal < -15)
			acaoFinal = -15;

		return acaoFinal;
	}

	public static double calculaAcaoProporcional(double kp, double erro){
		return kp * erro;
	}

	// ******Verificar como faremos na primeira iteracao, pois o erro ficará muito grande (erro - 0)****
	public static double calculaAcaoDerivativa(double kp, double td, double erro, double erroAnterior, double delta_t){
		return td * (erro - erroAnterior)/delta_t;
	}

	//*********Verificar se o cálculo está correto - 
	public static double calculaAcaoIntegral(double kp, double ti, double delta_t){
		return kp/ti * erroAcumulado * delta_t;
	}

}
