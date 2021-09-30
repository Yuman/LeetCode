package Companies.Amazon;

/*
Given an array of integers nums sorted in ascending order, find the starting and 
ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.
*/
public class FindFirstAndLastPositionOfElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] re = { -1, -1 };
        int l = binary(nums, target, true);
        if (l == nums.length || nums[l] != target) {
            return re;
        }
        re[0] = l;
        re[1] = binary(nums, target, false) - 1;
        return re;
    }

    private int binary(int[] nums, int target, boolean left) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target || nums[mid] == target && left) {
                r = mid;  // for left, squeeze on the right to push to the left end
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
