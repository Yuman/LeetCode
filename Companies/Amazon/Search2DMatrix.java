package Companies.Amazon;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1; // flatten the matrix r=p/n; c=p%n
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (matrix[mid / n][mid % n] == target) {
                return true;
            }
            if (matrix[mid / n][mid % n] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    /**
     * Integers in each row are sorted in ascending from left to right. Integers in
     * each column are sorted in ascending from top to bottom.
     * 
     * start search the matrix from top right corner, initialize the current
     * position to top right corner, if the target is greater than the value in
     * current position, then the target can not be in entire row of current
     * position because the row is sorted, if the target is less than the value in
     * current position, then the target can not in the entire column because the
     * column is sorted too. We can rule out one row or one column each time, so the
     * time complexity is O(m+n).
     * 
     * min: [0, 0]
     * max: [m-1, n-1]
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            }
            if (matrix[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }
}
