package Companies.Amazon;

import java.util.*;

/*
https://www.youtube.com/watch?v=ymxPZk7TesQ
*/
public class NumberofConnectedComponentsinUndirectedGraph {
    /** Union-find */
    public int countComponentsI(int n, int[][] edges) { // n: number of nodes
        int[] dsu = new int[n];
        for (int i = 0; i < n; i++) {// initial un-merged sets. each node in its own set
            dsu[i] = i;
        }
        for (int[] edge : edges) {
            int x = find(dsu, edge[0]);
            int y = find(dsu, edge[1]);
            if (x != y) {
                dsu[x] = y;
                n--;
            }
        }
        return n;
    }

    private int find(int[] dsu, int x) {
        if (dsu[x] != x) {
            dsu[x] = find(dsu, dsu[x]);
        }
        return dsu[x];
    }

    /** DFS */
    public int countComponentsII(int n, int[][] edges) {
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            l.add(new ArrayList<>());
        }
        for (int[] edge : edges) { // build graph, array index is used as the node id
            // this only works whe nodes are labelled [0..n-1]
            // a map is need otherwise
            l.get(edge[0]).add(edge[1]);
            l.get(edge[1]).add(edge[0]);
        }
        int re = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            re += dfs(l, i, visited);
        }
        return re;
    }

    private int dfs(List<List<Integer>> l, int i, boolean[] visited) {
        if (visited[i]) {
            return 0;
        }
        visited[i] = true;
        for (int neighbor : l.get(i)) {
            dfs(l, neighbor, visited);
        }
        return 1;
    }

    public int countComponentsUF(int n, int[][] edges) {
        int[] ids = new int[n];
        // initially each node a disjointed set
        for (int i = 0; i < n; i++)
            ids[i] = i; // used in parent-check later
        for (int[] edge : edges) {
            union(edge[0], edge[1], ids);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i, ids));
        }
        return set.size();
    }

    private void union(int id1, int id2, int[] ids) {
        // a node is its parent if the ids[i] == i
        int parent1 = find(id1, ids);
        int parent2 = find(id2, ids);
        ids[parent1] = parent2; // initially, ids[i] = i. This makes one the parent of the other
    }

    private int find(int id, int[] ids) { // find its parent
        if (ids[id] != id) {
            ids[id] = find(ids[id], ids); // recursive call does path compression
        }
        return ids[id]; // based case, found a parent
    }

    // Another dfs solution
    // https://www.geeksforgeeks.org/program-to-count-number-of-connected-components-in-an-undirected-graph/

    public int countComponentsStream(int n, String[][] edges) {
        Set<Set<String>> groups = new HashSet<>();
        for (String[] edge : edges) {
            Set<String> group = new HashSet<>();
            group.add(edge[0]);
            group.add(edge[1]);
            groups.add(group);
        }
        Set<Set<String>> merged = mergeSubsets(groups);
        return merged.size();
    }

    private Set<Set<String>> mergeSubsets(Set<Set<String>> groups) {
        Set<Set<String>> result = new HashSet<>();
        for (Set<String> set : groups) {
            // try to find a set in result that intersects this set
            // if one is found, merge the two. otherwise, add this set to result
            result.stream().filter(x -> !Collections.disjoint(x, set)) // intersets a result set, needs union
                    .findAny().ifPresentOrElse( // this method was added in java 9
                            x -> x.addAll(set), () -> result.add(new HashSet<>(set)));
        }

        // if nothing got merged we are done; otherwise, recurse and try again
        return result.size() == groups.size() ? result : mergeSubsets(result);
    }

    public int countComponentsI(int n, String[][] edges) {
        List<Set<String>> groups = new ArrayList<>();
        for (String[] edge : edges) {
            Set<String> group = new HashSet<>();
            group.add(edge[0]);
            group.add(edge[1]);
            groups.add(group);
        }
        // Shrinking merge
        for (int i = 0; i < groups.size() - 1;) {
            int mergeCount = 0;
            for (int j = i + 1; j < groups.size();) {
                if (!Collections.disjoint(groups.get(i), groups.get(j))) {
                    groups.get(i).addAll(groups.get(j));
                    groups.remove(j);
                    ++mergeCount;
                } else // no merge at j
                    j++;
            }
            if (mergeCount == 0) // no merge the whole cycle
                i++;
        }
        return groups.size();
    }
// unionfind implementation
// https://github.com/jgrapht/jgrapht/blob/master/jgrapht-core/src/main/java/org/jgrapht/alg/util/UnionFind.java

    public static void main(String[] a) {
        NumberofConnectedComponentsinUndirectedGraph nc = new NumberofConnectedComponentsinUndirectedGraph();
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        System.out.println("UF by int: " + nc.countComponentsUF(5, edges));
        String[][] es = { { "0", "1" }, { "1", "2" }, { "3", "4" } };
        System.out.println("Set by String: " + nc.countComponentsStream(5, es));
        System.out.println("Set by String forloop: " + nc.countComponentsI(5, es));
    }
}
