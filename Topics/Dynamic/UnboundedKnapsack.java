package Topics.Dynamic;

/**
 * Coin change 2 -- https://leetcode.com/problems/coin-change-2/ You are given
 * an integer array coins representing coins of different denominations and an
 * integer amount representing a total amount of money.
 * 
 * Return the number of combinations that make up that amount. If that amount of
 * money cannot be made up by any combination of the coins, return 0.
 * 
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * 
 * Example 1:
 * 
 * Input: amount = 5, coins = [1,2,5] Output: 4 Explanation: there are four ways
 * to make up the amount: 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1
 */
public class UnboundedKnapsack {
    /**
     * dp[i][j] : the number of combinations to make up amount j by using the first
     * i types of coins State transition:
     * 
     * not using the ith coin, only using the first i-1 coins to make up amount j,
     * then we have dp[i-1][j] ways. using the ith coin, since we can use unlimited
     * same coin, we need to know how many ways to make up amount j - coins[i-1] by
     * using first i coins(including ith), which is dp[i][j-coins[i-1]]
     * Initialization: dp[i][0] = 1
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

    public int change1D(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (coin < dp[i])
                    dp[i] += dp[i - coin]; // simulates taking a coin for the amoumt
            }
        }
        return dp[amount];
    }
}
