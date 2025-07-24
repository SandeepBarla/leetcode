/*
 * LeetCode Problem 217: Contains Duplicate
 * URL: https://leetcode.com/problems/contains-duplicate/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. HashSet Approach (Optimal) - O(n) time, O(n) space
 * 2. Sorting Approach - O(n log n) time, O(1) space
 * 3. Brute Force - O(n²) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass through array
 * - Approach 2: O(n log n) - sorting + linear scan
 * - Approach 3: O(n²) - nested loops for comparisons
 *
 * Space Complexity:
 * - Approach 1: O(n) - HashSet storage
 * - Approach 2: O(1) - in-place sorting (excluding recursion stack)
 * - Approach 3: O(1) - no extra space
 */

package solutions.top_interview_questions.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import common.ArrayUtils;
import common.Solution;

public class LC_217_ContainsDuplicate implements Solution {

    // Approach 1: HashSet Approach (Optimal)
    public boolean containsDuplicateHashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true; // Found duplicate
            }
            seen.add(num);
        }
        
        return false; // No duplicates found
    }

    // Approach 2: Sorting Approach
    public boolean containsDuplicateSorting(int[] nums) {
        // Sort the array first
        Arrays.sort(nums);
        
        // Check adjacent elements for duplicates
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        
        return false;
    }

    // Approach 3: Brute Force Approach
    public boolean containsDuplicateBruteForce(int[] nums) {
        // Compare each element with every other element
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public void run() {
        // Test Case 1: Array with duplicates
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test Case 1:");
        ArrayUtils.printArray("Input", nums1);
        System.out.println("HashSet Approach: " + containsDuplicateHashSet(nums1)); // Expected: true
        System.out.println("Sorting Approach: " + containsDuplicateSorting(nums1.clone())); // Expected: true
        System.out.println("Brute Force Approach: " + containsDuplicateBruteForce(nums1)); // Expected: true
        System.out.println();

        // Test Case 2: Array without duplicates
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test Case 2:");
        ArrayUtils.printArray("Input", nums2);
        System.out.println("HashSet Approach: " + containsDuplicateHashSet(nums2)); // Expected: false
        System.out.println("Sorting Approach: " + containsDuplicateSorting(nums2.clone())); // Expected: false
        System.out.println("Brute Force Approach: " + containsDuplicateBruteForce(nums2)); // Expected: false
        System.out.println();

        // Test Case 3: Array with multiple duplicates
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println("Test Case 3:");
        ArrayUtils.printArray("Input", nums3);
        System.out.println("HashSet Approach: " + containsDuplicateHashSet(nums3)); // Expected: true
        System.out.println("Sorting Approach: " + containsDuplicateSorting(nums3.clone())); // Expected: true
        System.out.println("Brute Force Approach: " + containsDuplicateBruteForce(nums3)); // Expected: true
        System.out.println();

        // Test Case 4: Single element array (edge case)
        int[] nums4 = {1};
        System.out.println("Test Case 4 (Edge case):");
        ArrayUtils.printArray("Input", nums4);
        System.out.println("HashSet Approach: " + containsDuplicateHashSet(nums4)); // Expected: false
        System.out.println("Sorting Approach: " + containsDuplicateSorting(nums4.clone())); // Expected: false
        System.out.println("Brute Force Approach: " + containsDuplicateBruteForce(nums4)); // Expected: false
        System.out.println();

        // Test Case 5: Large numbers
        int[] nums5 = {1000000, 1000000};
        System.out.println("Test Case 5 (Large numbers):");
        ArrayUtils.printArray("Input", nums5);
        System.out.println("HashSet Approach: " + containsDuplicateHashSet(nums5)); // Expected: true
        System.out.println("Sorting Approach: " + containsDuplicateSorting(nums5.clone())); // Expected: true
        System.out.println("Brute Force Approach: " + containsDuplicateBruteForce(nums5)); // Expected: true
    }
} 