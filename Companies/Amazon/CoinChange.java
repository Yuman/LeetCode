package Companies.Amazon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinChange {
    /**
     * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

     * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

     * You may assume that you have an infinite number of each kind of coin.
     * fewest number of coins
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }
        return dp[amount] > amount? -1:dp[amount];
    }


    /**
     * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
     * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
     * You may assume that you have an infinite number of each kind of coin.
     * The answer is guaranteed to fit into a signed 32-bit integer.
     * Input: amount = 5, coins = [1, 2, 5]
     * Output: 4
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }

    @Test
    void test() {
        assertEquals(3, coinChange(new int[]{1,2,5}, 11));
        assertEquals(4, change(5, new int[]{1,2,5}));
        assertEquals(4, change(60, new int[]{10,15,60}));
    }
}
