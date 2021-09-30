package Companies.Amazon;

public class MissingNumber {
    /*
     * The basic idea is to use XOR operation. We all know that a^b^b =a, which
     * means two xor operations with the same number will eliminate the number and
     * reveal the original number. In this solution, I apply XOR operation to both
     * the index and value of the array. In a complete array with no missing
     * numbers, the index and value should be perfectly corresponding( nums[index] =
     * index), so in a missing array, what left finally is the missing number.
     */
    public int missingNumber(int[] nums) {
        int re = nums.length;
        for (int i = 0; i < nums.length; i++) {
            re ^= i ^ nums[i];
        }
        return re;
    }

    /*
     * Given an array nums containing n distinct numbers in the range [0, n], return
     * the only number in the range that is missing from the array.
     * 
     * Follow up: Could you implement a solution using only O(1) extra space
     * complexity and O(n) runtime complexity?
     * 
     * Example 1:
     * 
     * Input: nums = [3,0,1] Output: 2 Explanation: n = 3 since there are 3 numbers,
     * so all numbers are in the range [0,3]. 2 is the missing number in the range
     * since it does not appear in nums.
     */
    public int missingNumber2(int[] nums) {
        int[] si = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            si[nums[i]] = nums[i];
        }
        for (int i = 0; i <= nums.length; i++) {
            if (si[i] != i)
                return i;
        }
        return 0;
    }

    public int missingNumber22(int[] nums) {
        int sum = 0;
        int len = nums.length + 1;
        for (int n : nums)
            sum += n;
        return ((len * (len - 1)) / 2 - sum);

    }

    public int missingNumberMath(int[] nums) {  // the best
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++)
            sum += i - nums[i];
        return sum;
    }

    public static void main(String[] a) {
        MissingNumber mn = new MissingNumber();
        System.out.println(mn.missingNumber2(new int[] { 3, 0, 1 }));
    }
}
