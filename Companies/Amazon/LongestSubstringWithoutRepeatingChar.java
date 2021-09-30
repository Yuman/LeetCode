package Companies.Amazon;

import java.util.HashMap;
import java.util.Map;
/*
Given a string s, find the length of the longest substring without repeating characters.
*/
import java.util.*;

public class LongestSubstringWithoutRepeatingChar {
    /**
     * Input: "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length
     * of 3.
     *
     * Space complexity : O(min(m,n)). We need O(k) space for the sliding window,
     * where k is the size of the map. The size of the map is upper bounded by the
     * size of the string n and the size of the charset/alphabet m.
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> m = new HashMap<>();
        int re = 0, l = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (m.containsKey(c)) {
                l = Math.max(l, m.get(c));
            }
            re = Math.max(re, r - l + 1);
            m.put(c, r + 1);
        }
        return re;
    }

    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> m = new HashMap<>();
        int re = 0, l = 0;
        for (int r = 0; r < s.length(); r++) {// r: leading
            char c = s.charAt(r);
            if (m.containsKey(c)) {
                l = Math.max(l, m.get(c));// move l  to the repeat position
            }
            re = Math.max(re, r - l); // update length
            m.put(c, r);   // update c position
        }
        return re;
    }

    /**
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z' int[128] for ASCII int[256] for
     * Extended ASCII
     *
     * Space complexity (Table): O(m). m is the size of the charset.
     */
    public int lengthOfLongestSubstringII(String s) {
        int[] index = new int[128]; // current index of character
        // try to extend the range [l, r]
        int re = 0, l = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            l = Math.max(index[c], l);
            re = Math.max(re, r - l + 1);
            index[c] = r + 1;
        }
        return re;
    }

    public int maxSub(String s) {
        int maxLen = 0;
        int left = 0, right = 0;
        Set<Character> window = new HashSet<>();
        for (int runningMax = 0; right < s.length(); right++) {
            if (window.contains(s.charAt(right))) {
                runningMax = right - left;
                maxLen = Math.max(runningMax, maxLen);
                while (window.contains(s.charAt(right))) {
                    window.remove(s.charAt(left++));
                }
            }
            window.add(s.charAt(right));
        }
        return maxLen;
    }

    public static void main(String[] a) {
        /*
         * Input: s = "abcabcbb" Output: 3 Explanation: The answer is "abc", with the
         * length of 3.
         */
        LongestSubstringWithoutRepeatingChar sublen = new LongestSubstringWithoutRepeatingChar();
        System.out.println("longestII: " + sublen.lengthOfLongestSubstringII("abcabcbb"));
        System.out.println("longest2: " + sublen.lengthOfLongestSubstring2("abcabcbb"));
        System.out.println("maxlen: " + sublen.maxSub("abcabcbb"));
    }
}
