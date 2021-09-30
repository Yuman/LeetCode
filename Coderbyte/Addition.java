package Coderbyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Addition {
    private Map<Integer, Boolean> cache = new HashMap<>();

    /**
     * Given an array of numbers and a targetSum, determine if it is possible to
     * generate the targeSum from the array of numbers. You may use any number as
     * many times as you want.
     */

    public boolean canSum(int[] nums, int targetSum) {
        if (targetSum == 0)
            return true;
        if (targetSum < 0)
            return false;
        if (cache.containsKey(targetSum))
            return cache.get(targetSum).booleanValue();

        for (int n : nums) {
            if (canSum(nums, targetSum - n)) {
                cache.put(targetSum - n, true);
                return true;
            }
        }
        cache.put(targetSum, false);
        return false;

    }

    /**
     * Find a combination of the array elements that add up to the sum
     * 
     * @param args
     */
    // private List<Integer> arr = new ArrayList<>();
    public List<Integer> howSum(int[] nums, int targetSum) {
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        if (targetSum < 0) {
            return null;
        }

        for (int n : nums) {
            int remainder = targetSum - n;
            List<Integer> res = howSum(nums, remainder);
            if (res != null) {
                res.add(n);
                return res;
            }
        }
        return null;
    }

    @SuppressWarnings({ "unchecked" })
    public List<Integer> howSumDpObj(int[] nums, int targetSum) {

        Object[] dp = new Object[targetSum + 1];
        dp[0] = new ArrayList<Integer>();
        for (int i = 1; i <= targetSum; i++) {
            for (int coin : nums) {
                if (coin <= i && dp[i - coin] != null) {
                    List<Integer> prev = (List<Integer>) dp[i - coin];
                    List<Integer> curr = new ArrayList<Integer>(prev);
                    curr.add(coin);
                    dp[i] = curr;
                }
            }
        }
        return (List<Integer>) dp[targetSum];
    }

    public List<Integer> howSumDpList(int[] nums, int targetSum) {
        ArrayList<ArrayList<Integer>> dp = new ArrayList<>();

        dp.add(new ArrayList<Integer>());
        for (int i = 1; i <= targetSum; i++) {
            dp.add(null);
        }

        for (int i = 1; i <= targetSum; i++) {
            for (int coin : nums) {// relation: dp[i] = coin + dp[i-coin]
                if (coin <= i && dp.get(i - coin) != null) {
                    ArrayList<Integer> prev = dp.get(i - coin);
                    ArrayList<Integer> curr = new ArrayList<Integer>(prev);
                    curr.add(coin);
                    dp.set(i, curr);
                }
            }
        }
        return dp.get(targetSum);
    }

    /**
     * Find the fewest numbers to add up to the targetSum Note that it is incorrect
     * to always take the biggest number first Exhaustive search is necessary
     * 
     * @param nums
     * @param targetSum
     * @return
     */
    public List<Integer> bestSum(int[] nums, int targetSum) {
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        if (targetSum < 0) {
            return null;
        }

        List<Integer> shortest = null;

        for (int n : nums) {
            int remainder = targetSum - n;
            List<Integer> remainderCombo = bestSum(nums, remainder);
            if (remainderCombo != null) {
                remainderCombo.add(n);
                if (shortest == null || shortest.size() > remainderCombo.size()) {
                    shortest = remainderCombo;
                }
            }

        }

        return shortest;
    }

    int sumR(List<Integer> nums) {
        if (nums.size() == 0)
            return 0;
        if (nums.size() == 1)
            return nums.get(0);
        int base = nums.get(0);
        nums.remove(0);
        return base + sumR(nums);
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 5, 10, 25 };
        Addition adder = new Addition();
        boolean res = adder.canSum(nums, 33);
        System.out.println("canSum: " + res);

        System.out.println("howSum: " + adder.howSum(nums, 15));
        System.out.println("howSumDpObj: " + adder.howSumDpObj(nums, 15));
        System.out.println("howSumDpList: " + adder.howSumDpList(nums, 15));
        System.out.println("bestSum: " + adder.bestSum(nums, 15));

        List<Integer> adds = new ArrayList<>();
        adds.add(1);
        adds.add(2);
        adds.add(3);
        System.out.println("sumR: " + adder.sumR(adds));

        List<Integer> r1 = new ArrayList<>(Arrays.asList(1, 1));
        List<Integer> r2 = new ArrayList<>(Arrays.asList(1, 2, 1));
        List<List<Integer>> st = new ArrayList<>();
        st.add(r1);
        st.add(r2);
        System.out.println(st);
    }
}
