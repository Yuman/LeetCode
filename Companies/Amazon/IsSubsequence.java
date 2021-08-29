package Companies.Amazon;

/**
 * 
 * Given two strings s and t, return true if s is a subsequence of t, or false
 * otherwise.
 * 
 * A subsequence of a string is a new string that is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (i.e., "ace" is a
 * subsequence of "abcde" while "aec" is not).
 * 
 * @see Companies.Bloomberg.CharInStrAtoBuildStrB
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        for (int idxT = 0, idxS = 0; idxT < t.length(); idxT++) {
            if (s.charAt(idxS) == t.charAt(idxT)) {
                idxS++;
                if (idxS == s.length()) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean isSubsequence2(String s, String t) {
        int posT = 0, hits = 0;
        for (int idxS = 0; idxS < s.length(); idxS++) {            
            for (int idxT = posT; idxT < t.length(); ++idxT) {
                if (s.charAt(idxS) == t.charAt(idxT)) {
                    if (++hits == s.length()) {
                        return true;
                    }  
                    posT = ++idxT;                  
                    break;
                }                
            }
        }
        return false;
    }

    public static void main(String[] a) {
        IsSubsequence is = new IsSubsequence();
        // System.out.println("big loop ace/abcde: " + is.isSubsequence("ace", "abcde"));
        // System.out.println("small loop ace/abcde: " + is.isSubsequence2("ace", "abcde"));
        System.out.println("big loop aec: " + is.isSubsequence("aec", "abcde"));
        System.out.println("small loop aec: " + is.isSubsequence2("aec", "abcde"));
    }
}