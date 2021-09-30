package Topics.Dynamic;

public class KnapsackMem {
  public int solveKnapsack(int[] profits, int[] weights, int capacity) {
    Integer[][] dp = new Integer[profits.length][capacity + 1];
    return this.knapsackMem(dp, profits, weights, capacity, 0);
  }

  private int knapsackMem(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {

    // base checks
    if (capacity <= 0 || currentIndex >= profits.length)
      return 0;

    // if we have already solved a similar problem, return the result from memory
    if (dp[currentIndex][capacity] != null)
      return dp[currentIndex][capacity];

    // recursive call after choosing the element at the currentIndex
    // if the weight of the element at currentIndex exceeds the capacity, we
    // shouldn't process this
    int profit1 = 0;
    if (weights[currentIndex] <= capacity)
      profit1 = profits[currentIndex]
          + knapsackMem(dp, profits, weights, capacity - weights[currentIndex], currentIndex + 1);

    // recursive call after excluding the element at the currentIndex
    int profit2 = knapsackMem(dp, profits, weights, capacity, currentIndex + 1);

    dp[currentIndex][capacity] = Math.max(profit1, profit2);
    return dp[currentIndex][capacity];
  }

  public static void main(String[] args) {
    KnapsackMem ks = new KnapsackMem();
    int[] profits = { 1, 6, 10, 16 };
    int[] weights = { 1, 2, 3, 5 };
    int maxProfit = ks.solveKnapsack(profits, weights, 7);
    System.out.println("Total knapsack profit mem ---> " + maxProfit);
    maxProfit = ks.solveKnapsackTab(profits, weights, 7);
    System.out.println("Total knapsack profit tab ---> " + maxProfit);
  }

  /**
   * https://www.youtube.com/watch?v=WNkqbqyvR_0
   * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
   * https://www.geeksforgeeks.org/printing-items-01-knapsack/
   */
  int solveKnapsackTab(int val[], int wt[], int capacity) {
    int n = val.length;
  
    int[][] dp = new int[n + 1][capacity + 1];

    for (int i = 0; i <= n; i++) {
      for (int w = 0; w <= capacity; w++) {
        if (i == 0 || w == 0)
          dp[i][w] = 0;
        else if (wt[i - 1] <= w)
          dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
        else
          dp[i][w] = dp[i - 1][w];
      }
    }

    return dp[n][capacity];
  }
}
