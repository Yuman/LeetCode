package Companies.Amazon;

import java.util.*;

/*
You have some sticks with positive integer lengths.

You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.

Example 1:

Input: sticks = [2,4,3]
Output: 14
Example 2:

Input: sticks = [1,8,3,5]
Output: 30 
*/
public class MinCostToCOnnectSticks {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int stick : sticks) {
            pq.add(stick);
        }

        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            pq.add(first + second);
        }

        return pq.poll();
    }
}
