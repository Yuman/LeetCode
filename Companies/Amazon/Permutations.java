package Companies.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

/*
Given an array nums of distinct integers, 
return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
*/
/**
 * A general approach to backtracking questions in Java (Subsets, Permutations, Combination Sum)
 * backtracking approach https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * @see CombinationSum
 * @see Subsets
 */
public class Permutations {
    //Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> re = new ArrayList<>();
        dfs(re, new ArrayList<>(), nums, new boolean[nums.length]);
        return re;
    }

    private void dfs(List<List<Integer>> re, List<Integer> l, int[] nums, boolean[] used) {
        if (l.size() == nums.length) {
            re.add(new ArrayList<>(l));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) { // contain duplicates
                continue;
            }
            used[i] = true;
            l.add(nums[i]);
            dfs(re, l, nums, used);
            l.remove(l.size() - 1);
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteBT(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
     }
     
     private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
           list.add(new ArrayList<>(tempList));
        } else{
           for(int i = 0; i < nums.length; i++){ 
              if(tempList.contains(nums[i])) continue; // element already exists, skip
              tempList.add(nums[i]);
              backtrack(list, tempList, nums);
              tempList.remove(tempList.size() - 1);
           }
        }
     } 

    public static void main(String[] args) {
        Permutations pm = new Permutations();
        int[] nums = { 1, 2, 3 };
        System.out.println("permuteUnique: " + pm.permuteUnique(nums));
        System.out.println("permute: " + pm.permute(nums));
        System.out.println("permuteBT: " + pm.permuteBT(nums));

    }

    /*
     * the basic idea is, to permute n numbers, we can add the nth number into the
     * resulting List<List<Integer>> from the n-1 numbers, in every possible
     * position.
     * 
     * For example, if the input num[] is {1,2,3}: First, add 1 into the initial
     * List<List<Integer>> (let's call it "answer").
     * 
     * Then, 2 can be added in front or after 1. So we have to copy the List in
     * answer (it's just {1}), add 2 in position 0 of {1}, then copy the original
     * {1} again, and add 2 in position 1. Now we have an answer of {{2,1},{1,2}}.
     * There are 2 lists in the current answer.
     * 
     * Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then
     * copy {2,1} and {1,2}, and add 3 into position 1, then do the same thing for
     * position 3. Finally we have 2*3=6 lists in answer, which is what we want.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (nums.length == 0)
            return result;
        List<Integer> cur = new LinkedList<Integer>();
        cur.add(nums[0]);
        result.add(cur);
        for (int ni = 1; ni < nums.length; ni++) {// loop over each element
            int curSize = result.size();
            for (int li = 0; li < curSize; li++) { // over growing list
                for (int pos = 0; pos < result.get(li).size(); pos++) {
                    List<Integer> newList = new LinkedList<Integer>(result.get(li));
                    newList.add(pos, nums[ni]);
                    result.add(newList);  // grow the result list
                }
                result.get(li).add(nums[ni]);
            }
        }
        return result;

    }

}