/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto2_SO_Hurtado_Ruiz;

import EDD.Queue;
import java.util.concurrent.Semaphore;
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
    private JTextField time;
    private Semaphore mutex;

    public IA(Queue queueSW1, Queue queueST1, Queue queueSWR, Queue queueSTR, JTextField time, Semaphore mutex) {
        this.queueSW1 = queueSW1;
        this.queueST1 = queueST1;
        this.queueSWR = queueSWR;
        this.queueSTR = queueSTR;
        this.status = "Esperando";
        this.time = time;
        this.mutex= mutex;
    }
    
    public void decideResult(CharacterS charSW, CharacterS charST){
        RandomGen random = new RandomGen();
        this.sleepCode();
        int result = random.result();
        if (result == 1){
            //Lógica de decidir el ganador
        } else if (result == 2){
            JOptionPane.showMessageDialog(null, "El combate ha resultado en empate");
            charSW.setPriority(1);
            charST.setPriority(1);
            queueSW1.enqueue(charSW);
            queueST1.enqueue(charST);
        } else{
            JOptionPane.showMessageDialog(null, "El combate no se pudo realizar");
            queueSWR.enqueue(charSW);
            queueSTR.enqueue(charST);
        }
    }
    
    public void sleepCode() {
        try {
            int num = Integer.parseInt(time.getText());
            Thread.sleep(num*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void run(){

    }
}
