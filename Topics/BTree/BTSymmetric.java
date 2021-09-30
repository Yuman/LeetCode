package Topics.BTree;

import Libs.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
/*
Given the root of a binary tree, 
check whether it is a mirror of itself (i.e., symmetric around its center).
*/
public class BTSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll(), t2 = q.poll();
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;

        // return isMirror(root, root);
    }
    public boolean isSymmetricR(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }



    // private boolean isSymmetric(TreeNode left, TreeNode right) {
    //     if (left == null && right == null)
    //         return true;
    //     if (left == null || right == null || left.val != right.val)
    //         return false;
    //     return isSymmetric(left.left, right.right) && isSymmetric(right.left, left.right);
    // }
}
