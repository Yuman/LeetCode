package Companies.Amazon;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * 
 * Note that you must do this in-place without making a copy of the array.
 */
public class MoveZeroes {
    /* Move zeros to right */
    public void moveZeroes(int[] nums) {
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != pos) {
                    nums[pos] = nums[i];
                    nums[i] = 0;
                }
                pos++;
            }
        }
    }

    /* Move zeros to left */
    public void moveZerosII(int[] nums) {
        int index = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != 0) {
                int temp = nums[index];
                nums[index--] = nums[i];
                nums[i] = temp;
            }
        }
    }
}
