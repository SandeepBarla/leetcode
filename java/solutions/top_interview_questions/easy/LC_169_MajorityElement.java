/*
 * LeetCode Problem 169: Majority Element
 * URL: https://leetcode.com/problems/majority-element/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Boyer-Moore Voting Algorithm (Optimal) - O(n) time, O(1) space
 * 2. HashMap Approach - O(n) time, O(n) space
 * 3. Sorting Approach - O(n log n) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass through array
 * - Approach 2: O(n) - single pass to build map, another to find majority
 * - Approach 3: O(n log n) - sorting + accessing middle element
 *
 * Space Complexity:
 * - Approach 1: O(1) - only using two variables
 * - Approach 2: O(n) - HashMap storing elements and counts
 * - Approach 3: O(1) - in-place sorting (excluding recursion stack)
 */

package solutions.top_interview_questions.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;

public class LC_169_MajorityElement implements Solution {

    // Approach 1: Boyer-Moore Voting Algorithm (Optimal)
    public int majorityElementVoting(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        
        // Find potential candidate
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // Since problem guarantees majority element exists, return candidate
        return candidate;
    }

    // Approach 2: HashMap Approach
    public int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int majorityThreshold = nums.length / 2;
        
        // Count frequency of each element
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
            
            // Return immediately when majority is found
            if (count > majorityThreshold) {
                return num;
            }
        }
        
        // Should never reach here based on problem constraints
        return -1;
    }

    // Approach 3: Sorting Approach
    public int majorityElementSorting(int[] nums) {
        // Sort the array
        Arrays.sort(nums);
        
        // Majority element will always be at the middle index
        return nums[nums.length / 2];
    }

    @Override
    public void run() {
        // Test Case 1: Normal case with clear majority
        int[] nums1 = {3, 2, 3};
        System.out.println("Test Case 1:");
        ArrayUtils.printArray("Input", nums1);
        System.out.println("Boyer-Moore Voting: " + majorityElementVoting(nums1)); // Expected: 3
        System.out.println("HashMap Approach: " + majorityElementHashMap(nums1)); // Expected: 3
        System.out.println("Sorting Approach: " + majorityElementSorting(nums1.clone())); // Expected: 3
        System.out.println();

        // Test Case 2: Larger array with majority
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Test Case 2:");
        ArrayUtils.printArray("Input", nums2);
        System.out.println("Boyer-Moore Voting: " + majorityElementVoting(nums2)); // Expected: 2
        System.out.println("HashMap Approach: " + majorityElementHashMap(nums2)); // Expected: 2
        System.out.println("Sorting Approach: " + majorityElementSorting(nums2.clone())); // Expected: 2
        System.out.println();

        // Test Case 3: Single element array (edge case)
        int[] nums3 = {1};
        System.out.println("Test Case 3 (Edge case):");
        ArrayUtils.printArray("Input", nums3);
        System.out.println("Boyer-Moore Voting: " + majorityElementVoting(nums3)); // Expected: 1
        System.out.println("HashMap Approach: " + majorityElementHashMap(nums3)); // Expected: 1
        System.out.println("Sorting Approach: " + majorityElementSorting(nums3.clone())); // Expected: 1
        System.out.println();

        // Test Case 4: All elements are the same
        int[] nums4 = {5, 5, 5, 5, 5};
        System.out.println("Test Case 4 (All same):");
        ArrayUtils.printArray("Input", nums4);
        System.out.println("Boyer-Moore Voting: " + majorityElementVoting(nums4)); // Expected: 5
        System.out.println("HashMap Approach: " + majorityElementHashMap(nums4)); // Expected: 5
        System.out.println("Sorting Approach: " + majorityElementSorting(nums4.clone())); // Expected: 5
        System.out.println();

        // Test Case 5: Negative numbers
        int[] nums5 = {-1, -1, -1, 2, 2};
        System.out.println("Test Case 5 (Negative numbers):");
        ArrayUtils.printArray("Input", nums5);
        System.out.println("Boyer-Moore Voting: " + majorityElementVoting(nums5)); // Expected: -1
        System.out.println("HashMap Approach: " + majorityElementHashMap(nums5)); // Expected: -1
        System.out.println("Sorting Approach: " + majorityElementSorting(nums5.clone())); // Expected: -1
    }
} 