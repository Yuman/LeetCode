package Companies.Facebook;
// SLIDING WINDOW  https://www.youtube.com/watch?v=XFPHg5KjHoo

import java.util.Arrays;

public class LongestSubarrayBySum {
    int[] findLongestSubarrayBySum(int s, int[] arr) {
        int[] res = new int[] { -1 };
        int sum = 0, left = 0, right = 0;
        while (right < arr.length) {
            sum += arr[right];
            while (left < right && sum > s) {
                sum -= arr[left++];
            }
            if (sum == s && (res.length == 1 || res[1] - res[0] < right - left)) {
                res = new int[] { left + 1, right + 1 };
            }
            right++;
        }
        return res;
    }

    public static void main(String[] a) {
        int[] arr = {1,2,3,4,5,6,7,8,0,0,0,9,1,2};
        LongestSubarrayBySum ls = new LongestSubarrayBySum();
        System.out.println(Arrays.toString( ls.findLongestSubarrayBySum(15, arr)));
    }
}
