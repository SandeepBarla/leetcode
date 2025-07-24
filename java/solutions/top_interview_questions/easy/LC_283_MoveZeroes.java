/*
 * LeetCode Problem 283: Move Zeroes
 * URL: https://leetcode.com/problems/move-zeroes/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Two Pointers (Optimal) - O(n) time, O(1) space
 * 2. Extra Array Approach - O(n) time, O(n) space
 * 3. Bubble Sort Style - O(n²) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass through array
 * - Approach 2: O(n) - two passes, one to copy non-zeros, one to fill zeros
 * - Approach 3: O(n²) - nested loops for bubble sort style movement
 *
 * Space Complexity:
 * - Approach 1: O(1) - in-place modification
 * - Approach 2: O(n) - extra array for storing result
 * - Approach 3: O(1) - in-place modification
 */

package solutions.top_interview_questions.easy;

import common.ArrayUtils;
import common.Solution;

public class LC_283_MoveZeroes implements Solution {

    // Approach 1: Two Pointers (Optimal)
    public void moveZeroesTwoPointers(int[] nums) {
        int writeIndex = 0;
        
        // Move all non-zero elements to the front
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != 0) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        
        // Fill remaining positions with zeros
        while (writeIndex < nums.length) {
            nums[writeIndex] = 0;
            writeIndex++;
        }
    }

    // Approach 2: Extra Array Approach
    public void moveZeroesExtraArray(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;
        
        // Copy all non-zero elements first
        for (int num : nums) {
            if (num != 0) {
                result[index] = num;
                index++;
            }
        }
        
        // Remaining positions are already zero in new array
        // Copy back to original array
        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    // Approach 3: Bubble Sort Style (Inefficient but educational)
    public void moveZeroesBubbleSort(int[] nums) {
        int n = nums.length;
        
        // For each position from the end
        for (int i = 0; i < n - 1; i++) {
            // Bubble zeros towards the end
            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] == 0 && nums[j + 1] != 0) {
                    // Swap zero with non-zero
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    // Approach 4: Optimized Two Pointers with Minimal Writes
    public void moveZeroesOptimized(int[] nums) {
        int writeIndex = 0;
        
        // Move non-zero elements and only write when necessary
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != 0) {
                if (readIndex != writeIndex) {
                    nums[writeIndex] = nums[readIndex];
                    nums[readIndex] = 0;
                }
                writeIndex++;
            }
        }
    }

    @Override
    public void run() {
        // Test Case 1: Normal case with zeros in middle
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] nums1Copy1 = nums1.clone();
        int[] nums1Copy2 = nums1.clone();
        int[] nums1Copy3 = nums1.clone();
        int[] nums1Copy4 = nums1.clone();
        
        System.out.println("Test Case 1:");
        ArrayUtils.printArray("Original", nums1);
        
        moveZeroesTwoPointers(nums1Copy1);
        ArrayUtils.printArray("Two Pointers", nums1Copy1); // Expected: [1, 3, 12, 0, 0]
        
        moveZeroesExtraArray(nums1Copy2);
        ArrayUtils.printArray("Extra Array", nums1Copy2); // Expected: [1, 3, 12, 0, 0]
        
        moveZeroesBubbleSort(nums1Copy3);
        ArrayUtils.printArray("Bubble Sort", nums1Copy3); // Expected: [1, 3, 12, 0, 0]
        
        moveZeroesOptimized(nums1Copy4);
        ArrayUtils.printArray("Optimized", nums1Copy4); // Expected: [1, 3, 12, 0, 0]
        System.out.println();

        // Test Case 2: All zeros
        int[] nums2 = {0, 0, 0};
        int[] nums2Copy = nums2.clone();
        
        System.out.println("Test Case 2 (All zeros):");
        ArrayUtils.printArray("Original", nums2);
        moveZeroesTwoPointers(nums2Copy);
        ArrayUtils.printArray("Result", nums2Copy); // Expected: [0, 0, 0]
        System.out.println();

        // Test Case 3: No zeros
        int[] nums3 = {1, 2, 3};
        int[] nums3Copy = nums3.clone();
        
        System.out.println("Test Case 3 (No zeros):");
        ArrayUtils.printArray("Original", nums3);
        moveZeroesTwoPointers(nums3Copy);
        ArrayUtils.printArray("Result", nums3Copy); // Expected: [1, 2, 3]
        System.out.println();

        // Test Case 4: Single element
        int[] nums4 = {0};
        int[] nums4Copy = nums4.clone();
        
        System.out.println("Test Case 4 (Single zero):");
        ArrayUtils.printArray("Original", nums4);
        moveZeroesTwoPointers(nums4Copy);
        ArrayUtils.printArray("Result", nums4Copy); // Expected: [0]
        System.out.println();

        // Test Case 5: Zeros at the beginning
        int[] nums5 = {0, 0, 1, 2, 3};
        int[] nums5Copy = nums5.clone();
        
        System.out.println("Test Case 5 (Zeros at beginning):");
        ArrayUtils.printArray("Original", nums5);
        moveZeroesTwoPointers(nums5Copy);
        ArrayUtils.printArray("Result", nums5Copy); // Expected: [1, 2, 3, 0, 0]
    }
} 