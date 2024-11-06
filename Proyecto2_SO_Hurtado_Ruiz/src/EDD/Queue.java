
package EDD;
import Proyecto2_SO_Hurtado_Ruiz.CharacterS;

public class Queue {
    private Nodo head;
    private Nodo tail;
    private int size;
    private int priority;

    public Queue(int priority) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.priority=priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
    
    public void gettingPeopleOut(Queue queueSuperior){
        Nodo pointer = getHead();
        while (pointer != null) {
            // Guarda el siguiente nodo antes de modificar la lista
            Nodo next = pointer.getNext();

            // Verifica la condición de starvation y realiza el traslado si aplica
            if (pointer.getElement().getCountStarvation() == 8) {
                // Transfiere el elemento y elimina el nodo actual de la cola
                queueSuperior.enqueue(pointer.getElement());
                lookDispatch(pointer);
                System.out.println("Se nos fue a una clase mejor " + pointer.getElement().getName());
            }

            // Avanza al siguiente nodo
            pointer = next;
        }
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
            names += pointer.getElement().getName()+" "+pointer.getElement().getId() +" "+pointer.getElement().getCountStarvation()+"\n";
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
    
    public void lookDispatch(Nodo characterNode) {
        Nodo pointer = getHead();
        Nodo aux = null; // Auxiliar para el nodo anterior

        // Itera para encontrar el nodo a eliminar
        while (pointer != null) {
            if (characterNode == pointer) {
                if (pointer == getHead()) {
                    // Si es la cabeza, usa dispatch
                    dispatch();
                } else if (pointer == getTail()) {
                    // Si es la cola, actualiza el puntero de aux
                    aux.setNext(null);
                    setTail(aux);
                    size--;
                } else {
                    // Nodo en el medio de la cola
                    aux.setNext(pointer.getNext());
                    size--;
                }
                return; // Salir de la función al encontrar el nodo
            }
            aux = pointer;
            pointer = pointer.getNext();
        }
    }
    
}
