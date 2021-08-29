package Companies.Bloomberg;
// https://www.youtube.com/watch?v=CYnvDzMprdc

/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, 
find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit 
from node i (i.e., there is a directed edge from node i to node graph[i][j]).

Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
*/

import java.util.*;

public class AllPathsFromSourcetoTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> re = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        cur.add(0);
        dfs(re, cur, 0, graph);
        return re;
    }

    private void dfs(List<List<Integer>> re, List<Integer> cur, int p, int[][] graph) {
        if (p == graph.length - 1) {
            re.add(new ArrayList<>(cur));
            return;
        }
        for (int next : graph[p]) {
            cur.add(next);
            dfs(re, cur, next, graph);
            cur.remove(cur.size() - 1);
        }

    }

    /**
     * @see ReconstructItinerary
     */
    public static List<List<Character>> getPossibleRoutes(char[][] chars, char start, char end) {
        Map<Character, Set<Character>> map = new HashMap<>();
        for (char[] cs : chars) {
            map.putIfAbsent(cs[0], new HashSet<>());
            map.putIfAbsent(cs[1], new HashSet<>());
            map.get(cs[0]).add(cs[1]);
            map.get(cs[1]).add(cs[0]);
        }
        List<List<Character>> res = new ArrayList<>();
        Set<Character> visited = new HashSet<>();
        visited.add(start);
        List<Character> cur = new ArrayList<>();
        cur.add(start);
        dfs(map, visited, res, cur, start, end);
        return res;
    }

    private static void dfs(Map<Character, Set<Character>> map, Set<Character> visited, List<List<Character>> res,
            List<Character> l, char cur, char end) {
        if (cur == end) {
            res.add(new ArrayList<>(l));
            return;
        }
        for (char nei : map.getOrDefault(cur, new HashSet<>())) {
            if (!visited.contains(nei)) {
                l.add(nei);
                visited.add(nei);
                dfs(map, visited, res, l, nei, end);
                l.remove(l.size() - 1);
                visited.remove(nei);
            }
        }
    }

    public List<List<Integer>> allPathsSourceTargetBFS(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<List<Integer>> que = new LinkedList<>();
        que.add(Arrays.asList(0)); // add node 0
        int goalNode = graph.length - 1; // the last node (adj-list carrier)
        while (!que.isEmpty()) {
            List<Integer> path = que.poll(); // this is
            int lastNode = path.get(path.size() - 1);
            if (lastNode == goalNode) { // only need to check the last that was added to the path.
                res.add(new ArrayList<>(path));
            } else {
                int[] neighbors = graph[lastNode];
                for (int neighbor : neighbors) { // only need to check node0 and bfs its neighbors
                    List<Integer> li = new ArrayList<>(path);
                    li.add(neighbor); // grow the path
                    que.add(li);
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        // char[][] chars = {{'A', 'B'}, {'A', 'C'}, {'A', 'D'}, {'B', 'C'}, {'B',
        // 'D'}};
        // char start = 'C', end = 'D';
        // System.out.println(getPossibleRoutes(chars, start, end));
        AllPathsFromSourcetoTarget paths = new AllPathsFromSourcetoTarget();
        List<List<Integer>> res = paths.allPathsSourceTargetBFS(new int[][] { { 1, 2 }, { 3 }, { 3 }, {} });
        System.out.println(res);
    }
}
