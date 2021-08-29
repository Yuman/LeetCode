package Topics.Binary;

import java.util.Arrays;

public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int imin = 0, imax = m, half = (m + n + 1) / 2;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = half - i;
            if (i > imin && nums1[i - 1] > nums2[j]) {
                imax = i - 1;
            } else if (i < imax && nums1[i] < nums2[j - 1]) {
                imin = i + 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0;
    }

    public double medianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) { // nums1 is longer
            medianSortedArrays(nums2, nums1);
        }
        if (median(nums1) - median(nums2) < 0.0000001) {
            return median(nums1);
        }
        if (nums2.length == 1) {
            if (nums2[0] < median(nums1)) {
                return median(Arrays.copyOfRange(nums1, 0, nums1.length - 2));
            } else
                return median(Arrays.copyOfRange(nums1, 1, nums1.length - 1));
        }
        if (median(nums1) < median(nums2)) {
            return medianSortedArrays(Arrays.copyOfRange(nums1, nums2.length / 2 - 1, nums2.length - 1),
                    Arrays.copyOfRange(nums2, 0, nums2.length / 2));
        } else {
            return medianSortedArrays(Arrays.copyOfRange(nums1, 0, nums2.length - nums1.length / 2),
                    Arrays.copyOfRange(nums2, nums1.length / 2, nums2.length - 1));
        }
    }

    private double median(int[] nums) {
        if (nums.length % 2 == 0) {
            return (nums[nums.length / 2 - 2] + nums[nums.length / 2 - 1]) / 2.0;
        } else
            return nums[nums.length / 2];
    }
}
