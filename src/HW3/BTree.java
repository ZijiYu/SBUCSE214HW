package HW3;

import javax.naming.OperationNotSupportedException;
import java.util.Collection.*;
import java.util.Iterator;
import java.util.LinkedList;

class Node<E extends Comparable<E>> {
    Node<E> parent;
    LinkedList<E> key;
    LinkedList<Node<E>> children;
    boolean isLeaf;
    Node(int degree, boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.key = new LinkedList<>();
        if(!isLeaf){
            this.children = new LinkedList<>();
        }
    }
}

public class BTree<E extends Comparable<E>> {

    private Node<E> root;
    private int degree;

    public BTree(int minimumDegree){
        if(minimumDegree<=0){
            throw new IllegalArgumentException("[Warning] Minimum degree must be greater than 0");
        }
        this.degree = minimumDegree;
        this.root = null;
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
        int max = 2*degree-1;
        // if root is null
        if(root.key.isEmpty()){
            root.key.add(element);
        }

        else if(root.key.size()==max){

        }
    }

    /*
    1. split the Node(linked list) from the middle number
    2. add the middle number into the parents and delete the original one
    3. give the parents the extra reference
     */
    private void split(Node<E> node){

        int mid = node.key.size();
        LinkedList<E> keys = node.key;
        Node<E> left = new Node (degree, node.isLeaf);
        Node<E> right = new Node (degree, node.isLeaf);
        Iterator<E> iterator = keys.listIterator();
        E midValue = keys.get(mid);

        // add keys into the left node
        for (int i = 0; i < mid-1; i++) {
            left.key.add(keys.get(i));
        }
        // add keys into the right node
        for (int i = mid-1; i < keys.size()-1; i++) {
            right.key.add(keys.get(i));
        }
        // get the reference from children
        if(!node.isLeaf){
            for (int i = 0; i <mid; i++) {
                left.children.add(node.children.get(i));
            }
            // move the middle one to the parent so that don't need to consider it
            for (int i = mid+1; i <node.children.size(); i++) {
                right.children.add(node.children.get(i));
            }
        }

        // put the value into the parents
        if(node == root){
            Node<E> newRoot = new Node(degree,false);
            newRoot.key.add(midValue);
            newRoot.children.add(left);
            newRoot.children.add(right);

        }else{
            // find the position for insert
            int addLocation = lessEqual(midValue, node.parent.key);
            node.parent.key.add(addLocation,midValue);
            node.parent.key.add(midValue);
            node.parent.children.add(addLocation,left);
            node.parent.children.add(addLocation+1,right);

        }

        keys.clear();
        if(!node.children.isEmpty()) node.children.clear();

    }

    private void insertNotFull(Node<E> node, E element){
        int i = node.key.size()-1;

        if(node.isLeaf){

        }
    }
    private int lessEqual(Comparable<E> comparable, LinkedList<E> list) {
        int count = 0;
        for (E element : list) {
            if (comparable.compareTo(element) >= 0) {
                count++;
            }
        }
        return count;
    }




}
