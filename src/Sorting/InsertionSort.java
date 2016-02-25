package sorting;

public class InsertionSort implements  SortStrategy {

    @Override
    public int[] sort(int[] a) {
        for (int i=1; i<a.length; i++) {
            insert(a, i);
        }

        return a;
    }

    private void insert(int[] a, int i) {
        // list is sorted until i (exclusive)
        while (i > 0 && a[i] < a[i-1]) {
            swap(a, i, i-1);
            i--;
        }
    }

    private void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
}
