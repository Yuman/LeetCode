package Companies.Amazon;

import Libs.ListNode;

public class ReverseLinkedList {
    /* Iterative */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nxt = head.next; // set up: working set has 3 nodes: prev - head - nxt. 
            head.next = prev;  // flip head to tail
            prev = head;  // tandem move hind leg
            head = nxt;   // move head forward. at the end, head==null==nxt
        }
        return prev;
    }

    /* Recursive */
    public ListNode reverseListR(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseListR(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /* Between m-n */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode re = new ListNode(0);
        re.next = head;
        ListNode pre = re;
        ListNode cur = pre.next;
        int i = 1;
        while (i < m) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        ListNode node = pre;
        while (i <= n) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            i++;
        }
        node.next.next = cur;
        node.next = pre;
        return re.next;
    }
}
