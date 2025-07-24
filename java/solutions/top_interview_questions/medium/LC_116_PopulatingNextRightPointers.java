package solutions.top_interview_questions.medium;

import java.util.LinkedList;
import java.util.Queue;

import common.Node;
import common.PerfectTreeUtils;
import common.Solution;

public class LC_116_PopulatingNextRightPointers implements Solution {

  // Optimized In-Place BFS Approach (O(1) Space)
  public Node connect(Node root) {
    if (root == null)
      return null;

    Node currentLevelStart = root; // Start at root (level 0)

    while (currentLevelStart.left != null) { // Process until reaching leaf level
      Node current = currentLevelStart;

      while (current != null) { // Traverse current level
        current.left.next = current.right; // Connect left → right

        if (current.next != null) {
          current.right.next = current.next.left; // Connect right → next left
        }

        current = current.next; // Move to next node in the level
      }

      currentLevelStart = currentLevelStart.left; // Move to next level
    }

    return root;
  }

  // Alternative BFS (Queue-Based) Solution (O(n) Space)
  public Node connectUsingQueue(Node root) {
    if (root == null)
      return null;

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      Node prev = null;

      for (int i = 0; i < levelSize; i++) {
        Node current = queue.poll();
        if (prev != null)
          prev.next = current; // Connect previous node → current node
        prev = current;

        if (current.left != null)
          queue.add(current.left);
        if (current.right != null)
          queue.add(current.right);
      }
    }
    return root;
  }

  @Override
  public void run() {
    // Test case 1: Balanced Perfect Binary Tree
    Integer[] values = { 1, 2, 3, 4, 5, 6, 7 };

    Node root = PerfectTreeUtils.createPerfectTree(values);

    System.out.println("Test Case 1: Before Connecting Next Pointers");
    PerfectTreeUtils.printTreeWithNextPointers(root);

    connect(root);

    System.out.println("\nTest Case 1: After Connecting Next Pointers");
    PerfectTreeUtils.printTreeWithNextPointers(root);
  }
}