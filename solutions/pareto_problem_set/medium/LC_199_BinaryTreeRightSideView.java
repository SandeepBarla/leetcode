/*
 * LeetCode Problem 199: Binary Tree Right Side View
 * URL: https://leetcode.com/problems/binary-tree-right-side-view/
 * Difficulty: Medium
 *
 * Approach 1 (BFS):
 * - Use level-order traversal to process nodes level by level.
 * - At each level, capture the last node encountered (rightmost node).
 *
 * Approach 2 (DFS):
 * - Use preorder DFS traversal (right before left).
 * - Add the first node we visit at each depth level to the result.
 *
 * Time Complexity: O(n) — both BFS and DFS visit every node once.
 * Space Complexity: O(n) — for queue (BFS) or call stack (DFS).
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

  public List<Integer> rightSideViewDFS(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    dfs(root, 0, res);
    return res;
  }

  private void dfs(TreeNode node, int depth, List<Integer> res) {
    if (node == null)
      return;

    if (depth == res.size())
      res.add(node.val);

    dfs(node.right, depth + 1, res);
    dfs(node.left, depth + 1, res);
  }

  @Override
  public void run() {
    TreeNode root = TreeUtils.createTree(new Integer[] { 1, 2, 3, null, 5, null, 4 });
    TreeUtils.printTreeVisual(root);

    List<Integer> bfsView = rightSideView(root);
    System.out.println("Right Side View (BFS): " + bfsView);

    List<Integer> dfsView = rightSideViewDFS(root);
    System.out.println("Right Side View (DFS): " + dfsView);
  }
}