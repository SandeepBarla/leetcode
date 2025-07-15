/*
 * LeetCode Problem 297: Serialize and Deserialize Binary Tree
 * URL: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Difficulty: Hard
 *
 * Approach:
 * - Use preorder traversal to serialize the tree into a string (with "null" placeholders).
 * - Deserialize by reading the string into a queue and reconstructing the tree recursively.
 * - Both serialize and deserialize operate in O(n) time and space where n = number of nodes.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.hard;

import common.TreeNode;
import common.Solution;

import java.util.*;

public class LC_297_SerializeDeserializeBinaryTree implements Solution {

  public static class Codec {

    // Serializes a binary tree to a string using preorder traversal
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      preOrder(root, sb);
      return sb.toString();
    }

    private void preOrder(TreeNode node, StringBuilder sb) {
      if (node == null) {
        sb.append("null,");
        return;
      }
      sb.append(node.val).append(",");
      preOrder(node.left, sb);
      preOrder(node.right, sb);
    }

    // Deserializes the string back to a binary tree
    public TreeNode deserialize(String data) {
      Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
      return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
      String val = queue.poll();
      if ("null".equals(val))
        return null;

      TreeNode node = new TreeNode(Integer.parseInt(val));
      node.left = buildTree(queue);
      node.right = buildTree(queue);
      return node;
    }
  }

  @Override
  public void run() {
    Codec codec = new Codec();

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);

    String serialized = codec.serialize(root);
    System.out.println("Serialized Tree: " + serialized);

    TreeNode deserialized = codec.deserialize(serialized);
    System.out.println("Deserialized Root Value: " + deserialized.val); // Expected: 1
  }
}