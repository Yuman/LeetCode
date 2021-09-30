package Topics.BTree;

import Libs.TreeNode;
import java.util.*;

public class BTFlattentoLinkedList {
    // Find current node's prenode that links to current node's right subtree.
    // Use current node's left subtree to replace its right subtree
    // (original right subtree is already linked by current node's prenode)
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode pre = curr.left;
                while (pre.right != null)
                    pre = pre.right;
                pre.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    public void flattenR(TreeNode root) {
        flattenR(root, null);
    }

    private TreeNode flattenR(TreeNode root, TreeNode pre) {
        if (root == null) {
            return pre; // be careful
        }
        pre = flattenR(root.right, pre);
        pre = flattenR(root.left, pre);
        root.right = pre;
        root.left = null;
        return root;
    }

    public void flattenStack(TreeNode root) {// null the left, left goes to right thru the stack
        if (root == null)
            return;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        while (!stack.isEmpty()) {  // push order: right, left. pop order: cur, left, right
            TreeNode cur = stack.pollLast();
            if (cur.right != null)
                stack.offer(cur.right);
            if (cur.left != null)
                stack.offer(cur.left);
            if (!stack.isEmpty())
                cur.right = stack.peekLast();
            cur.left = null;
        }
    }
}
