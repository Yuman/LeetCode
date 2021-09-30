package Companies.Bloomberg;

import java.util.Arrays;
/*
Given an array of intervals intervals 
where intervals[i] = [starti, endi], return the minimum number of intervals 
you need to remove to make the rest of the intervals non-overlapping.


Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
*/

/**
 * @see AvailableTime
 * @see EmployeeFreeTime
 * @see MaximumLengthofPairChain
 * @see MeetingRooms
 * @see MergeIntervals
 * @see RemoveArrayElementsinGivenIndexRanges
 */
public class NonoverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        if (intervals == null || intervals.length == 0) {
            return count;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prev[1]) {
                count++;
                prev[1] = Math.min(prev[1], intervals[i][1]);
            } else {
                prev = intervals[i];
            }
        }
        return count;
    }

    public int eraseOverlapIntervalsII(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int max = 0, lastend = Integer.MIN_VALUE;
        for (int[] inv : intervals) {
            if (lastend <= inv[0]) {
                max++;
                lastend = inv[1];
            }
        }
        return intervals.length - max;
    }
}
