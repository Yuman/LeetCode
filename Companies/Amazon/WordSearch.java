package Companies.Amazon;

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, 
where adjacent cells are horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

It's like a crossword puzzle.
*/
/**
 * Time Complexity: O(N*4^L), N is the number of cells and L is the length of
 * the word Space Complexity: O(L)
 */
public class WordSearch {
    public boolean exist(char[][] b, String word) {
        /*
         * Find word's first letter word[0]. Then call method to check it's surroundings
         */
        for (int r = 0; r < b.length; r++)
            for (int c = 0; c < b[0].length; c++) {
                if (b[r][c] == word.charAt(0) && help(b, word, 0, r, c))
                    return true;
            }

        return false;

    }

    public boolean help(char[][] b, String word, int start, int r, int c) {
        /*
         * once we get past word.length, we are done. The offset start increments with
         * recursion.
         */
        if (word.length() <= start)
            return true;

        /*
         * if off bounds, letter is seen, letter is unequal to word.charAt(start) return
         * false
         */
        if (r < 0 || c < 0 || r >= b.length || c >= b[0].length || b[r][c] == '0' || b[r][c] != word.charAt(start))
            return false;

        /* set this board position to seen. (Because we can use this postion) */
        char tmp = b[r][c];
        b[r][c] = '0';

        /* recursion on all 4 sides for next letter, if works: return true */
        if (help(b, word, start + 1, r + 1, c) || help(b, word, start + 1, r - 1, c)
                || help(b, word, start + 1, r, c + 1) || help(b, word, start + 1, r, c - 1))
            return true;

        // Set back to unseen/unused
        b[r][c] = tmp;

        return false;
    }

    public boolean exist2(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist2(board, y, x, w, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean exist2(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length)
            return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length)
            return false;
        if (board[y][x] != word[i])
            return false;
        board[y][x] ^= 256; // hide
        boolean exist = exist2(board, y, x + 1, word, i + 1) || exist2(board, y, x - 1, word, i + 1)
                || exist2(board, y + 1, x, word, i + 1) || exist2(board, y - 1, x, word, i + 1);
        board[y][x] ^= 256;  //show
        return exist;
    }
}
