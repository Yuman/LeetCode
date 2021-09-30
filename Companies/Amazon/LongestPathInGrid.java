package Companies.Amazon;
import java.util.*;
/**
 *     Given a 2 dimensional grid and a starting coordinate,
    find all paths which lead to purely decreasing numbers.
    Paths terminate only when there are no further steps to take.
    
    Argument 1
      x  0      1     2     3
    —————————----------------
    0 y| 10     3     2     8
    1  | 0      4     8     7
    2  | 6      9    10     2
    3  | 7      8     3     1
    
    Argument 2
    2, 2
    
    Output Example
    [ 
    [ (2,2), (1,2), (0,2), (0,1) ], // 10, 9, 6, 0
    [ (2,2), (1,2), (1,1), (0,1) ], // 10, 9, 4, 0
    [ (2,2), (2,1), (1,1), (0,1) ], // 10, 8, 4, 0
    [ (2,2), (2,1), (2,0) ], // 10, 8, 2
    ... <lots more>
    ]
    
    [
    [ 1, 3, 5, 67, 7, 8],
    [ 2, 2, 2, 1, 2, 0]
    ]
    
 */
public class LongestPathInGrid {

    // List<List<Pair>> findPath (int[][] grid, int x, int y ){
    //     List<List<<Pair>> res = new ArrayList<>();
    //     List<Pair> currPath = new ArrayList<>();
    //     dfs(grid, x, y, grid[x][y], res, currPath);
    //     return res;
    // }
    
    // private void dfs(int[])[] grid, int x, int y, int prev, List<List<Pair>> res, List<Pair> currPath ){
    //     if (x<0 || y<0 || i > grid.length-1 || x>grid.length-1 || y>grid[0].lengh-1) return;
    //     if (res.size() == 0 ) {
    //         currPath.add(new Pair(x,y));
    //     }
    //     if (grid[x][y] < prev) {
    //         currPath.add(new Pair(x,y));
    //         dfs(grid, x-1, y, grid[x][y], res, currPath);
    //         dfs(grid, x+1, y, grid[x][y], res, currPath);
    //         dfs(grid, x, y-1, grid[x][y], res, currPath);
    //         dfs(grid, x, y+1, grid[x][y], res, currPath);
    //         currPath.remove(currPath.length - 1)
    //     }
    //     else {
    //         res.add(currPath.clone());
    //     }
    // }
    
    
    
    // class Pair{
    //     int x,y;
    //     public toString(){
    //         return "(" + x + "," +y+")";
    //     }
    // } 
}
