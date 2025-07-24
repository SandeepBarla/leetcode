package solutions.top_interview_questions.easy;

import java.util.LinkedList;
import java.util.Queue;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_104_MaxDepthBinaryTree implements Solution {

    // 🔹 Recursive DFS Approach
    public int maxDepthDFS(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepthDFS(root.left), maxDepthDFS(root.right));
    }

    // 🔹 Iterative BFS Approach
    public int maxDepthBFS(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            depth++;
        }
        return depth;
    }

    @Override
    public void run() {
        // 🔹 Test Case 1: Balanced Tree
        TreeNode root1 = TreeUtils.createTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        System.out.println("Test Case 1 (Balanced Tree):");
        TreeUtils.printTreeVisual(root1);
        System.out.println("Max Depth (DFS): " + maxDepthDFS(root1));
        System.out.println("Max Depth (BFS): " + maxDepthBFS(root1));
        System.out.println();

        // 🔹 Test Case 2: Skewed Left Tree
        TreeNode root2 = TreeUtils.createTree(new Integer[] { 1, 2, null, 3, null, 4, null });
        System.out.println("Test Case 2 (Skewed Left Tree):");
        TreeUtils.printTreeVisual(root2);
        System.out.println("Max Depth (DFS): " + maxDepthDFS(root2));
        System.out.println("Max Depth (BFS): " + maxDepthBFS(root2));
        System.out.println();

        // 🔹 Test Case 3: Single Node
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3 (Single Node):");
        TreeUtils.printTreeVisual(root3);
        System.out.println("Max Depth (DFS): " + maxDepthDFS(root3));
        System.out.println("Max Depth (BFS): " + maxDepthBFS(root3));
        System.out.println();

        // 🔹 Test Case 4: Empty Tree
        TreeNode root4 = null;
        System.out.println("Test Case 4 (Empty Tree):");
        System.out.println("Max Depth (DFS): " + maxDepthDFS(root4));
        System.out.println("Max Depth (BFS): " + maxDepthBFS(root4));
    }
}