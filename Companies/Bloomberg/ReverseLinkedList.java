package Companies.Bloomberg;

import Libs.ListNode;

public class ReverseLinkedList {
    /* Iterative */
    public ListNode reverseList(ListNode head) {
        ListNode re = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = re;
            re = head;
            head = temp;
        }
        return re;
    }

    public ListNode reverseList3(ListNode head) {
        if (head == null) {
            return null;
        }
//3 pointers, in-place
        ListNode prev = null;
        ListNode current = head;
        ListNode nxt = null;
        while (current != null) {
            nxt = current.next;  //move front leg to set up 3 pointers
            current.next = prev; //flip back
            prev = current;  //move forward
            current = nxt;   //move forward
        }
        head = prev;   // current is already null
        return head;
    }

    /* Recursive */
    public ListNode reverseListII(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseListII(head.next);
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
