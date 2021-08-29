package Companies.Amazon;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        int num = 0; // reverseNumber
        while (x > num) {
            num = num * 10 + x % 10;
            x /= 10;
        }
        return x == num || x == num / 10;
    }

    public boolean isPalindromeII(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        char[] c = s.toCharArray();
        for (int l = 0, r = c.length - 1; l < r; l++, r--) {
            if (c[l] != c[r]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        int rev = 0;
        for (int inp = x; inp > 0; rev = rev * 10 + inp % 10, inp /= 10) {
          //  rev = rev * 10 + inp % 10;
        }
        return rev == x;
    }

    public static void main(String[] a) {
        PalindromeNumber pn = new PalindromeNumber();
        System.out.println(pn.isPalindrome2(123454321));
    }

}
