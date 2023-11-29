package HW3;
import java.util.Collection.*;
import java.util.LinkedList;

class Node<E extends Comparable<E>>{
    private E num;
    private LinkedList<E> key;
    private boolean isLeaf;
    private LinkedList<Node<E>> children;

    private Node(int degree, boolean isLeaf){
        this.num = null;
        this.isLeaf = true;
        this.key = null;
        this.children = null;
    }

public class BTree<E extends Comparable<E>> {
        private Node<E> root;
        private int degree;
        public void BTree(int degree){
            this.degree = degree;
        }



    }


}
