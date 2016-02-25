package sorting;

/**
 * Created by Usiel on 25.02.2016.
 */
public class MyNaiveCountingSort implements SortStrategy {

    @Override
    public int[] sort(int[] a) {
        // both should be part of the input
        int max = getMax(a);
        int min = getMin(a);

        int[] counts = new int[max+1-min];

        for (int i=0; i<a.length; i++) {
            counts[a[i]-min]++;
        }

        int insertAt = 0;
        for (int i=0; i<counts.length; i++) {
            for (int k=0; k<counts[i]; k++) {
                a[insertAt++] = i+min;
            }
        }

        return a;
    }

    private int getMin(int[] a) {
        if (a.length == 0) {
            return -1;
        }
        int min = a[0];
        for (int i=1; i<a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    public int getMax(int[] a) {
        if (a.length == 0) {
            return -1;
        }
        int max = a[0];
        for (int i=1; i<a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
}
