package Companies.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

/**
 * @see Permutations
 * @see Subsets
 * @see CoinChange2
 */
public class CombinationSum {
    /*
     * Given an array of distinct integers candidates and a target integer target,
     * return a list of all unique combinations of candidates where the chosen
     * numbers sum to target. You may return the combinations in any order.
     * 
     * The same number may be chosen from candidates an unlimited number of times.
     * Two combinations are unique if the frequency of at least one of the chosen
     * numbers is different.
     * 
     * It is guaranteed that the number of unique combinations that sum up to target
     * is less than 150 combinations for the given input.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> re = new ArrayList<>();
        dfs(re, new ArrayList<>(), candidates, target, 0);
        return re;
    }

    private void dfs(List<List<Integer>> re, List<Integer> l, int[] candidates, int target, int index) {
        if (target == 0) {
            re.add(new ArrayList<>(l));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // if (i > index && candidates[i] == candidates[i-1]) { // duplicates
            // continue;
            // }
            if (candidates[i] <= target) {
                l.add(candidates[i]);
                dfs(re, l, candidates, target - candidates[i], i); // dfs(...., i+1)
                l.remove(l.size() - 1);
            }
        }
    }

    /**
     * Combination Sum IV
     * 
     * Given an array of distinct integers nums and a target integer target, return
     * the number of possible combinations that add up to target.
     * 
     * The answer is guaranteed to fit in a 32-bit integer.
     * 
     * nums = [1, 2, 3] target = 4 The possible combination ways are: (1, 1, 1, 1)
     * (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3, 1)
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int sum = 1; sum <= target; sum++) {
            for (int num : nums) {
                if (sum - num >= 0) {
                    dp[sum] += dp[sum - num];
                }
            }
        }
        return dp[target];
    }

    /*
     * Combination SUm II Given a collection of candidate numbers (candidates) and a
     * target number (target), find all unique combinations in candidates where the
     * candidate numbers sum to target.
     * 
     * Each number in candidates may only be used once in the combination.
     * 
     * Note: The solution set must not contain duplicate combinations.
     */
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs_com(cand, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path)); // found path. add a clone
            return;
        }
        if (target < 0)
            return;
        for (int i = cur; i < cand.length; i++) {
            if (i > cur && cand[i] == cand[i - 1])  // use once
                continue;
            path.add(path.size(), cand[i]);  // taking a number cand[i] for the iteration
            dfs_com(cand, i + 1, target - cand[i], path, res);
            path.remove(path.size() - 1);  // return cand[i] for the next iteration
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        int target = 4;
        CombinationSum cs = new CombinationSum();
        System.out.println("sum: " + cs.combinationSum(nums, target));
        System.out.println("sum4: " + cs.combinationSum4(nums, target));
        System.out.println("sum2: " + cs.combinationSum2(nums, target));

    }
}
