package Coderbyte;

import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Goldbach%27s_conjecture It states that every
 * even whole number greater than 2 is the sum of two prime numbers.
 * 
 * Some background on primality -- https://en.wikipedia.org/wiki/Prime_number#Computational_methods
 * 
 * https://www.youtube.com/watch?v=klcIklsWzrY
 * 
 * https://www.baeldung.com/java-generate-prime-numbers  -- find primes
 * 
 */
public interface GoldbachService {
    // Given an even number, find pairs of prime numbers that 
    // that add up to the even number.
    // test cases: 12345678, 20000000, 3000, 400, 50, 6
    // Your code will be called as:
    // GoldbachService gb = new YourClassName();
    // List<PrimePair> prms= gb.findPrimePairs(400);
    // System.out.println(prms);
    // System.out.println(prms.get(0).getLesser());

    /**
     * 
     * @param even, an even number >0
     * @return All pairs of prime numbers that sum to the even input
     * @throws IllegalArgumentException Rejects the input if it is not even.
     */
    List<PrimePair> findPrimePairs(int even) throws IllegalArgumentException;
}


