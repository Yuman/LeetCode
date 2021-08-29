package Companies.Amazon;

/**
 * 
 * see CoderByte
 * Take a number n as input, return the nth number in the Fibonacci sequence The
 * 0th number of the sequence is 0. The 1st number of the sequence is 1. To
 * generate the next number of the sequence, we sum the previous two.
 * 
 * @see ClimbingStairs
 */
public class FibonacciNumber {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        int num1 = 0, num2 = 1;
        for (int i = 2; i <= N; i++) {
            int num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
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

    

    public int fibTab(int N) {
        int[] fibAll = new int[N+2];
        fibAll[0] = 0;
        fibAll[1] = 1;
        for (int i = 0; i < N ; i++) {
            fibAll[i + 1] += fibAll[i];
            fibAll[i + 2] += fibAll[i];
        }
        return fibAll[N];
    }

    public static void main(String[] args){
        FibonacciNumber fibo = new FibonacciNumber();
        System.out.println(fibo.fib(11));
        System.out.println(fibo.fibDp(11));
        System.out.println(fibo.fibTab(11));
    }
}
