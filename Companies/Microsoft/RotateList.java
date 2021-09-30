package Companies.Microsoft;

import Libs.ListNode;

/*
Given the head of a linked list, rotate the list to the right by k places.

Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

*/
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        int len = 0;
        while (fast.next != null) {  // count len
            fast = fast.next;
            len++;
        }
        k = len - k % len;
        while (k > 0) {    // walk to cutting point
            slow = slow.next;
            k--;
        }
        fast.next = dummy.next;  // connectextend tail with old head
        dummy.next = slow.next;  // after cutting, new head
        slow.next = null;       // new tail
        return dummy.next;
    }
}
