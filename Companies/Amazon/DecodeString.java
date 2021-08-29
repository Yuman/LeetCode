package Companies.Amazon;

import java.util.LinkedList;
import java.util.Queue;
/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
*/
public class DecodeString {
    public String decodeString(String s) {
        Queue<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            q.offer(c);
        }
        return helper(q);
    }

    private String helper(Queue<Character> q) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!q.isEmpty()) {
            char c = q.poll();
            if (Character.isDigit(c)) {
                num = num*10 + c-'0';
            } else if (c == '[') {
                String inner = helper(q);
                for (int i = 0; i < num; i++) {
                    sb.append(inner);
                }
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private int i = 0;

    private String decodeStringII(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num*10 + c-'0';
            } else if (c == '[') {
                i++;
                String after = decodeStringII(s);
                for (int k = 0; k < num; k++) {
                    sb.append(after);
                }
                num = 0;
            } else if (c == ']') {
                return sb.toString();
            } else {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }
}
