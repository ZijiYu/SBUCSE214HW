package HW1;

import HW1.DoublyLinkedList_Interface.*;

import java.util.Iterator;
import java.util.LinkedList;

public class HotPotato {
    public static DoublyLinkedList<Integer> playWithDoublyLinkedList(int numberOfPlayers, int lengthOfPass) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> ans = new DoublyLinkedList<>();

        int index = 0;
        for (int i = 1; i <= numberOfPlayers; i++) {
            list.add(index,i);
            index++;
        }
        TwoWayListIterator<Integer> iterator = list.iterator();
        int currentPossition = 0;
        index = 0;
        while (numberOfPlayers!= 0 ) {
            for (int i = 0; i < lengthOfPass+1; i++) {
                // Restart
                if (!iterator.hasNext()) {
                    iterator = list.iterator();
                }
                iterator.next();
            }
            currentPossition = (currentPossition+lengthOfPass)%numberOfPlayers;
            ans.add(index,list.get(currentPossition));
            index++;
            iterator.remove();
            numberOfPlayers--;

        }

        return ans;
    }
// This the prototype of the method above.
//        index = 0;
//        int currentPossition = 0;
//        int nums =  list.size();
//        while(numberOfPlayers!=0){
//            currentPossition = (currentPossition+lengthOfPass)%numberOfPlayers;
//            int nodeElement = list.get(currentPossition);
//            ans.add(index, nodeElement);
//            index++;
//            list.remove(currentPossition);
//            numberOfPlayers--;
//
//        }
//        return ans; // TODO
//    }
    public static LinkedList<Integer> playWithLinkedList(int numberOfPlayers, int lengthOfPass) {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        int index = 0;
        for (int i = 1; i < numberOfPlayers+1; i++) {
            list.add(index,i);
            index++;
        }
        Iterator<Integer> iterator = list.iterator();
        int currentPossition = 0;
        while(numberOfPlayers != 0){
            for (int i = 0; i < lengthOfPass+1; i++) {
                if (!iterator.hasNext()) {
                    iterator = list.iterator();  // Start with the beginning
                }
                iterator.next();
            }
            currentPossition = (currentPossition+lengthOfPass)%numberOfPlayers;
            ans.add(list.get(currentPossition));
            iterator.remove();
            numberOfPlayers--;
        }
        return ans; // TODO
    }
    public static void main(String... args) {
        // in both methods, the list is the order in which the players are eliminated
        // the last player (i.e., the last element in the returned list) is the winner
        System.out.println(playWithDoublyLinkedList(5, 1).toString()); // expected output: [1, 2, 3, 4, 5]
        System.out.println(playWithLinkedList(5, 1)); // expected output: [2, 4, 1, 5, 3]
    } }
