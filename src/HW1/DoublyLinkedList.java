
package HW1;

import HW1.DoublyLinkedList_Interface.*;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements ListAbstractType<E>, Iterable<E>{
    /*
    1. A private static class nested inside the DoublyLinkedList.
    3. Class must be private, its fields can be public.
    4. A constructor for this class, and each node instance must have a reference to the previous node, the next node, and elements.
     */
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        public E element;
        public Node<E> next;
        public Node<E> prev;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

    }
    /*
    1. Users may add index 1 or index 0 in the first node of List, or sometimes out of the range.
    2. This helper method can correct the index they input in to a standard way.
     */
    private int standardIndex(int index){
        if(size == 0 && index == 1){
            index = 0;
        } else if (size+1 < index){
            index = size+1;
        } else if (index<0){
            index = 0;
        }
        return index;
    }
    /*
    1. Helper method can solve the problem of return type in method
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    }
    /*
    1. Use two pointer thinking way to have an efficient search
    2. I think most of the methods will use this block of code,therefore I package it
     */
    private Node<E> getCurrent(int index){
        if (index < 0 ) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else{
            current  = tail;
            for (int i = size-1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    /*
    1. Adds the specified element at the specified position
    2. Keep the original node
    3. Push the subsequent elements to higher index position
    4. If index equals to zero then add in the front of the list
    5. If index equals to size then add at the end of the list
     */
    public void add(int index, E element) {
        Node<E> newNode = new Node<>(element, null, null);
        if(index>0 && size ==0){
            throw new IndexOutOfBoundsException();
        }

        if (size == 0) {
            // List is empty
            head = tail = newNode;
        } else if (index == 0) {
            // Add at the beginning
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index >= size && size != 0) {
            // Add at the end
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            // Add in the middle
            Node<E> current = getCurrent(index);
            newNode.next = current.next; // newNode->current.next
            newNode.prev = current; // current <- newNode -> current.next
            current.next.prev = newNode; // current <-newNode <->newNode
            current.next = newNode; // current <-> newNode <-> newNode
        }
        size++;
    }

    @Override
    /*
    1. Returns the element found at the specified position in this list.\
    2. Consider the bound range for the INPUT
    */
    public E get(int index) {
        Node<E> current = getCurrent(index);
        return current.element;
    }

    @Override
    /*
    1. Replaces the current element at the specified position with the provided new element
    2. Return the element previously held at the specified position
     */
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> current = head;
        E oldVal;
        current = getCurrent(index);
        oldVal = current.element;
        current.element = element;
        return oldVal;
    }

    @Override
    /*
    1. Removes the element at the specified position.
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = getCurrent(index);
        // remove the beginning
        if (current.prev == null) {
            head = current.next;
            if (head != null) {  // If the current node have next node
                head.prev = null;
            } else {
                tail = null;    // The list is empty
            }
            // remove the tail
        } else if (current.next == null) {
            tail = current.prev;
            tail.next = null;
        } else {
            // remove the middle one
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }

    @Override
    /*
     1. Adds the specified element to this collection.
     2. If the collection offers a particular iteration order, then the element is added at the end of the collection.
     */
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element, tail, null); // we can directly assign tail as the previous node during instantiation

        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        ++size;
        return true;
    }

    /*
    1. Removes the specified element (if it exists) from this collection.
    2. If the collection offers a particular iteration order, then the first occurrence of this element (as encountered in the iteration) is removed.
    */
    @Override
    public boolean remove(E element) {
        Node<E> current = head;
        for (int index = 0; index < size; index++) {
            if(current.element == element){
                current.prev.next = current.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        if(isEmpty()){return 0;}
        int size = 0;
        Node<E> current = head;
        while(current!= tail){
            current= current.next;
            size++;
        }
        return size;
    }
    /*
    1. if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    1. Dentifies regardless of whether this collection contains the specified element.
     */
    @Override
    public boolean contains(E element) {
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    @Override
    public String toString() {
        Iterator<E> it = this.iterator();
        if (!it.hasNext())
            return "[]";
        StringBuilder builder = new StringBuilder("[");
        while (it.hasNext()) {
            E e = it.next();
            builder.append(e.toString());
            if (!it.hasNext())
                return builder.append("]").toString();
            builder.append(", ");
        }
        // code execution should never reach this line
        return null;
    }

//    @Override
//    public Iterator<E> iterator() {
//        return new ListIterator();
//    }
//
//    private class ListIterator implements Iterator<E> {
//        private Node<E> currentNode = head;
//
//        @Override
//        public boolean hasNext() {
//            return currentNode != null;
//        }
//
//        @Override
//        public E next() {
//            if (currentNode == null) {
//                throw new NoSuchElementException();
//            }
//
//            E data = currentNode.element;
//            currentNode = currentNode.next;
//            return data;
//        }
//    }

    @Override
    public TwoWayListIterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements TwoWayListIterator<E>{

        private Node<E> current = head;
        private Node<E> lastAccessed = null;
        /*
        1. This two method is the fundamental method in iterator.
        2. I use the same way from the build in iterator.
         */
        @Override
        public boolean hasNext() {
            //&& current.next != null
            return current != null;
        }
//------
        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            lastAccessed = current; // Make a mark for the lastAccessed
            E Data = current.element;
            current = current.next;
            return Data;
        }

        @Override
        public boolean hasPrevious() {
            return current != null && current.prev != null;
        }

        /*
        1. Returns the previous element in this list.
        2. Moves the cursor position backward by one.
         */
        @Override
        public E previous() {
            if(!hasPrevious()){
                throw new NoSuchElementException();
            }
            lastAccessed = current;
            E Data = current.element;
            current = current.prev;
            return Data;
        }

        @Override
        public void add(E element) {
            if(element == null){throw new UnsupportedOperationException();}
            Node<E> newNode = new Node<>(element,null,null);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else if (current == null) {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            } else {
                newNode.prev = current.prev;
                newNode.next = current;
                if (current.prev != null) {
                    current.prev.next = newNode;
                } else {
                    head = newNode;
                }
                current.prev = newNode;
            }
            size++;
            lastAccessed = null;
        }

        @Override
        public void set(E element) {
            if (lastAccessed == null) {
                throw new IllegalStateException();
            }
            lastAccessed.element = element;
        }
        @Override
        public void remove() {
            if (lastAccessed == null) {
                throw new IllegalStateException("next() or previous() must be called before removing an element.");
            }

            // 当我们要移除lastAccessed，我们需要更新其前后节点的指向
            if (lastAccessed.prev != null) {
                lastAccessed.prev.next = lastAccessed.next;
            } else {
                // 如果lastAccessed没有前一个节点，那它就是head
                head = lastAccessed.next;
            }

            if (lastAccessed.next != null) {
                lastAccessed.next.prev = lastAccessed.prev;
            } else {
                // 如果lastAccessed没有下一个节点，那它就是tail
                tail = lastAccessed.prev;
            }

            // 如果lastAccessed是current前面的节点，那么减少current的位置
            if (current == lastAccessed) {
                current = lastAccessed.next;
            }

            size--;
            lastAccessed = null; // 重置lastAccessed
        }

    }
}
