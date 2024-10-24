
package EDD;

public class Nodo {
    private Character element;
    private Nodo next;

    public Nodo(Character element) {
        this.element = element;
        this.next = null;
    }

    /**
     * @return the element
     */
    public Character getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(Character element) {
        this.element = element;
    }

    /**
     * @return the next
     */
    public Nodo getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Nodo next) {
        this.next = next;
    }
}
