/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto2_SO_Hurtado_Ruiz;

import EDD.Queue;
import GUI.Game;
import GUI.Home;
import static GUI.Home.admin;
import static GUI.Home.publicQueueST1;
import static GUI.Home.publicQueueST2;
import static GUI.Home.publicQueueST3;
import static GUI.Home.publicQueueSW1;
import static GUI.Home.publicQueueSW2;
import static GUI.Home.publicQueueSW3;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author José Hurtado y Joseph Ruiz
 */
public class IA extends Thread{
    private Queue queueSW1;
    private Queue queueST1;
    private Queue queueSWR;
    private Queue queueSTR;
    private String status;
    public JTextField time;
    private Semaphore mutex;
    private static final Logger logger = Logger.getLogger(IA.class.getName());


    public IA(Queue queueSW1, Queue queueST1, Queue queueSWR, Queue queueSTR, JTextField time) {
        this.queueSW1 = queueSW1;
        this.queueST1 = queueST1;
        this.queueSWR = queueSWR;
        this.queueSTR = queueSTR;
        this.status = Home.stateJLabel.getText();
        this.time = time;
//        this.mutex= mutex;
    }
    
    public void decideResult(CharacterS charSW, CharacterS charST){
        RandomGen random = new RandomGen();
        Home.stateJLabel.setText("Waiting");
        Home.aIImagenPublic.setIcon(new ImageIcon(getClass().getResource("/Img/waitRobot (1).png")));
        sleepCode();
        int result = random.result();
        if (result == 1){
            Home.stateJLabel.setText("Winner");
            Home.aIImagenPublic.setIcon(new ImageIcon(getClass().getResource("/Img/winnerRobot (1).png")));
            Game startMiniGame = new Game();
            startMiniGame.setVisible(true);
            while (startMiniGame.isVisible()) {                
                try {
                    sleep(100);
                    
                } catch (Exception e) {
                }
            }
        } else if (result == 2){
            Home.stateJLabel.setText("Winner");
            Home.aIImagenPublic.setIcon(new ImageIcon(getClass().getResource("/Img/winnerRobot (1).png")));
            JOptionPane.showMessageDialog(null, "El combate ha resultado en empate");
            charSW.setPriority(1);
            charST.setPriority(1);
            queueSW1.enqueue(charSW);
            queueST1.enqueue(charST);
        } else{
            Home.stateJLabel.setText("Winner");
            Home.aIImagenPublic.setIcon(new ImageIcon(getClass().getResource("/Img/winnerRobot (1).png")));
            JOptionPane.showMessageDialog(null, "El combate no se pudo realizar");
            queueSWR.enqueue(charSW);
            queueSTR.enqueue(charST);
        }
        ;
        
    }
    
    public void sleepCode() {
        try {
            int num = Integer.parseInt(time.getText());
            sleep(num*10);
            //System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void run(){
        while(true){
            try {
                Home.mutex.acquire();
                CharacterS warriorSW=admin.giveSWChar();
                CharacterS warriorST=admin.giveSTChar();
                
                Home.charSW.setText(warriorSW.getName());
                Home.charST.setText(warriorST.getName());
                System.out.println(warriorSW.getName() +" y "+warriorST.getName());
                
                decideResult(warriorSW, warriorST);
                Home.mutex.release();
                int num = Integer.parseInt(time.getText());
                Home.aIImagenPublic.setIcon(new ImageIcon(getClass().getResource("/Img/chooseRobot (1).png")));
                
                admin.starvation();
                sleep(num);
                
                
                
            } catch (InterruptedException e) {
                System.out.println("error ai");
                logger.log(Level.SEVERE, "Thread interrupted", e);
            }
        }
    }
}
