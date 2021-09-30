package Coderbyte;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;

public class GoldbachImpl implements GoldbachService {

    public static void main(String[] args) {
        GoldbachService gb = new GoldbachImpl();
        List<PrimePair> ls = gb.findPrimePairs(20);
        System.out.println(new GoldbachImpl().isPrime(2047));
    }

    @Override
    public List<PrimePair> findPrimePairs(int even) throws IllegalArgumentException {
        if (even % 2 != 0)
            throw new IllegalArgumentException("The input must be even.");
        Set<Integer> primes = sieveOfEratosthenes(even - 1);
        List<PrimePair> pairs = new ArrayList<>();
        for (int less : primes) {
            if (less > even / 2)
                break;
            if (primes.contains(even - less)) {
                pairs.add(new PairPrimes(less, even - less));
            }
        }
        System.out.println("prime count:" + pairs.size() + " below " + even);
        return pairs;
    }

    Set<Integer> sieveOfEratosthenes(int n) {
        boolean primeIdx[] = new boolean[n + 1];
        Arrays.fill(primeIdx, true);
        for (int stride = 2; stride * stride <= n; stride++) {
            if (primeIdx[stride]) {
                for (int jump = stride * stride; jump <= n; jump += stride) {
                    primeIdx[jump] = false;
                }
            }
        }
        Set<Integer> primeNumbers = new TreeSet<>(); // must use sorted set for ascending search
        for (int i = 2; i <= n; i++) {
            if (primeIdx[i]) {
                primeNumbers.add(i);
            }
        }
        // System.out.println(primeNumbers);
        return primeNumbers;
    }

    boolean isPrime(int num) {
        for (int f = 2; f * f <= num; f++) {
            if (num % f == 0) {
                return false;
            }
        }
        return true;
    }

}

class PairPrimes implements PrimePair {
    private int less, great;

    PairPrimes(int less, int great) {
        this.less = less;
        this.great = great;
    }

    @Override
    public int getLesser() {
        return less;
    }

    @Override
    public int getGreater() {
        return great;
    }

    @Override
    public boolean has(int either) {
        return either == less || either == great;
    }

    @Override
    public String toString() {
        return "(" + less + ", " + great + ")";
    }

}
