package Topics.BTree;

import Libs.TreeNode;

import java.util.*;

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 
isBST
*/
public class BSTValidate {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        double min = -Double.MAX_VALUE; // ！！！
        while (!s.isEmpty() || root != null) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            if (root.val <= min) {
                return false;
            }
            min = root.val;
            root = root.right;
        }
        return true;

    }

    public boolean isValidBSTdfs(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
/////////////////////////////  inorder traversal
    private List<Integer> list = new ArrayList<Integer>();

    public boolean isValidBSTInOrder(TreeNode root) {
        inOrder(root);
        return isSortedArray();
    }

    private boolean isSortedArray() {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }

}
