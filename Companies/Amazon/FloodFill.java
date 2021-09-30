package Companies.Amazon;

import java.util.Arrays;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) {
            dfs(image, sr, sc, color, newColor);
        }
        return image;
    }

    private void dfs(int[][] image, int r, int c, int color, int newColor) {
        if (image[r][c] == color) {
            image[r][c] = newColor;
            if (r >= 1)
                dfs(image, r - 1, c, color, newColor);
            if (c >= 1)
                dfs(image, r, c - 1, color, newColor);
            if (r < image.length - 1)
                dfs(image, r + 1, c, color, newColor);
            if (c < image[0].length - 1)
                dfs(image, r, c + 1, color, newColor);
        }
    }

    public int[][] floodFillK(int[][] image, int sr, int sc, int newColor) {
        int srcColor = image[sr][sc];
        dfsK(image, srcColor, newColor, sr, sc);
        return image;
    }

    private void dfsK(int[][] image, int srcColor, int newColor, int i, int j) {
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length || image[i][j] != srcColor) {
            return;
        }
        image[i][j] = newColor;
        dfsK(image, srcColor, newColor, i - 1, j);
        dfsK(image, srcColor, newColor, i + 1, j);
        dfsK(image, srcColor, newColor, i, j - 1);
        dfsK(image, srcColor, newColor, i, j + 1);
    }

    public static void main(String[] a) {
        /*
         * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2 Output:
         * [[2,2,2],[2,2,2]]
         */
        int[][] image = { { 0, 0, 0 }, { 0, 0, 0 } };
        int sr = 0, sc = 0, newColor = 2;
        FloodFill ff = new FloodFill();
        System.out.println("FF1: " + Arrays.deepToString(ff.floodFill(image, sr, sc, newColor)));
        int[][] image2 = { { 0, 0, 0 }, { 0, 0, 0 } };
        System.out.println("FFK: " + Arrays.deepToString(ff.floodFillK(image2, sr, sc, newColor)));
    }

}
