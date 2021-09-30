package Companies.GfG;

/**
 * A String is said to be a rotation of another String, if it has the same
 * length, contains the same characters, and they were rotated around one of the
 * characters. For example, String"bcda" is a rotation of "abcd" but "bdca" is
 * not a rotation of String "abcd". One of the simplest solutions to this
 * interesting problem is first to check if two String has the same length, if
 * not then one String cannot be the rotation of another. If they are of the
 * same length then just create another String by concatenating first String
 * with itself, now check if second String is a substring of this concatenated
 * String or not, if yes, the second String is a rotation of first.
 * 
 * https://leetcode.com/problems/rotate-string/
 */
public class RotatedString {
    boolean checkRotation(String s1, String s2) {
        /* Comparing and checking string lengths */
        if (s1.length() != s2.length())
            return false;

        String temp = s1 + s1; // storing concatenated string

        if (temp.indexOf(s2) != -1) {
            return true; // returning true if 2nd string is present in concatenated string
        } else {
            return false;
        }
    }

    boolean isRotated(String s1, String s2) { // O(N), fast, beats 100% java solutions
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                if (rotateString(s2, s1, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rotateString(String A, String B) {// naive n^2 solution
        if (A == null || B == null) {
            // throw exception on A and B both being null?
            return false;
        }
        if (A.length() != B.length()) {
            return false;
        }
        if (A.length() == 0) {
            return true;
        }
        for (int i = 0; i < A.length(); i++) {
            if (rotateString(A, B, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean rotateString(String A, String B, int rotation) {
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt((i + rotation) % B.length())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "rotatestring";
        String b = "ringrotatest";
        RotatedString rs = new RotatedString();
        System.out.println(rs.checkRotation(a, b));
        System.out.println(rs.isRotated(a, b));
        System.out.println(rs.rotateString(a, b));

    }
}
