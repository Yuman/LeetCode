package Companies.Amazon;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
*/
public class MinimumWindowSubstring {
    /*
     * 1. Use two pointers: start and end to represent a window. 2. Move end to find
     * a valid window. 3. When a valid window is found, move start to find a smaller
     * window. To check if a window is valid, we use a map to store (char, count)
     * for chars in t. And use counter for the number of chars of t to be found in
     * s. The key part is m[s[end]]--;. We decrease count for each char in s. If it
     * does not exist in t, the count will be negative.
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : t.toCharArray()) { // store t in map for lookup
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = 0, dis = Integer.MAX_VALUE, left = -1, count = m.size();
        while (r < s.length()) {
            char c = s.charAt(r);
            m.put(c, m.getOrDefault(c, 0) - 1); // mark not-found as '-1'
            if (m.get(c) == 0) {
                count--;
            }
            while (count == 0) {
                if (dis > r - l + 1) {
                    dis = r - l + 1;
                    left = l;
                }
                char begin = s.charAt(l);
                if (m.get(begin) == 0) { // first check, then add 1
                    count++;
                }
                m.put(begin, m.get(begin) + 1);
                l++;
            }
            r++;
        }
        return left == -1 ? "" : s.substring(left, left + dis);
    }

    public String minWindowM2(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())  // add letters in s to set
            map.put(c, 0);
        for (char c : t.toCharArray()) { // count letters in t in the set
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return "";              // check s contains all letters in t. a substring exists
        }

        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);  
            if (map.get(c1) > 0)  // found one letter for t
                counter--;
            map.put(c1, map.get(c1) - 1);

            end++;

            while (counter == 0) {  // found all t letters, counter=0
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }

                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);

                if (map.get(c2) > 0)
                    counter++;

                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] a) {
        MinimumWindowSubstring mw = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println("min wind: " + mw.minWindow(s, t));
        System.out.println("min windM2: " + mw.minWindowM2(s, t));
    }
}
