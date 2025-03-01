package Companies.Amazon;

import java.util.ArrayList;
import java.util.List;
/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.
*/
/**
 * @see LinkedListCycle
 */
public class FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        int head = nums[0];
        while (head != slow) {
            head = nums[head];
            slow = nums[slow];
        }
        return head;
    }

    /* Warning: Modify the array */
    public int findDuplicateII(int[] nums) {
        for (int num : nums) {
            int index = Math.abs(num)-1;  // mark with '-', ignore with Math.abs()
            if (nums[index] < 0) {
                return index+1;
            }
            nums[index] = -nums[index];
        }
        return 0;
    }

    /* Find all duplicates */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> l = new ArrayList<>();
        for (int num : nums) {
            int index = Math.abs(num)-1;
            if (nums[index] < 0) {
                l.add(index+1);
            }
            nums[index] = -nums[index];  // using minus sign '-' as a marker; Math.abs() to neutralize it
        }
        return l;
    }
}
