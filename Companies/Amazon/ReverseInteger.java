package Companies.Amazon;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If
 * reversing x causes the value to go outside the signed 32-bit integer range
 * [-231, 231 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed or
 * unsigned).
 */
public class ReverseInteger {
    public int reverse(int x) {
        int re = 0, digit = 0, next = 0;
        while (x != 0) {
            digit = x % 10;
            next = re * 10 + digit;
            if ((next - digit) / 10 != re) { // check overflow
                return 0;
            }
            re = next;
            x /= 10;
        }
        return re;
    }

    public int flip(int x) { // reverses digits of an int, 123 -> 321
        int res = 0;
        for (int digit = 0; x > 0;) {
            digit = x % 10; // pick the ones digit from old
            res = res * 10 + digit; // append the digit to the new
            x /= 10; // remove the digit from old
        }
        return res;
    }

    public static void main(String[] a) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println("reverse(): " + ri.reverse(987654));
        System.out.println("flip(): " + ri.flip(987654));
    }
}
