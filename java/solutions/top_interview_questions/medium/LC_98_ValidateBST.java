package solutions.top_interview_questions.medium;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_98_ValidateBST implements Solution {

  // Recursive method to validate if the tree is a BST using min-max constraints
  public boolean isValidBST(TreeNode root) {
    return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isValid(TreeNode node, long leftRange, long rightRange) {
    if (node == null)
      return true;
    if (node.val <= leftRange || node.val >= rightRange)
      return false;
    return isValid(node.left, leftRange, node.val) && isValid(node.right, node.val, rightRange);
  }

  @Override
  public void run() {
    // Test case 1: Valid BST
    TreeNode root1 = TreeUtils.createTree(new Integer[] { 5, 3, 7, 2, 4, null, 8 });
    System.out.println("Test Case 1 (Valid BST): " + isValidBST(root1));
    TreeUtils.printTreeVisual(root1);

    // Test case 2: Invalid BST
    TreeNode root2 = TreeUtils.createTree(new Integer[] { 5, 6, 7 });
    System.out.println("\nTest Case 2 (Invalid BST): " + isValidBST(root2));
    TreeUtils.printTreeVisual(root2);
  }
}