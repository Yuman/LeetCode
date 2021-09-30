package Companies.Amazon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinChange {
    /**
     * You are given an integer array coins representing coins of different
     * denominations and an integer amount representing a total amount of money.
     * 
     * Return the fewest number of coins that you need to make up that amount. If
     * that amount of money cannot be made up by any combination of the coins,
     * return -1.
     * 
     * You may assume that you have an infinite number of each kind of coin. fewest
     * number of coins Input: coins = [1, 2, 5], amount = 11 Output: 3 Explanation:
     * 11 = 5 + 5 + 1
     */
    public int coinChangeFewest(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;        // 0 coins to make 0 amount
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) { // i <= amount, use the coin toward i
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                // use any one coin to reduce the amount. (i-coin) is a previous cell
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * You are given an integer array coins representing coins of different
     * denominations and an integer amount representing a total amount of money.
     * Return the number of combinations that make up that amount. If that amount of
     * money cannot be made up by any combination of the coins, return 0. You may
     * assume that you have an infinite number of each kind of coin. The answer is
     * guaranteed to fit into a signed 32-bit integer. Input: amount = 5, coins =
     * [1, 2, 5] Output: 4
     */
    public int changeWaysCount(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;       // 1 way to make 0 amount
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) { // selectively take a coin
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public int changeWaysCountR(int amt, int[] coins) {
        return countWays(coins, coins.length, amt);
    }

    private int countWays(int[] S, int m, int n) {
        // If n is 0 then there is 1 solution
        // (do not include any coin)
        if (n == 0)
            return 1;

        // If n is less than 0 then no
        // solution exists
        if (n < 0)
            return 0;

        // If there are no coins and n
        // is greater than 0, then no
        // solution exist
        if (m <= 0 && n >= 1)
            return 0;

        // count is sum of solutions
        // 1. skipping S[m-1]
        // 2. taking S[m-1]
        return countWays(S, m - 1, n) + countWays(S, m, n - S[m - 1]);
    }

    public int changeCountFewestRI(int amt, int[] coins) {
        return changeWaysCountRIHelper(coins, amt, new int[amt + 1]);
    }

    private int changeWaysCountRIHelper(int[] coins, int rem, int[] count) {
        // rem: remaining coins after the last step; count[rem]: minimum number of coins
        // to sum up to rem
        if (rem < 0)
            return -1; // not valid
        if (rem == 0)
            return 0; // completed
        if (count[rem] != 0)
            return count[rem]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = changeWaysCountRIHelper(coins, rem - coin, count);
            if (res != -1) {
                min = Math.min(res + 1, min);
            }
        }
        count[rem] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem];
    }

    public static void main(String[] a) {
        CoinChange cc = new CoinChange();
        System.out.println("fewest: " + cc.coinChangeFewest(new int[] { 1, 2, 5 }, 11));
        System.out.println("count DP: " + cc.changeWaysCount(11, new int[] { 1, 2, 5 }));
        System.out.println("count Rec: " + cc.changeWaysCountR(11, new int[] { 1, 2, 5 }));
        System.out.println("count fewest RI: " + cc.changeCountFewestRI(11, new int[] { 1, 2, 5 }));
    }
}
