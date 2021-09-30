package Companies.Amazon;

import java.util.*;

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
typically using all the original letters exactly once.

Amazon most asked question
*/
public class GroupAnagrams {
    /**
     * Time Complexity: O(NKlogK) N is the length of strs K is the maximum length of
     * a string in strs.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String s : strs) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            m.computeIfAbsent(new String(cs), k -> new ArrayList<>()).add(s);
            // initializes the multi-value map. 'new' happens once.
            // computeIfPresent removes the key when the last element of the collection is
            // removed.
        }
        return new ArrayList<>(m.values());
    }

    /* Time Complexity: O(NK) */
    public List<List<String>> groupAnagramsCount(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            for (char ch : s.toCharArray()) {
                count[ch - 'a']++;
            }
            String keyStr = Arrays.toString(count);// more robust, with the ','s, than String.valueOf()
            // int keyHash = Arrays.hashCode(count);
            m.computeIfAbsent(keyStr, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(m.values());
    }

    public static void main(String[] a) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println("sort: " + ga.groupAnagrams(strs));
        System.out.println("count: " + ga.groupAnagramsCount(strs));
    }
}
