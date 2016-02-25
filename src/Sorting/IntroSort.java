package sorting;

/**
 * Created by Usiel on 21.02.2016.
 */
public class IntroSort implements SortStrategy {
    SortStrategy q = new Quicksort();
    SortStrategy i = new InsertionSort();

    @Override
    public int[] sort(int[] a) {

        return a;
    }
}
