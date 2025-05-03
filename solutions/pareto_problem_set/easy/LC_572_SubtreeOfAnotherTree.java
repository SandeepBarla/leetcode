/*
 * LeetCode Problem 572: Subtree of Another Tree
 * URL: https://leetcode.com/problems/subtree-of-another-tree/
 * Difficulty: Easy
 *
 * Approach:
 * - Use DFS traversal on the root tree.
 * - At each node, check if the subtree rooted at that node matches subRoot using isSameTree().
 * - If a match is found, return true. Otherwise, recurse on left and right subtrees.
 *
 * Time Complexity: O(n * m) in the worst case
 * Space Complexity: O(h1 + h2) for recursion stack, where h1 is height of root, h2 of subRoot
 */

package solutions.pareto_problem_set.easy;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_572_SubtreeOfAnotherTree implements Solution {

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (subRoot == null)
      return true;
    if (root == null)
      return false;
    if (isSameTree(root, subRoot))
      return true;
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  private boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null)
      return true;
    if (p == null || q == null || p.val != q.val)
      return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  @Override
  public void run() {
    TreeNode root = TreeUtils.createTree(new Integer[] { 3, 4, 5, 1, 2 });
    TreeNode subRoot = TreeUtils.createTree(new Integer[] { 4, 1, 2 });

    TreeUtils.printTreeVisual(root);
    TreeUtils.printTreeVisual(subRoot);

    boolean result = isSubtree(root, subRoot);
    System.out.println("Is Subtree: " + result);
  }
}