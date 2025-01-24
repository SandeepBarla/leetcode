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
// S.C = O(n)
public class Solution {
    public IList<int> InorderTraversal(TreeNode root) {
        List<int> res = new List<int>();
        InOrder(root, res);
        return res;
    }
    // Recursive method to find InOrderTraversal of a Node
    // Note that this method also has result list as parameter, as it allows us to add the InOrder node value within the method
    private void InOrder(TreeNode node, List<int> res){
        if(node == null) return;
        InOrder(node.left, res);
        res.Add(node.val);
        InOrder(node.right, res);
    }
}

// Iterative approach with Stack
// T.C = O(n); n is  number of nodes
// S.C = O(n)
public class Solution {
    public IList<int> InorderTraversal(TreeNode root) {
        List<int> res = new List<int>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root!=null || stack.Count > 0){
            while(root!=null){ // loop through until left most node is found
                stack.Push(root); // stack all the parents
                root = root.left;
            }
            root = stack.Pop();
            res.Add(root.val); // add the value of left most node
            root = root.right; // repeat the same for right node
        }
        return res;
    }
}