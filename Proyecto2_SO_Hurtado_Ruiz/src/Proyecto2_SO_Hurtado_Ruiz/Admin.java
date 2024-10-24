
package Proyecto2_SO_Hurtado_Ruiz;

import EDD.Queue;

public class Admin {
    private Queue queueSW1;
    private Queue queueSW2;
    private Queue queueSW3;
    private Queue queueSWR;
    
    private Queue queueST1;
    private Queue queueST2;
    private Queue queueST3;
    private Queue queueSTR;

    public Admin(Queue queueSW1, Queue queueSW2, Queue queueSW3, Queue queueSWR, Queue queueST1, Queue queueST2, Queue queueST3, Queue queueSTR) {
        this.queueSW1 = queueSW1;
        this.queueSW2 = queueSW2;
        this.queueSW3 = queueSW3;
        this.queueSWR = queueSWR;
        this.queueST1 = queueST1;
        this.queueST2 = queueST2;
        this.queueST3 = queueST3;
        this.queueSTR = queueSTR;
    }
    
    public Character giveSWChar(){
        if (!queueSW1.isEmpty()){
            return queueSW1.dispatch().getElement();
        } else if (!queueSW2.isEmpty()){
            return queueSW2.dispatch().getElement();
        } else if (!queueSW3.isEmpty()){
            return queueSW3.dispatch().getElement();
        }
        return null;
    }
    
    public Character giveSTChar(){
        if (!queueST1.isEmpty()){
            return queueST1.dispatch().getElement();
        } else if (!queueST2.isEmpty()){
            return queueST2.dispatch().getElement();
        } else if (!queueST3.isEmpty()){
            return queueST3.dispatch().getElement();
        }
        return null;
    }
}
