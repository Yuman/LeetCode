package Companies.Amazon;

import java.util.Arrays;

/*
ou are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
DO NOT allocate another 2D matrix and do the rotation.
*/
/**
 * (i, len-1-j) (j,i) (len-1-j, len-1-i) (len-1-i, j)
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            for (int j = i; j < len - 1 - i; j++) {
                int temp1 = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = matrix[j][i];
                int temp2 = matrix[len - 1 - j][len - 1 - i];
                matrix[len - 1 - j][len - 1 - i] = temp1;
                temp1 = matrix[len - 1 - i][j];
                matrix[len - 1 - i][j] = temp2;
                matrix[j][i] = temp1;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public void rotate2(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){ 
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i]; // transpose
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j]; // horizontal flip
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }

    public static void main(String[] a) {
        /*
         * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]] Output: [[7,4,1],[8,5,2],[9,6,3]]
         */
        RotateImage ri = new RotateImage();

        ri.rotate(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
    }

}
