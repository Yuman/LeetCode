package Companies.Google;

import Libs.ListNode;

import java.util.Random;

/**
 * Reservoir Sampling
 * Problem:
 * Choose k entries from n numbers. Make sure each number is selected with the probability of k/n
 *
 * Basic idea:
 * Choose 1, 2, 3, ..., k first and put them into the reservoir.
 * For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
 * For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
 * Repeat until k+i reaches n
 *
 * Proof:
 * For k+i, the probability that it is selected and will replace a number in the reservoir is k/(k+i)
 * For a number in the reservoir before (let's say X), the probability that it keeps staying in the reservoir is
 * P(X was in the reservoir last time) × P(X is not replaced by k+i)
 * = P(X was in the reservoir last time) × (1 - P(k+i is selected and replaces X))
 * = k/(k+i-1) × （1 - k/(k+i) × 1/k）
 * = k/(k+i)
 * When k+i reaches n, the probability of each number staying in the reservoir is k/n
 *
 * Example:
 * Choose 3 numbers from [111, 222, 333, 444]. Make sure each number is selected with a probability of 3/4
 * First, choose [111, 222, 333] as the initial reservoir
 * Then choose 444 with a probability of 3/4
 * For 111, it stays with a probability of
 * P(444 is not selected) + P(444 is selected but it replaces 222 or 333)
 * = 1/4 + 3/4*2/3
 * = 3/4
 * The same case with 222 and 333
 * Now all the numbers have the probability of 3/4 to be picked
 *
 */
public class LinkedListRandomNode {

    ListNode root;
    private Random rand = new Random();

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        root = head;
    }

    /** Returns Companies.Amazon random node's value. */
    public int getRandom() {
        int re = root.val;
        ListNode cur = root.next;
        int i = 1;
        while (cur != null) {
            double r = rand.nextDouble();
            double p = 1.0/(1+i);
            if (r < p) {
                re = cur.val;
            }
            i++;
            cur = cur.next;
        }
        return re;
    }
}
