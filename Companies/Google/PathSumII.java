package Companies.Google;

import Libs.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

*/
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {// this works on LC
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(new ArrayList<Integer>(), root, sum, result);  // new List with each call
        return result;
    }

    private void helper(List<Integer> list, TreeNode root, int sum, List<List<Integer>> result) {
        if (root == null)
            return;
        list.add(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null) {
            if (sum == 0)
                result.add(list);
            return;
        }
        helper(new ArrayList<Integer>(list), root.left, sum, result);
        helper(new ArrayList<Integer>(list), root.right, sum, result);
    }
}
