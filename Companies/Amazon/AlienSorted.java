package Companies.Amazon;

import java.util.Comparator;

/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/
public class AlienSorted {
    public boolean isAlienSorted(String[] words, String order) {
        Comparator<String> c = (o1, o2) -> {
            for (int i = 0; i < o1.length() && i < o2.length(); i++) {
                if (o1.charAt(i) != o2.charAt(i))
                    return order.indexOf(o1.charAt(i)) - order.indexOf(o2.charAt(i));
            }
            return o1.length() - o2.length();
        };
        for (int i = 1; i < words.length; i++) {
            if (c.compare(words[i], words[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String words[] = new String[] { "apple", "app" };
        String order = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(new AlienSorted().isAlienSorted(words, order));
    }
}
