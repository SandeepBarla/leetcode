/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Recursive Approach
// T.C = O(n); n is  number of nodes
// S.C = O(h); h is height of tree
public class Solution {
    public bool IsSymmetric(TreeNode root) {
        return CheckSymmetric(root.left, root.right); // recursive method to verify the mirror image of a node
    }

    private bool CheckSymmetric(TreeNode left, TreeNode right){
        // we always verify the left most and right most elements
        if(left == null || right == null) return left == right;
        return (left.val == right.val) && CheckSymmetric(left.left, right.right) & CheckSymmetric(left.right, right.left);
    }
}