package Companies.Amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AlienDictionary {
    /*
     * topological sort 
     * 
     * Given a sorted dictionary (array of words) of an alien
     * language, find order of characters in the language. That is, to construct the
     * alphabet.
     * 
     * Examples:
     * 
     * Input: words[] = {"baa", "abcd", "abca", "cab", "cad"} Output: Order of
     * characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given
     * language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
     * Similarly we can find other orders.
     * 
     * Input: words[] = {"caa", "aaa", "aab"} Output: Order of characters is 'c',
     * 'a', 'b'
     */
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return "";
        Map<Character, Set<Character>> m = new HashMap<>();
        // build a map of set keyed on letters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                m.putIfAbsent(c, new HashSet<>());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            int j = 0;
            while (j < words[i].length() && j < words[i + 1].length()) {
                char a = words[i].charAt(j);
                char b = words[i + 1].charAt(j);
                if (a != b) {
                    m.get(a).add(b); // build the follower set for each letter
                    break;
                }
                j++;
            }
            if (j < words[i].length() && j == words[i + 1].length()) { // ["abc", "ab"]
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        int[] visited = new int[26];
        for (char c : m.keySet()) {
            if (visited[c - 'a'] == 0) {
                if (helper(c, sb, m, visited) == -1) {
                    return "";
                }
            }
        }
        return sb.toString();
    }

    private int helper(char c, StringBuilder sb, Map<Character, Set<Character>> m, int[] visited) {
        if (visited[c - 'a'] != 0) {
            return visited[c - 'a'];
        }
        visited[c - 'a'] = -1;
        for (char next : m.get(c)) {
            if (helper(next, sb, m, visited) == -1) {
                return -1;
            }
        }
        visited[c - 'a'] = 1;
        sb.insert(0, c);
        return 1;
    }
}
