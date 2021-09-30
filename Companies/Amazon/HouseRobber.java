package Companies.Amazon;

import Libs.TreeNode;

import java.util.Arrays;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
*/
public class HouseRobber {
    public int robI1(int[] nums) {
        int curSum = 0, prev = 0; // max over a sliding window of 3 positions
        for (int num : nums) {
            int temp = curSum;
            curSum = Math.max(num + prev, curSum);
            prev = temp;
        }
        return curSum;
    }

    public int robI2(int[] nums) {
        int[] memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return helper(memo, nums, nums.length - 1);
    }

    private int helper(int[] memo, int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        int result = Math.max(helper(memo, nums, i - 2) + nums[i], helper(memo, nums, i - 1));
        memo[i] = result;
        return result;
    }

    /* Cycle */
    public int robII(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(helperII(nums, 0, nums.length - 1), helperII(nums, 1, nums.length));
    }

    private int helperII(int[] nums, int l, int r) {
        int cur = 0, prev = 0;
        for (int i = l; i < r; i++) {
            int temp = cur;
            cur = Math.max(prev + nums[i], cur);
            prev = temp;
        }
        return cur;
    }

    /* TreeNode */
    public int rob(TreeNode root) {
        int[] dual = helper(root);
        return Math.max(dual[0], dual[1]);
    }

    private int[] helper(TreeNode root) {
        int[] re = new int[2];
        if (root == null) {
            return re;
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        re[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        re[1] = left[0] + right[0] + root.val;
        return re;
    }

    public int robDp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length ; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 1 };
        HouseRobber hr = new HouseRobber();
        System.out.println("robI1: "+hr.robI1(nums));
        System.out.println("robDp: "+hr.robDp(nums));
    }
}
