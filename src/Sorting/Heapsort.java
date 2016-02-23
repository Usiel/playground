package Sorting;

/**
 * Created by Usiel on 21.02.2016.
 */
public class Heapsort implements SortStrategy {
    @Override
    public int[] sort(int[] a) {
        heapify(a);

        int heapEnd = a.length;

        for (int i=1; i<a.length; i++) {
            swap(a, 0, heapEnd-i);
            siftDown(a, 0, heapEnd-i);

        }
        return a;
    }

    public void heapify(int[] a) {
        for (int i=a.length/2; i>=0; i--) {
            siftDown(a, i, a.length);
        }
    }

    public void siftDown(int[] a, int i, int heapEnd) {
        int l = i*2;
        int r = l+1;

        int maxIndex = i;

        if (l < heapEnd && a[l] > a[r] && a[l] > a[i]) {
            maxIndex = l;
        } else if (r < heapEnd && a[r] > a[l] && a[r] > a[i]) {
            maxIndex = r;
        }

        if (i != maxIndex) {
            swap(a, maxIndex, i);
            siftDown(a, maxIndex, heapEnd);
        }
    }

    public void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
}
