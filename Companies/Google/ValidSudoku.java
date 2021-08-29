package Companies.Google;

import java.util.HashSet;
import java.util.Set;

/**
 * @see Companies.Microsoft.SudokuSolver
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> row = new HashSet<>();
            Set<Character> col = new HashSet<>();
            Set<Character> sub = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !col.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !row.add(board[j][i])) {
                    return false;
                }
            }
            for (int m = 0; m < 3; m++) {
                for (int n = 0; n < 3; n++) {
                    int r = i / 3 * 3 + m, c = i % 3 * 3 + n;
                    if (board[r][c] != '.' && !sub.add(board[r][c])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) 
                    || !seen.add(number + " in column " + j)
                    || !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }
}
