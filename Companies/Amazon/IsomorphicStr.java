package Companies.Amazon;

/*
Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true

The idea is that we need to map a char to another one, for example, "egg" and "add", 
we need to constract the mapping 'e' -> 'a' and 'g' -> 'd'. 
Instead of directly mapping 'e' to 'a', another way is to mark them with same value, 
for example, 'e' -> 1, 'a'-> 1, and 'g' -> 2, 'd' -> 2, this works same.

So we use two arrays here m1 and m2, initialized space is 256 
(Since the whole ASCII size is 256, 128 also works here). 
Traverse the character of both s and t on the same position, 
if their mapping values in m1 and m2 are different, 
means they are not mapping correctly, returen false; else we construct the mapping, 
since m1 and m2 are both initialized as 0, we want to use a new value when i == 0, 
so i + 1 works here.

*/
public class IsomorphicStr {
    boolean isIsomorphic(String s, String t) {
        int m1[] = new int[256], m2[] = new int[256], n = s.length();
        for (int i = 0; i < n; ++i) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}
