/*
 * LeetCode Problem 110: Balanced Binary Tree
 * URL: https://leetcode.com/problems/balanced-binary-tree/
 * Difficulty: Easy
 *
 * Approach:
 * - Perform a post-order DFS traversal.
 * - Return -1 if a subtree is unbalanced (i.e., left and right depths differ by more than 1).
 * - Otherwise, return the height of the subtree.
 * - A tree is balanced if all subtrees are balanced.
 *
 * Time Complexity: O(n) - visiting each node once
 * Space Complexity: O(h) - recursion stack, where h is tree height
 */

package solutions.pareto_problem_set.easy;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_110_BalancedBinaryTree implements Solution {

  public boolean isBalanced(TreeNode root) {
    return checkHeight(root) != -1;
  }

  private int checkHeight(TreeNode node) {
    if (node == null)
      return 0;

    int left = checkHeight(node.left);
    if (left == -1)
      return -1;

    int right = checkHeight(node.right);
    if (right == -1)
      return -1;

    if (Math.abs(left - right) > 1)
      return -1;

    return 1 + Math.max(left, right);
  }

  @Override
  public void run() {
    // Balanced Tree
    TreeNode balancedRoot = TreeUtils.createTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
    TreeUtils.printTreeVisual(balancedRoot);
    System.out.println("Is Balanced: " + isBalanced(balancedRoot));

    // Unbalanced Tree
    TreeNode unbalancedRoot = TreeUtils.createTree(new Integer[] { 1, 2, 2, 3, 3, null, null, 4, 4 });
    TreeUtils.printTreeVisual(unbalancedRoot);
    System.out.println("Is Balanced: " + isBalanced(unbalancedRoot));
  }
}