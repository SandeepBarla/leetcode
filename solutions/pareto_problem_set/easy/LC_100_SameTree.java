/*
 * LeetCode Problem 100: Same Tree
 * URL: https://leetcode.com/problems/same-tree/
 * Difficulty: Easy
 *
 * Approach:
 * - Use DFS recursion to compare both structure and values of the nodes.
 * - Trees are the same if:
 *   1. Both nodes are null, or
 *   2. Both nodes are non-null, values match, and subtrees match recursively.
 *
 * Time Complexity: O(n) — each node is visited once
 * Space Complexity: O(h) — recursion stack (h = height of the tree)
 */

package solutions.pareto_problem_set.easy;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_100_SameTree implements Solution {

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null)
      return true;
    if (p == null || q == null || p.val != q.val)
      return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  @Override
  public void run() {
    TreeNode t1 = TreeUtils.createTree(new Integer[] { 1, 2, 3 });
    TreeNode t2 = TreeUtils.createTree(new Integer[] { 1, 2, 3 });
    TreeUtils.printTreeVisual(t1);
    TreeUtils.printTreeVisual(t2);
    System.out.println("Is Same Tree: " + isSameTree(t1, t2));

    TreeNode t3 = TreeUtils.createTree(new Integer[] { 1, 2 });
    TreeNode t4 = TreeUtils.createTree(new Integer[] { 1, null, 2 });
    TreeUtils.printTreeVisual(t3);
    TreeUtils.printTreeVisual(t4);
    System.out.println("Is Same Tree: " + isSameTree(t3, t4));
  }
}