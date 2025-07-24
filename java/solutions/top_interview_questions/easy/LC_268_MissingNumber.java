/*
 * LeetCode Problem 268: Missing Number
 * URL: https://leetcode.com/problems/missing-number/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Sum Formula (Optimal) - O(n) time, O(1) space
 * 2. XOR Approach - O(n) time, O(1) space
 * 3. Sorting Approach - O(n log n) time, O(1) space
 * 4. HashSet Approach - O(n) time, O(n) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass to calculate sum
 * - Approach 2: O(n) - single pass with XOR operations
 * - Approach 3: O(n log n) - sorting + linear search
 * - Approach 4: O(n) - single pass to build set, another to check
 *
 * Space Complexity:
 * - Approach 1: O(1) - only using variables
 * - Approach 2: O(1) - only using variables
 * - Approach 3: O(1) - in-place sorting
 * - Approach 4: O(n) - HashSet storage
 */

package solutions.top_interview_questions.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import common.ArrayUtils;
import common.Solution;

public class LC_268_MissingNumber implements Solution {

    // Approach 1: Sum Formula (Optimal)
    public int missingNumberSumFormula(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2; // Sum of 0 to n
        int actualSum = 0;
        
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }

    // Approach 2: XOR Approach
    public int missingNumberXOR(int[] nums) {
        int missing = nums.length; // Start with n
        
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i]; // XOR with index and value
        }
        
        return missing;
    }

    // Approach 3: Sorting Approach
    public int missingNumberSorting(int[] nums) {
        Arrays.sort(nums);
        
        // Check if 0 is missing
        if (nums[0] != 0) {
            return 0;
        }
        
        // Check for missing number in sequence
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                return nums[i - 1] + 1;
            }
        }
        
        // Missing number is n (the last number)
        return nums.length;
    }

    // Approach 4: HashSet Approach
    public int missingNumberHashSet(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        
        // Add all numbers to set
        for (int num : nums) {
            numSet.add(num);
        }
        
        // Check for missing number from 0 to n
        for (int i = 0; i <= nums.length; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        
        return -1; // Should never reach here
    }

    @Override
    public void run() {
        // Test Case 1: Missing number in middle
        int[] nums1 = {3, 0, 1};
        System.out.println("Test Case 1:");
        ArrayUtils.printArray("Input", nums1);
        System.out.println("Sum Formula: " + missingNumberSumFormula(nums1)); // Expected: 2
        System.out.println("XOR Approach: " + missingNumberXOR(nums1)); // Expected: 2
        System.out.println("Sorting: " + missingNumberSorting(nums1.clone())); // Expected: 2
        System.out.println("HashSet: " + missingNumberHashSet(nums1)); // Expected: 2
        System.out.println();

        // Test Case 2: Missing number at end
        int[] nums2 = {0, 1};
        System.out.println("Test Case 2:");
        ArrayUtils.printArray("Input", nums2);
        System.out.println("Sum Formula: " + missingNumberSumFormula(nums2)); // Expected: 2
        System.out.println("XOR Approach: " + missingNumberXOR(nums2)); // Expected: 2
        System.out.println("Sorting: " + missingNumberSorting(nums2.clone())); // Expected: 2
        System.out.println("HashSet: " + missingNumberHashSet(nums2)); // Expected: 2
        System.out.println();

        // Test Case 3: Missing number at beginning
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Test Case 3:");
        ArrayUtils.printArray("Input", nums3);
        System.out.println("Sum Formula: " + missingNumberSumFormula(nums3)); // Expected: 8
        System.out.println("XOR Approach: " + missingNumberXOR(nums3)); // Expected: 8
        System.out.println("Sorting: " + missingNumberSorting(nums3.clone())); // Expected: 8
        System.out.println("HashSet: " + missingNumberHashSet(nums3)); // Expected: 8
        System.out.println();

        // Test Case 4: Single element array
        int[] nums4 = {1};
        System.out.println("Test Case 4 (Single element):");
        ArrayUtils.printArray("Input", nums4);
        System.out.println("Sum Formula: " + missingNumberSumFormula(nums4)); // Expected: 0
        System.out.println("XOR Approach: " + missingNumberXOR(nums4)); // Expected: 0
        System.out.println("Sorting: " + missingNumberSorting(nums4.clone())); // Expected: 0
        System.out.println("HashSet: " + missingNumberHashSet(nums4)); // Expected: 0
        System.out.println();

        // Test Case 5: Only zero missing
        int[] nums5 = {1, 2};
        System.out.println("Test Case 5 (Missing zero):");
        ArrayUtils.printArray("Input", nums5);
        System.out.println("Sum Formula: " + missingNumberSumFormula(nums5)); // Expected: 0
        System.out.println("XOR Approach: " + missingNumberXOR(nums5)); // Expected: 0
        System.out.println("Sorting: " + missingNumberSorting(nums5.clone())); // Expected: 0
        System.out.println("HashSet: " + missingNumberHashSet(nums5)); // Expected: 0
    }
} 