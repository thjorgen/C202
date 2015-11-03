public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    /** Create a default list */
    public MyLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /** Return the head element in the list */
    public E getFirst() {
       if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }

    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /** Add a new element at the specified index in this list
     * The index of the head element is 0 */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }

    }

    /** Remove the head node and
     *  return the object that is contained in the removed node. */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /** Remove the last node and
     * return the object that is contained in the removed node. */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /** Remove the element at the specified position in this list.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /** Clear the list */
    public void clear() {
        head = tail = null;
    }
    
    /**
     methods for lab 8 should be implemented here
     */
    
    /** This method determines whether or not an element is contained in the 
     *  linked list
     *  The variable e is an element of E and will return true if the element is
     *  in the list or false if it is not.
     */
     public boolean contains (E e){
        Node<E> current = head;
        for (int i = 0; i < size; i++){
            if(current.element.equals(e)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
     
    /** This method determines whether or not an element is contained in the 
     *  linked list and counts the comparisons made
     *  The variable e is an element of E and will return true if the element is
     *  in the list or false if it is not.
     *  The variable count is there to figure out how many comparisons are made.
     */
     public boolean contains(E e, int [] count){
         Node <E> current = head;
         for (int i =0; i < size; i++){
             if (current.element.equals(e)){
                 count[0] = i + 1;
                 return true;
             }
             current = current.next;
         }
         count[0]=size;
         return false;
     }
     /**
      * This method goes into the method and retrieves the element at the index 
      * inputted. 
      * @param index the position at which you want to get the element
      * @return return the element
      */
    public E get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        else{
            Node <E> current = head;
            for (int i = 0; i != index; i++){
                current = current.next;
            }
            return current.element;
        }
    }
    
    /**
     * Sees if an element is contained in the method and if it is then return 
     * return the index
     * @param e the element that is being tested 
     * @return the index the element is at
     */
    public int indexOf(E e){
        Node <E> current = head;
        for (int i = 0; i < size; i++){
            if (current.element.equals(e)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }
    
    /**
     * This method returns the last element that is contained in the linked list
     * so if there are two elements that are the same and will return the last 
     * one.
     * @param e the element that is tested to be contained and find the last 
     * element
     * @return return the last index.  
     */
    public int lastIndexOf (E e){
        Node <E> current = head;
        int index = -1;
        for (int i = 0; i< size; i++){
            if (current.element.equals(e)){
                index = i;
            }
            current = current.next;
        }
        return index;
    }
    
    /**
     * This method takes an index and a new element and goes to the index and  
     * replaces the element at that index with a new element 
     * @param index the index you want to replace
     * @param e the element that is being replaced
     * @return the element that was replaced
     */
    public E set(int index, E e){
        if (index < 0 || index >= size){
            return null;
        }
        else{
            Node <E> current = head;
            for (int i = 0; i != index; i++){
                current = current.next;
            }
            E word = current.element;
            current.element = e;
            return word;
        }
        
    }
    
    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element) {
            this.element = element;
        }
    }
}
    
/**
 * Output: 
 * run:
(1) [America]
(2) [Canada, America]
(3) [Canada, America, Russia]
(4) [Canada, America, Russia, France]
(5) [Canada, America, Germany, Russia, France]
(6) [Canada, America, Germany, Russia, France, Norway]
(7) [Poland, Canada, America, Germany, Russia, France, Norway]
(8) [Canada, America, Germany, Russia, France, Norway]
(9) [Canada, America, Russia, France, Norway]
(10) [Canada, America, Russia, France]
(11) The list does not contain Germany
(12) Invalid position
(13) The list element France is at position 3
(14) [India, Canada, America, Russia, France]
(15) [India, Canada, America, Russia, France, America]
(16) The list element America occurs last at 5
(17) [India, Canada, America, Russia, France, China]
BUILD SUCCESSFUL (total time: 1 second)
 */  
