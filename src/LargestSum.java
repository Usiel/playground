/**
 * Created by Usiel on 22.02.2016.
 */
public class LargestSum {
    public static void main(String[] args) {
        int[] a = new int[]{1,-3,4,3,2,-1,-1,2,0,0,-10,1,1,1,1,1,1,1,1,1,1,1};
        int[] b = new int[]{-9,-7,-6,-5,-4,-3,-2, -5};
        Slice s = getLargestSumSlice(a);

        System.out.println("from " + s.start + " to " + s.end);
        s = getLargestSumSlice(b);
        System.out.println("from " + s.start + " to " + s.end);
    }

    private static Slice getLargestSumSlice(int[] a) {
        if (a.length == 0) {
            return null;
        }
        int maxStart = -1;
        int maxEnd = -1;
        Integer maxSum = null;

        int start = 0;
        int end = 0;
        int sum = 0;

        while (end < a.length) {
            sum += a[end];
            if (maxSum == null || sum > maxSum) {
                maxSum = sum;
                maxStart = start;
                maxEnd = end;
            }
            end++;
            if (sum < 0) {
                // try again
                start = end;
                sum = 0;
            }
        }

        return new Slice(maxStart, maxEnd);
    }

    private static class Slice {
        int start;
        int end;
        public Slice(int start, int end) {
            this.end = end;
            this.start = start;
        }
    }
}
