package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_103_BinaryTreeZigzagLevelOrderTraversal implements Solution {

  // Optimized BFS approach using a Deque for Zigzag Traversal
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null)
      return new ArrayList<>();

    Queue<TreeNode> que = new LinkedList<>();
    que.add(root);
    List<List<Integer>> res = new ArrayList<>();
    boolean shouldReverse = false;

    while (!que.isEmpty()) {
      int levelSize = que.size();
      Deque<Integer> level = new LinkedList<>(); // Deque for O(1) insertions at both ends

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = que.poll();

        // Insert at the front or back depending on the order
        if (shouldReverse) {
          level.addFirst(node.val); // O(1) operation
        } else {
          level.addLast(node.val); // O(1) operation
        }

        if (node.left != null)
          que.add(node.left);
        if (node.right != null)
          que.add(node.right);
      }

      res.add(new ArrayList<>(level)); // Convert Deque to List before adding to result
      shouldReverse = !shouldReverse;
    }
    return res;
  }

  @Override
  public void run() {
    // Test case 1: Balanced Tree
    TreeNode root1 = TreeUtils.createTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
    System.out.println("Test Case 1 (Balanced Tree) Zigzag Level Order Traversal: ");
    System.out.println(zigzagLevelOrder(root1));
    TreeUtils.printTreeVisual(root1);

    // Test case 2: Skewed Tree (Right-heavy)
    TreeNode root2 = TreeUtils.createTree(new Integer[] { 1, null, 2, null, null, null, 3 });
    System.out.println("\nTest Case 2 (Right-heavy Skewed Tree) Zigzag Level Order Traversal: ");
    System.out.println(zigzagLevelOrder(root2));
    TreeUtils.printTreeVisual(root2);

    // Test case 3: Single Node Tree
    TreeNode root3 = TreeUtils.createTree(new Integer[] { 1 });
    System.out.println("\nTest Case 3 (Single Node) Zigzag Level Order Traversal: ");
    System.out.println(zigzagLevelOrder(root3));
    TreeUtils.printTreeVisual(root3);

    // Test case 4: Empty Tree
    TreeNode root4 = TreeUtils.createTree(new Integer[] {});
    System.out.println("\nTest Case 4 (Empty Tree) Zigzag Level Order Traversal: ");
    System.out.println(zigzagLevelOrder(root4));
    TreeUtils.printTreeVisual(root4);
  }
}