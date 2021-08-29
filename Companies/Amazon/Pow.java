package Companies.Amazon;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * Example 1:
 * 
 * Input: x = 2.00000, n = 10 Output: 1024.00000
 * 
 * @see Sqrt
 */
public class Pow {
    public double myPow(double x, int n) {
        long N = n;
        if (n < 0) {
            N = -N;
            x = 1 / x;
        }
        double re = 1, prod = x;
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 == 1) {
                re *= prod;
            }
            prod *= prod;
        }
        return re;

    }

    private double powRecursive(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        if (n < 0) {
            n = -n;
        }
        double half = powRecursive(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;  // this handles odd n including 1, when half=1
        }
    }

    public static void main(String[] args){
        Pow pow = new Pow();
        System.out.println(pow.myPow(1.1, 3));
        System.out.println(pow.powRecursive(1.1, 3));
    }
}
