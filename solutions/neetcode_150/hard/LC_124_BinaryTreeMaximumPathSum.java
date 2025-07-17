/*
 * LeetCode: 124 - Binary Tree Maximum Path Sum
 * URL: https://leetcode.com/problems/binary-tree-maximum-path-sum
 * Difficulty: Hard
 *
 * Approach:
 * - The goal is to find the maximum sum path in the binary tree.
 * - A valid path can start and end at any node but must be continuous (parent-child connections).
 * - Use DFS to calculate the max gain from left and right subtrees.
 * - For each node, update the global max using: node.val + leftGain + rightGain
 * - Return the max gain from current node down to help parent calculations.
 *
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) for the recursion stack, worst case O(n)
 */

package solutions.neetcode_150.hard;

import common.Solution;
import common.TreeNode;

public class LC_124_BinaryTreeMaximumPathSum implements Solution {

  @Override
  public void run() {
    TreeNode root = new TreeNode(-10,
        new TreeNode(9),
        new TreeNode(20, new TreeNode(15), new TreeNode(7)));
    System.out.println("Max Path Sum: " + maxPathSum(root)); // 42
  }

  public int maxPathSum(TreeNode root) {
    int[] max = new int[] { root.val }; // Global max sum (stored as array to allow mutation)
    dfs(root, max);
    return max[0];
  }

  private int dfs(TreeNode node, int[] max) {
    if (node == null)
      return 0;

    int left = Math.max(0, dfs(node.left, max)); // Only add if left gain > 0
    int right = Math.max(0, dfs(node.right, max)); // Only add if right gain > 0

    int currMax = node.val + left + right; // Full path through current node
    max[0] = Math.max(max[0], currMax); // Update global max if needed

    return node.val + Math.max(left, right); // Return max gain to parent
  }
}