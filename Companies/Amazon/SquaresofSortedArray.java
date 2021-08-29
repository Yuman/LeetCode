package Companies.Amazon;

/*
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
*/
public class SquaresofSortedArray {
    public int[] sortedSquares(int[] A) {
        int[] re = new int[A.length];
        int l = 0, r = A.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (Math.abs(A[l]) > Math.abs(A[r])) {
                re[i] = A[l] * A[l];
                l++;
            } else {
                re[i] = A[r] * A[r];
                r--;
            }
        }
        return re;
    }
}
