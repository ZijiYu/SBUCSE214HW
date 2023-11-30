package HW3;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Queue;

class Node<E extends Comparable<E>> {
    // 不要用有效键数num，会增加无效内存
    int degree;
    LinkedList<E> key;
    LinkedList<Node<E>> children;
    boolean isLeaf;
    // constructer for node
    Node(int degree, boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.key = new LinkedList<>();
        if(!isLeaf){
            this.children = new LinkedList<>();
        }
    }

    public void addIntoNoFull(E element) {
        if (isLeaf) {
            int index = getLocation(element);
            key.add(index, element); // 插入到正确的位置
        } else {
            int index = getLocation(element);
            // 检查子节点是否已满
            if (children.get(index).key.size() == 2 * degree - 1) {
                splitChild(children.get(index), index);
                // 检查分裂后的位置
                if (key.get(index).compareTo(element) < 0) {
                    index++;
                }
            }
            children.get(index).addIntoNoFull(element);
        }
    }
    public void splitChild(Node<E> child, int i) {
        Node<E> newchild = new Node<>(degree, child.isLeaf);

        // 移动键到新节点
        for (int j = 0; j < degree - 1; j++) {
            newchild.key.add(child.key.get(degree));
            child.key.remove(degree); // 总是移除同一个位置的元素
        }

        // 如果不是叶节点，移动子节点到新节点
        if (!child.isLeaf) {
            for (int j = 0; j < degree; j++) {
                newchild.children.add(child.children.get(degree));
                child.children.remove(degree); // 总是移除同一个位置的元素
            }
        }

        // 将新节点添加到父节点的子节点列表中
        children.add(i + 1, newchild);

        // 将child的中间键移动到父节点
        key.add(i, child.key.get(degree - 1));
        child.key.remove(degree - 1); // 移除已经上移的键
    }

    private int getLocation(E element) {
        int location = 0;
        while (location < key.size() && key.get(location).compareTo(element) < 0) {
            location++;
        }
        return location;
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
        if(root == null) {
            root = new Node<E>(degree, true);
            root.key.add(element);
        } else {
            if(root.key.size()==max){
                Node<E> newRoot = new Node<E>(degree,false);
                // 旧的根节点成为新根节点的一个子节点
                newRoot.children.add(root);
                newRoot.splitChild(root,0);
                int i = 0;
                // 新根节点有两个子节点。决定哪个子节点将有新键
                if(newRoot.key.get(i).compareTo(element)<0) i++;
                newRoot.children.get(i).addIntoNoFull(element);
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
        queue1.add(root); /* root of the tree being added */
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
