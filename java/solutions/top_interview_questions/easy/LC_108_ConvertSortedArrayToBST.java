package solutions.top_interview_questions.easy;

import common.Solution;
import common.TreeNode;
import common.TreeUtils;

public class LC_108_ConvertSortedArrayToBST implements Solution {

    // 🔹 Recursive Approach
    // T.C = O(n); Each element is processed once
    // S.C = O(log n); Recursive stack space for balanced tree
    public TreeNode sortedArrayToBSTRecursive(int[] nums) {
        return formTree(nums, 0, nums.length - 1);
    }

    private TreeNode formTree(int[] nums, int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2; // Avoids overflow
        TreeNode node = new TreeNode(nums[mid]);
        node.left = formTree(nums, left, mid - 1);
        node.right = formTree(nums, mid + 1, right);
        return node;
    }

    @Override
    public void run() {
        int[] nums = { -10, -3, 0, 5, 9 };

        System.out.println("Input Sorted Array: ");
        common.ArrayUtils.printArray("Sorted Array", nums);

        // 🔹 Test Recursive Approach
        TreeNode rootRecursive = sortedArrayToBSTRecursive(nums);
        System.out.println("BST (Recursive):");
        TreeUtils.printTreeVisual(rootRecursive);
    }
}