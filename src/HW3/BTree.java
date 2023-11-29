package HW3;

import javax.naming.OperationNotSupportedException;
import java.util.Collection.*;
import java.util.LinkedList;

class Node<E extends Comparable<E>> {
    private E num;
    private LinkedList<E> key;
    private boolean isLeaf;
    private LinkedList<Node<E>> children;

    private Node(int degree, boolean isLeaf) {
        this.num = null;
        this.isLeaf = true;
        this.key = null;
        this.children = null;
    }
}

class BTree<E extends Comparable<E>> {
    private Node<E> root;
    private int degree;
    public void BTree(int minimumdegree){
        if(minimumdegree<=0){
            throw new IllegalArgumentException("[Warning] Minimum degree must be greater than 0");
        }
        this.degree = minimumdegree;
    }

    /**
     * Adds the specified element to this B-tree. If the B-tree already contains this
     * element, this operation has no effect on the tree (i.e., it silently ignores
     * attempts to add duplicates).
     *
     * @param element The element to be added to this B-tree.
     * @throws OperationNotSupportedException If the add operation results in a call to add
     * the element to a non-full node while the node
     * is actually full.
     */
    public void add(E element) throws OperationNotSupportedException{

    }




}