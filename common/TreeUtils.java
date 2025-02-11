package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtils {

  // Method to create a binary tree from an array (Level Order)
  public static TreeNode createTree(Integer[] values) {
    if (values == null || values.length == 0)
      return null;

    TreeNode root = new TreeNode(values[0]);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int i = 1;

    while (!queue.isEmpty() && i < values.length) {
      TreeNode current = queue.poll();

      if (values[i] != null) {
        current.left = new TreeNode(values[i]);
        queue.offer(current.left);
      }
      i++;

      if (i < values.length && values[i] != null) {
        current.right = new TreeNode(values[i]);
        queue.offer(current.right);
      }
      i++;
    }
    return root;
  }

  // Print a binary tree in a structured way
  public static void printTreeVisual(TreeNode root) {
    if (root == null) {
      System.out.println("(empty tree)");
      return;
    }

    int maxLevel = getTreeHeight(root);
    List<TreeNode> currentLevel = new ArrayList<>();
    currentLevel.add(root);

    int level = 1;
    while (!currentLevel.isEmpty() && level <= maxLevel) {
      List<TreeNode> nextLevel = new ArrayList<>();
      int spaces = (int) Math.pow(2, maxLevel - level + 1); // Adjust spacing dynamically

      printSpaces(spaces);
      for (TreeNode node : currentLevel) {
        if (node == null) {
          System.out.print("  "); // Empty placeholder for alignment
          nextLevel.add(null);
          nextLevel.add(null);
        } else {
          System.out.print(node.val);
          nextLevel.add(node.left);
          nextLevel.add(node.right);
        }
        printSpaces(spaces * 2); // Space between nodes
      }
      System.out.println();

      // Remove trailing nulls to prevent infinite loops
      while (!nextLevel.isEmpty() && nextLevel.get(nextLevel.size() - 1) == null) {
        nextLevel.remove(nextLevel.size() - 1);
      }

      currentLevel = nextLevel;
      level++;
    }
  }

  // Get tree height
  private static int getTreeHeight(TreeNode root) {
    if (root == null)
      return 0;
    return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
  }

  // Print spaces
  private static void printSpaces(int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(" ");
    }
  }
}