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
public class MainFrame extends JFrame {
    String currentPath = System.getProperty("user.dir");
     //(currentPath);
    JLabel background = new JLabel();
    JButton initSimulation = new JButton("Simular vôo");
    ImageIcon bkg = new ImageIcon(currentPath+"\\build\\classes\\images\\background.jpg");
    public MainFrame(){
        super("Simulador de Vôo - DPE");
        Container container = getContentPane();
        container.setBackground(Color.WHITE);
        container.setLayout(new GridBagLayout());
        
        background.setIcon(bkg);
        background.setFont(new Font("Helvetica", Font.BOLD, 18));
        background.setOpaque(true);
        background.setBackground(Color.white);

        background.setVerticalTextPosition(SwingConstants.BOTTOM);
        background.setHorizontalTextPosition(SwingConstants.CENTER);

        background.setForeground(new Color(0,0,0,250));
        
        container.add(background);
       
        initSimulation.setBounds(210,550,220,40);
        
        initSimulation.setBackground(new Color(135,206,250,250));
        initSimulation.setForeground(Color.BLACK);
        initSimulation.setFont(new Font("Arial", Font.PLAIN, 15));
        initSimulation.setToolTipText("example");
        MainFrame frame = this;
        ActionListener ac = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if("initSimulation".equals(e.getActionCommand())){
                    frame.dispose();
                    Properties properties = new Properties();
                }
            }
        };
        initSimulation.setActionCommand("initSimulation");
        initSimulation.addActionListener(ac);
        background.add(initSimulation);
        setSize(700,700);
        pack();
        setVisible(true);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
