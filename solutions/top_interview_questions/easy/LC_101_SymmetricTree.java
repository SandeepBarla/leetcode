package solutions.top_interview_questions.easy;

import java.util.LinkedList;
import java.util.Queue;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_101_SymmetricTree implements Solution {

    // 🔹 Recursive Approach: Compare Mirror Images
    // T.C = O(n), S.C = O(h)
    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null)
            return true;
        return compareMirrorImages(root.left, root.right);
    }

    private boolean compareMirrorImages(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null)
            return node1 == node2;
        return node1.val == node2.val &&
                compareMirrorImages(node1.left, node2.right) &&
                compareMirrorImages(node1.right, node2.left);
    }

    // 🔹 Iterative Approach (Queue-based BFS)
    // T.C = O(n), S.C = O(n)
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if (node1 == null && node2 == null)
                continue;
            if (node1 == null || node2 == null || node1.val != node2.val)
                return false;

            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }
        return true;
    }

    @Override
    public void run() {
        // ✅ Test Case 1: Symmetric Tree
        Integer[] values1 = { 1, 2, 2, 3, 4, 4, 3 };
        TreeNode root1 = TreeUtils.createTree(values1);
        TreeUtils.printTreeVisual(root1);
        System.out.println("Recursive: " + isSymmetricRecursive(root1)); // Expected: true
        System.out.println("Iterative: " + isSymmetricIterative(root1)); // Expected: true

        // ✅ Test Case 2: Asymmetric Tree
        Integer[] values2 = { 1, 2, 2, null, 3, null, 3 };
        TreeNode root2 = TreeUtils.createTree(values2);
        TreeUtils.printTreeVisual(root2);
        System.out.println("Recursive: " + isSymmetricRecursive(root2)); // Expected: false
        System.out.println("Iterative: " + isSymmetricIterative(root2)); // Expected: false
    }
}