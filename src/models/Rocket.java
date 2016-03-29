package models;

public class Rocket {
	
    //Dados a serem preenchidos
    private double massa_foguete_kilogramas;
    private double aceleracao_gravidade;
    private double angulo_velocidade_inicial_rad;
    private double x_inicial_metros ;
    private double densidade_do_ar_kg_por_m3;
    private double corda_media_da_asa_metro;
    private double comprimento_da_asa_metro;
    private double delta_tempo_segundos;
    private double coeficiente_drag_fuselagem;
    private double impulso_motor_newton_segundo; //Impulso classe D. Altitude recorde ~500m
    private double tempo_queima_segundos;
    private double diametro_tubo_metros;
    private double altitude_objetivo;
    private double coeficiente_lift;

    private double coeficiente_lift_max;
	private double coeficiente_drag_asa;
    private double coeficiente_drag_max;
    private double coeficiente_lift_min;
    private double coeficiente_drag_min;
    
    //Dados a serem calculados
    private double altitude_inical_metros;
    private double velocidade_inicial_metros_por_segundo;
    private double forca_drag_induzida_newtons;
    private double forca_drag_parasita_newtons;
    private double velocidade_inicial_x_metros_por_segundo;
    private double velocidade_inicial_y_metros_por_segundo;
    private double forca_motor;
    
    public Rocket(
            double massa_foguete_kilogramas,
            double aceleracao_gravidade,
            double angulo_velocidade_inicial_rad,
            double x_inicial_metros ,
            double densidade_do_ar_kg_por_m3, 
            double corda_media_da_asa_metro,
            double comprimento_da_asa_metro, 
            double delta_tempo_segundos, 
            double coeficiente_drag_fuselagem, 
            double impulso_motor_newton_segundo,
            double tempo_queima_segundos,
            double diametro_tubo_metros,
            double altitude_objetivo,
            double coeficiente_lift_max,
            double coeficiente_drag_asa,
            double coeficiente_drag_max
        ){
            this.massa_foguete_kilogramas = massa_foguete_kilogramas;
            this.aceleracao_gravidade = aceleracao_gravidade;
            this.angulo_velocidade_inicial_rad = angulo_velocidade_inicial_rad;
            this.x_inicial_metros = x_inicial_metros;
            this.densidade_do_ar_kg_por_m3 = densidade_do_ar_kg_por_m3; 
            this.corda_media_da_asa_metro = corda_media_da_asa_metro;
            this.comprimento_da_asa_metro = comprimento_da_asa_metro; 
            this.delta_tempo_segundos = delta_tempo_segundos; 
            this.coeficiente_drag_fuselagem = coeficiente_drag_fuselagem; 
            this.impulso_motor_newton_segundo = impulso_motor_newton_segundo;
            this.tempo_queima_segundos = tempo_queima_segundos; 
            this.diametro_tubo_metros = diametro_tubo_metros;
            this.altitude_objetivo = altitude_objetivo;
            this.coeficiente_lift_max = coeficiente_lift_max;
            this.coeficiente_drag_asa = coeficiente_drag_asa;
            this.coeficiente_drag_max = coeficiente_drag_max;
        }

    //Get/Set	

    public void setMassa_foguete_kilogramas(double massa_foguete_kilogramas){
        this.massa_foguete_kilogramas = massa_foguete_kilogramas;
    }
    public void setAceleracao_gravidade(double aceleracao_gravidade){
        this.aceleracao_gravidade = aceleracao_gravidade;
    }
    public void setAngulo_velocidade_inicial_rad(double angulo_velocidade_inicial_rad){
        this.angulo_velocidade_inicial_rad = angulo_velocidade_inicial_rad;
    }
    public void setX_inicial_metros (double x_inicial_metros ){
        this.x_inicial_metros = x_inicial_metros;
    }
    public void setDensidade_do_ar_kg_por_m3(double densidade_do_ar_kg_por_m3){
        this.densidade_do_ar_kg_por_m3 = densidade_do_ar_kg_por_m3;
    }
    public void setCorda_media_da_asa_metro(double corda_media_da_asa_metro){
        this.corda_media_da_asa_metro = corda_media_da_asa_metro;
    }
    public void setComprimento_da_asa_metro(double comprimento_da_asa_metro){
        this.comprimento_da_asa_metro = comprimento_da_asa_metro;
    }
    public void setDelta_tempo_segundos(double delta_tempo_segundos){
        this.delta_tempo_segundos = delta_tempo_segundos;
    }
    public void setCoeficiente_drag_fuselagem(double coeficiente_drag_fuselagem){
        this.coeficiente_drag_fuselagem = coeficiente_drag_fuselagem;
    }
    public void setImpulso_motor_newton_segundo(double impulso_motor_newton_segundo){
        this.impulso_motor_newton_segundo = impulso_motor_newton_segundo;
    }

    public void setDiametro_tubo_metros(double diametro_tubo_metros) {
        this.diametro_tubo_metros = diametro_tubo_metros;
    }

    public void setAltitude_objetivo(double altitude_objetivo) {
        this.altitude_objetivo = altitude_objetivo;
    }

    public double getDiametro_tubo_metros() {
        return diametro_tubo_metros;
    }

    public double getAltitude_objetivo() {
        return altitude_objetivo;
    }
   
    public  double getMassa_foguete_kilogramas(){
        return this.massa_foguete_kilogramas;
    }
    public double getAceleracao_gravidade(){
        return this.aceleracao_gravidade;
    }
    public double getAngulo_velocidade_inicial_rad(){
        return this.angulo_velocidade_inicial_rad;
    }
    public double getX_inicial_metros (){
        return this.x_inicial_metros;
    }
    public double getDensidade_do_ar_kg_por_m3(){
        return this.densidade_do_ar_kg_por_m3;
    }
    public double getCorda_media_da_asa_metro(){
        return this.corda_media_da_asa_metro;
    }
    public double getComprimento_da_asa_metro(){
        return this.comprimento_da_asa_metro;
    }
    public double getDelta_tempo_segundos(){
        return this.delta_tempo_segundos;
    }
    public double getCoeficiente_drag_fuselagem(){
        return this.coeficiente_drag_fuselagem;
    }
    public double getImpulso_motor_newton_segundo(){
        return this.impulso_motor_newton_segundo;
    }

	public double getTempo_queima_segundos() {
		return tempo_queima_segundos;
	}

	public void setTempo_queima_segundos(double tempo_queima_segundos) {
		this.tempo_queima_segundos = tempo_queima_segundos;
	}

	public double getCoeficiente_drag_asa() {
		return coeficiente_drag_asa;
	}

	public void setCoeficiente_drag_asa(double coeficiente_drag_asa) {
		this.coeficiente_drag_asa = coeficiente_drag_asa;
	}

	public double getCoeficiente_lift_max() {
		return coeficiente_lift_max;
	}

	public void setCoeficiente_lift_max(double coeficiente_lift_max) {
		this.coeficiente_lift_max = coeficiente_lift_max;
	}

	public double getCoeficiente_drag_max() {
		return coeficiente_drag_max;
	}

	public void setCoeficiente_drag_max(double coeficiente_drag_max) {
		this.coeficiente_drag_max = coeficiente_drag_max;
	}

	public double getCoeficiente_lift_min() {
		return coeficiente_lift_min;
	}

	public void setCoeficiente_lift_min(double coeficiente_lift_min) {
		this.coeficiente_lift_min = coeficiente_lift_min;
	}

	public double getCoeficiente_drag_min() {
		return coeficiente_drag_min;
	}

	public void setCoeficiente_drag_min(double coeficiente_drag_min) {
		this.coeficiente_drag_min = coeficiente_drag_min;
	}

	public double getAltitude_inical_metros() {
		return this.altitude_inical_metros;
	}

	public void setAltitude_inical_metros(double altitude_inical_metros) {
		this.altitude_inical_metros = altitude_inical_metros;
	}

	public double getVelocidade_inicial_metros_por_segundo() {
		return velocidade_inicial_metros_por_segundo;
	}

	public void setVelocidade_inicial_metros_por_segundo(double velocidade_inicial_metros_por_segundo) {
		this.velocidade_inicial_metros_por_segundo = velocidade_inicial_metros_por_segundo;
	}

	public double getForca_drag_induzida_newtons() {
		return forca_drag_induzida_newtons;
	}

	public void setForca_drag_induzida_newtons(double forca_drag_induzida_newtons) {
		this.forca_drag_induzida_newtons = forca_drag_induzida_newtons;
	}

	public double getForca_drag_parasita_newtons() {
		return forca_drag_parasita_newtons;
	}

	public void setForca_drag_parasita_newtons(double forca_drag_parasita_newtons) {
		this.forca_drag_parasita_newtons = forca_drag_parasita_newtons;
	}

	public double getVelocidade_inicial_x_metros_por_segundo() {
		return velocidade_inicial_x_metros_por_segundo;
	}

	public void setVelocidade_inicial_x_metros_por_segundo(double velocidade_inicial_x_metros_por_segundo) {
		this.velocidade_inicial_x_metros_por_segundo = velocidade_inicial_x_metros_por_segundo;
	}

	public double getVelocidade_inicial_y_metros_por_segundo() {
		return velocidade_inicial_y_metros_por_segundo;
	}

	public void setVelocidade_inicial_y_metros_por_segundo(double velocidade_inicial_y_metros_por_segundo) {
		this.velocidade_inicial_y_metros_por_segundo = velocidade_inicial_y_metros_por_segundo;
	}

	public double getForca_motor() {
		return forca_motor;
	}

	public void setForca_motor(double forca_motor) {
		this.forca_motor = forca_motor;
	}

	public double getCoeficiente_lift() {
		return coeficiente_lift;
	}

	public void setCoeficiente_lift(double coeficiente_lift) {
		this.coeficiente_lift = coeficiente_lift;
	}
    

}
 