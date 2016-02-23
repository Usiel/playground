import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Usiel on 22.02.2016.
 */
public class Merge {
    public static void main(String[] args) {
        int[] a = new int[]{1, 4, 9, 12};
        int[] b = new int[]{1};
        int[] c = new int[]{1, 1, 2, 15, 16, 17, 18, 19};

        List<int[]> s = new ArrayList<>(3);
        s.add(a);
        s.add(b);
        s.add(c);

        print(merge(s));
    }

    public static void print(int[] a) {
        for (int i=0; i<a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static int[] merge(List<int[]> sorted) {
        int[] m = new int[sorted.stream().mapToInt(s -> s.length).sum()];
        int[] indices = new int[sorted.size()];

        for (int i=0; i<m.length; i++) {
            int l = getMinArrayIndex(sorted, indices);
            m[i] = sorted.get(l)[indices[l]++];
        }

        return m;
    }

    public static int getMinArrayIndex(List<int[]> sorted, int[] indices) {
        Integer min = null;
        int minIndex = 0;

        for (int i=0; i<sorted.size(); i++) {
            int[] a = sorted.get(i);
            int currentIndex = indices[i];
            if (currentIndex < a.length && (min == null || a[currentIndex] < min)) {
                min = a[currentIndex];
                minIndex = i;
            }
        }

        return minIndex;
    }
}
