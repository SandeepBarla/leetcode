package solutions.top_interview_questions.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_94_BinaryTreeInorderTraversal implements Solution {

    // 🔹 Recursive Approach (DFS)
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderDFS(root, res);
        return res;
    }

    private void inorderDFS(TreeNode node, List<Integer> res) {
        if (node == null)
            return;
        inorderDFS(node.left, res); // Left
        res.add(node.val); // Root
        inorderDFS(node.right, res); // Right
    }

    // 🔹 Iterative Approach (Stack)
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left; // Keep traversing left
            }
            node = stack.pop(); // Process leftmost node
            res.add(node.val);
            node = node.right; // Move to right child
        }

        return res;
    }

    @Override
    public void run() {
        // ✅ Test Case 1: Balanced Tree
        TreeNode root1 = TreeUtils.createTree(new Integer[] { 1, null, 2, 3 });
        System.out.println("Input Tree:");
        TreeUtils.printTreeVisual(root1);

        List<Integer> expected1 = List.of(1, 3, 2);
        List<Integer> actualRecursive1 = inorderTraversalRecursive(root1);
        List<Integer> actualIterative1 = inorderTraversalIterative(root1);
        System.out.println("Expected: " + expected1);
        System.out.println("Recursive Output: " + actualRecursive1);
        System.out.println("Iterative Output: " + actualIterative1);
        System.out.println();

        // ✅ Test Case 2: Single Node
        TreeNode root2 = new TreeNode(1);
        System.out.println("Input Tree:");
        TreeUtils.printTreeVisual(root2);

        List<Integer> expected2 = List.of(1);
        List<Integer> actualRecursive2 = inorderTraversalRecursive(root2);
        List<Integer> actualIterative2 = inorderTraversalIterative(root2);
        System.out.println("Expected: " + expected2);
        System.out.println("Recursive Output: " + actualRecursive2);
        System.out.println("Iterative Output: " + actualIterative2);
        System.out.println();

        // ✅ Test Case 3: Empty Tree
        TreeNode root3 = null;
        System.out.println("Input Tree: null");
        List<Integer> expected3 = List.of();
        List<Integer> actualRecursive3 = inorderTraversalRecursive(root3);
        List<Integer> actualIterative3 = inorderTraversalIterative(root3);
        System.out.println("Expected: " + expected3);
        System.out.println("Recursive Output: " + actualRecursive3);
        System.out.println("Iterative Output: " + actualIterative3);
    }
}