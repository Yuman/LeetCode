package Companies.Amazon;

public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 1 || num > nums[i - 1]) {
                nums[i++] = num;
            }
        }
        return i;
    }

    public int removeDuplicatesI(int[] nums) {
        int tail = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[tail])  // pick the uniques
                nums[++tail] = nums[i];
        }
        return tail + 1;
    }


    /* duplicates appeared at most twice */
    public int removeDuplicatesII(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i - 2]) {
                nums[i++] = num;
            }
        }
        return i;
    }
}
