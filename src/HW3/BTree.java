package HW3;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class BTree<E extends Comparable<E>> {

    private Node<E> root;
    private int degree;

    // Node class
    class Node<E extends Comparable<E>> {
        int size;
        int degree;
        LinkedList<E> key;
        LinkedList<Node> children;
        boolean isLeaf;

        // Node constructor
        Node(int degree, boolean isLeaf) {
            this.size = 0;
            this.degree = degree;
            this.isLeaf = isLeaf;
            this.key = new LinkedList<>();
            this.children = new LinkedList<>();

        }

        @Override
        public String toString(){
            return key.toString();
        }
    }

    // B-Tree constructor
    public BTree(int minimumDegree){
        // Cannot have negative degree
        if(minimumDegree<=0){
            throw new IllegalArgumentException("[Warning] Minimum degree must be greater than 0");
        }
        this.degree = minimumDegree;
        this.root = null;
    }

    // Add element into not full tree
    public void addIntoNoFull(E element, Node<E> node) {
        // test if exist
        if(exist(element)==false){
            // if node is leaf
            if (node.isLeaf) {
                int location = getLocation(element,node);
                node.key.add(location, element);
                node.size++;
                if(node.size == 2*degree-1 && node== root){
                    Node<E> newRoot = new Node<>(degree,false);
                    newRoot.children.add(0,root);
                    splitChild(newRoot,root,0);
                    root = newRoot; // let the newRoot become root
                }
            }
            // if it is inner node
            else {

                int location = getLocation(element,node);
                // get a Child of the given node
                Node<E> current = node.children.get(location);
                // if current is full
                if(current.size == 2*degree-1){
                    splitChild(node,current,location);
                    // if element is greater than key.get(location), location++;
                    // this is for max_degree detection
                    if (element.compareTo(node.key.get(location))>0) ++location;
                }
                // add the element into the subtree
                addIntoNoFull(element,current);
                // if the node is full split
                if(current.size==2*degree-1){
                    splitChild(node,current,location);
                }
            }
        }
    }

    public void splitChild(Node<E> parent,Node<E> child, int i) {

        Node<E> newchild = new Node<>(degree, child.isLeaf);
        E midvalue = child.key.get(degree-1);

        // move key into new node
        for (int j = 0; j < degree-1; j++) {
            newchild.key.add(child.key.get(degree));
            ++newchild.size;
            child.key.remove(degree);
            --child.size;
        }
        --child.size;
        parent.key.add(i,midvalue);
        parent.size+=1;
        child.key.remove(degree - 1);

        // if it is inner node
        if (!child.isLeaf) {
            for (int j = 0; j < degree; j++) {
                newchild.children.add(child.children.get(degree));
                child.children.remove(degree);
            }
        }
        // parent add children node reference
        parent.children.add(i+1,newchild);
    }

    private int getLocation(E element, Node<E> node) {
        int location = node.size;
        // search from last element
        while (location > 0 && element.compareTo(node.key.get(location-1))<0) {
            location--;
        }
//            if(location != -1){
//                return location;
//            }else {
//                return node.size;
//            }
        return location;
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
        // add the first element
        if(root == null) {
            root = new Node<E>(degree, true);
            root.key.add(element);
            root.size++;
        } else {
            if(root.key.size()==max){
                Node<E> newRoot = new Node<E>(degree,false);
                newRoot.children.add(root);
                splitChild(newRoot,root,0);
                addIntoNoFull(element,newRoot);
                root = newRoot;
            }else{// 树没有满
                addIntoNoFull(element,root);
            }
        }
    }

    public boolean exist(E element) {
        return existRec(element, root);
    }

    private boolean existRec(E element, Node<E> node) {
        if (node == null) {
            return false;
        } else {
            int index = 0;
            while (index < node.size && element.compareTo(node.key.get(index)) > 0) {
                index++;
            }
            if (index < node.size && element.compareTo(node.key.get(index)) == 0) {
                return true;
            }
            // no need to recurse for leaves
            if (node.isLeaf) {
                return false;
            }
            return existRec(element, node.children.get(index));
        }
    }

    public void addAll(Collection<E> elements) throws OperationNotSupportedException {
        for (E e : elements) this.add(e);
    }

    public void show() {
        String nodesep = " ";
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        if(root!=null){
            queue1.add(root); /* root of the tree being added */
        }
        while (true) {
            while (!queue1.isEmpty()) {
                Node node = queue1.poll();
                System.out.printf("%s%s", node.toString(), nodesep);
                if (!node.children.isEmpty())
                    queue2.addAll(node.children);
            }
            System.out.printf("%n");
            if (queue2.isEmpty())
                break;
            else {
                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }
    }

    public class Pair{
        private Node<E> node;
        private int index;

        public Pair(Node node, int index){
            this.node = node;
            this.index = index;
        }
    }
    public String find(E element){
        Pair pair = findRec(element, root);
        StringBuffer str = new StringBuffer();
        if(pair== null){
            str.append("null");
        }else{
            str.append(pair.node.toString());
            str.append(pair.index);
        }
        return str.toString();
    }

    private Pair findRec(E element, Node<E> node) {
        if (node == null) {
            return null; // node is null
        }

        int index = 0;
        while (index < node.size && element.compareTo(node.key.get(index)) > 0) {
            index++;
        }

        if (index < node.size && element.compareTo(node.key.get(index)) == 0) {
            return new Pair(node, index);
        }

        if (node.isLeaf) {
            return null; // not in the tree
        }

        // not in the tree and find subtree
        return findRec(element, node.children.get(index));
    }
}
