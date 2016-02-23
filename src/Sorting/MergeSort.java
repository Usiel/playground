package Sorting;

/**
 * Created by Usiel on 21.02.2016.
 */
public class MergeSort implements SortStrategy {
    @Override
    public int[] sort(int[] a) {
        mergeSort(a, 0, a.length);
        return a;
    }

    private void mergeSort(int[] a, int lo, int hi) {
        if (hi-lo > 1) {
            int b = lo + (hi-lo)/2;
            mergeSort(a, lo, b);
            mergeSort(a, b, hi);
            merge(a, lo, b, hi);
        }
    }

    private void merge(int[] a, int lo, int b, int hi) {
        int left = lo;
        int right = b;
        int[] swap = new int[hi-lo];
        for (int i=0; i<swap.length; i++) {
            if (right >= hi || (a[left] < a[right] && left < b)) {
                swap[i] = a[left++];
            } else {
                swap[i] = a[right++];
            }
        }
        for (int i=0; i<swap.length; i++) {
            a[lo+i] = swap[i];
        }
    }
}
