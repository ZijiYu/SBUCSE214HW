package HW1.DoublyLinkedList_Interface;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DO NOT MODIFY THIS CODE!!
 *
 * @author Ritwik Banerjee
 */
public interface TwoWayListIterator<E> extends Iterator<E> {
    /**
     * @return {@code true} if the reverse iteration has more elements. In other words,
     *         returns {@code true} if {@link #previous} would return an element rather than
     *         throwing an exception.
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in this list and moves the cursor position backward
     * by one. This method may, if needed, be intermixed with calls to {@link #next()}
     * to go back and forth.
     *
     * @return the previous element in this list
     * @throws NoSuchElementException if the iteration has no previous elements
     */
    E previous();

    /**
     * Inserts the specified element into the list. The element is inserted immediately
     * before the element that would be returned by {@link #next()}, if any, and after
     * the element that would be returned by {@link #previous()}, if any. If the list
     * contains no elements, the new element becomes the sole element on the list. The
     * new element is inserted before the implicit cursor: a subsequent call to
     * {@code next} would be unaffected, and a subsequent call to {@code previous} would
     * return the new element.
     *
     * @param element the element to insert
     * @throws UnsupportedOperationException if the {@code add} method is not supported
     *                                       by this list iterator
     */
    void add(E element);

    /**
     * Replaces the last element returned by {@link #next()} or {@link #previous()} with
     * the specified element (optional operation).
     * This call can be made only if neither {@link #remove()} nor {@link #add(E)} have
     * been called after the last call to {@code next()} or {@code previous}.
     *
     * @param element the element with which to replace the last element returned by
     *                {@code next} or {@code previous}
     * @throws UnsupportedOperationException if the {@code set} operation is not
     *                                       supported by this list iterator
     * @throws IllegalStateException      if neither {@code next} nor {@code previous}
     *                                       have been called, or {@code remove} or
     *                                       {@code add} have been called after the last
     *                                       call to {@code next} or {@code previous}
     */
    void set(E element);
}
