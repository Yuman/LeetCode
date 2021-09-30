package Companies.Amazon;

import Libs.ListNode;

/*
Given the head of a singly linked list and two integers left and right 
where left <= right, reverse the nodes of the list from position left to position right, 
and return the reversed list.

https://leetcode.com/problems/reverse-linked-list-ii/
*/
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        int k = 1;
        while (cur.next != null && k != left) {// move two pointer until counting to left
            cur = cur.next;
            pre = pre.next;
            k++;
        }

        while (cur.next != null && k != right) { 
            ListNode preNext = pre.next;
            pre.next = cur.next;
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = preNext;
            k++;
        }

        return dummy.next;
    }
}
