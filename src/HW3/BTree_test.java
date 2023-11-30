package HW3;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import javax.naming.OperationNotSupportedException;


public class BTree_test {

    private Node root;

    public static void main(String[] args) throws OperationNotSupportedException {
        BTree<Integer> tree = new BTree<>(3); /* minimum degree is 3 */
        tree.addAll(Arrays.asList(1, 2, 3, 4, 5,6, 7, 8, 9, 11, 12, 22, 19, 25, 100, 88, 64, 65, 16));
        tree.show();
    }
}
