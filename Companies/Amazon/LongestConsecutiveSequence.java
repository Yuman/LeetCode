package Companies.Amazon;

import java.util.HashSet;
import java.util.Set;

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/
public class LongestConsecutiveSequence {
    /**
     * First turn the input into a set of numbers. That takes O(n) and then we can
     * ask in O(1) whether we have a certain number.
     * 
     * Then go through the numbers. If the number x is the start of a streak (i.e.,
     * x-1 is not in the set), then test y = x+1, x+2, x+3, ... and stop at the
     * first number y not in the set. The length of the streak is then simply y-x
     * and we update our global best with that. Since we check each streak only
     * once, this is overall O(n).
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int re = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int len = 0;
                while (set.contains(num)) {
                    len++;
                    num++;
                }
                re = Math.max(re, len);
            }
        }
        return re;
    }
}
