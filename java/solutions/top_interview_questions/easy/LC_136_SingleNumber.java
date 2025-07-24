/*
 * LeetCode Problem 136: Single Number
 * URL: https://leetcode.com/problems/single-number/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. XOR Approach (Optimal) - O(n) time, O(1) space
 * 2. HashMap Approach - O(n) time, O(n) space
 * 3. HashSet Approach - O(n) time, O(n) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass with XOR operation
 * - Approach 2: O(n) - single pass to build map, another to find single
 * - Approach 3: O(n) - single pass with HashSet operations
 *
 * Space Complexity:
 * - Approach 1: O(1) - only using one variable
 * - Approach 2: O(n) - HashMap storing all unique numbers
 * - Approach 3: O(n) - HashSet storing numbers
 */

package solutions.top_interview_questions.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import common.ArrayUtils;
import common.Solution;

public class LC_136_SingleNumber implements Solution {

    // Approach 1: XOR Approach (Optimal)
    // Key XOR properties: A XOR 0 = A, A XOR A = 0
    public int singleNumberXOR(int[] nums) {
        int result = 0;
        
        // XOR all elements - duplicates cancel out, single remains
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }

    // Approach 2: HashMap Approach
    public int singleNumberHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        // Count frequency of each number
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        // Find number with frequency 1
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        
        return -1; // Should never reach here based on problem constraints
    }

    // Approach 3: HashSet Approach
    public int singleNumberHashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                seen.remove(num); // Remove if seen before (duplicate)
            } else {
                seen.add(num); // Add if first time seeing
            }
        }
        
        // Only one element should remain
        return seen.iterator().next();
    }

    @Override
    public void run() {
        // Test Case 1: Normal case with multiple duplicates
        int[] nums1 = {2, 2, 1};
        System.out.println("Test Case 1:");
        ArrayUtils.printArray("Input", nums1);
        System.out.println("XOR Approach: " + singleNumberXOR(nums1)); // Expected: 1
        System.out.println("HashMap Approach: " + singleNumberHashMap(nums1)); // Expected: 1
        System.out.println("HashSet Approach: " + singleNumberHashSet(nums1)); // Expected: 1
        System.out.println();

        // Test Case 2: Larger array
        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("Test Case 2:");
        ArrayUtils.printArray("Input", nums2);
        System.out.println("XOR Approach: " + singleNumberXOR(nums2)); // Expected: 4
        System.out.println("HashMap Approach: " + singleNumberHashMap(nums2)); // Expected: 4
        System.out.println("HashSet Approach: " + singleNumberHashSet(nums2)); // Expected: 4
        System.out.println();

        // Test Case 3: Single element array
        int[] nums3 = {1};
        System.out.println("Test Case 3 (Edge case):");
        ArrayUtils.printArray("Input", nums3);
        System.out.println("XOR Approach: " + singleNumberXOR(nums3)); // Expected: 1
        System.out.println("HashMap Approach: " + singleNumberHashMap(nums3)); // Expected: 1
        System.out.println("HashSet Approach: " + singleNumberHashSet(nums3)); // Expected: 1
        System.out.println();

        // Test Case 4: Negative numbers
        int[] nums4 = {-1, -1, -3};
        System.out.println("Test Case 4 (Negative numbers):");
        ArrayUtils.printArray("Input", nums4);
        System.out.println("XOR Approach: " + singleNumberXOR(nums4)); // Expected: -3
        System.out.println("HashMap Approach: " + singleNumberHashMap(nums4)); // Expected: -3
        System.out.println("HashSet Approach: " + singleNumberHashSet(nums4)); // Expected: -3
    }
} 