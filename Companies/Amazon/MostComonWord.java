package Companies.Amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.*;

/*

*/
public class MostComonWord {
    public String mostCommonWord(String p, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        String[] words = p.replaceAll("\\W+", " ").toLowerCase().split("\\s+");
        for (String w : words)
            if (!ban.contains(w))
                count.put(w, count.getOrDefault(w, 0) + 1);
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public String mostCommonWord2(String paragraph, String[] banned) {
        String[] words = paragraph.toLowerCase().split("[ !?',;.]+");
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (String word : banned)
            if (!set.contains(word))
                set.add(word);
        String hottest = null;
        for (String word : words) {
            if (set.contains(word))
                continue;
            map.put(word, map.getOrDefault(word, 0) + 1);
            if (hottest == null || map.get(word) > map.get(hottest))
                hottest = word; // this runs a read at each iteration. 
                //same as another loop
        }
        return hottest;
    }

    public static void main(String[] a) {

    }
}
