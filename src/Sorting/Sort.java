package Sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Sort {
    public static void main(String[] args) {
        int len = 3000;
        int[] a = new int[len];

        fill(a);

        List<SortStrategy> strategies = new LinkedList<>();
        strategies.add(new Quicksort());
        strategies.add(new Heapsort());
        strategies.add(new InsertionSort());
        strategies.add(new BubbleSort());
        strategies.add(new MergeSort());
        strategies.add(new IntroSort());
        strategies.add(new MyNaiveCountingSort());

        //print(a);
        for (SortStrategy s : strategies) {
            System.out.println();
            System.out.println(s.getClass());
            int[] toSort = a.clone();
            //print(s.sort(toSort));
            long start = System.nanoTime();
            s.sort(toSort);
            long end = System.nanoTime();

            if (isSorted(toSort)) {
                System.out.println("Sort OK");
            } else {
                System.out.println("Sort INVALID");
            }
            System.out.println("Sorted in " + ((end-start) / 1000000) + " ms");
        }
    }

    private static boolean isSorted(int[] a) {
        for (int i=0; i<a.length-1; i++) {
            if (a[i] > a[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static void fill(int[] a) {
        for (int i=0; i<a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(-a.length/2, a.length/2);
        }
    }

    public static void print(int[] a) {
        System.out.print("[ ");
        for (int i=0; i<a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print("]");
        System.out.println("");
    }
}
