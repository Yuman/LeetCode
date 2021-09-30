package Companies.Amazon;
/*
Given the root node of a binary search tree and two integers low and high, 
return the sum of values of all nodes with a value in the inclusive range [low, high].
*/
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null)
            return 0;
        int n = 0;
        if (root.val >= L && root.val <= R)
            n = root.val;
        n += rangeSumBST(root.left, L, R);
        n += rangeSumBST(root.right, L, R);
        return n;
    }
}
