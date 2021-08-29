package Companies.Google;

/**
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

        // return helper(x, N);
    }

    private double helper(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = helper(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double pow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            return 1 / x * pow(1 / x, -(n + 1));
        }
        return (n % 2 == 0) ? pow(x * x, n / 2) : x * pow(x * x, n / 2);
    }

    public static void main(String[] a) {
        Pow p = new Pow();
        double res = p.pow(2, -2147483647);
        System.out.println(res);
    }
}
