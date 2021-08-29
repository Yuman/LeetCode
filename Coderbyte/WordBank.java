package Coderbyte;

public class WordBank {
    /**
     * canConstruct
     */
    public boolean canConstruct(String target, String[] wordBank) {
        if (target.length() == 0)
            return true;

        for (String w : wordBank) {
            if (target.startsWith(w)) {
                String rightEnd = target.replaceFirst(w, "");
                if (canConstruct(rightEnd, wordBank)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int countConstruct(String target, String[] wordBank) {
        if (target.length() == 0)
            return 1;
        int total = 0;
        for (String w : wordBank) {
            if (target.startsWith(w)) {
                String rightEnd = target.replaceFirst(w, "");
                total += countConstruct(rightEnd, wordBank);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        WordBank bank = new WordBank();
        String[] words = new String[] { "ska", "te", "ard", "bo" };
        boolean res = bank.canConstruct("skateboard", words);
        System.out.println(res);

        words = new String[]{"purp", "p", "ur", "le","purl"};
        int count = bank.countConstruct("purple", words);
        System.out.println(count);

    }
}
