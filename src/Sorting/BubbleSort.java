package sorting;

/**
 * Created by Usiel on 21.02.2016.
 */
public class BubbleSort implements SortStrategy {
    @Override
    public int[] sort(int[] a) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i=0; i<a.length-1; i++) {
                if (a[i] > a[i+1]) {
                    swap(a, i, i+1);
                    swapped = true;
                }
            }
        }

        return a;
    }

    private void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
}
