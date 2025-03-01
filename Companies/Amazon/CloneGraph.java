package Companies.Amazon;

import Libs.Node;

import java.util.*;
// https://leetcode.com/problems/clone-graph/
/**
 * @see CopyListwithRandomPointer
 */
public class CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Node neigh : cur.neighbors) {
                if (!visited.containsKey(neigh)) {
                    visited.put(neigh, new Node(neigh.val, new ArrayList<>()));
                    q.offer(neigh);
                }
                visited.get(cur).neighbors.add(visited.get(neigh));
            }
        }
        return visited.get(node);
    }



    public Node cloneGraphDfs(Node node) {
        if (node == null)
        return null;
        HashMap<Integer, Node> visited = new HashMap<>();
        return cloneDfs(node, visited);
    }

    private Node cloneDfs(Node node, Map<Integer, Node> visited) {
        if (visited.containsKey(node.val))            // 1. Base case: check for shortcircuit
            return visited.get(node.val);

        Node copy = new Node(node.val, new ArrayList<Node>());  // copy self
        visited.put(copy.val, copy);                            // track self
        for (Node neighbor : node.neighbors)                    // recurse neighbors. Check/copy/track/Recurse
            copy.neighbors.add(cloneDfs(neighbor, visited));
        return copy;
    }
}
