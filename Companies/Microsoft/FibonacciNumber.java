package Companies.Microsoft;

/**
 * @see ClimbingStairs
 */
public class FibonacciNumber {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        int num1 = 0, num2 = 1;
        for (int i = 2; i <= N; i++) {
            int temp = num1 + num2;
            num1 = num2;
            num2 = temp;
        }
        return num2;
    }

    int[] fib_cache = new int[31];
// dynamic programming with memoizaiton
    public int fibDp(int N) {
        if (N <= 1)
            return N;
        if (fib_cache[N] != 0)
            return fib_cache[N];
        else
            return fib_cache[N] = fib(N - 1) + fib(N - 2);
    }
}
