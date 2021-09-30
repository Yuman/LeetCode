package Companies.Google;

/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

https://leetcode.com/problems/longest-common-subsequence/discuss/348884/C%2B%2B-with-picture-O(nm)

*/
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] a) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.longestCommonSubsequence("abcde", "ace"));

        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        char[] X = s1.toCharArray();
        char[] Y = s2.toCharArray();
        int m = X.length;
        int n = Y.length;

        System.out.println("Length of LCS is" + " " + lcs.lcs(X, Y, m, n));
        System.out.println("Length of LCSK is" + " " + lcs.lcs(X, Y, m, n));
    }

    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcs(char[] X, char[] Y, int m, int n) {
        int grid[][] = new int[m + 1][n + 1];

        /*
         * Following steps build L[m+1][n+1] in bottom up fashion. Note that L[i][j]
         * contains length of LCS of X[0..i-1] and Y[0..j-1]
         */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    grid[i][j] = 0;
                else if (X[i - 1] == Y[j - 1])
                    grid[i][j] = grid[i - 1][j - 1] + 1;
                else
                    grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m][n];
    }

    // https://www.youtube.com/watch?v=NnD96abizww
    int lcsK(char[] X, char[] Y, int m, int n) {
        int grid[][] = new int[m + 1][n + 1];

        /*
         * Following steps build L[m+1][n+1] in bottom up fashion. Note that L[i][j]
         * contains length of LCS of X[0..i-1] and Y[0..j-1]
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    grid[i][j] = 0;
                else if (X[i ] == Y[j ])
                    grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]) + 1;
                else
                    grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m][n];
    }


}
