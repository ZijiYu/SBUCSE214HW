package HW1.DoublyLinkedList_Interface;

public interface CollectionType<E> extends Iterable<E> {
    /**
     * Adds the specified element to this collection. If the collection offers a particular
     * iteration order, then the element is added at the end of the collection.
     *
     * @param element the specified element
     * @return {@code true} if the element was successfully added
     */
    boolean add(E element);

    /**
     * Removes the specified element (if it exists) from this collection. If the collection
     * offers a particular iteration order, then the first occurrence of this element (as
     * encountered in the iteration) is removed.
     *
     * @param element the specified element
     * @return {@code true} if the element was successfully removed from this collection
     */
    boolean remove(E element);

    /**
     * @return the number of elements in this collection
     */
    int size();

    /**
     * @return {@code true} if this collection contains no elements
     */
    boolean isEmpty();

    /**
     * Identifies whether or not this collection contains the specified element.
     *
     * @param element the specified element
     * @return {@code true} if the specified element was found in this collection
     */
    boolean contains(E element);
}
