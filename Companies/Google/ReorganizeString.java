package Companies.Google;

import java.util.PriorityQueue;
import java.util.*;

/*
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""
*/
public class ReorganizeString {

    public String reorganizeString(String S) {
        int[] dict = new int[26];
        for (char c : S.toCharArray()) {
            dict[c - 'A']++;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++) {
            if (dict[i] != 0) {
                q.offer(new int[] { i, dict[i] });
            }
        }
        if (q.peek()[1] > (S.length() + 1) / 2) { // aaab
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int[] prev = { -1, 0 }; // vvvlo
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (prev[1] > 0) {
                q.offer(prev);
            }
            sb.append((char) (cur[0] + 'A')); // be careful (char)
            cur[1]--;
            prev = cur;
        }
        return sb.toString();
    }

    public String reorganizeStringCount(String S) {
        if (S == null || S.length() == 1)
            return S;

        Map<Character, Integer> dic = new HashMap<>();
        char maxChar = S.charAt(0);
        int L = S.length();

        // count chars in map, get max
        for (char c : S.toCharArray()) {
            dic.put(c, dic.getOrDefault(c, 0) + 1);
            if (dic.get(c) > dic.get(maxChar)) {
                maxChar = c;
            }
        }

        if (dic.get(maxChar) > (L + 1) / 2)
            return "";

        int idx = 0;
        char[] ret = new char[L];

        // put all maxChar's into array (may not reach end of S)
        while (idx < L && dic.get(maxChar) > 0) {
            ret[idx] = maxChar;
            dic.put(maxChar, dic.get(maxChar) - 1);
            idx += 2;
        }

        // loop through dic, may go through a key where val is 0, but won't do anything
        for (Character c : dic.keySet()) {
            while (dic.get(c) > 0) {
                if (idx >= L)
                    idx = 1; // First time it reaches L, reset it. Won't be inf loop cause not looping on idx
                ret[idx] = c;
                dic.put(c, dic.get(c) - 1);
                idx += 2;
            }
        }
        return new String(ret);
    }

    // there is a much more efficient way:
    // create a count map. Lay the same chars at even indeces until full {0, 2, ...
    // length -1}
    // then fill the odd indeces
    // if there is a mojority, return "";
    public String reorganizeStringZip(String S) {
        if (S == null || S.length() == 1)
            return S;

        Map<Character, Integer> dic = new HashMap<>();
        char maxChar = S.charAt(0);
        int L = S.length();

        // count chars in map, get max
        for (char c : S.toCharArray()) {
            dic.put(c, dic.getOrDefault(c, 0) + 1);
            if (dic.get(c) > dic.get(maxChar)) {
                maxChar = c;
            }
        }

        if (dic.get(maxChar) > (L + 1) / 2)
            return "";

        int idx = 0;
        char[] ret = new char[L];

        // put all maxChar's into array (may not reach end of S)
        while (idx < L && dic.get(maxChar) > 0) {
            ret[idx] = maxChar;
            dic.put(maxChar, dic.get(maxChar) - 1);
            idx += 2;
        }

        // loop through dic, may go through a key where val is 0, but won't do anything
        for (Character c : dic.keySet()) {
            while (dic.get(c) > 0) {
                if (idx >= L)
                    idx = 1; // First time it reaches L, reset it. Won't be inf loop cause not looping on idx
                ret[idx] = c;
                dic.put(c, dic.get(c) - 1);
                idx += 2;
            }
        }
        return new String(ret);

    }

    public static void main(String... args) {
        ReorganizeString test = new ReorganizeString();

        // System.out.println(test.reorganizeStringZip("baabcccccddd"));
        System.out.println("Zip aab: " + test.reorganizeStringZip("aab"));
        System.out.println("Zip aaab: " + test.reorganizeStringZip("aaab"));
        System.out.println("Zip sfffp: " + test.reorganizeStringZip("sfffp"));

    }
}
