package HW3;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class BTree<E extends Comparable<E>> {

    private Node<E> root;
    private int degree;

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
            this.children = new LinkedList<>();

        }

        @Override
        public String toString(){
            return key.toString();
        }
    }

    public BTree(int minimumDegree){
        if(minimumDegree<=0){
            throw new IllegalArgumentException("[Warning] Minimum degree must be greater than 0");
        }
        this.degree = minimumDegree;
        this.root = null;
    }

    public void addIntoNoFull(E element, Node<E> node) {
        if(exist(element)==false){
            if (node.isLeaf) {
                int location = getLocation(element,node);
                if(location != -10){
                    node.key.add(location, element);// 插入到正确的位置
                    node.size++;
                    if(node.size == 2*degree-1 && node== root){
                        Node<E> newRoot = new Node<>(degree,false);
                        newRoot.children.add(0,root);
                        splitChild(newRoot,root,0);
                        root = newRoot;
                    }
                }
            }
            else {
                int location = getLocation(element,node);
                // check is there any duplication
                if(location != -10){
                    Node<E> current = node.children.get(location);//子节点[4,5,6,7,8]
                    if(current.size == 2*degree-1){
                        this.splitChild(node,current,location);
                        if (element.compareTo(node.key.get(location))>0) location+=1;
                    }
                    addIntoNoFull(element,current);
                    if(current.size==2*degree-1){
                        splitChild(node,current,location);
                    }
                }
            }
        }
    }

    public void splitChild(Node<E> parent,Node<E> child, int i) {

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
        parent.key.add(i,midvalue);
        parent.size+=1;
        child.key.remove(degree - 1);

        if (!child.isLeaf) {
            for (int j = 0; j < degree; j++) {
                newchild.children.add(child.children.get(degree));
                child.children.remove(degree);
            }
        }
        parent.children.add(i+1,newchild);
    }

    private int getLocation(E element, Node<E> node) {
        int location = node.size;
        while (location >=0 && element.compareTo(node.key.get(location-1))<0) {
            location--;
            if(location == 0){
                break;
            }
        }
        if(location != -1){
            return location;
        }else {
            return node.size;
        }
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
                splitChild(newRoot,root,0);
                addIntoNoFull(element,newRoot);
                root = newRoot;
            }else{// 树没有满
                addIntoNoFull(element,root);
            }
        }


    }

    public Node find(E element){
        return findRec(element, root);
    }

    private Node<E> findRec(E element, Node<E> node) {
        if (node == null) {
            return null; // 元素不存在于树中
        }

        int index = 0;
        while (index < node.size && element.compareTo(node.key.get(index)) > 0) {
            index++;
        }

        if (index < node.size && element.compareTo(node.key.get(index)) == 0) {
            return node; // 找到元素
        }

        if (node.isLeaf) {
            return null; // 元素不存在于树中
        }

        // 继续在适当的子节点中查找
        return findRec(element, node.children.get(index));
    }

    public boolean exist(E element) {
        return existRec(element, root);
    }

    private boolean existRec(E element, Node<E> node) {
        if (node == null) {
            return false;
        } else {
            int index = 0;
            // 查找元素或找到第一个大于它的元素的位置
            while (index < node.size && element.compareTo(node.key.get(index)) > 0) {
                index++;
            }

            // 检查元素是否在当前节点
            if (index < node.size && element.compareTo(node.key.get(index)) == 0) {
                return true;
            }

            // 如果是叶子节点，元素不存在
            if (node.isLeaf) {
                return false;
            }

            // 否则，在子节点中递归查找
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



}

