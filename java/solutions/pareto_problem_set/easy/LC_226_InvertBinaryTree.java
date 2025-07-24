package solutions.pareto_problem_set.easy;

import java.util.LinkedList;
import java.util.Queue;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_226_InvertBinaryTree implements Solution {

  // 🔹 BFS (Iterative) Approach
  // Time Complexity: O(N) | Space Complexity: O(N) (Worst case)
  public TreeNode invertTreeBFS(TreeNode root) {
    if (root == null)
      return null;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();

      // Swap left and right children
      TreeNode temp = node.left;
      node.left = node.right;
      node.right = temp;

      // Add children to queue for further swapping
      if (node.left != null)
        queue.add(node.left);
      if (node.right != null)
        queue.add(node.right);
    }

    return root;
  }

  // 🔹 DFS (Recursive) Approach
  // Time Complexity: O(N) | Space Complexity: O(H) (Recursive stack, O(logN) for
  // balanced tree, O(N) for skewed tree)
  public TreeNode invertTreeDFS(TreeNode root) {
    if (root == null)
      return null;

    // Swap left and right subtrees
    TreeNode left = invertTreeDFS(root.left);
    TreeNode right = invertTreeDFS(root.right);
    root.left = right;
    root.right = left;

    return root;
  }

  @Override
  public void run() {
    // 🔹 Test Case 1: Balanced Tree
    Integer[] tree1 = { 4, 2, 7, 1, 3, 6, 9 };
    TreeNode root1 = TreeUtils.createTree(tree1);
    System.out.println("Original Tree 1:");
    TreeUtils.printTreeVisual(root1);

    TreeNode inverted1 = invertTreeBFS(root1);
    System.out.println("Inverted Tree 1 (BFS):");
    TreeUtils.printTreeVisual(inverted1);

    // 🔹 Test Case 2: Left-Skewed Tree
    Integer[] tree2 = { 1, 2, null, 3, null, 4, null };
    TreeNode root2 = TreeUtils.createTree(tree2);
    System.out.println("\nOriginal Tree 2:");
    TreeUtils.printTreeVisual(root2);

    TreeNode inverted2 = invertTreeDFS(root2);
    System.out.println("Inverted Tree 2 (DFS):");
    TreeUtils.printTreeVisual(inverted2);

    // 🔹 Test Case 3: Right-Skewed Tree
    Integer[] tree3 = { 1, null, 2, null, 3, null, 4 };
    TreeNode root3 = TreeUtils.createTree(tree3);
    System.out.println("\nOriginal Tree 3:");
    TreeUtils.printTreeVisual(root3);

    TreeNode inverted3 = invertTreeBFS(root3);
    System.out.println("Inverted Tree 3 (BFS):");
    TreeUtils.printTreeVisual(inverted3);

    // 🔹 Test Case 4: Single Node Tree
    Integer[] tree4 = { 1 };
    TreeNode root4 = TreeUtils.createTree(tree4);
    System.out.println("\nOriginal Tree 4:");
    TreeUtils.printTreeVisual(root4);

    TreeNode inverted4 = invertTreeDFS(root4);
    System.out.println("Inverted Tree 4 (DFS):");
    TreeUtils.printTreeVisual(inverted4);
  }
}