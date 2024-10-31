
package EDD;
import Proyecto2_SO_Hurtado_Ruiz.CharacterS;

public class Queue {
    private Nodo head;
    private Nodo tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * @return the head
     */
    public Nodo getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Nodo head) {
        this.head = head;
    }

    /**
     * @return the tail
     */
    public Nodo getTail() {
        return tail;
    }

    /**
     * @param tail the tail to set
     */
    public void setTail(Nodo tail) {
        this.tail = tail;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty(){
        return getHead() == null;
    }
    
    public Nodo enqueue(CharacterS element){
        Nodo node = new Nodo(element);
        if (isEmpty()){
            setHead(node);
            setTail(node);
        } else{
            Nodo pointer = getTail();
            pointer.setNext(node);
            setTail(node);
        }
        size++;
        return node;
    }
    
    public Nodo dispatch(){
        if (!isEmpty()){
            Nodo pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            if (getHead() == null){
                setTail(null);
            }
            size--;
            return pointer;
        }
        return null;
    }
    
    public String showIDs(){
        String ids = "";
        Nodo pointer = getHead();
        for (int i = 0; i < this.getSize(); i++) {
            ids += pointer.getElement().getId()+"\n";
            pointer = pointer.getNext();
        }
        return ids;
    }
    
    public String showNames(){
        String names = "";
        Nodo pointer = getHead();
        for (int i = 0; i < this.getSize(); i++) {
            names += pointer.getElement().getName()+"\n";
            pointer = pointer.getNext();
        }
        return names;
    }
    public CharacterS lookFor(String characterName){
        Nodo pointer = getHead();
        for (int i = 0; i < this.getSize(); i++) {
            if (characterName==pointer.getElement().getName()) {
                return pointer.getElement();
            } else {
                pointer = pointer.getNext();
            }
            
        }
        return null;
    }
    
}
