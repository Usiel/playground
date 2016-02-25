import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Usiel on 23.02.2016.
 */
public class StringMisc {
    public static void main(String[] args) {
        String a = "abcde";
        String b = "edaba";
        System.out.println(isAnagram(a, b));
    }

    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        Arrays.sort(aChars);
        Arrays.sort(bChars);

        for (int i=0; i<a.length(); i++) {
            if (aChars[i] != bChars[i]) {
                return false;
            }
        }

        return true;
    }
}
