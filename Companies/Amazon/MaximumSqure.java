package Companies.Amazon;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * https://www.youtube.com/watch?v=RElcqtFYTm0
 */
public class MaximumSqure {
    public int maximalSquare(char[][] a) {
        if (a.length == 0)
            return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }
}
