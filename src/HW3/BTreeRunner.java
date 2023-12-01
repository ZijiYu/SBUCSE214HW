package HW3;

import java.util.*;
import javax.naming.OperationNotSupportedException;


public class BTreeRunner {

    public static void main(String[] args) throws OperationNotSupportedException {
//        BTree<Integer> tree = new BTree<>(3); /* minimum degree is 3 */
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 22, 19, 25, 100, 88, 64, 65, 16);
//        Collections.sort(list);
//        System.out.println(list);
//        tree.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 22, 19, 25, 100, 88, 64, 65, 16, 16, 16, 16, 16)); //1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 22, 19, 25, 100, 88, 64, 65, 16
//        tree.show();
//        System.out.println(tree.find(11));

         /*
        * If your tree is properly constructed, the show() method should print
            [9]
            [3, 6] [19, 64]
            [1, 2] [4, 5] [7, 8] [11, 12, 16] [22, 25] [65, 88, 100]
        * */
        BTree<Integer> tree = new BTree<>(3); /* minimum degree is 3 */
        System.out.println("Minimum Degree: 3");
        tree.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 22, 19, 25, 100, 88, 64, 65, 16));
        tree.show();
        // This should print: <[65, 88, 100], 0>.
        System.out.println("-------------------Find 65: " + tree.find(65));
        System.out.println("-------------------Find 2: " + tree.find(2));
        System.out.println("-------------------Find 16: " + tree.find(16));
        System.out.println("-------------------Find 89: " + tree.find(89));
        System.out.println();

        System.out.println("Minimum Degree: 2");
        BTree<Integer> tree2 = new BTree<>(2);
        tree2.addAll(Arrays.asList(15, 10, 25, 5, 12, 20, 30, 37, 18, 22, 28, 35, 1, 8, 16, 19, 21, 27, 32));
        tree2.show();
        System.out.println("-------------------Find 35: " + tree2.find(35));
        System.out.println("-------------------Find 15: " + tree2.find(15));
        System.out.println("-------------------Find 16: " + tree2.find(16));
        System.out.println("-------------------Find 1000: " + tree2.find(1000));
        System.out.println();

        System.out.println("Minimum Degree: 4");
        BTree<Integer> tree3 = new BTree<>(4);
        tree3.addAll(Arrays.asList(45, 12, 78, 34, 56, 23, 89, 9, 67, 91, 19, 72, 5, 38, 81, 27, 62, 3, 50,
                15, 73, 42, 88, 14, 69, 10, 61, 29, 51, 76, 7));
        tree3.show();
        System.out.println("-------------------Find 15: " + tree3.find(15));
        System.out.println("-------------------Find 45: " + tree3.find(45));
        System.out.println("-------------------Find 56: " + tree3.find(56));
        System.out.println("-------------------Find -1: " + tree3.find(-1));
        System.out.println();

        System.out.println("Minimum Degree: 5");
        BTree<Integer> tree4 = new BTree<>(5);
        tree4.addAll(Arrays.asList(50, 30, 70, 20, 40, 60, 80, 15, 25, 35,
                45, 55, 65, 75, 85, 10, 5, 3, 7, 13,
                17, 23, 27, 33, 37, 43, 47, 53, 57, 63,
                67, 73, 77, 83, 87, 90, 95, 97, 100, 1,
                2, 4, 6, 8, 9, 11, 12, 14, 16, 18, 19));
        tree4.show();
        System.out.println("-------------------Find 27: " + tree4.find(27));
        System.out.println("-------------------Find 95: " + tree4.find(95));
        System.out.println("-------------------Find 100: " + tree4.find(100));
        System.out.println("-------------------Find 0: " + tree4.find(0));
        System.out.println();

        System.out.println("Minimum Degree: 2");
        BTree<Double> tree5 = new BTree<>(2);
        tree5.add(1.2);
        tree5.add(1.2); // ignore this case
        tree5.add(2.3);
        tree5.add(4.5);
        tree5.add(7.1);
        tree5.add(8.9);
        tree5.add(9.3);
        tree5.show();
        System.out.println("-------------------Find 4.5: " + tree5.find(4.5));
        System.out.println("-------------------Find 2.3: " + tree5.find(2.3));
        System.out.println("-------------------Find 9.3: " + tree5.find(9.3));
        System.out.println("-------------------Find 0.2: " + tree5.find(0.2));

        System.out.println();
        BTree<String> tree6 = new BTree<>(3);
        System.out.println("Minimum Degree: 3");
        tree6.addAll(Arrays.asList("R", "Y", "F", "X", "A", "M", "C", "D", "E", "T", "H", "V", "L", "W", "G"));
        tree6.show();
        System.out.println("-------------------Find M: " + tree6.find("M"));
        System.out.println("-------------------Find X: " + tree6.find("X"));
        System.out.println("-------------------Find R: " + tree6.find("R"));
        System.out.println("-------------------Find B: " + tree6.find("B"));
    }
}
