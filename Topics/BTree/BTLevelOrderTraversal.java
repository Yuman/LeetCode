package Topics.BTree;

import Libs.TreeNode;

import java.util.*;

/*
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

*/
public class BTLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if (root == null)
            return wrapList;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for (int i = 0; i < levelNum; i++) {
                if (queue.peek().left != null)
                    queue.offer(queue.peek().left);
                if (queue.peek().right != null)
                    queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;

    }

    public List<List<Integer>> levelOrderR(TreeNode root) {
        List<List<Integer>> re = new ArrayList<>();
        helper(re, root, 0);
        return re;
    }

    private void helper(List<List<Integer>> re, TreeNode root, int level) {
        if (root != null) {
            if (level == re.size()) {
                re.add(new ArrayList<>());
            }
            re.get(level).add(root.val);
            helper(re, root.left, level + 1);
            helper(re, root.right, level + 1);
        }
    }
}
