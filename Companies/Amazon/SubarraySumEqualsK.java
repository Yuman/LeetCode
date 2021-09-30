package Companies.Amazon;

import java.util.HashMap;

/*
Given an array of integers nums and an integer k, 
return the total number of continuous subarrays whose sum equals to k.

@see ZeroSumSubarray
*/
public class SubarraySumEqualsK {
    /*
     * https://leetcode.com/problems/subarray-sum-equals-k/discuss/803317/Java-
     * Solution-with-Detailed-Explanation
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))  // the key (sum-k) handles all delta(sum)
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;
        SubarraySumEqualsK sub = new SubarraySumEqualsK();
        System.out.println(sub.subarraySum(nums, k));

//Output: 2
    }
}
