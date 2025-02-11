package common;

// Definition for a binary tree node
public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  // Constructor
  public TreeNode(int val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }

  // Constructor with left and right child
  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  // Print Tree (Helper method)
  public static void printTree(TreeNode root) {
    printTreeHelper(root, "", true);
  }

  private static void printTreeHelper(TreeNode node, String prefix, boolean isLeft) {
    if (node != null) {
      System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);
      printTreeHelper(node.left, prefix + (isLeft ? "│   " : "    "), true);
      printTreeHelper(node.right, prefix + (isLeft ? "│   " : "    "), false);
    }
  }
}