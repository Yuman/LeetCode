package Companies.Amazon;
import java.util.*;
public class CountPrimes {
    public int countPrimes(int n) {  // wrong answer
        boolean[] dp = new boolean[n];
        if (n < 2) {
            return 0;
        }
        int count = 2; // 0 and 1
        for (int i = 2; i < Math.sqrt(n); i++) {// sieve of era
            if (!dp[i]) {
                for (int j = i * i; j < n; j += i) {  // j is multiples of i
                    if (dp[j]) {
                        continue;
                    }
                    count++;
                    dp[j] = true;
                }
            }
        }
        return n - count;
    }

    public int countf(int n) {  // prime factors
        Set<Integer> primes = new HashSet<>();
        for (int i = 2; n >= i;) {
            if (n % i == 0) {
                primes.add(i);
                n /= i;
            } else
                i++;
        }
        return primes.size();
    }

    public static void main(String[] args) {
        CountPrimes cp = new CountPrimes();
        System.out.println("count: " + cp.countPrimes(30));
        System.out.println("countf: " + cp.countf(30));
    }
}
