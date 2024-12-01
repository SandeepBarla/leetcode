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

 // Recursive approach 1
public class Solution {
    public int MaxDepth(TreeNode root) {
        return FindDepth(root);
    }
    private int FindDepth(TreeNode node){
        if(node == null) return 0;
        return 1+ Math.Max(FindDepth(node.left),FindDepth(node.right));
    }
}

 // Recursive approach 2
public class Solution {
    public int MaxDepth(TreeNode root) {
        if(root == null) return 0;
        int leftDepth = MaxDepth(root.left);
        int rightDepth = MaxDepth(root.right);
        return 1 + Math.Max(leftDepth, rightDepth);
    }
}