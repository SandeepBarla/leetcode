package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_102_BinaryTreeLevelOrderTraversal implements Solution {

  // Optimized BFS approach for Level Order Traversal
  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null)
      return new ArrayList<>();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    List<List<Integer>> res = new ArrayList<>();

    while (!queue.isEmpty()) {
      int levelSize = queue.size(); // Get number of nodes in current level
      List<Integer> list = new ArrayList<>();

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll(); // Remove the front node
        list.add(node.val);

        if (node.left != null)
          queue.add(node.left); // Add left child if exists
        if (node.right != null)
          queue.add(node.right); // Add right child if exists
      }
      res.add(list);
    }
    return res;
  }

  @Override
  public void run() {
    // Test case 1: Balanced Tree
    TreeNode root1 = TreeUtils.createTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
    System.out.println("Test Case 1 (Balanced Tree) Level Order Traversal: ");
    System.out.println(levelOrder(root1));
    TreeUtils.printTreeVisual(root1);

    // Test case 2: Skewed Tree (Right-heavy)
    TreeNode root2 = TreeUtils.createTree(new Integer[] { 1, null, 2, null, null, null, 3 });
    System.out.println("\nTest Case 2 (Right-heavy Skewed Tree) Level Order Traversal: ");
    System.out.println(levelOrder(root2));
    TreeUtils.printTreeVisual(root2);

    // Test case 3: Single Node Tree
    TreeNode root3 = TreeUtils.createTree(new Integer[] { 1 });
    System.out.println("\nTest Case 3 (Single Node) Level Order Traversal: ");
    System.out.println(levelOrder(root3));
    TreeUtils.printTreeVisual(root3);

    // Test case 4: Empty Tree
    TreeNode root4 = TreeUtils.createTree(new Integer[] {});
    System.out.println("\nTest Case 4 (Empty Tree) Level Order Traversal: ");
    System.out.println(levelOrder(root4));
    TreeUtils.printTreeVisual(root4);
  }
}