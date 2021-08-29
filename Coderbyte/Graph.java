package Coderbyte;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T extends GraphNode<?>> {
    private final List<GraphNode<?>> mNodes;

    public Graph() {
        mNodes = new ArrayList<>();
    }

    public Graph(List<GraphNode<?>> aNodes) {
        mNodes = aNodes;
    }

    public void add(GraphNode<?> aNode) {
        mNodes.add(aNode);
    }

    public List<GraphNode<?>> getNodes() {
        return mNodes;
    }

    public String toString() {
        return "Graph{" + "nodes=" + mNodes + '}';
    }

    public static void bfs(GraphNode<?> aHead, GraphNode<?> target) {
        Queue<GraphNode<?>> q = new ArrayDeque<>();
        Set<GraphNode<?>> visited = new HashSet<>();
        q.offer(aHead);
        visited.add(aHead);
        // int level = 0;
        while (!q.isEmpty()) {
            // System.out.printf("level %sn", level++);
            for (int i = 0; i < q.size(); i++) {
                GraphNode<?> n = q.poll();
                System.out.println("Visiting >> " + n);
                if (n.value() == target.value()) {
                    return;
                }
                n.getAdjacentNodes().stream().filter(aNode -> !visited.contains(aNode)).forEach(aNode -> {
                    visited.add(aNode);
                    q.offer(aNode);
                });
            }
        }
    }

    public static List<GraphNode<?>> shortestPath(GraphNode<?> aStart, GraphNode<?> aTarget) {
        Map<GraphNode<?>, GraphNode<?>> traceback = new HashMap<>();
        Queue<GraphNode<?>> q = new ArrayDeque<>();
        q.offer(aTarget);
        traceback.put(aTarget, null); // traceback has (to, from)
        while (!q.isEmpty()) {
            GraphNode<?> curr = q.poll();
            curr.getAdjacentNodes().stream().filter(aNode -> !traceback.containsKey(aNode)).forEach(aNode -> {
                traceback.put(aNode, curr);
                q.offer(aNode);
            });
            if (curr.value() == aStart.value()) {
                return reconstructPath(traceback, aStart, aTarget);
            }
        }
        return null;
    }

    private static List<GraphNode<?>> reconstructPath(Map<GraphNode<?>, GraphNode<?>> aTraceback, GraphNode<?> aStart,
            GraphNode<?> aTarget) {
        List<GraphNode<?>> path = new ArrayList<>();
        GraphNode<?> curr = aStart;
        while (curr != null) {
            path.add(curr);
            curr = aTraceback.get(curr);
        }
        return path;
    }
}
