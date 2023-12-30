
package HW4;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;



public class SortingMethods<E extends Comparable<E>> implements Demonstrable {



    private static final int UPPER_LIMIT_FOR_SHOW = 15;



    StringBuilder steps = new StringBuilder();



    /** DO NOT CHANGE THIS METHOD */

    public String show() {

        steps.setLength(steps.length() - 1);

        return steps.toString();

    }



    /**

     * This is a bottom-up implementation of the mergesort algorithm where multiple small sublists of the same size

     * are merged in a single pass. The demonstration of this algorithm is done by showing each stage of the merge on

     * sublists of the same size (except possibly the very last sublist, if the input list has odd number of elements).

     * For example, if the input is [5, 2, 4, 6, 7, 1, 3], a call to show() returns the string:

     * <p>

     * [5, 2, 4, 6, 7, 1, 3]

     * [2, 5, 4, 6, 1, 7, 3]

     * [2, 4, 5, 6, 1, 3, 7]

     * [1, 2, 3, 4, 5, 6, 7]

     * </p>

     * The first line shows the input, the second line demonstrates the pairwise merges creating sorted sublists of

     * size 2 (except the very last sublist, which is [3]), the third line demonstrates merges of size-2 lists together

     * to create [2, 4, 5, 6] and [1, 3, 7] (again, the very last sublist was not a pair because the input has an odd

     * number of elements), and the fourth line merges the size-4 sublists together, which is the sorted list.

     *

     * @param elements the input list to be sorted

     */
    //    public void mergeSort(List<E> elements) {
//        // TODO: implement this as part of the assignment
//        int n = elements.size();
//        List<E> aux = new ArrayList<>(n);
//        for (int i = 0; i < n; i++) {
//            aux.add(null);
//        }
//        steps.append(elements.toString()).append('\n');
//
//        for (int size = 1; size < n; size = size + size) {
//            for (int low = 0; low < n - size; low += size + size) {
//                merge(elements, aux, low, low + size - 1, Math.min(low + size + size - 1, n - 1));
//            }
//            steps.append(elements.toString()).append('\n');
//        }
//
//    }
//
//    private void merge(List<E> elements, List<E> aux,int low,int mid, int high){
//        for (int k = low; k <= high; k++) {
//            aux.set(k, elements.get(k));
//        }
//
//        int i = low, j = mid + 1;
//        for (int k = low; k <= high; k++) {
//            if (i > mid) {
//                elements.set(k, aux.get(j++));
//            } else if (j > high) {
//                elements.set(k, aux.get(i++));
//            } else if (aux.get(j).compareTo(aux.get(i)) < 0) {
//                elements.set(k, aux.get(j++));
//            } else {
//                elements.set(k, aux.get(i++));
//            }
//        }
//    }
    public void mergeSort(List<E> elements){
        int n = elements.size();
        steps.append(elements.toString()).append('\n');
        for (int size = 1; size < n; size += size) {
            for (int low = 0; low < n - size; low += size) {
                int mid = low + size - 1;
                int high = Math.min(low + size + size - 1, n - 1);
                inPlaceMerge(elements, low, mid, high);
            }

            if(elements.size()<UPPER_LIMIT_FOR_SHOW)
                steps.append(elements.toString()).append('\n');
        }
    }



    private void inPlaceMerge(List<E> elements, int low, int mid, int high) {
        int i = low, j = mid + 1;
        while (i <= mid && j <= high) {
            if (elements.get(i) .compareTo(elements.get(j))<=0) {
                i++;
            } else {
                E value = elements.get(j);
                int index = j;
                while (index != i) {
                    elements.set(index, elements.get(index - 1));
                    index--;
                }
                elements.set(i, value);
                i++;
                mid++;
                j++;
            }
        }
    }

    /**

     * This is an implementation of the insertion sort algorithm. The steps are demonstrated by adding a line to the

     * string representation whenever the unsorted portion of the input list decreases in size by 1. For example, if

     * the input is [8, 3, 15, 0, 9], then show() returns the string:

     * <p>

     * [4, 19, 14, 15, 1, 6]

     * [3, 8, 15, 0, 9]

     * [3, 8, 15, 0, 9]

     * [0, 3, 8, 15, 9]

     * [0, 3, 8, 9, 15]

     * </p>

     *

     * @param elements the input list to be sorted

     */

    public void insertionSort(List<E> elements) {

        steps.append(elements.toString()).append('\n');

        // TODO: implement this as part of the assignment
        // 19,4, 14, 15, 1, 6
        for (int i = 1; i < elements.size(); i++) {
            E currentElement = elements.remove(i);
            int j = i - 1;

            while (j >= 0 && elements.get(j).compareTo(currentElement) > 0) {
                j--;
            }
            elements.add(j+1,currentElement);
            if(elements.size()<UPPER_LIMIT_FOR_SHOW)
                steps.append(elements.toString()).append('\n');
        }

    }



    public void selectionSort(List<E> elements) {

        steps.setLength(0);

        if (elements.size() < UPPER_LIMIT_FOR_SHOW) {

            steps.append(elements.toString()).append('\n');

        }

        int boundary = 0;

        while (boundary < elements.size() - 1) {

            int minIndex = findMinIndex(elements, boundary);

            swap(elements, boundary++, minIndex);

            if (elements.size() < UPPER_LIMIT_FOR_SHOW)

                steps.append(elements.toString()).append('\n');

        }

    }



    private int findMinIndex(List<E> elements, int boundary) {

        int minIndex = boundary;

        if (boundary == elements.size() - 1)

            return minIndex;

        E min = elements.get(minIndex);

        for (int i = boundary + 1; i < elements.size(); i++) {

            E e = elements.get(i);

            if (e.compareTo(min) < 0) {min = e; minIndex = i;}

        }

        return minIndex;

    }



    private void swap(List<E> elements, int i, int j) {

        if (i < 0 || j < 0 || i >= elements.size() || j >= elements.size()) {

            String err = String.format("Cannot swap elements between positions %d and %d in list of %d elements.",

                    i, j, elements.size());

            throw new IndexOutOfBoundsException(err);

        }

        E tmp = elements.get(i);

        elements.set(i, elements.get(j));

        elements.set(j, tmp);

    }


}

//
//        public void mergeSort(List<E> elements) {
//
//        // TODO: implement this as part of the assignment
//        List<List<E>> list = divide(elements);//  divide
//        steps.append(list.toString()).append('\n');
//
//        for (List<E> temp:list) {
//            if(temp.get(0).compareTo(temp.get(1))>0){
//                swap(temp,0,1);
//            }
//        }
//        steps.append(list.toString()).append('\n');
//        //[4, 19, 15, 14, 6, 1]
//        while(list.size()>=2){
//            List<E> temp = new ArrayList<>();
//            temp = merge(list.get(0),list.get(1));
//            list.remove(0);
//            if(list.size()!= 0){
//                list.remove(0);
//            }
//            list.add(0,temp);
////            steps.append(list.toString()).append('\n');
//        }
//
//    }
//
//        private List<List<E>> divide(List<E> element){
//        List<List<E>> list = new ArrayList<>();
//        while(element.size()>2) {
//            List<E> temp = new ArrayList<>();
//            temp.add(0,element.get(0));
//            temp.add(1,element.get(1));
//            element.remove(0);
//            element.remove(0);
//            list.add(temp);
//        }
//        if(element.size()<=2){
//            list.add(element);
//        }
//        return list;
//    }
//
//        private List<E> merge(List<E> list1, List<E> list2){
//        List<E> merged = new ArrayList<>();
//        int cur1 = 0, cur2 = 0;
//        while(cur1 < list1.size() && cur2 < list2.size()){
//            if(list1.get(cur1).compareTo(list2.get(cur2)) < 0){
//                merged.add(list1.get(cur1++));
//            } else if(list1.get(cur1).compareTo(list2.get(cur2)) == 0){
//                merged.add(list1.get(cur1++));
//                merged.add(list2.get(cur2++));
//            } else {
//                merged.add(list2.get(cur2++));
//            }
//        }
//
//        // 添加剩余元素
//        while(cur1 < list1.size()){
//            merged.add(list1.get(cur1++));
//        }
//        while(cur2 < list2.size()){
//            merged.add(list2.get(cur2++));
//        }
//
//        return merged;
//    }