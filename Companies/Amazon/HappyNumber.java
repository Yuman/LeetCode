package Companies.Amazon;

import java.util.*;

public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do { // Floyd cyce detection
            slow = next(slow);
            fast = next(next(fast));
            if (fast == 1) {
                return true;
            }
        } while (slow != fast);
        return false;
    }

    private int next(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    boolean isHappySet(int n) {
        Set<Integer> seen = new HashSet<Integer>();
        int squareSum, remain;
        while (seen.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1)
                return true;
            else
                n = squareSum;
        }
        return false;
    }

    /*
     * The idea is to use one hash set to record sum of every digit square of every
     * number occurred. Once the current sum cannot be added to set, return false;
     * once the current sum equals 1, return true;
     */
    public static void main(String[] a) {
        HappyNumber hn = new HappyNumber();
        System.out.println("Floyd: " + hn.isHappy(19));
        System.out.println("Set: " + hn.isHappySet(19));
    }

}
