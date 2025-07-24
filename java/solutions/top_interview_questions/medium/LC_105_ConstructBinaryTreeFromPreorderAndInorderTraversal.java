package solutions.top_interview_questions.medium;

import java.util.HashMap;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_105_ConstructBinaryTreeFromPreorderAndInorderTraversal implements Solution {

    // Optimized Recursive Solution: Construct Binary Tree from Preorder and Inorder
    // Traversal
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIdxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIdxMap.put(inorder[i], i); // Store indices for O(1) lookups
        }
        return build(preorder, inorder, inorderIdxMap, 0, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, Map<Integer, Integer> inorderIdxMap, int preorderIdx,
            int start, int end) {
        if (start > end || preorderIdx >= preorder.length)
            return null;

        int val = preorder[preorderIdx]; // Current root value
        TreeNode node = new TreeNode(val);
        int idx = inorderIdxMap.get(val); // Find root index in inorder traversal

        // Left subtree from start to idx - 1
        node.left = build(preorder, inorder, inorderIdxMap, preorderIdx + 1, start, idx - 1);
        // Right subtree from idx + 1 to end
        node.right = build(preorder, inorder, inorderIdxMap, preorderIdx + (idx - start) + 1, idx + 1, end);

        return node;
    }

    @Override
    public void run() {
        // Test case 1: Balanced Tree
        int[] preorder1 = { 3, 9, 20, 15, 7 };
        ArrayUtils.printArray("preorder1", preorder1);
        int[] inorder1 = { 9, 3, 15, 20, 7 };
        ArrayUtils.printArray("inorder1", inorder1);
        TreeNode root1 = buildTree(preorder1, inorder1);
        System.out.println("Test Case 1 (Balanced Tree) Constructed Tree: ");
        TreeUtils.printTreeVisual(root1);

        // Test case 2: Left-skewed Tree
        int[] preorder2 = { 1, 2, 3 };
        ArrayUtils.printArray("preorder2", preorder2);
        int[] inorder2 = { 3, 2, 1 };
        ArrayUtils.printArray("inorder2", inorder2);
        TreeNode root2 = buildTree(preorder2, inorder2);
        System.out.println("\nTest Case 2 (Left-skewed Tree) Constructed Tree: ");
        TreeUtils.printTreeVisual(root2);

        // Test case 3: Right-skewed Tree
        int[] preorder3 = { 1, 2, 3 };
        ArrayUtils.printArray("preorder3", preorder3);
        int[] inorder3 = { 1, 2, 3 };
        ArrayUtils.printArray("inorder3", inorder3);
        TreeNode root3 = buildTree(preorder3, inorder3);
        System.out.println("\nTest Case 3 (Right-skewed Tree) Constructed Tree: ");
        TreeUtils.printTreeVisual(root3);

        // Test case 4: Single Node Tree
        int[] preorder4 = { 1 };
        ArrayUtils.printArray("preorder4", preorder4);
        int[] inorder4 = { 1 };
        ArrayUtils.printArray("inorder4", inorder4);
        TreeNode root4 = buildTree(preorder4, inorder4);
        System.out.println("\nTest Case 4 (Single Node) Constructed Tree: ");
        TreeUtils.printTreeVisual(root4);
    }
}