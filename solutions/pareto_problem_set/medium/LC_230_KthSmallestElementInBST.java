package solutions.pareto_problem_set.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_230_KthSmallestElementInBST implements Solution {

  // ✅ Approach 1: Recursive Inorder Traversal (Brute Force) - O(N) Time | O(N)
  // Space
  public int kthSmallestRecursive(TreeNode root, int k) {
    List<Integer> res = new ArrayList<>();
    inorderTraversal(root, res);
    return res.get(k - 1); // Return k-th smallest element (1-based index)
  }

  private void inorderTraversal(TreeNode node, List<Integer> res) {
    if (node == null)
      return;
    inorderTraversal(node.left, res);
    res.add(node.val);
    inorderTraversal(node.right, res);
  }

  // ✅ Approach 2: Iterative Inorder Traversal using Stack (Optimized) - O(N) Time
  // | O(H) Space
  public int kthSmallestIterative(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;

    while (node != null || !stack.isEmpty()) {
      // Traverse left subtree (inorder: Left -> Root -> Right)
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      // Process the current node
      node = stack.pop();
      k--;

      // If we reach the k-th element, return it
      if (k == 0)
        return node.val;

      // Move to the right subtree
      node = node.right;
    }
    return -1; // Should never reach here if k is valid
  }

  @Override
  public void run() {
    // 🔹 Test Case 1: Balanced BST
    Integer[] tree1 = { 3, 1, 4, null, 2 };
    TreeNode root1 = TreeUtils.createTree(tree1);
    TreeUtils.printTreeVisual(root1);
    System.out.println("Kth Smallest (Recursive, k=1): " + kthSmallestRecursive(root1, 1));
    System.out.println("Kth Smallest (Iterative, k=1): " + kthSmallestIterative(root1, 1));

    // 🔹 Test Case 2: Left-skewed BST
    Integer[] tree2 = { 5, 3, null, 2, 4, 1 };
    TreeNode root2 = TreeUtils.createTree(tree2);
    TreeUtils.printTreeVisual(root2);
    System.out.println("Kth Smallest (Recursive, k=3): " + kthSmallestRecursive(root2, 3));
    System.out.println("Kth Smallest (Iterative, k=3): " + kthSmallestIterative(root2, 3));

    // 🔹 Test Case 3: Right-skewed BST
    Integer[] tree3 = { 1, null, 2, null, 3, null, 4, null, 5 };
    TreeNode root3 = TreeUtils.createTree(tree3);
    TreeUtils.printTreeVisual(root3);
    System.out.println("Kth Smallest (Recursive, k=4): " + kthSmallestRecursive(root3, 4));
    System.out.println("Kth Smallest (Iterative, k=4): " + kthSmallestIterative(root3, 4));
  }
}