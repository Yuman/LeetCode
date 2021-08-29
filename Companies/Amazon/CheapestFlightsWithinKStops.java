package Companies.Amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/cheapest-flights-within-k-stops/
// https://medium.com/swlh/graph-dynamic-programming-heap-cheapest-flights-within-k-stops-e622ce956479

public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> flightsMap = new HashMap<>();
        for (int[] flight : flights) { // builds [src, [dst, prc]] graph
            flightsMap.computeIfAbsent(flight[0], k -> new HashMap<>()).put(flight[1], flight[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] { 0, src, K });
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int price = cur[0], position = cur[1], step = cur[2];
            if (position == dst) {
                return price;
            }
            if (step >= 0 && flightsMap.containsKey(position)) {
                Map<Integer, Integer> next = flightsMap.get(position);
                for (int np : next.keySet()) {
                    pq.add(new int[] { price + next.get(np), np, step - 1 });
                }
            }
        }
        return -1;
    }
    
    // Dijkshtra with Seen HashMap to Reduce Time Cost
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        // this is used to store node to node information
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        for (int[] flight : flights) {
            map.putIfAbsent(flight[0], new HashMap<>());
            map.get(flight[0]).put(flight[1], flight[2]);
        }

        // a[0] is the price, a[1] is the dst, a[2] is the stops
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // use the seen hashmap to reduce the time cost
        HashMap<Integer, int[]> seen = new HashMap<>(); // new add

        pq.add(new int[] { 0, src, k + 1 });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int currPrice = curr[0];
            int currPosition = curr[1];
            int currStop = curr[2];

            // if we have visit the curr position and the cost and stops of the rount in the
            // hashmap is better, we just ignore the current rount
            if (seen.containsKey(currPosition) && seen.get(currPosition)[0] < currPrice
                    && seen.get(currPosition)[1] >= currStop)
                continue; // new add

            if (currPosition == dst) {
                return currPrice;
            }

            // add the new seen rount into the seen hashmap
            seen.put(currPosition, new int[] { currPrice, currStop }); // new add

            if (currStop > 0) {
                if (map.containsKey(currPosition)) {
                    for (int i : map.get(currPosition).keySet()) {
                        pq.add(new int[] { currPrice + map.get(currPosition).get(i), i, currStop - 1 });
                    }
                }
            }

        }

        return -1;
    }
}
