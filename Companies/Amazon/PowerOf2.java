package Companies.Amazon;

/*
Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.

 
*/
public class PowerOf2 {
    public boolean isPowerOfTwoR(int n) {
        return n > 0 && (n == 1 || (n % 2 == 0 && isPowerOfTwoR(n / 2)));
    }

    public boolean isPowerOfTwoI(int n) {
        long i = 1;
        while (i < n) {
            i *= 2;
        }
        return i == n;
    }

    public boolean isPowerOfTwoM(int n) {
        return 2 << 30 % n == 0;
    }

    public boolean isPowerOfTwoD(int n) {
        if (n <= 0)
            return false;
        while (n % 2 == 0)
            n /= 2;
        return n == 1;
    }

    public boolean isPowerOfTwoB(int n) {
        return n > 0 && ((n & (n-1)) == 0);
    }

    public static void main(String[] a) {
        PowerOf2 po2 = new PowerOf2();
        System.out.println("R: " + po2.isPowerOfTwoR(12));
        System.out.println("I: " + po2.isPowerOfTwoI(12));
        System.out.println("M: " + po2.isPowerOfTwoM(12));
        System.out.println("D: " + po2.isPowerOfTwoD(12));
        System.out.println("B: " + po2.isPowerOfTwoB(12));
    }
}
