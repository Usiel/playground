/**
 * Created by Usiel on 22.02.2016.
 */
public class Palindrome {
    String value;

    public Palindrome(String value) {
        this.value = value;
    }

    public boolean isPalindrome() {
        if (value == null) {
            return false;
        }

        if (value.length() == 0) {
            return true;
        }

        int a = 0;
        int z = value.length()-1;
        while (a < value.length()/2) {
            if (value.charAt(a++) != value.charAt(z--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Palindrome ok = new Palindrome("a");
        Palindrome ok2 = new Palindrome("abcdcba");
        Palindrome no = new Palindrome("abcb");
        Palindrome no2 = new Palindrome("acab");

        System.out.println(ok.isPalindrome());
        System.out.println(ok2.isPalindrome());
        System.out.println(no.isPalindrome());
        System.out.println(no2.isPalindrome());
    }
}
