package sorting;

/**
 * Created by Usiel on 21.02.2016.
 */
public class Quicksort implements SortStrategy {
    @Override
    public int[] sort(int[] a) {
        sort(a, 0, a.length);
        return a;
    }

    public void sort(int[] a, int lo, int hi) {
        if (lo+1 < hi) {
            int pivotIndex = partition(a, lo, hi);
            sort(a, lo, pivotIndex);
            sort(a, pivotIndex+1, hi);
        }
    }

    public int partition(int[] a, int lo, int hi) {
        int pivot = a[hi-1];
        int j = lo;
        for (int i=lo; i<hi-1; i++) {
            if (a[i] <= pivot) {
                swap(a, i, j);
                j++;
            }
        }
        swap(a, j, hi-1);

        return j;
    }

    public void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }


}

