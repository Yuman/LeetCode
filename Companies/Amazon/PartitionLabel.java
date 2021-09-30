package Companies.Amazon;

import java.util.*;

/*
You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

Return a list of integers representing the size of these parts.

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
*/
public class PartitionLabel {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[] index = new int[26];
        for (int i = 0; i < S.length(); i++) { // loop through to get the last index of all chars
            index[S.charAt(i) - 'a'] = i;
        }
        int start = 0; // left pointer
        int end = 0; // right pointer
        for (int i = 0; i < S.length(); i++) {
            int c = S.charAt(i) - 'a'; // position in the lastIndex array
            if (index[c] > i) { // for this c, i not at the last yet
                if (index[c] > end) {
                    end = index[c]; // end = right. maximize end for all chars
                }
            } else if (i == end) {
                res.add(i - start + 1);
                start = i + 1;
                end = i + 1;
            }
        }
        return res;
    }

    public List<Integer> partitionLabelsMap(String S) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            lastIndex.put(S.charAt(i), i);
        }
        for (int i = 0, right = 0; i < S.length(); i++) { // i: walker, two trackers
            right = lastIndex.get(S.charAt(i));
            for (int left = i + 1; left < right; left++) {
                right = Math.max(right, lastIndex.get(S.charAt(left)));
            }
            int len = right - i + 1;
            res.add(len);
            i = right + 1;
        }

        return res; // looks good, but off by 1
    }

    public static void main(String[] a) {
        PartitionLabel part = new PartitionLabel();
        System.out.println(part.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(part.partitionLabelsMap("ababcbacadefegdehijhklij"));
    }
}
