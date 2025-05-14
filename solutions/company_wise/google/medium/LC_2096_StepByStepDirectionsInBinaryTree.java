/*
 * LeetCode Problem 2096: Step-By-Step Directions From a Binary Tree Node to Another
 * URL: https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 * Difficulty: Medium
 *
 * Approach:
 * - Use DFS to get path from root to startValue and root to destValue.
 * - Remove common prefix (Lowest Common Ancestor logic).
 * - Replace the unique suffix of start path with 'U' (upward steps).
 * - Append the unique suffix of destination path.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h) for call stack and path storage
 */

package solutions.company_wise.google.medium;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_2096_StepByStepDirectionsInBinaryTree implements Solution {

  public String getDirections(TreeNode root, int startValue, int destValue) {
    StringBuilder pathToStart = new StringBuilder();
    StringBuilder pathToDest = new StringBuilder();

    findPath(root, startValue, pathToStart);
    findPath(root, destValue, pathToDest);

    int i = 0;
    while (i < pathToStart.length() && i < pathToDest.length() && pathToStart.charAt(i) == pathToDest.charAt(i)) {
      i++;
    }

    int ups = pathToStart.length() - i;
    StringBuilder result = new StringBuilder();
    for (int j = 0; j < ups; j++) {
      result.append('U');
    }
    result.append(pathToDest.substring(i));
    return result.toString();
  }

  private boolean findPath(TreeNode node, int target, StringBuilder path) {
    if (node == null)
      return false;
    if (node.val == target)
      return true;

    path.append('L');
    if (findPath(node.left, target, path))
      return true;
    path.deleteCharAt(path.length() - 1);

    path.append('R');
    if (findPath(node.right, target, path))
      return true;
    path.deleteCharAt(path.length() - 1);

    return false;
  }

  @Override
  public void run() {
    TreeNode root = TreeUtils.createTree(new Integer[] { 5, 1, 2, 3, null, 6, 4 });
    TreeUtils.printTreeVisual(root);

    int start = 3, dest = 6;
    String directions = getDirections(root, start, dest);
    System.out.println("Directions from " + start + " to " + dest + ": " + directions);
  }
}