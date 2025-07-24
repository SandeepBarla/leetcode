package solutions.pareto_problem_set.medium;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_1448_GoodNodesInBinaryTree implements Solution {

  // 🔹 Recursive Preorder Traversal (DFS)
  // ✅ T.C = O(N) | ✅ S.C = O(H) where H is tree height (O(log N) for balanced,
  // O(N) for skewed)
  public int goodNodes(TreeNode root) {
    return countGoodNodes(root, Integer.MIN_VALUE);
  }

  private int countGoodNodes(TreeNode node, int max) {
    if (node == null)
      return 0;

    int count = 0;
    if (node.val >= max) {
      count = 1; // Current node is a "good" node
      max = node.val; // Update max value on this path
    }

    // Recur for left and right subtrees
    return count + countGoodNodes(node.left, max) + countGoodNodes(node.right, max);
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    TreeNode root1 = TreeUtils.createTree(new Integer[] { 3, 1, 4, 3, null, 1, 5 });
    TreeUtils.printTreeVisual(root1);
    System.out.println("Good Nodes: " + goodNodes(root1)); // Expected: 4

    // 🔹 Test Case 2
    TreeNode root2 = TreeUtils.createTree(new Integer[] { 3, 3, null, 4, 2 });
    TreeUtils.printTreeVisual(root2);
    System.out.println("Good Nodes: " + goodNodes(root2)); // Expected: 3

    // 🔹 Test Case 3
    TreeNode root3 = TreeUtils.createTree(new Integer[] { 1 });
    TreeUtils.printTreeVisual(root3);
    System.out.println("Good Nodes: " + goodNodes(root3)); // Expected: 1
  }
}