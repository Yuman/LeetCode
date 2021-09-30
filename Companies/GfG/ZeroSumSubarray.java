package Companies.GfG;

import java.util.*;

/**
 * Given an array of positive and negative numbers, find if there is a subarray
 * (of size at-least one) with 0 sum.
 * 
 * The idea is to iterate through the array and for every element arr[i],
 * calculate the sum of elements from 0 to i (this can simply be done as sum +=
 * arr[i]). If the current sum has been seen before, then there is a zero-sum
 * array. Hashing is used to store the sum values so that we can quickly store
 * sum and find out whether the current sum is seen before or not.
 * 
 * @see SubarraySumEqualsK
 */
public class ZeroSumSubarray {
    // Returns true if arr[]
    // has a subarray with sero sum
    static Boolean subArrayExists(int arr[]) {
        // Creates an empty hashset hs
        Set<Integer> hs = new HashSet<Integer>();

        // Initialize sum of elements
        int sum = 0;

        // Traverse through the given array
        for (int i = 0; i < arr.length; i++) {
            // Add current element to sum
            sum += arr[i];

            // Return true in following cases
            // a) Current element is 0
            // b) sum of elements from 0 to i is 0
            // c) sum is already present in hash map
            if (arr[i] == 0 || sum == 0 || hs.contains(sum))
                return true;

            // Add sum to hash set
            hs.add(sum);
        }

        // We reach here only when there is
        // no subarray with 0 sum
        return false;
    }

    // Driver code
    public static void main(String arg[]) {
        int arr[] = { -3, 2, 3, 1, 6 };
        if (subArrayExists(arr))
            System.out.println("Found a subarray with 0 sum");
        else
            System.out.println("No Such Sub Array Exists!");
    }
}
