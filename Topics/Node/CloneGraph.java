package Topics.Node;

import Libs.Node;

import java.util.*;
// https://leetcode.com/problems/clone-graph/

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

    private HashMap<Integer, Node> visited = new HashMap<>();

    public Node cloneGraphDfs(Node node) {
        if (node == null)
        return null;
        return cloneDfs(node);
    }

    private Node cloneDfs(Node node) {
        if (visited.containsKey(node.val))
            return visited.get(node.val);

        Node newNode = new Node(node.val, new ArrayList<Node>());
        visited.put(newNode.val, newNode);
        for (Node neighbor : node.neighbors)
            newNode.neighbors.add(cloneDfs(neighbor));
        return newNode;
    }
}
