package Companies.Google;

import java.util.*;

/**
 * You are given a network of n nodes, labeled from 1 to n. You are also given
 * times, a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node, vi is the target node, and wi is the time it
 * takes for a signal to travel from source to target.
 * 
 * We will send a signal from a given node k. Return the time it takes for all
 * the n nodes to receive the signal. If it is impossible for all the n nodes to
 * receive the signal, return -1.
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> m = new HashMap<>();
        boolean[] marks = new boolean[N + 1];
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int[] time : times) {
            m.computeIfAbsent(time[0], k -> new ArrayList<>()).add(new int[] { time[1], time[2] });
        }
        dist[K - 1] = 0;
        while (true) {
            int cur = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                if (dist[i - 1] < min && !marks[i]) {
                    min = dist[i - 1];
                    cur = i;
                }
            }
            if (cur == -1) {
                break;
            }
            marks[cur] = true;
            if (m.get(cur) != null) {
                for (int[] nei : m.get(cur)) {
                    int target = nei[0];
                    int dis = nei[1];
                    if (!marks[target]) {
                        dist[target - 1] = Math.min(dist[target - 1], dis + dist[cur - 1]);
                    }
                }
            }
        }
        int re = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            re = Math.max(re, dist[i]);
        }
        return re;
    }

    // Dijkstra's algorithm
    // https://leetcode.com/problems/network-delay-time/discuss/210698/Java-Djikstrabfs-Concise-and-very-easy-to-understand
    public int networkDelayTimePq(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(); // {src, {target, time}}
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        // distance, node into pq, tracking [time, node]
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));

        pq.add(new int[] { 0, K });

        boolean[] visited = new boolean[N + 1];
        int res = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.remove();
            int curNode = cur[1];
            int curDist = cur[0];
            if (visited[curNode])
                continue;
            visited[curNode] = true;
            res = curDist;
            N--;
            if (map.containsKey(curNode)) {
                for (int next : map.get(curNode).keySet()) {
                    pq.add(new int[] { curDist + map.get(curNode).get(next), next });
                }
            }
        }
        return N == 0 ? res : -1;

    }

    public static void main(String[] args) {
        int[][] times = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
        int n = 4, k = 2;
        // Output: 2
        NetworkDelayTime ndt = new NetworkDelayTime();
        System.out.println(ndt.networkDelayTimePq(times, n, k));
    }
}
