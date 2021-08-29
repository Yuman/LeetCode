package Companies.Amazon;
/*
329. Longest Increasing Path in a Matrix
https://www.youtube.com/watch?v=uLjO2LUlLN4
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. 
You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
*/
public class LongestIncreasingPathInAMatrix {
    private int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, cache, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int[][] cache, int i, int j) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int max = 1;
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r < 0 || c < 0 || r >= cache.length || c >= cache[0].length || matrix[r][c] <= matrix[i][j]) {
                continue;
            }
            max = Math.max(max, 1 + dfs(matrix, cache, r, c));
        }
        cache[i][j] = max;
        return max;
    }
}
