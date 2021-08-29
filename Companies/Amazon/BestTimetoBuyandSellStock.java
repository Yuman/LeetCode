package Companies.Amazon;
//https://www.youtube.com/watch?v=tmakGVOGV3A
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 

Constraints:

1 <= prices.length <= 10^5
0 <= prices[i] <= 10^4
*/

public class BestTimetoBuyandSellStock {
    /* Once */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int prof = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            prof = Math.max(prof, prices[i]-min);
        }
        return prof;
    }

    /* Multiple times 
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
    */
    public int maxProfitII(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                sum += prices[i]-prices[i-1];
            }
        }
        return sum;
    }

    /* At most two transactions. */
    public int maxProfitIII(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE, sell1 = 0, sell2 = 0;
        for (int price : prices) {                  // Assume we only have 0 money at first
            sell2 = Math.max(sell2, buy2+price);    // The maximum if we've just sold 2nd stock so far.
            buy2 = Math.max(buy2, sell1-price);     // The maximum if we've just buy  2nd stock so far.
            sell1 = Math.max(sell1, buy1+price);    // The maximum if we've just sold 1nd stock so far.
            buy1 = Math.max(buy1, -price);          // The maximum if we've just buy  1st stock so far.
        }
        return sell2;                               // Since release1 is initiated as 0, so release2 will always higher than release1.
    }

    /* At most K transactions. */
    public int maxProfitIV(int k, int[] prices) {
        if (k >= prices.length/2) {
            int re = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i-1])
                    re += prices[i] - prices[i-1];
            }
            return re;
        }
        int[][] dp = new int[k+1][prices.length];
        for (int i = 1; i <= k; i++) {
            int buy = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j]+buy);
                buy = Math.max(buy, dp[i-1][j]-prices[j]);
            }
        }
        return dp[k][prices.length-1];
    }

    /* Cooldown */
    public int maxProfitCooldown(int[] prices) {
        int buy = Integer.MIN_VALUE, prevbuy = 0, sell = 0, prevsell = 0;
        for (int price : prices) {
            prevbuy = buy;
            buy = Math.max(prevbuy, prevsell-price);
            prevsell = sell;
            sell = Math.max(prevsell, prevbuy+price);
        }
        return sell;
    }

    /* Transaction Fee */
    public int maxProfit(int[] prices, int fee) {
        int buy = -prices[0], sell = 0;
        for (int i = 1; i < prices.length; i++) {
            sell = Math.max(sell, buy+prices[i]-fee); // sell first, since sell and buy at same day can't be
            buy = Math.max(buy, sell-prices[i]);      // better than just continuing to hold the stock
        }
        return sell;
    }
}
