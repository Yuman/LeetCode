package Companies.Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticSlices {
    /*
An integer array is called arithmetic if it consists of at least three elements 
and if the difference between any two consecutive elements is the same.

For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.

Given an integer array nums, return the number of arithmetic subarrays of nums.

A subarray is a contiguous subsequence of the array.

 

Example 1:

Input: nums = [1,2,3,4]
Output: 3
Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
Example 2:

Input: nums = [1]
Output: 0
 

Constraints:

1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000
    */
    public int numberOfArithmeticSlices(int[] A) {
        int re = 0, count = 0; //https://www.youtube.com/watch?v=rKrLUXfzRQ4
        for (int i = 2; i < A.length; i++) {
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                count++;
                re += count;
            } else {
                count = 0;
            }
        }
        return re;
    }

    /**
     * @Follow-up: Subsequence
     */
    public int numberOfArithmeticSlicesII(int[] A) {
        int n = A.length;
        int ans = 0;
        int[][] dp = new int[n][n];
        Map<Long, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey((long)A[i])) {
                map.put((long)A[i], new ArrayList<>());
            }
            map.get((long)A[i]).add(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long target = 2 * (long)A[j] - A[i];
                if (map.containsKey(target)) {
                    for (int k: map.get(target)) {
                        if (k < j) {
                            dp[i][j] += (dp[j][k] + 1);
                        }
                    }
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }

}
