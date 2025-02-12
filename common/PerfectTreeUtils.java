package common;

import java.util.LinkedList;
import java.util.Queue;

public class PerfectTreeUtils {

  // Method to create a Perfect Binary Tree from an Array
  public static Node createPerfectTree(Integer[] values) {
    if (values == null || values.length == 0)
      return null;

    Node root = new Node(values[0]);
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    int i = 1;

    while (i < values.length) {
      Node current = queue.poll();

      if (values[i] != null) {
        current.left = new Node(values[i]);
        queue.add(current.left);
      }
      i++;

      if (i < values.length && values[i] != null) {
        current.right = new Node(values[i]);
        queue.add(current.right);
      }
      i++;
    }
    return root;
  }

  // Method to print the tree including next pointers
  public static void printTreeWithNextPointers(Node root) {
    if (root == null) {
      System.out.println("(empty tree)");
      return;
    }

    Node levelStart = root;
    while (levelStart != null) {
      Node current = levelStart;
      while (current != null) {
        System.out.print(current.val + " -> ");
        current = current.next;
      }
      System.out.println("null"); // End of level
      levelStart = levelStart.left; // Move to the next level
    }
  }
}