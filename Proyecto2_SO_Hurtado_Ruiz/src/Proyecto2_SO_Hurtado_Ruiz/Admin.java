
package Proyecto2_SO_Hurtado_Ruiz;

import EDD.Names;
import EDD.Nodo;
import EDD.Queue;
import GUI.Home;
import static GUI.Home.publicQueueST1;
import static GUI.Home.publicQueueST2;
import static GUI.Home.publicQueueST3;
import static GUI.Home.publicQueueSTR;
import static GUI.Home.publicQueueSW1;
import static GUI.Home.publicQueueSW2;
import static GUI.Home.publicQueueSW3;
import static GUI.Home.publicQueueSWR;
import java.awt.Event;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Admin extends Thread{
    
    public static Names namesArrays;
    public static String[] namesSW;
    public static String[] namesST;
    
    private Queue queueSW1;
    private Queue queueSW2;
    private Queue queueSW3;
    private Queue queueSWR;
    public JTextField time;

    
    private Queue queueST1;
    private Queue queueST2;
    private Queue queueST3;
    private Queue queueSTR;
    private Semaphore mutex;
    RandomGen random = new RandomGen();
    private static final Logger logger = Logger.getLogger(Admin.class.getName());

    public Admin(Queue queueSW1, Queue queueSW2, Queue queueSW3, Queue queueSWR, Queue queueST1, Queue queueST2, Queue queueST3, Queue queueSTR, JTextField time) {
        this.queueSW1 = queueSW1;
        this.queueSW2 = queueSW2;
        this.queueSW3 = queueSW3;
        this.queueSWR = queueSWR;
        
        this.queueST1 = queueST1;
        this.queueST2 = queueST2;
        this.queueST3 = queueST3;
        this.queueSTR = queueSTR;
        this.time = time;
//        this.mutex= mutex;
        
        namesArrays = new Names();
        namesSW = namesArrays.namesSW();
        namesST = namesArrays.namesST();
    }
    
    public CharacterS giveSWChar(){
        if (!queueSW1.isEmpty()){
            return queueSW1.dispatch().getElement();
        } else if (!queueSW2.isEmpty()){
            return queueSW2.dispatch().getElement();
        } else if (!queueSW3.isEmpty()){
            return queueSW3.dispatch().getElement();
        }
        return null;
    }
    
    public CharacterS giveSTChar(){
        if (!queueST1.isEmpty()){
            return queueST1.dispatch().getElement();
        } else if (!queueST2.isEmpty()){
            return queueST2.dispatch().getElement();
        } else if (!queueST3.isEmpty()){
            return queueST3.dispatch().getElement();
        }
        return null;
    }
    
    public void reforceST(){
        int result=random.qualityNum(40);
        if (!queueSTR.isEmpty() && result==1){
            queueST1.enqueue(queueSTR.dispatch().getElement());
        } else if (!queueSTR.isEmpty()&& result==0){
            queueSTR.enqueue(queueSTR.dispatch().getElement());
        } else {
//            nothing juju
        }
    }
    public void reforceSW(){
        int result=random.qualityNum(40);
        if (!queueSWR.isEmpty() && result==1){
            queueSW1.enqueue(queueSWR.dispatch().getElement());
        } else if (!queueSWR.isEmpty()&& result==0){
            queueSWR.enqueue(queueSWR.dispatch().getElement());
        } else {
//            nothing juju
        }
    }
    
    
//    public CharacterS createCharacter(){
//        RandomGen random = new RandomGen();
//        CharacterS character = new CharacterS(random.genRandomNum(0, 100000));
//        return character;
//    }
    
    public void locateCharacter(CharacterS character, int franchise){           //Si franchise es 1, es un personaje de Star Wars
        if (franchise == 1){                                                    //Si franchise es 2, es un personaje de Star Trek
            if (character.getPriority() == 1){
                queueSW1.enqueue(character);
            } else if (character.getPriority() == 2){
                queueSW2.enqueue(character);
            } else{
                queueSW3.enqueue(character);
            }
        } else if (franchise == 2){
            if (character.getPriority() == 1){
                queueST1.enqueue(character);
            } else if (character.getPriority() == 2){
                queueST2.enqueue(character);
            } else{
                queueST3.enqueue(character);
            }
        }
    }
    
    public void StartSimulation(){
        //Crea y lleva a su respectiva cola los personajes de Star Wars
        for (int i = 0; i < 20; i++) {
            CharacterS character = new CharacterS(i, namesSW);
            locateCharacter(character, 1);
        }
        
        //Crea y lleva a su respectiva cola los personajes de Star Trek
        for (int i = 20; i < 40; i++) {
            CharacterS character = new CharacterS(i, namesST);
            locateCharacter(character, 2);
        }
        
        JOptionPane.showMessageDialog(null, "Personajes agregados a sus colas");
        start();
    }
    
    public void sleepCode() {
        try {
            int num = Integer.parseInt(time.getText());
            sleep(num);
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void run(){
        while(true){
            try {
                Home.mutex.acquire();
                System.out.println("start admin");
                reforceSW();
                reforceST();
                
                updateTextAreas();
                System.out.println("Ready admin");
                Home.mutex.release();
                sleepCode();
            } catch (InterruptedException e) {
                System.out.println("error");
                logger.log(Level.SEVERE, "Thread interrupted", e);
            }
        }
        
    }
    
    public void starvation(){
        counterAddOne(queueSW2, queueSW1);
        counterAddOne(queueSW3, queueSW2);
        counterAddOne(queueST2, queueST1);
        counterAddOne(queueST3, queueST2);
    }
    
    
    public void counterAddOne(Queue queue1, Queue queue2){
        Nodo pointer = queue1.getHead();
        if (pointer!=null) {
            for (int i = 0; i < queue1.getSize(); i++) {
                if (pointer.getElement().getCountStarvation()==8) {
                    pointer.getElement().setCountStarvation(0);
                    if (queue1.getPriority()!=1) {
                        queue1.lookDispatch(pointer);
                        queue2.enqueue(pointer.getElement());
                    }
                } else {
                    pointer.getElement().setCountStarvation(pointer.getElement().getCountStarvation()+1);
                    pointer = pointer.getNext();
                }
            }
            
        }
    }
    
    public void updateTextAreas(){
        publicQueueSW1.setText(queueSW1.showNames());
        publicQueueSW2.setText(queueSW2.showNames());
        publicQueueSW3.setText(queueSW3.showNames());
        publicQueueSWR.setText(queueSWR.showNames());

        publicQueueST1.setText(queueST1.showNames());
        publicQueueST2.setText(queueST2.showNames());
        publicQueueST3.setText(queueST3.showNames());
        publicQueueSTR.setText(queueSTR.showNames());
        }
}
