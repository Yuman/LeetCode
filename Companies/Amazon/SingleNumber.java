package Companies.Amazon;
/*
Given a non-empty array of integers nums, every element appears twice except for one. 
Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.
*/

//    a = 0011 1100
//    b = 0000 1101
//  -----------------
//  a&b = 0000 1100 bitwise and
//  a|b = 0011 1101 bitwise or
//  a^b = 0011 0001 bitwise XOR
//  ~a  = 1100 0011 bitwise compliment

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int re = 0;
        for (int i : nums) {
            re ^= i;
        }
        return re;
    }

    /*
     * Given an integer array nums where every element appears three times except
     * for one, which appears exactly once. Find the single element and return it.
     * 
     * You must implement a solution with a linear runtime complexity and use only
     * constant extra space.
     */
    public int singleNumber_2(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int sum = 0, dsum = 0;
        for (int num : nums) {
            if (!s.contains(num)) {
                s.add(num);
                sum += num;
            }
            dsum += num;
        }
        return sum * 2 - dsum;
    }

    /* Every element appears three times except for one */
    public int singleNumberII(int[] nums) {
        int one = 0, two = 0;
        for (int i : nums) {
            one = ~two & (one ^ i);
            two = ~one & (two ^ i);
        }
        return one;
    }

    /**
     * think about the number in 32 bits and just count how many 1s are there in
     * each bit, and sum %= 3 will clear it once it reaches 3. After running for all
     * the numbers for each bit, if we have a 1, then that 1 belongs to the single
     * number, we can simply move it back to its spot by doing ans |= sum << i;
     * 
     * This has complexity of O(32n), which is essentially O(n) and very easy to
     * think and implement. Plus, you get a general solution for any times of
     * occurrence. Say all the numbers have 5 times, just do sum %= 5.
     */
    public int singleNumberII2(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            int mask = 1 << i;
            for (int n : nums)
                if ((n & mask) == 1)
                    sum++;
            if (sum % 3 == 1)
                res |= mask;
        }
        return res;
    }

    /*
     * exactly two elements appear only once and all the other elements appear
     * exactly twice
     */
    public int[] singleNumberIII(int[] nums) {
        int i = 0;
        for (int num : nums) {
            i ^= num;
        }
        int diff = i & (-i);
        int x = 0;
        for (int num : nums) {
            if ((num & diff) != 0) {
                x ^= num;
            }
        }
        return new int[] { x, i ^ x };
    }

    public int singleNumberSum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int n : nums) {
            if (set.contains(n)) {
                sum -= n;

            } else {
                set.add(n);
                sum += n;
            }
        }
        return sum;
    }

    public static void main(String[] a) {
        SingleNumber sn = new SingleNumber();
        int[] nums = { 1, 1, 2, 2, 3, 4, 4 };
        System.out.println(sn.singleNumberSum(nums));
    }
}
