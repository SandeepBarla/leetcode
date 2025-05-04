/*
 * LeetCode Problem 199: Binary Tree Right Side View
 * URL: https://leetcode.com/problems/binary-tree-right-side-view/
 * Difficulty: Medium
 *
 * Approach:
 * - Use BFS (level-order traversal) to process nodes level by level.
 * - At each level, capture the last node encountered (rightmost node).
 * - Add it to the result list.
 *
 * Time Complexity: O(n) — we visit every node once.
 * Space Complexity: O(n) — queue stores up to one level of the tree.
 */

package solutions.pareto_problem_set.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_199_BinaryTreeRightSideView implements Solution {

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)
      return res;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (i == size - 1)
          res.add(node.val);
        if (node.left != null)
          queue.offer(node.left);
        if (node.right != null)
          queue.offer(node.right);
      }
    }

    return res;
  }

  @Override
  public void run() {
    TreeNode root = TreeUtils.createTree(new Integer[] { 1, 2, 3, null, 5, null, 4 });
    TreeUtils.printTreeVisual(root);

    List<Integer> view = rightSideView(root);
    System.out.println("Right Side View: " + view);
  }
}