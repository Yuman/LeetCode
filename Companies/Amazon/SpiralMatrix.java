package Companies.Amazon;

import java.util.*;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0)
            return res;
        int n = matrix.length, m = matrix[0].length;
        int up = 0, down = n - 1;
        int left = 0, right = m - 1;
        while (res.size() < n * m) {
            for (int j = left; j <= right && res.size() < n * m; j++)
                res.add(matrix[up][j]);

            for (int i = up + 1; i <= down - 1 && res.size() < n * m; i++)
                res.add(matrix[i][right]);

            for (int j = right; j >= left && res.size() < n * m; j--)
                res.add(matrix[down][j]);

            for (int i = down - 1; i >= up + 1 && res.size() < n * m; i--)
                res.add(matrix[i][left]);

            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }

    /*
     * generate a square matrix filled with elements from 1 to n^2 in spiral order.
     */
    public int[][] generateMatrix(int n) {
        int[][] re = new int[n][n];
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int r = 0, c = 0, i = 0;
        boolean[][] visited = new boolean[n][n];
        for (int num = 1; num <= n * n; num++) {
            re[r][c] = num;
            visited[r][c] = true;
            int rr = r + dirs[i][0];
            int cc = c + dirs[i][1];
            if (rr >= 0 && rr < n && cc >= 0 && cc < n && !visited[rr][cc]) {
                r = rr;
                c = cc;
            } else {
                i = (i + 1) % 4;
                r += dirs[i][0];
                c += dirs[i][1];
            }
        }
        return re;
    }

    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] re = new int[R * C][2];
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int n = 0, index = 0;
        for (int i = 0; i < R * C; n++) {
            for (int j = 0; j < n / 2 + 1; j++) {
                if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
                    re[i++] = new int[] { r0, c0 };
                }
                r0 += dirs[index][0];
                c0 += dirs[index][1];
            }
            index = (index + 1) % 4;
        }
        return re;
    }
}
