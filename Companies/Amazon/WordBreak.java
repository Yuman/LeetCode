package Companies.Amazon;

import java.util.HashSet;
import java.util.List;
import java.util.*;
import java.util.Set;

/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
*/
public class WordBreak {
    // https://www.youtube.com/watch?v=hLQYQ4zj0qg 12:00
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreakDp2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> set = new HashSet<>();
        set.addAll(wordDict);

        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] && set.contains(s.substring(j, i));
                if (dp[i])
                    break;
            }
        }
        return dp[s.length()];
    }

    /**
     * Time complexity: O(n^2). Size of recursion tree can go up to O(n^2). Space
     * complexity: O(n). The depth of recursion tree can go up to O(n).
     */
    public boolean wordBreakR(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }

    private boolean dfs(String s, List<String> l, Map<String, Boolean> m) {
        if (m.containsKey(s)) {
            return m.get(s);
        }
        boolean exist = false;
        for (String word : l) {
            if (s.startsWith(word)) {
                String after = s.substring(word.length());
                if (after.length() == 0) {
                    exist = true;
                } else {
                    exist = dfs(after, l, m);
                }
                if (exist) {
                    break;
                }
            }
        }
        m.put(s, exist);
        return exist;
    }
}
