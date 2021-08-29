package Companies.Google;
/*
In a gold mine grid of size m x n, each cell in this mine has an integer representing 
the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.

https://leetcode.com/problems/path-with-maximum-gold/
https://www.youtube.com/watch?v=8nlmcgy7vso
 
*/
public class PathWithMaximumGold {
    int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};

    public int getMaximumGold(int[][] grid) {
        int re = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    int gold = dfs(grid, i, j);
                    re = Math.max(re, gold);
                }
            }
        }
        return re;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] <= 0) {
            return 0;
        }
        int re = grid[i][j];
        int gold = grid[i][j];
        grid[i][j] = -1;
        for (int[] dir : dirs) {
            re = Math.max(re, gold + dfs(grid, i+dir[0], j+dir[1]));
        }
        grid[i][j] = gold;
        return re;
    }

    public int getMaximumGoldK(int[][] grid) {
        int re = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    int gold = dfsK(grid, i, j);
                    re = Math.max(re, gold);
                }
            }
        }
        return re;
    }

    private int dfsK(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] <= 0) {
            return 0;
        }
        int re = 0;
        grid[i][j] *= -1;
        for (int[] dir : dirs) {
            re = Math.max(re, Math.abs(grid[i][j]) + dfs(grid, i+dir[0], j+dir[1]));
        }
        grid[i][j] *= -1;
        return re;
    }

        /*
Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
        */
    public static void main(String[] a){
        PathWithMaximumGold mg = new PathWithMaximumGold();
        int[][] grid = {{0,6,0},{5,8,7},{0,9,0}};
        int gold = mg.getMaximumGoldK(grid);
        System.out.println(gold);
    }
}
