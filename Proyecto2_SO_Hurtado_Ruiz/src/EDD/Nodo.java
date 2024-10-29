
package EDD;
import Proyecto2_SO_Hurtado_Ruiz.CharacterS;

public class Nodo {
    private CharacterS element;
    private Nodo next;

    public Nodo(CharacterS element) {
        this.element = element;
        this.next = null;
    }

    /**
     * @return the element
     */
    public CharacterS getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(CharacterS element) {
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
