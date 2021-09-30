package Topics.Design;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Design a hit counter which counts the number of hits received in the past 5
 * minutes.
 * 
 * Each function accepts a timestamp parameter (in seconds granularity) and you
 * may assume that calls are being made to the system in chronological order
 * (ie, the timestamp is monotonically increasing). You may assume that the
 * earliest timestamp starts at 1.
 * 
 * It is possible that several hits arrive roughly at the same time.
 * 
 * https://www.geeksforgeeks.org/design-a-hit-counter/
 * 
 * Note: the time-dimension is lacking in moving-average. the ts[] is needed.
 */
public class HitCounter {
    private Deque<int[]> q;
    private int size;

    /** Initialize your data structure here. */
    public HitCounter() {
        q = new ArrayDeque<>();
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        if (q.isEmpty() || q.peekLast()[0] != timestamp) {
            q.offer(new int[] { timestamp, 1 });
        } else {
            q.peekLast()[1]++;
        }
        size++;
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while (!q.isEmpty() && q.peekFirst()[0] + 300 <= timestamp) {
            size -= q.pollFirst()[1];
        }
        return size;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such: HitCounterA
 * obj = new HitCounterA(); obj.hit(timestamp); int param_2 =
 * obj.getHits(timestamp);
 * 
 * https://massivealgorithms.blogspot.com/2016/08/leetcode-362-design-hit-counter.html
 * 
 * Note: the time-dimension is lacking in moving-average. the ts[] is needed.
 */
class HitCounterA {

    int[] ts;
    int[] hits;

    /** Initialize your data structure here. */
    public HitCounterA() {
        ts = new int[300];
        hits = new int[300];
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        int i = timestamp % 300;
        if (ts[i] != timestamp) { // a new cycle starts. overriding previous data
            ts[i] = timestamp;
            hits[i] = 1;
        } else {
            hits[i]++;
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int result = 0;
        for (int i = 0; i < hits.length; i++) {
            if (timestamp - ts[i] < 300) {
                result += hits[i];
            }
        }
        return result;
    }
}