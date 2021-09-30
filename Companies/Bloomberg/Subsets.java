package Companies.Bloomberg;

import java.util.*;

/**
 * https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
 * 
 * @see CombinationSum
 * @see Permutations
 */
public class Subsets {
    public List<List<Integer>> subsetsR(int[] nums) {
        // Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new LinkedList<>(), nums, 0);
        return res;
    }
// aka backtracking
    private void dfs(List<List<Integer>> res, Deque<Integer> curr, int[] nums, int start) {
        res.add(new LinkedList<>(curr)); // add a copy of the subset without the num[start]
                                         // base case when start == nums.length-1
                                         // implicit return
                                         // start++ driving toward the base
        for (int i = start; i < nums.length; i++) { // in each step toward the base, use a num
            // if (i > start && nums[i] == nums[i-1]) { // duplicates
            // continue;
            // }
            curr.addLast(nums[i]);
            dfs(res, curr, nums, i + 1);  // work on subset with num[start], in curr
            curr.removeLast(); 
        }
    }

    /*
     * While iterating through all numbers, for each new number, we can either pick
     * it or not pick it 1. if picking, just add current number to every existing
     * subset. 2, if not pick, just leave all existing subsets as they are. We just
     * combine both into our result.
     * 
     * For example, {1,2,3} intially we have an emtpy set as result [ [ ] ]
     * Considering 1, if not use it, still [ ], if use 1, add it to [ ], so we have
     * [1] now Combine them, now we have [ [ ], [1] ] as all possible subset
     * 
     * Next considering 2, if not use it, we still have [ [ ], [1] ], if use 2, just
     * add 2 to each previous subset, we have [2], [1,2] Combine them, now we have [
     * [ ], [1], [2], [1,2] ]
     * 
     * Next considering 3, if not use it, we still have [ [ ], [1], [2], [1,2] ], if
     * use 3, just add 3 to each previous subset, we have [ [3], [1,3], [2,3],
     * [1,2,3] ] Combine them, now we have [ [ ], [1], [2], [1,2], [3], [1,3],
     * [2,3], [1,2,3] ]
     * 
     * https://leetcode.com/problems/subsets/discuss/122645/3ms-easiest-solution-no-
     * backtracking-no-bit-manipulation-no-dfs-no-bullshit
     * 
     * Note that the nested loop is over a growing collection
     * A similar technique is used in mergeSubsets or unionfind, where loop is over a shrinking list
     * @see NumberofConnectedComponentsinUndirectedGraph
     */
    public List<List<Integer>> subsetsI(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // add the base, or seed, subset
        for (int n : nums) {  // loop over each element
            int size = result.size(); // to read by index, avoiding ConcurrentModificationException
            for (int i = 0; i < size; i++) { //loop over each subset without the element. can't use result.size() here
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }

    public static void main(String[] a) {
        /*
         * Input: nums = [1,2,3] Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
         */
        Subsets ss = new Subsets();
        System.out.println("subsetR" + ss.subsetsR(new int[] { 1, 2, 3 }));
        List<List<Integer>> res = ss.subsetsI(new int[] { 1, 2, 3 });
        System.out.println("subsetI" + res);

    }

}
