/*
 * LeetCode Problem 235: Lowest Common Ancestor of a Binary Search Tree
 * URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Difficulty: Easy
 *
 * Approach:
 * - Use BST property to navigate from root.
 * - If both nodes are smaller than current, go left.
 * - If both are larger, go right.
 * - When nodes split across current or one is equal to current, return it as LCA.
 *
 * Time Complexity: O(h), where h is the height of the tree
 * Space Complexity: O(1) — iterative solution
 */

package solutions.pareto_problem_set.medium;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_235_LowestCommonAncestorBST implements Solution {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
      if (root.val > p.val && root.val > q.val) {
        root = root.left;
      } else if (root.val < p.val && root.val < q.val) {
        root = root.right;
      } else {
        return root;
      }
    }
    return null;
  }

  @Override
  public void run() {
    TreeNode root = TreeUtils.createTree(new Integer[] { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 });
    TreeUtils.printTreeVisual(root);

    TreeNode p = root.left; // Node with value 2
    TreeNode q = root.left.right; // Node with value 4

    TreeNode lca = lowestCommonAncestor(root, p, q);
    System.out.println("LCA of " + p.val + " and " + q.val + " is: " + (lca != null ? lca.val : "null"));
  }
}