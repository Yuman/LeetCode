package Companies.Microsoft;

import java.util.Arrays;

/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
*/
public class RotateArray {
    /*
     * nums = "----->-->"; k =3 result = "-->----->";
     * 
     * reverse "----->-->" we can get "<--<-----" reverse "<--" we can get
     * "--><-----" reverse "<-----" we can get "-->----->" this visualization help
     * me figure it out :)
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length; // makes sure that k is less than the length of the array.
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] s, int l, int r) {
        while (l < r) {
            int temp = s[r];
            s[r] = s[l];
            s[l] = temp;
            l++;
            r--;
        }
    }

    // (3) move k times
    void rotateBy1(int[] nums, int k) {
        while (k-- > 0) { // rotate by 1 for k times
            int tmp = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = tmp;
        }
    }

    public void rotateCopy(int[] nums, int k) {
        k = k % nums.length;
        int[] oldNums = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = oldNums[i];
        }
    }

    public static void main(String[] args) {
        RotateArray ra = new RotateArray();
        int[] ar = { 1, 2, 3, 4, 5, 6, 7 };
        ra.rotate(ar, 3);
        // ra.rotateBy1(ar, 3);
        System.out.println(Arrays.toString(ar));
    }
}
