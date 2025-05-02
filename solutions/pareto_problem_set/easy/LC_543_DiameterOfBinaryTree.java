/*
 * LeetCode Problem 543: Diameter of Binary Tree
 * URL: https://leetcode.com/problems/diameter-of-binary-tree/
 * Difficulty: Easy
 *
 * Approach: DFS (Post-order Traversal)
 * - Recursively compute depth of left and right subtree.
 * - At each node, update diameter with sum of left and right depths.
 * - Return 1 + max(leftDepth, rightDepth) to parent.
 *
 * Time Complexity: O(n), where n is the number of nodes
 * Space Complexity: O(h), where h is the height of the tree (recursion stack)
 */
package solutions.pareto_problem_set.easy;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_543_DiameterOfBinaryTree implements Solution {

  private int diameter = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    maxDepth(root);
    return diameter;
  }

  private int maxDepth(TreeNode node) {
    if (node == null)
      return 0;
    int leftDepth = maxDepth(node.left);
    int rightDepth = maxDepth(node.right);
    diameter = Math.max(diameter, leftDepth + rightDepth);
    return 1 + Math.max(leftDepth, rightDepth);
  }

  @Override
  public void run() {
    // Example: [1, 2, 3, 4, 5] (diameter is 3 -> 4-2-1-3)
    TreeNode root = TreeUtils.createTree(new Integer[] { 1, 2, 3, 4, 5 });
    TreeUtils.printTreeVisual(root);
    int result = diameterOfBinaryTree(root);
    System.out.println("Diameter of Binary Tree: " + result);
  }
}