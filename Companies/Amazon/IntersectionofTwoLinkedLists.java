package Companies.Amazon;

import Libs.ListNode;

/**
 * Maintain two pointers pA and pB initialized at the head of A and B,
 * respectively. Then let them both traverse through the lists, one node at a
 * time.
 *
 * When pA reaches the end of a list, then redirect it to the head of B (yes, B,
 * that's right.); similarly when pB reaches the end of a list, redirect it the
 * head of A.
 *
 * If at any point pA meets pB, then pA/pB is the intersection node.
 *
 * To see why the above trick would work, consider the following two lists: A =
 * {1,3,5,7,9,11} and B = {2,4,9,11}, which are intersected at node '9'. Since
 * B.length (=4) < A.length (=6), pB would reach the end of the merged list
 * first, because pB traverses exactly 2 nodes less than pA does. By redirecting
 * pB to head A, and pA to head B, we now ask pB to travel exactly 2 more nodes
 * than pA would. So in the second iteration, they are guaranteed to reach the
 * intersection node at the same time.
 *
 * If two lists have intersection, then their last nodes must be the same one.
 * So when pA/pB reaches the end of a list, record the last element of A/B
 * respectively. If the two last elements are not the same one, then the two
 * lists have no intersections.
 *
 * Time complexity: O(m+n). Space complexity: O(1).
 * 
 * XYZABCdefg
 * LMNdefg
 *  
 * XYZABCdefgLMNdefg
 * LMNdefgXYZABCdefg
 */
public class IntersectionofTwoLinkedLists {// parallel-serial walk
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    /*
     * two parallel iterations: In the first iteration, we will reset the pointer of
     * one linkedlist to the head of another linkedlist after it reaches the tail
     * node. In the second iteration, we will move two pointers until they points to
     * the same node. Our operations in first iteration will help us counteract the
     * difference. So if two linkedlist intersects, the meeting point in second
     * iteration must be the intersection point. If the two linked lists have no
     * intersection at all, then the meeting pointer in second iteration must be the
     * tail node of both lists, which is null
     */
    public ListNode getIntersectionNodeCount(ListNode headA, ListNode headB) {

        if (headA == null || headB == null)
            return null;

        int countA = 0;
        ListNode pa = headA;
        while (pa != null) {
            pa = pa.next;
            countA++;
        }

        int countB = 0;
        ListNode pb = headB;
        while (pb != null) {
            pb = pb.next;
            countB++;
        }

        pa = headA;
        pb = headB;

        if (countA > countB) {
            for (int i = 0; i < countA - countB; i++) {
                pa = pa.next;
            }
        } else if (countB > countA) {
            for (int i = 0; i < countB - countA; i++) {
                pb = pb.next;
            }
        }

        while (pa != pb && pa != null) {
            pa = pa.next;
            pb = pb.next;
        }

        return pa;
    }
}