package Companies.Amazon;

import java.util.*;

/*
Given a set of distinct positive integers nums, return the largest subset answer 
such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

First, the array needs to be sorted. The problem statement says to find the subsets satisfy Si % Sj = 0 or Sj % Si = 0. If we sorted array, this can be simplified to Si % Sj = 0, i > j.

Then, let's talk about the meaning of count and pre arrays. count[i] present the maximum length of the divisible subset at index i.
For example, we have nums = [1,2,3],
At i = 0,count[0] = 1, nums = [1] the length of divisible subset is 1 because the we have only one number so far.
At i = 1,count[1] = 2, nums = [1,2], and 2 % 1 == 0, which satisfy the condition Si % Sj = 0, i > j.
At i = 2,count[2] = 2, nums = [1,2,3], and 3 % 2 != 0, 3 % 1 = 0, 2 % 1 == 0. Therefore, the length of the subset is also 2.

If the problem is asking to find the maximum length of the subset satisfies Si % Sj = 0 or Sj % Si = 0 we just need to return the count[n - 1], but we need to find the actual subset so, we need more information. pre array presents the previous index of the divisible subsets at index i of nums. Initially, the value set to -1 because we don't have any subset yet.
At i = 0,pre[0] = -1, nums = [1], previous index of divisible subset [1] is -1.
At i = 1,pre[1] = 0, nums = [1,2], previous index of divisible subset [1,2] is 0.
At i = 2,pre[2] = 0, nums = [1,2,3], previous index of divisible subset [1,2] or [1,3] is 0.

Finally, count[i] is to find the maximum length of the divisible subset, and pre[i] find all previous indices of the divisible subset, so we can iterate pre array and find the corresponding elements from nums array and get the largest divisible subset.

https://leetcode.com/problems/largest-divisible-subset/discuss/84006/Classic-DP-solution-similar-to-LIS-O(n2)

*/
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n];
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }

    public static void main(String[] a) {
        LargestDivisibleSubset ds = new LargestDivisibleSubset();
        int[] nums = { 1, 2, 3 };
        System.out.println(ds.largestDivisibleSubset(nums));
    }
}
