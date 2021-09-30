package Topics.BTree;

import Libs.TreeNode;

/**
 Given the root of a binary tree and an integer targetSum, 
 return true if the tree has a root-to-leaf path such that adding up all the values 
 along the path equals targetSum.

A leaf is a node with no children.
 */
public class BTPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
