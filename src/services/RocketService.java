package services;
import models.*;

public class RocketService {

    //Dados usados no calculo
    private double velocidade_x_k_metros_por_segundo;
	private double velocidade_y_k_metros_por_segundo;
    private double velocidade_k_metros_por_segundo;
    private double angulo_k_rad; 
    private double altitude_k_metros;
    private double x_k_metros;
    private double forca_lift_k_newtons;
    private double forca_drag_k_newtons;
    private double forca_drag_fuselagem_k_newtons;
    private double forca_resultante_k_newtons;
    private double angulo_forca_resultante_k_rad;
    private double altitudeMaxima;

    public RocketService(){
        this.altitudeMaxima = 0;
    }

    public void initialize(Rocket foguete){

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

    public void atualizaMovimentoFoguete(Rocket foguete){
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
        double diametro_tubo_metros = foguete.getDiametro_tubo_metros();
        // ---
        
        angulo_k_rad = Math.atan2(velocidade_y_k_metros_por_segundo, velocidade_x_k_metros_por_segundo);

        forca_lift_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_lift * Math.pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2;
        forca_drag_fuselagem_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_drag_fuselagem * Math.pow(velocidade_k_metros_por_segundo, 2) * Math.PI * Math.pow(diametro_tubo_metros/2, 2) / 2);
        forca_drag_k_newtons = (densidade_do_ar_kg_por_m3 * coeficiente_drag_asa * Math.pow(velocidade_k_metros_por_segundo, 2) * corda_media_da_asa_metro * comprimento_da_asa_metro) / 2 + forca_drag_fuselagem_k_newtons + forca_drag_induzida_newtons + forca_drag_parasita_newtons;
        forca_resultante_k_newtons = Math.sqrt(Math.pow(forca_lift_k_newtons * Math.cos(angulo_k_rad+Math.PI/2) + forca_drag_k_newtons * Math.cos(Math.PI + angulo_k_rad), 2) + Math.pow(forca_lift_k_newtons*Math.sin(Math.PI/2+angulo_k_rad) + forca_drag_k_newtons * Math.sin(Math.PI + angulo_k_rad)-massa_foguete_kilogramas*aceleracao_gravidade, 2));
        angulo_forca_resultante_k_rad = Math.atan2(forca_lift_k_newtons*Math.sin(Math.PI/2+angulo_k_rad) + forca_drag_k_newtons * Math.sin(Math.PI + angulo_k_rad) - massa_foguete_kilogramas * aceleracao_gravidade, forca_lift_k_newtons * Math.cos(angulo_k_rad+Math.PI/2) + forca_drag_k_newtons * Math.cos(Math.PI + angulo_k_rad));
        altitude_k_metros += velocidade_y_k_metros_por_segundo * delta_tempo_segundos;
        x_k_metros += velocidade_x_k_metros_por_segundo * delta_tempo_segundos;
        velocidade_x_k_metros_por_segundo += forca_resultante_k_newtons * Math.cos(angulo_forca_resultante_k_rad) / massa_foguete_kilogramas * delta_tempo_segundos;
        velocidade_y_k_metros_por_segundo += forca_resultante_k_newtons * Math.sin(angulo_forca_resultante_k_rad) / massa_foguete_kilogramas * delta_tempo_segundos;
        velocidade_k_metros_por_segundo = Math.sqrt(Math.pow(velocidade_x_k_metros_por_segundo, 2) + Math.pow(velocidade_y_k_metros_por_segundo, 2));

        //Registra a maior altitude atingida
        if(altitude_k_metros >= altitudeMaxima)
            altitudeMaxima = altitude_k_metros;
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

	public double getVelocidade_x_k_metros_por_segundo() {
		return velocidade_x_k_metros_por_segundo;
	}

	public void setVelocidade_x_k_metros_por_segundo(double velocidade_x_k_metros_por_segundo) {
		this.velocidade_x_k_metros_por_segundo = velocidade_x_k_metros_por_segundo;
	}

	public double getVelocidade_y_k_metros_por_segundo() {
		return velocidade_y_k_metros_por_segundo;
	}

	public void setVelocidade_y_k_metros_por_segundo(double velocidade_y_k_metros_por_segundo) {
		this.velocidade_y_k_metros_por_segundo = velocidade_y_k_metros_por_segundo;
	}

	public double getVelocidade_k_metros_por_segundo() {
		return velocidade_k_metros_por_segundo;
	}

	public void setVelocidade_k_metros_por_segundo(double velocidade_k_metros_por_segundo) {
		this.velocidade_k_metros_por_segundo = velocidade_k_metros_por_segundo;
	}

	public double getAngulo_k_rad() {
		return angulo_k_rad;
	}

	public void setAngulo_k_rad(double angulo_k_rad) {
		this.angulo_k_rad = angulo_k_rad;
	}

	public double getAltitude_k_metros() {
		return altitude_k_metros;
	}

	public void setAltitude_k_metros(double altitude_k_metros) {
		this.altitude_k_metros = altitude_k_metros;
	}

	public double getX_k_metros() {
		return x_k_metros;
	}

	public void setX_k_metros(double x_k_metros) {
		this.x_k_metros = x_k_metros;
	}

	public double getForca_lift_k_newtons() {
		return forca_lift_k_newtons;
	}

	public void setForca_lift_k_newtons(double forca_lift_k_newtons) {
		this.forca_lift_k_newtons = forca_lift_k_newtons;
	}

	public double getForca_drag_k_newtons() {
		return forca_drag_k_newtons;
	}

	public void setForca_drag_k_newtons(double forca_drag_k_newtons) {
		this.forca_drag_k_newtons = forca_drag_k_newtons;
	}

	public double getForca_drag_fuselagem_k_newtons() {
		return forca_drag_fuselagem_k_newtons;
	}

	public void setForca_drag_fuselagem_k_newtons(double forca_drag_fuselagem_k_newtons) {
		this.forca_drag_fuselagem_k_newtons = forca_drag_fuselagem_k_newtons;
	}

	public double getForca_resultante_k_newtons() {
		return forca_resultante_k_newtons;
	}

	public void setForca_resultante_k_newtons(double forca_resultante_k_newtons) {
		this.forca_resultante_k_newtons = forca_resultante_k_newtons;
	}

	public double getAngulo_forca_resultante_k_rad() {
		return angulo_forca_resultante_k_rad;
	}

	public void setAngulo_forca_resultante_k_rad(double angulo_forca_resultante_k_rad) {
		this.angulo_forca_resultante_k_rad = angulo_forca_resultante_k_rad;
	}

	public double getAltitudeMaxima() {
		return altitudeMaxima;
	}

	public void setAltitudeMaxima(double altitudeMaxima) {
		this.altitudeMaxima = altitudeMaxima;
	}
}
   