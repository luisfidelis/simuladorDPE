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
    ImageIcon bkg = new ImageIcon(currentPath+"\\build\\classes\\images\\background.jpg");
    public Properties(){
        super("Parâmetros de Vôo");
        Container container = getContentPane();
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        setLayout(null);
               
       
        
        
        lbl_DADOS_FOGUETE.setBounds(20,30,100,30);
    
     lbl_massa.setBounds(20,30,100,30);
     lbl_diametroTubo.setBounds(20,30,100,30);
     lbl_coefDragFus.setBounds(20,30,100,30);
     lbl_impulsoMotor.setBounds(20,30,100,30);
     lbl_tempoQueima.setBounds(20,30,100,30);
    
     jtf_massa.setBounds(20,30,100,30);
     jtf_diametroTubo.setBounds(20,30,100,30);
     jtf_coefDragFus.setBounds(20,30,100,30);
     jtf_impulsoMotor.setBounds(20,30,100,30);
     jtf_tempoQueima.setBounds(20,30,100,30);
    
    //--- DADOS DAS ASAS
    
     lbl_DADOS_ASAS.setBounds(20,30,100,30);
    
     lbl_coefLiftSimul.setBounds(20,30,100,30);
     lbl_coefDragSimul.setBounds(20,30,100,30);
     lbl_coefLiftMax.setBounds(20,30,100,30);
     lbl_coefDragMax.setBounds(20,30,100,30);
     lbl_cordaMediaAsa.setBounds(20,30,100,30);
     lbl_envergaduraAsa.setBounds(20,30,100,30);
    
     jtf_coefLiftSimul.setBounds(20,30,100,30);
     jtf_coefDragSimul.setBounds(20,30,100,30);
     jtf_coefLiftMax.setBounds(20,30,100,30);
     jtf_coefDragMax.setBounds(20,30,100,30);
     jtf_cordaMediaAsa.setBounds(20,30,100,30);
     jtf_envergaduraAsa.setBounds(20,30,100,30);
    
    //--- DADOS DA SIMULAÇÃO
     lbl_DADOS_SIMULACAO.setBounds(20,30,100,30);
    
    
     lbl_aceleracaoGravid.setBounds(20,30,100,30);
     lbl_densidadeAr.setBounds(20,30,100,30);
     lbl_anguloInicial.setBounds(20,30,100,30);
     lbl_passoTempo.setBounds(20,30,100,30);
     lbl_altitudeObjetivo.setBounds(20,30,100,30);
    
     jtf_aceleracaoGravid.setBounds(20,30,100,30);
     jtf_densidadeAr.setBounds(20,30,100,30);
     jtf_anguloInicial.setBounds(20,30,100,30);
     jtf_passoTempo.setBounds(20,30,100,30);
     jtf_altitudeObjetivo.setBounds(20,30,100,30);
        
        
        
        
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
                    frame.dispose();
                }
            }
        };
        initSimulation.setActionCommand("initSimulation");
        initSimulation.addActionListener(ac);
        container.add(initSimulation);
        
         
        
    container.add(lbl_DADOS_FOGUETE)
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
