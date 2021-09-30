package Companies.Bloomberg;

import java.util.Arrays;

public class LongestCommonPrefix {
    /* O(S), where S is the sum of all characters in all strings. */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String re = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(re) != 0) {  // not a prefix? shrink the prefix
                re = re.substring(0, re.length() - 1);
            }
        }
        return re;
    }

    public String longest(String[] strs) {
        if (strs.length == 0)
            return "";

        int minlength = strs[0].length();

        // find the min length of strings
        for (String s : strs) {
            if (s.length() < minlength) {
                minlength = s.length();
            }
        }

        for (int i = 0; i < strs.length - 1; i++) {
            for (int k = 0; k <= minlength - 1; k++) {
                if (strs[i].charAt(k) != strs[i + 1].charAt(k)) {
                    minlength = k;
                    break;
                }
            }
        }

        return strs[0].substring(0, minlength);
    }

    public String longestSub(String[] strs) {
        if (strs.length == 0)
            return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (!strs[i].startsWith(pre)) // chop off prefix until it is a prefix to the current word
                pre = pre.substring(0, pre.length() - 1);
        return pre;
    }

    public String longestComp(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        Arrays.sort(strs);
        int i = 0;
        int minLen = Math.min(strs[0].length(), strs[strs.length - 1].length());
        while (i < minLen && strs[0].charAt(i) == strs[strs.length - 1].charAt(i))
            i++;
        return strs[0].substring(0, i);
    }

    public static void main(String[] a) {
        String[] s = { "flower", "flow", "flight" };
        LongestCommonPrefix prefix = new LongestCommonPrefix();
        System.out.println("longest: " + prefix.longest(s));
        System.out.println("longestSub: " + prefix.longestSub(s));
        System.out.println("longestComp: " + prefix.longestComp(s));
    }
}
