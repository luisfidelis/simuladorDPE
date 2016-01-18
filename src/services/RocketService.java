package services;
import models.*;

public class RocketService {

    //Dados usados no calculo
    private static double velocidade_x_k_metros_por_segundo;
	private static double velocidade_y_k_metros_por_segundo;
    private static double velocidade_k_metros_por_segundo;
    private static double angulo_k_rad;
    private static double altitude_k_metros;
    private static double x_k_metros;
    private static double forca_lift_k_newtons;
    private static double forca_drag_k_newtons;
    private static double forca_drag_fuselagem_k_newtons;
    private static double forca_resultante_k_newtons;
    private static double angulo_forca_resultante_k_rad;

    public static void initialize(Rocket foguete){

        foguete.setForca_motor(calcularForca_motor(foguete.getImpulso_motor_newton_segundo(), foguete.getTempo_queima_segundos()));
        foguete.setAltitude_inical_metros(calcularAltitude_inical_metros(foguete.getForca_motor(), foguete.getMassa_foguete_kilogramas(), foguete.getTempo_queima_segundos())); 
        foguete.setVelocidade_inicial_metros_por_segundo(calcularVelocidade_inicial_metros_por_segundo(foguete.getForca_motor(), foguete.getMassa_foguete_kilogramas(), foguete.getTempo_queima_segundos()));
        foguete.setForca_drag_induzida_newtons(calcularForca_drag_induzida_newtons());
        foguete.setForca_drag_parasita_newtons(calcularForca_drag_parasita_newtons());
        foguete.setVelocidade_inicial_x_metros_por_segundo(calcularVelocidade_inicial_x_metros_por_segundo(foguete.getVelocidade_inicial_metros_por_segundo(), foguete.getAngulo_velocidade_inicial_rad()));
        foguete.setVelocidade_inicial_y_metros_por_segundo(calcularVelocidade_inicial_y_metros_por_segundo(foguete.getVelocidade_inicial_metros_por_segundo(), foguete.getAngulo_velocidade_inicial_rad()));

        altitude_k_metros = foguete.getAltitude_inical_metros();
        x_k_metros = foguete.getX_inicial_metros();
        velocidade_x_k_metros_por_segundo = foguete.getVelocidade_inicial_x_metros_por_segundo();
        velocidade_y_k_metros_por_segundo = foguete.getVelocidade_inicial_y_metros_por_segundo();
        velocidade_k_metros_por_segundo = foguete.getVelocidade_inicial_metros_por_segundo();
    }

    public static void atualizaMovimentoFoguete(Rocket foguete){
        // --- Parametros do foguete
        double massa_foguete_kilogramas = foguete.getMassa_foguete_kilogramas();
        double densidade_do_ar_kg_por_m3 = foguete.getDensidade_do_ar_kg_por_m3();
        double coeficiente_lift = foguete.getCoeficiente_lift();
        double corda_media_da_asa_metro = foguete.getCorda_media_da_asa_metro();
        double comprimento_da_asa_metro = foguete.getComprimento_da_asa_metro();
        double coeficiente_drag_asa = foguete.getCoeficiente_drag_asa();
        double forca_drag_induzida_newtons = foguete.getForca_drag_induzida_newtons();
        double delta_tempo_segundos = foguete.getDelta_tempo_segundos();
        double coeficiente_drag_fuselagem = foguete.getCoeficiente_drag_fuselagem();
        double forca_drag_parasita_newtons = foguete.getForca_drag_parasita_newtons();
        double aceleracao_gravidade = foguete.getAceleracao_gravidade();
        // ---
        
        angulo_k_rad = Math.atan2(velocidade_y_k_metros_por_segundo, velocidade_x_k_metros_por_segundo);

        forca_lift_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_lift * Math.pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
        forca_drag_fuselagem_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_drag_fuselagem * Math.pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
        forca_drag_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_drag_asa * Math.pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2 + forca_drag_fuselagem_k_newtons + forca_drag_induzida_newtons + forca_drag_parasita_newtons;
        forca_resultante_k_newtons = Math.sqrt(Math.pow(forca_lift_k_newtons * Math.cos(angulo_k_rad+Math.PI/2) + forca_drag_k_newtons * Math.cos(Math.PI + angulo_k_rad), 2) + Math.pow(forca_lift_k_newtons*Math.sin(Math.PI/2+angulo_k_rad) + forca_drag_k_newtons * Math.sin(Math.PI + angulo_k_rad)-massa_foguete_kilogramas*aceleracao_gravidade, 2));
        angulo_forca_resultante_k_rad = Math.atan2(forca_lift_k_newtons*Math.sin(Math.PI/2+angulo_k_rad) + forca_drag_k_newtons * Math.sin(Math.PI + angulo_k_rad) - massa_foguete_kilogramas * aceleracao_gravidade, forca_lift_k_newtons * Math.cos(angulo_k_rad+Math.PI/2) + forca_drag_k_newtons * Math.cos(Math.PI + angulo_k_rad));
        altitude_k_metros += velocidade_y_k_metros_por_segundo * delta_tempo_segundos;
        x_k_metros += velocidade_x_k_metros_por_segundo * delta_tempo_segundos;
        velocidade_x_k_metros_por_segundo += forca_resultante_k_newtons * Math.cos(angulo_forca_resultante_k_rad) / massa_foguete_kilogramas * delta_tempo_segundos;
        velocidade_y_k_metros_por_segundo += forca_resultante_k_newtons * Math.sin(angulo_forca_resultante_k_rad) / massa_foguete_kilogramas * delta_tempo_segundos;
        velocidade_k_metros_por_segundo = Math.sqrt(Math.pow(velocidade_x_k_metros_por_segundo, 2) + Math.pow(velocidade_y_k_metros_por_segundo, 2));
    }

    public static double calcularAltitude_inical_metros(
            double forca_motor,
            double massa_foguete_kilogramas,
            double tempo_queima_segundos
        )
    {
        return ((forca_motor / massa_foguete_kilogramas) * Math.pow(tempo_queima_segundos, 2)) / 2;
    }

    public static double calcularVelocidade_inicial_metros_por_segundo(
            double forca_motor,
            double massa_foguete_kilogramas,
            double tempo_queima_segundos)
    {   
        double velocidadeInicial = (forca_motor / massa_foguete_kilogramas) * tempo_queima_segundos;
        return velocidadeInicial;
    }

    public static double calcularForca_drag_induzida_newtons(){
        return 0;
    }

    public static double calcularForca_drag_parasita_newtons(){
        return 0;
    }

    public static double calcularVelocidade_inicial_x_metros_por_segundo(
            double velocidade_inicial_metros_por_segundo,
            double angulo_velocidade_inicial_rad
            )
    {
        return velocidade_inicial_metros_por_segundo * Math.cos(angulo_velocidade_inicial_rad);
    }

    public static double calcularVelocidade_inicial_y_metros_por_segundo(
        double velocidade_inicial_metros_por_segundo,
        double angulo_velocidade_inicial_rad)
    {
        return velocidade_inicial_metros_por_segundo * Math.sin(angulo_velocidade_inicial_rad);
    }

    public static double calcularForca_motor(
        double impulso_motor_newton_segundo,
        double tempo_queima_segundos)
    {
        return impulso_motor_newton_segundo / tempo_queima_segundos;
    }
    
    public static double getVelocidade_x_k_metros_por_segundo() {
		return velocidade_x_k_metros_por_segundo;
	}

	public static void setVelocidade_x_k_metros_por_segundo(double velocidade_x_k_metros_por_segundo) {
		RocketService.velocidade_x_k_metros_por_segundo = velocidade_x_k_metros_por_segundo;
	}

	public static double getVelocidade_y_k_metros_por_segundo() {
		return velocidade_y_k_metros_por_segundo;
	}

	public static void setVelocidade_y_k_metros_por_segundo(double velocidade_y_k_metros_por_segundo) {
		RocketService.velocidade_y_k_metros_por_segundo = velocidade_y_k_metros_por_segundo;
	}

	public static double getVelocidade_k_metros_por_segundo() {
		return velocidade_k_metros_por_segundo;
	}

	public static void setVelocidade_k_metros_por_segundo(double velocidade_k_metros_por_segundo) {
		RocketService.velocidade_k_metros_por_segundo = velocidade_k_metros_por_segundo;
	}

	public static double getAngulo_k_rad() {
		return angulo_k_rad;
	}

	public static void setAngulo_k_rad(double angulo_k_rad) {
		RocketService.angulo_k_rad = angulo_k_rad;
	}

	public static double getAltitude_k_metros() {
		return altitude_k_metros;
	}

	public static void setAltitude_k_metros(double altitude_k_metros) {
		RocketService.altitude_k_metros = altitude_k_metros;
	}

	public static double getX_k_metros() {
		return x_k_metros;
	}

	public static void setX_k_metros(double x_k_metros) {
		RocketService.x_k_metros = x_k_metros;
	}

	public static double getForca_lift_k_newtons() {
		return forca_lift_k_newtons;
	}

	public static void setForca_lift_k_newtons(double forca_lift_k_newtons) {
		RocketService.forca_lift_k_newtons = forca_lift_k_newtons;
	}

	public static double getForca_drag_k_newtons() {
		return forca_drag_k_newtons;
	}

	public static void setForca_drag_k_newtons(double forca_drag_k_newtons) {
		RocketService.forca_drag_k_newtons = forca_drag_k_newtons;
	}

	public static double getForca_drag_fuselagem_k_newtons() {
		return forca_drag_fuselagem_k_newtons;
	}

	public static void setForca_drag_fuselagem_k_newtons(double forca_drag_fuselagem_k_newtons) {
		RocketService.forca_drag_fuselagem_k_newtons = forca_drag_fuselagem_k_newtons;
	}

	public static double getForca_resultante_k_newtons() {
		return forca_resultante_k_newtons;
	}

	public static void setForca_resultante_k_newtons(double forca_resultante_k_newtons) {
		RocketService.forca_resultante_k_newtons = forca_resultante_k_newtons;
	}

	public static double getAngulo_forca_resultante_k_rad() {
		return angulo_forca_resultante_k_rad;
	}

	public static void setAngulo_forca_resultante_k_rad(double angulo_forca_resultante_k_rad) {
		RocketService.angulo_forca_resultante_k_rad = angulo_forca_resultante_k_rad;
	}

}
