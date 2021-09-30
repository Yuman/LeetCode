package Companies.Amazon;

import Libs.Node;

import java.util.HashMap;
import java.util.Map;

/*
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

@see CloneGraph
*/
public class CopyListwithRandomPointer {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node copy = head;
        while (copy != null) {
            Node newone = new Node(copy.val);
            newone.next = copy.next;
            copy.next = newone;
            copy = newone.next;
        }
        copy = head;
        while (copy != null) {
            copy.next.random = copy.random == null ? null : copy.random.next;
            copy = copy.next.next;
        }
        Node re = head.next;
        Node newhead = head.next;
        Node oldhead = head;
        while (oldhead != null) {
            oldhead.next = oldhead.next.next;
            newhead.next = newhead.next == null ? null : newhead.next.next;
            oldhead = oldhead.next;
            newhead = newhead.next;
        }
        return re;
    }

    private Map<Node, Node> visited = new HashMap<>();

    public Node copyRandomListII(Node head) {
        if (head == null) {
            return null;
        }
        if (visited.containsKey(head)) { // base case: shortcircuit
            return visited.get(head);
        }
        Node node = new Node(head.val); // copy self
        visited.put(head, node); // track self
        node.next = copyRandomListII(head.next); // recurse kins.  Check/copy/track/Recurse
        node.random = copyRandomListII(head.random);
        return node;
    }

    public Node copy(Node head) {// clone simple linkedlist with recursion
        if (head == null)
            return null;
        Node newHead = new Node(head.val);
        newHead.next = copy(head.next);
        return newHead;
    }
}
