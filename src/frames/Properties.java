/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;
import javax.swing.JPasswordField;  
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import controllers.Simulacao;
/**
 *
 * @author user
 */
public class Properties extends JFrame {
    String currentPath = System.getProperty("user.dir");
     //(currentPath);
    /*final double massa_foguete_kilogramas = 0.1;
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
        private double coeficiente_lift = 0;
        private double coeficiente_drag_asa = 0.007;
        private double coeficiente_lift_max = 3.0;
        private double coeficiente_drag_max=0.024;
        private double coeficiente_lift_min =-0.2;
        private double coeficiente_drag_min = 0.007;
    */
    //--- DADOS DO FOGUETE
   
    JLabel lbl_DADOS_FOGUETE = new JLabel("Dados do foguete");
    
    JLabel lbl_massa = new JLabel("Massa(kg)");
    JLabel lbl_diametroTubo = new JLabel("Diametro do Tubo(mm)");
    JLabel lbl_coefDragFus = new JLabel("Coeficiente Drag Fuselagem");
    JLabel lbl_impulsoMotor = new JLabel("Impulso do Motor (Ns)");
    JLabel lbl_tempoQueima = new JLabel("Tempo de Queima(s)");
    
    JTextField jtf_massa = new JTextField();
    JTextField jtf_diametroTubo = new JTextField();
    JTextField jtf_coefDragFus = new JTextField();
    JTextField jtf_impulsoMotor = new JTextField();
    JTextField jtf_tempoQueima = new JTextField();
    
    //--- DADOS DAS ASAS
    
    JLabel lbl_DADOS_ASAS = new JLabel("Dados das asas");
    
    JLabel lbl_coefLiftSimul = new JLabel("Coeficiente Lift Simulado");
    JLabel lbl_coefDragSimul = new JLabel("Coeficiente Drag Simulado");
    JLabel lbl_coefLiftMax = new JLabel("Coeficiente Lift CL/CD Max");
    JLabel lbl_coefDragMax = new JLabel("Coeficiente Drag CL/CD Max");
    JLabel lbl_cordaMediaAsa = new JLabel("Corda Média da Asa(m)");
    JLabel lbl_envergaduraAsa = new JLabel("Envergadura da Asa(m)");
    
    JTextField jtf_coefLiftSimul = new JTextField();
    JTextField jtf_coefDragSimul = new JTextField();
    JTextField jtf_coefLiftMax = new JTextField();
    JTextField jtf_coefDragMax = new JTextField();
    JTextField jtf_cordaMediaAsa = new JTextField();
    JTextField jtf_envergaduraAsa = new JTextField();
    
    //--- DADOS DA SIMULAÇÃO
    JLabel lbl_DADOS_SIMULACAO = new JLabel("Dados da simulação");
    
    
    JLabel lbl_aceleracaoGravid = new JLabel("Acel. Gravidade(m/s²)");
    JLabel lbl_densidadeAr = new JLabel("Densidade Ar(Kg/m³)");
    JLabel lbl_anguloInicial = new JLabel("Angulo inicial (º)");
    JLabel lbl_passoTempo = new JLabel("Intervalo entre pontos (ms)");
    JLabel lbl_altitudeObjetivo = new JLabel("Altitude Objetivo(m)");
    
    JTextField jtf_aceleracaoGravid = new JTextField();
    JTextField jtf_densidadeAr = new JTextField();
    JTextField jtf_anguloInicial = new JTextField();
    JTextField jtf_passoTempo = new JTextField();
    JTextField jtf_altitudeObjetivo = new JTextField();

    
    JButton initSimulation = new JButton("Iniciar");
    ImageIcon bkg = new ImageIcon(currentPath+"\\src\\images\\dpe.jpg");
    public Properties(){
        super("Parâmetros de Vôo");
        Container container = getContentPane();
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        setLayout(null);
               
       
        
        
        lbl_DADOS_FOGUETE.setBounds(30,20,200,20);
    
     lbl_massa.setBounds(20,50,200,20);
     lbl_diametroTubo.setBounds(20,75,200,20);
     lbl_coefDragFus.setBounds(20,100,200,20);
     lbl_impulsoMotor.setBounds(20,125,200,20);
     lbl_tempoQueima.setBounds(20,150,200,20);
    
     jtf_massa.setBounds(230,50,100,20);
     jtf_diametroTubo.setBounds(230,75,100,20);
     jtf_coefDragFus.setBounds(230,100,100,20);
     jtf_impulsoMotor.setBounds(230,125,100,20);
     jtf_tempoQueima.setBounds(230,150,100,20);

     jtf_massa.setText("0.1");
     jtf_diametroTubo.setText("18");
     jtf_coefDragFus.setText("0.5");
     jtf_impulsoMotor.setText("15");
     jtf_tempoQueima.setText("3");
    
    //--- DADOS DAS ASAS
    
     lbl_DADOS_ASAS.setBounds(30,180,200,20);
    
     lbl_coefLiftSimul.setBounds(20,210,200,20);
     lbl_coefDragSimul.setBounds(20,235,200,20);
     lbl_coefLiftMax.setBounds(20,260,200,20);
     lbl_coefDragMax.setBounds(20,285,200,20);
     lbl_cordaMediaAsa.setBounds(20,310,200,20);
     lbl_envergaduraAsa.setBounds(20,335,200,20);
    
     jtf_coefLiftSimul.setBounds(230,210,100,20);
     jtf_coefDragSimul.setBounds(230,235,100,20);
     jtf_coefLiftMax.setBounds(230,260,100,20);
     jtf_coefDragMax.setBounds(230,285,100,20);
     jtf_cordaMediaAsa.setBounds(230,310,100,20);
     jtf_envergaduraAsa.setBounds(230,335,100,20);
     
     jtf_coefLiftSimul.setText("0");
     jtf_coefDragSimul.setText("0.0007");
     jtf_coefLiftMax.setText("3");
     jtf_coefDragMax.setText("0.024");
     jtf_cordaMediaAsa.setText("0.025");
     jtf_envergaduraAsa.setText("0.05");
    
    //--- DADOS DA SIMULAÇÃO
     lbl_DADOS_SIMULACAO.setBounds(30,365,200,20);
    
    
     lbl_aceleracaoGravid.setBounds(20,395,200,20);
     lbl_densidadeAr.setBounds(20,420,200,20);
     lbl_anguloInicial.setBounds(20,445,200,20);
     lbl_passoTempo.setBounds(20,470,200,20);
     lbl_altitudeObjetivo.setBounds(20,495,200,20);
    
     jtf_aceleracaoGravid.setBounds(230,395,100,20);
     jtf_densidadeAr.setBounds(230,420,100,20);
     jtf_anguloInicial.setBounds(230,445,100,20);
     jtf_passoTempo.setBounds(230,470,100,20);
     jtf_altitudeObjetivo.setBounds(230,495,100,20);
        
     jtf_aceleracaoGravid.setText("9.81");
     jtf_densidadeAr.setText("1.2922");
     jtf_anguloInicial.setText("45");
     jtf_passoTempo.setText("10");
     jtf_altitudeObjetivo.setText("400");
        
        initSimulation.setBounds(440,600,220,40);
        initSimulation.setBackground(new Color(135,206,250,250));
        initSimulation.setForeground(Color.BLACK);
        initSimulation.setFont(new Font("Arial", Font.PLAIN, 15));
        initSimulation.setToolTipText("example");
        Properties frame = this;
        ActionListener ac = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if("initSimulation".equals(e.getActionCommand())){
                    
                    Double jtf_massaValue = Double.parseDouble(jtf_massa.getText());
                    Double jtf_diametroTuboValue = Double.parseDouble(jtf_diametroTubo.getText())/1000;
                    Double jtf_coefDragFusValue = Double.parseDouble(jtf_coefDragFus.getText());
                    Double jtf_impulsoMotorValue = Double.parseDouble(jtf_impulsoMotor.getText());
                    Double jtf_tempoQueimaValue = Double.parseDouble(jtf_tempoQueima.getText());
                    
                    Double jtf_coefLiftSimulValue = Double.parseDouble(jtf_coefLiftSimul.getText());
                    Double jtf_coefDragSimulValue = Double.parseDouble(jtf_coefDragSimul.getText());
                    Double jtf_coefLiftMaxValue = Double.parseDouble(jtf_coefLiftMax.getText());
                    Double jtf_coefDragMaxValue = Double.parseDouble(jtf_coefDragMax.getText());
                    Double jtf_cordaMediaAsaValue = Double.parseDouble(jtf_cordaMediaAsa.getText());
                    
                    Double jtf_envergaduraAsaValue = Double.parseDouble(jtf_envergaduraAsa.getText());
                    Double jtf_aceleracaoGravidValue = Double.parseDouble(jtf_aceleracaoGravid.getText());
                    Double jtf_densidadeArValue = Double.parseDouble(jtf_densidadeAr.getText());
                    Double jtf_anguloInicialValue = Math.toRadians(Double.parseDouble(jtf_anguloInicial.getText()));
                    Double jtf_passoTempoValue = Double.parseDouble(jtf_passoTempo.getText())/1000;
                    Double jtf_altitudeObjetivoValue = Double.parseDouble(jtf_altitudeObjetivo.getText());

                   // SimulationGraphic simulGraph = new SimulationGraphic(jtf_massaValue,jtf_diametroTuboValue,jtf_coefDragFusValue,jtf_impulsoMotorValue,jtf_tempoQueimaValue,jtf_coefLiftSimulValue,jtf_coefDragSimulValue,jtf_coefLiftMaxValue,jtf_coefDragMaxValue,jtf_cordaMediaAsaValue,jtf_envergaduraAsaValue,jtf_aceleracaoGravidValue,jtf_densidadeArValue,jtf_anguloInicialValue,jtf_passoTempoValue,jtf_altitudeObjetivoValue);
                    frame.dispose();
                    
                    Simulacao s = new Simulacao();
                    
                        
	s.run(jtf_massaValue, jtf_diametroTuboValue, jtf_coefDragFusValue
			, jtf_impulsoMotorValue, jtf_tempoQueimaValue, jtf_coefLiftSimulValue
			, jtf_aceleracaoGravidValue, jtf_densidadeArValue, jtf_altitudeObjetivoValue
			, jtf_envergaduraAsaValue, jtf_cordaMediaAsaValue, jtf_passoTempoValue, jtf_anguloInicialValue
			, jtf_coefLiftMaxValue, jtf_coefDragSimulValue, jtf_coefDragMaxValue);
                }
            }   
        };
        initSimulation.setActionCommand("initSimulation");
        initSimulation.addActionListener(ac);
        container.add(initSimulation);
        
    container.add(lbl_DADOS_FOGUETE);
    container.add(lbl_massa);
    container.add(lbl_diametroTubo);
    container.add(lbl_coefDragFus);
    container.add(lbl_impulsoMotor);
    container.add(lbl_tempoQueima);
    container.add(jtf_massa);
    container.add(jtf_diametroTubo);
    container.add(jtf_coefDragFus);
    container.add(jtf_impulsoMotor);
    container.add(jtf_tempoQueima);
    container.add(lbl_DADOS_ASAS);
    container.add(lbl_coefLiftSimul);
    container.add(lbl_coefDragSimul);
    container.add(lbl_coefLiftMax);
    container.add(lbl_coefDragMax);
    container.add(lbl_cordaMediaAsa);
    container.add(lbl_envergaduraAsa);
    container.add(jtf_coefLiftSimul);
    container.add(jtf_coefDragSimul);
    container.add(jtf_coefLiftMax);
    container.add(jtf_coefDragMax);
    container.add(jtf_cordaMediaAsa);
    container.add(jtf_envergaduraAsa);
    container.add(lbl_DADOS_SIMULACAO);
    container.add(lbl_aceleracaoGravid);
    container.add(lbl_densidadeAr);
    container.add(lbl_anguloInicial);
    container.add(lbl_passoTempo);
    container.add(lbl_altitudeObjetivo);
    container.add(jtf_aceleracaoGravid);
    container.add(jtf_densidadeAr);
    container.add(jtf_anguloInicial);
    container.add(jtf_passoTempo);
    container.add(jtf_altitudeObjetivo);
        
        
        pack();
        setVisible(true);
        setSize(700,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    public void  toggleVisible(){
        setVisible(!isVisible());
        setSize(700,700);
    }
}
