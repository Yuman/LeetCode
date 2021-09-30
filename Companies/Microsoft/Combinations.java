package Companies.Microsoft;

import java.util.*;

/*
Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

A general approach:
https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 
*/
public class Combinations {
    /*
     * There are 2 cases here:
     * 
     * Add current element to final solution combine(n-1, k-1) Do not add current
     * element to final solution combine(n-1, k)
     * 
     */
    public List<List<Integer>> combine(int n, int k) {
        if (k == n || k == 0) { // base case
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            // List<List<Integer>> r = Arrays.asList(row); // turns one element into an
            // array, then a list
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = this.combine(n - 1, k - 1); // pick n, grow inner list
        result.forEach(e -> e.add(n));
        result.addAll(this.combine(n - 1, k)); // not pick n, grow outer list
        return result;
    }

    /*
     * Example 1:
     * 
     * Input: n = 4, k = 2 Output: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
     */
    public static void main(String[] args) {
        Combinations com = new Combinations();
        System.out.println("combine 2x2: " + com.combine(2, 2));
        System.out.println("combine: " + com.combine(4, 2));
        System.out.println("combineI: " + com.combine(4, 2));
        System.out.println("combineII: " + com.combine(4, 2));
        System.out.println("combineIII: " + com.combine(4, 2));
    }

    /*
     * The idea is to iteratively generate combinations for all lengths from 1 to k.
     * We start with a list of all numbers <= n as combinations for k == 1. When we
     * have all combinations of length k-1, we can get the new ones for a length k
     * with trying to add to each one all elements that are <= n and greater than
     * the last element of a current combination.
     */
    public List<List<Integer>> combineI(int n, int k) {
        if (k == 0 || n == 0 || k > n)
            return Collections.emptyList();
        List<List<Integer>> combs = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            combs.add(Arrays.asList(i)); // make n seeds with 1..n
        for (int i = 2; i <= k; i++) { // for 2..k
            List<List<Integer>> newCombs = new ArrayList<>();
            for (int j = i; j <= n; j++) {
                for (List<Integer> comb : combs) {
                    if (comb.get(comb.size() - 1) < j) {
                        List<Integer> newComb = new ArrayList<>(comb);
                        newComb.add(j);
                        newCombs.add(newComb);
                    }
                }
            }
            combs = newCombs;
        }
        return combs;
    }

    public List<List<Integer>> combineII(int n, int k) {
        if (k == 0 || n == 0 || k > n)
            return Collections.emptyList();
        List<List<Integer>> combs = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            combs.add(Arrays.asList(i)); // make n seeds with 1..n
        for (int i = 2; i <= k; i++) { // for 2..k
            for (int j = i; j <= n; j++) {
                for (List<Integer> comb : combs) {
                    if (comb.get(comb.size() - 1) < j) {
                        List<Integer> newComb = new ArrayList<>(comb);
                        newComb.add(j);
                        combs.add(newComb);
                    }
                }
            }
        }
        return combs;
    }

    public List<List<Integer>> combineIII(int n, int k) {
        if (k == 0 || n == 0 || k > n)
            return Collections.emptyList();
        List<List<Integer>> combs = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            combs.add(Arrays.asList(i)); // make n seeds with 1..n
        for (int i = 2; i <= k; i++) { // for 2..k
            for (List<Integer> comb : combs) { // loop over growing list
                if (comb.get(comb.size() - 1) < i) {
                    List<Integer> newComb = new ArrayList<>(comb);// copy the seed array to add an element
                    newComb.add(i); // grow inside
                    combs.add(newComb); // grow outside
                }
            }
        }
        return combs;
    }

    public List<List<Integer>> combineBt(int n, int k) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        backtrack(list, new ArrayList<Integer>(), n, k, 1);
        return list;
    }

    public void backtrack(List<List<Integer>> list, ArrayList<Integer> currList, int n, int k, int start) {
        if (currList.size() == k)
            list.add(new ArrayList<>(currList));
        else if (currList.size() > k)
            return;

        for (int i = start; i <= n; i++) {
            currList.add(i);
            backtrack(list, currList, n, k, i + 1);
            currList.remove(currList.size() - 1);
        }
    }
}
