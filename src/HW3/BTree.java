package HW3;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Queue;

class Node<E extends Comparable<E>> {
    int size;
    int degree;
    LinkedList<E> key;
    LinkedList<Node> children;
    boolean isLeaf;
    // constructor for node
    Node(int degree, boolean isLeaf) {
        this.size = 0;
        this.degree = degree;
        this.isLeaf = isLeaf;
        this.key = new LinkedList<>();
        if(!isLeaf){
            this.children = new LinkedList<>();
        }
    }

    public void addIntoNoFull(E element) {
        if (isLeaf) {
            int location = getLocation(element);
            if(location != -1){
                key.add(location, element);// 插入到正确的位置
                size++;
            }
        } else {
            int location = getLocation(element);
            // check is there any duplication
            if(location != -1){
                Node<E> current = children.get(location);
                if(current.size == 2*degree-1){
                    current.splitChild(current.children.get(location),location);
                    if (element.compareTo(key.get(location))>0) location+=1;
                }
                current.addIntoNoFull(element);
            }
        }
    }

    public void splitChild(Node<E> child, int i) {

        Node<E> newchild = new Node<>(degree, child.isLeaf);
        E midvalue = child.key.get(degree-1);

        // 移动键到新节点
        for (int j = 0; j < degree-1; j++) {
            newchild.key.add(child.key.get(degree));
            ++newchild.size;
            child.key.remove(degree);
            --child.size;
        }
        --child.size;
        key.add(i,midvalue);
        child.key.remove(degree - 1);

        // 如果不是叶节点，移动子节点到新节点
        if (!child.isLeaf) {
            for (int j = 0; j < degree; j++) {
                newchild.children.add(child.children.get(degree));
                child.children.remove(degree);
            }
        }
        children.add(i+1,newchild);
    }

    private int getLocation(E element) {
        int location = size;
        while (location >=0 && element.compareTo(key.get(location-1))<0) {
            if(key.get(location).compareTo(element) == 0) return -1;
            location--;
        }
        return size;
    }

    @Override
    public String toString(){
        return key.toString();
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
        if(root == null) {
            root = new Node<E>(degree, true);
            root.key.add(element);
            root.size++;
        } else {
            if(root.key.size()==max){
                Node<E> newRoot = new Node<E>(degree,false);
                newRoot.children.add(root);
                newRoot.splitChild(root,0);
                newRoot.size+=1;
//                    int i = 0;
//                    if(newRoot.key.get(i).compareTo(element)<0) i++;
                newRoot.addIntoNoFull(element);
                root = newRoot;
            }else{// 树没有满
                root.addIntoNoFull(element);
            }
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

}

