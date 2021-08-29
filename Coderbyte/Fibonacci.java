package Coderbyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * The first number in the Fibonacci sequence is 0. The second is 1. --
 * https://en.wikipedia.org/wiki/Fibonacci_number
 * 
 * We are curious in further features of the sequence.
 * 
 * Create a class to implement the methods to address the curiosity.
 */
interface Fibo {

    /**
     * Calculate how many Fibonacci numbers are in the range [0, Integer.MAX_VALUE]
     * --https://www.educative.io/edpresso/what-is-integermaxvalue
     */
    int fibCount();

    /**
     * What is the largest Fibonacci number in the range?
     */
    int maxFib();

    /**
     * Among the Fibonacci number in the range, find the numbers that are perfect
     * squares.
     */
    int[] squareFibs();

    /**
     * Among the Fibonacci number in the range, find the prime numbers
     * https://en.wikipedia.org/wiki/Fibonacci_prime
     * https://www.geeksforgeeks.org/prime-numbers/
     */
    int[] primeFibs();

    /**
     * For each Fibanacci number in the sequence, calculate its digit sum (13--> 4,
     * 55 --> 10), and generate the sequence of those sums
     * http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibmaths.html#section1.2
     */
    int[] digitSum();
}

public class Fibonacci implements Fibo {
    private List<Integer> seq = new ArrayList<>();

    public Fibonacci() {
        seq.add(0);
        seq.add(1);
        for (int f0 = 0, f1 = 1, f = 1; f < Integer.MAX_VALUE && f0 + f1 > 0;) {
            f = f0 + f1;
            seq.add(f);
            f0 = f1;
            f1 = f;
        }
        //System.out.println(seq);
    }

    public int fibCount() {
        return seq.size();
    }

    public int maxFib() {
        return seq.get(seq.size() - 1);
    }

    public int[] squareFibs() {
        List<Integer> squares = new ArrayList<>();
        for (int i : seq) {
            double rt = Math.sqrt(i);
            if (Math.floor(rt) == Math.ceil(rt)) {
                squares.add(i);
                System.out.println(i);
            }
        }
        return squares.stream().mapToInt(i -> i).toArray();
    }

    public int[] primeFibs() {
        return null;
    }

    public int[] digitSum() {
        return new int[] { 0 };
    }

    public static void main(String[] args) {
        Fibo f = new Fibonacci();
        System.out.println(f.fibCount());
        System.out.println(f.maxFib());
        System.out.println(Arrays.asList(f.squareFibs()));
    }
}
