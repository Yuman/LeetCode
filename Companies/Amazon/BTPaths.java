package Companies.Amazon;

import java.util.*;
/*
Binary Tree Paths
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.
*/

public class BTPaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode root, String currPath, List<String> res) {
        currPath += String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            res.add(currPath);
            return;
        }
        if (root.left != null)
            dfs(root.left, currPath + "->", new ArrayList<>(res));
        if (root.right != null)
            dfs(root.right, currPath + "->", new ArrayList<>(res));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
