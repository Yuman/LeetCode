package Companies.Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string containing digits from 2-9 inclusive, 
return all possible letter combinations that the number could represent. 
Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. 
Note that 1 does not map to any letters.
*/
public class LetterCombinationsofPhoneNumber {
    private Map<Character, String> m = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        List<String> re = new ArrayList<>();
        if (digits.length() == 0) {
            return re;
        }
        m.put('2', "abc");
        m.put('3', "def");
        m.put('4', "ghi");
        m.put('5', "jkl");
        m.put('6', "mno");
        m.put('7', "pqrs");
        m.put('8', "tuv");
        m.put('9', "wxyz");

        dfs(re, digits, 0, new StringBuilder());
        return re;
    }

    private void dfs(List<String> re, String digits, int start, StringBuilder sb) {
        if (start == digits.length()) {
            re.add(sb.toString());
            return;
        }
        for (char c : m.get(digits.charAt(start)).toCharArray()) {
            sb.append(c);
            dfs(re, digits, start + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static List<String> letterCombinationsI(String digits) {
        String digitletter[] = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> prefix = new ArrayList<String>();

        if (digits.length() == 0)
            return prefix;

        prefix.add("");
        for (int i = 0; i < digits.length(); i++)
            prefix = combineI(digitletter[digits.charAt(i) - '0'], prefix);// add a digit, x3 in O()

        return prefix;
    }

    public static List<String> combineI(String digit, List<String> prefix) {
        List<String> result = new ArrayList<String>(); // looping over prefix gets concurrency error
        // the prefix starts with "". for each digit and each letter/digit, extend+add
        for (int i = 0; i < digit.length(); i++) // x3
            for (String x : prefix)
                result.add(x + digit.charAt(i));

        return result;
    }

    final char[][] L = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' }, { 'j', 'k', 'l' },
            { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

    public List<String> letterCombinationsR(String digits) {
        int len = digits.length();
        List<String> ans = new ArrayList<>();
        if (len == 0)
            return ans;
        dfsR(0, len, new StringBuilder(), ans, digits);
        return ans;
    }

    public void dfsR(int pos, int len, StringBuilder sb, List<String> ans, String digits) {
        if (pos == len)
            ans.add(sb.toString());
        else {
            char[] letters = L[Character.getNumericValue(digits.charAt(pos))];
            for (int i = 0; i < letters.length; i++)
                dfsR(pos + 1, len, new StringBuilder(sb).append(letters[i]), ans, digits);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinationsI(digits));
    }
}
