package HW1;

public class main {
    // This is the test environment for DoublyLinkedList.java
    public static void main(String[] args) {
//      int test
        DoublyLinkedList<Integer> nums = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> nums1 = new DoublyLinkedList<>();
//        int size = 5;
//        int index = 0;
//        for (int i = 1; i <= size; i++) {
//            nums.add(index,i);
//            index++;
//        }
//        int nums2 = nums.get(2);
//        System.out.println(nums2);
//        System.out.println(nums.toString());


        DoublyLinkedList<String> list = new DoublyLinkedList<>();
//
//         1. Test add();

        list.add("a");
        list.add("b");
        System.out.println("List Abstract Type Interface Test :");
        System.out.println("1.------");
        System.out.println("List elements: ");
        System.out.println(list.toString());
        System.out.println();
////         2. Test get();
        System.out.println("2.------");
        System.out.println("Let's get the second Node's name: [ " + list.get(2) + "]");
////         3. Test set
        System.out.println("3.------");
        System.out.println("Input index 3 set with: [" + list.set(2, "Bob") + "]");
        System.out.println(list.toString());
////         4. Test remove()
//        System.out.println("4.------");
////        list.remove(2);
//        System.out.println("List elements: ");
//        System.out.println(list.toString());
//        System.out.println("\n");
//        System.out.println("Collection Type Interface Test: ");
//        System.out.println("1.------");
////         1. Test size();
//        System.out.println("The size of the list is:+ [" + list.size() + "]");
//        System.out.println("2.------");
////         2. Test add();
//        list.add("Dick");
//        System.out.println(list.toString());
//        System.out.println();
////         3. Test remove
//        System.out.println("3.------");
//        list.remove("Bob");
//        System.out.println(list.toString());


    }


}
