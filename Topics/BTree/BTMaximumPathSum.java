package Topics.BTree;

import Libs.TreeNode;

/*
https://www.youtube.com/watch?v=6cA_NDtpyz8
A path in a binary tree is a sequence of nodes 
where each pair of adjacent nodes in the sequence has an edge connecting them. 
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any path.

 
*/
/**
 * Time complexity: O(N), where N is number of nodes, since we visit each node
 * not more than 2 times. Space complexity: O(log(N)), we have to keep a
 * recursion stack of the size of the tree height.
 */
public class BTMaximumPathSum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {// post-order traversal
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        max = Math.max(max, left + right + root.val);
        return root.val + Math.max(left, right);
    }
}
