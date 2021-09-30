package Companies.Amazon;

/*
@see grid traveller
*/
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {

        for (int i = 1; i < grid.length; i++) {// fill 0th column with running sum
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) {// fill 0th row with running sum
            grid[0][j] = grid[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public int minPathSumII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = grid[i][j];
                } else if (i == 0 || j == 0) {
                    dp[j] = i == 0 ? dp[j - 1] + grid[i][j] : dp[j] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }
}
