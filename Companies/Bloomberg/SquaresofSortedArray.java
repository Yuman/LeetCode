package Companies.Bloomberg;

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

    /*
     * Given an integer array nums sorted in non-decreasing order, return an array
     * of the squares of each number sorted in non-decreasing order.
     * 
     * Example 1:
     * 
     * Input: nums = [-4,-1,0,3,10] Output: [0,1,9,16,100] Explanation: After
     * squaring, the array becomes [16,1,0,9,100]. After sorting, it becomes
     * [0,1,9,16,100]. Example 2:
     * 
     * Input: nums = [-7,-3,2,3,11] Output: [4,9,9,49,121]
     */

    public int[] sortedSquares2(int[] A) {
        int[] res = new int[A.length];
        for (int i = 0, j = A.length - 1, r = A.length - 1; i <= j; r--) {
            if (Math.abs(A[i]) > Math.abs(A[j])) {
                res[r] = A[i] * A[i];
                i++;
            } else {
                res[r] = A[j] * A[j];
                j--;
            }
        }

        return res;
    }

    public static void main(String[] a) {
        SquaresofSortedArray ss = new SquaresofSortedArray();
        System.out.println(ss.sortedSquares2(new int[] { -7, -3, 2, 3, 11 }));
    }

}
