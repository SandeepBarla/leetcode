/*
 * LeetCode Problem 27: Remove Element
 * URL: https://leetcode.com/problems/remove-element/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Two Pointers (Optimal) - O(n) time, O(1) space
 * 2. Single Pass with Index Tracking - O(n) time, O(1) space
 * 3. Two Pointers (Copy When Different) - O(n) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(n) - single pass through array
 *
 * Space Complexity:
 * - All approaches: O(1) - in-place modification
 */

package solutions.easy;

import common.ArrayUtils;
import common.Solution;

public class LC_27_RemoveElement implements Solution {

    // Approach 1: Two Pointers (Optimal) - Most efficient for arrays with many duplicates
    public int removeElementTwoPointers(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            if (nums[left] == val) {
                // Swap with element from the right
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        
        return left;
    }

    // Approach 2: Single Pass with Index Tracking - Simple and intuitive
    public int removeElementSinglePass(int[] nums, int val) {
        int writeIndex = 0;
        
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != val) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        
        return writeIndex;
    }

    // Approach 3: Two Pointers (Copy When Different) - Alternative implementation
    public int removeElementCopyWhenDifferent(int[] nums, int val) {
        int k = 0; // Number of elements not equal to val
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (i != k) {
                    nums[k] = nums[i];
                }
                k++;
            }
        }
        
        return k;
    }

    @Override
    public void run() {
        // Test Case 1: Array with multiple occurrences of val
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        int[] nums1Copy1 = nums1.clone();
        int[] nums1Copy2 = nums1.clone();
        int[] nums1Copy3 = nums1.clone();
        
        System.out.println("Test Case 1:");
        ArrayUtils.printArray("Original", nums1);
        System.out.println("Remove value: " + val1);
        
        int result1_1 = removeElementTwoPointers(nums1Copy1, val1);
        ArrayUtils.printArray("Two Pointers", java.util.Arrays.copyOf(nums1Copy1, result1_1));
        System.out.println("Length: " + result1_1);
        
        int result1_2 = removeElementSinglePass(nums1Copy2, val1);
        ArrayUtils.printArray("Single Pass", java.util.Arrays.copyOf(nums1Copy2, result1_2));
        System.out.println("Length: " + result1_2);
        
        int result1_3 = removeElementCopyWhenDifferent(nums1Copy3, val1);
        ArrayUtils.printArray("Copy When Different", java.util.Arrays.copyOf(nums1Copy3, result1_3));
        System.out.println("Length: " + result1_3);
        System.out.println();

        // Test Case 2: Array with val at different positions
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int[] nums2Copy1 = nums2.clone();
        int[] nums2Copy2 = nums2.clone();
        int[] nums2Copy3 = nums2.clone();
        
        System.out.println("Test Case 2:");
        ArrayUtils.printArray("Original", nums2);
        System.out.println("Remove value: " + val2);
        
        int result2_1 = removeElementTwoPointers(nums2Copy1, val2);
        ArrayUtils.printArray("Two Pointers", java.util.Arrays.copyOf(nums2Copy1, result2_1));
        System.out.println("Length: " + result2_1);
        
        int result2_2 = removeElementSinglePass(nums2Copy2, val2);
        ArrayUtils.printArray("Single Pass", java.util.Arrays.copyOf(nums2Copy2, result2_2));
        System.out.println("Length: " + result2_2);
        
        int result2_3 = removeElementCopyWhenDifferent(nums2Copy3, val2);
        ArrayUtils.printArray("Copy When Different", java.util.Arrays.copyOf(nums2Copy3, result2_3));
        System.out.println("Length: " + result2_3);
        System.out.println();

        // Test Case 3: Edge case - single element array
        int[] nums3 = {1};
        int val3 = 1;
        int[] nums3Copy = nums3.clone();
        
        System.out.println("Test Case 3 (Edge case):");
        ArrayUtils.printArray("Original", nums3);
        System.out.println("Remove value: " + val3);
        
        int result3 = removeElementTwoPointers(nums3Copy, val3);
        System.out.println("Length after removal: " + result3); // Expected: 0
    }
} 