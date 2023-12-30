package HW4;
import java.util.ArrayList;

import java.util.List;

import java.util.Random;



public class SortingClient {



    private static final int MILLION          = (int) 1.0e6;

    private static final int HUNDRED_THOUSAND = (int) 1.0e5;

    private static final int TEN_THOUSAND     = 10000;



    private static void runtimeAnalysis() {

        SortingMethods<Integer> integerSortingMethods = new SortingMethods<>();



        Random generator = new Random(10283645586984L); // you can change this seed if you want, but it's not necessary

        System.out.printf("%s    %s    %s%n", "No. of elements", "Selection Sort Time (in ms)",

                "MergeSort Time (in ms)");

        for (int listSize = TEN_THOUSAND; listSize < HUNDRED_THOUSAND; listSize += TEN_THOUSAND) {

            List<Integer> integers = new ArrayList<>(generator.ints(listSize, 0, MILLION).boxed().toList());



            long begin1 = System.nanoTime();

            integerSortingMethods.selectionSort(integers);

            long   end1       = System.nanoTime();

            double timeTaken1 = (double) (end1 - begin1) / HUNDRED_THOUSAND;



            long begin2 = System.nanoTime();

            integerSortingMethods.mergeSort(integers);

            long   end2       = System.nanoTime();

            double timeTaken2 = (double) (end2 - begin2) / HUNDRED_THOUSAND;



            System.out.printf("%-15d    %-27.2f    %.2f%n", listSize, timeTaken1, timeTaken2);

        }

    }



    private static void aSampleTest() {

        Random                  generator             = new Random(1028984L);

        List<Integer>           integers              = new ArrayList<>(generator.ints(6, 0, 20).boxed().toList());

        SortingMethods<Integer> integerSortingMethods = new SortingMethods<>();

        integerSortingMethods.selectionSort(integers);

        System.out.println(integerSortingMethods.show());

    }



    public static void main(String[] args) {

        aSampleTest();

        runtimeAnalysis();

    }

}