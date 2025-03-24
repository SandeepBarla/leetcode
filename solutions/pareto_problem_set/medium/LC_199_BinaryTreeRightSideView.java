/*
 * LeetCode Problem 199: Binary Tree Right Side View
 * URL: https://leetcode.com/problems/binary-tree-right-side-view/
 * Difficulty: Medium
 *
 * Approach: BFS (Level Order Traversal)
 * - Traverse the tree level by level using a queue.
 * - For each level, record the last node (rightmost node) visible from the side.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
package solutions.pareto_problem_set.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.ArrayUtils;
import common.Solution;
import common.TreeNode;

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

        if (i == size - 1) {
          res.add(node.val); // Rightmost node at this level
        }

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
    // Construct a sample binary tree:
    // 1
    // / \
    // 2 3
    // \ \
    // 5 4

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.right = new TreeNode(5);
    root.right.right = new TreeNode(4);

    List<Integer> view = rightSideView(root);
    ArrayUtils.printList("Right Side View", view);
  }
}