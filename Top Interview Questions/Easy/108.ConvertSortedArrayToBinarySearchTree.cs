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
public class Solution {
    public TreeNode SortedArrayToBST(int[] nums) {
        return ConvertToBST(0, nums.Length - 1, nums);
    }
    private TreeNode ConvertToBST(int l, int r, int[] nums){
        if(l>r) return null;
        int mid = (l+r)/2; // find the mid and assign it to the node value
        TreeNode node = new TreeNode();
        node.val = nums[mid];
        node.left = ConvertToBST(l, mid-1, nums); // left subtree
        node.right = ConvertToBST(mid+1, r, nums); // right subtree
        return node;
    }
}