package HW1.DoublyLinkedList_Interface;

public interface ListAbstractType<E> extends CollectionType<E> {
    /**
     * Returns the element found at the specified position in this list.
     *
     * @param index the specified position in this list
     * @return the element found at the specified position in this list
     */
    E get(int index);

    /**
     * Replaces the current element at the specified position with the provided new element.
     *
     * @param index   the specified position
     * @param element the new element provided
     * @return the element previously held at the specified position
     */
    E set(int index, E element);

    /**
     * Adds the specified element at the specified position, pushing subsequent elements to
     * one position higher. If {@code index == 0}, the addition happens at the front of this
     * list. If {@code index == size()}, the element is added as the new last element.
     *
     * @param index   the specified position
     * @param element the specified element to be added
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position.
     *
     * @param index the specified position
     */
    void remove(int index);
}
