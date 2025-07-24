/*
 * LeetCode Problem 46: Permutations
 * URL: https://leetcode.com/problems/permutations/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Backtracking with Used Array - O(n! * n) time, O(n) space
 * 2. Backtracking with Swapping - O(n! * n) time, O(n) space
 * 3. Iterative Approach - O(n! * n) time, O(n! * n) space
 * 4. Built-in with Collections - O(n! * n) time, O(n) space
 *
 * Time Complexity:
 * - All approaches: O(n! * n) - n! permutations, each taking O(n) to build
 *
 * Space Complexity:
 * - Approaches 1,2,4: O(n) - recursion depth + current permutation
 * - Approach 3: O(n! * n) - storing all intermediate results
 */

package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import common.Solution;

public class LC_46_Permutations implements Solution {

    // Approach 1: Backtracking with Used Array (Most Common)
    public List<List<Integer>> permuteBacktrackingUsed(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackUsed(result, new ArrayList<>(), nums, used);
        return result;
    }
    
    private void backtrackUsed(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] used) {
        // Base case: current permutation is complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try each unused number
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);
                used[i] = true;
                backtrackUsed(result, current, nums, used);
                current.remove(current.size() - 1); // backtrack
                used[i] = false; // backtrack
            }
        }
    }

    // Approach 2: Backtracking with Swapping (Optimal Space)
    public List<List<Integer>> permuteBacktrackingSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSwap(result, nums, 0);
        return result;
    }
    
    private void backtrackSwap(List<List<Integer>> result, int[] nums, int start) {
        // Base case: we've fixed all positions
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        
        // Try each element from start to end at position start
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            backtrackSwap(result, nums, start + 1);
            swap(nums, start, i); // backtrack
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Approach 3: Iterative Approach
    public List<List<Integer>> permuteIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        
        for (int num : nums) {
            List<List<Integer>> newResult = new ArrayList<>();
            
            for (List<Integer> current : result) {
                // Insert num at each possible position
                for (int i = 0; i <= current.size(); i++) {
                    List<Integer> newPermutation = new ArrayList<>(current);
                    newPermutation.add(i, num);
                    newResult.add(newPermutation);
                }
            }
            
            result = newResult;
        }
        
        return result;
    }

    // Approach 4: Using Collections (Educational)
    public List<List<Integer>> permuteWithCollections(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        
        generatePermutations(result, numsList, 0);
        return result;
    }
    
    private void generatePermutations(List<List<Integer>> result, List<Integer> nums, int start) {
        if (start == nums.size()) {
            result.add(new ArrayList<>(nums));
            return;
        }
        
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            generatePermutations(result, nums, start + 1);
            Collections.swap(nums, start, i);
        }
    }

    // Helper method to compare results
    private boolean compareResults(List<List<Integer>> result1, List<List<Integer>> result2) {
        if (result1.size() != result2.size()) return false;
        
        // Convert to sets for comparison (order doesn't matter)
        return result1.containsAll(result2) && result2.containsAll(result1);
    }

    @Override
    public void run() {
        // Test Case 1: Basic case [1,2,3]
        int[] nums1 = {1, 2, 3};
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        
        List<List<Integer>> result1_1 = permuteBacktrackingUsed(nums1);
        System.out.println("Backtracking Used (" + result1_1.size() + "): " + result1_1);
        // Expected: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        
        List<List<Integer>> result1_2 = permuteBacktrackingSwap(nums1.clone());
        System.out.println("Backtracking Swap (" + result1_2.size() + "): " + result1_2);
        
        List<List<Integer>> result1_3 = permuteIterative(nums1);
        System.out.println("Iterative (" + result1_3.size() + "): " + result1_3);
        
        List<List<Integer>> result1_4 = permuteWithCollections(nums1);
        System.out.println("Collections (" + result1_4.size() + "): " + result1_4);
        System.out.println();

        // Test Case 2: Two elements [0,1]
        int[] nums2 = {0, 1};
        System.out.println("Test Case 2: " + Arrays.toString(nums2));
        
        List<List<Integer>> result2 = permuteBacktrackingUsed(nums2);
        System.out.println("Backtracking Used: " + result2); // Expected: [[0,1],[1,0]]
        System.out.println();

        // Test Case 3: Single element [1]
        int[] nums3 = {1};
        System.out.println("Test Case 3: " + Arrays.toString(nums3));
        
        List<List<Integer>> result3 = permuteBacktrackingUsed(nums3);
        System.out.println("Backtracking Used: " + result3); // Expected: [[1]]
        System.out.println();

        // Test Case 4: Negative numbers [-1,0,1]
        int[] nums4 = {-1, 0, 1};
        System.out.println("Test Case 4: " + Arrays.toString(nums4));
        
        List<List<Integer>> result4 = permuteBacktrackingUsed(nums4);
        System.out.println("Total permutations: " + result4.size()); // Expected: 6
        System.out.println("Sample permutations: " + result4.subList(0, Math.min(3, result4.size())));
        System.out.println();

        // Test Case 5: Performance test [1,2,3,4]
        int[] nums5 = {1, 2, 3, 4};
        System.out.println("Test Case 5: " + Arrays.toString(nums5) + " (performance test)");
        
        long start = System.currentTimeMillis();
        List<List<Integer>> result5_1 = permuteBacktrackingUsed(nums5);
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Backtracking Used: " + result5_1.size() + " permutations in " + time1 + "ms");
        
        start = System.currentTimeMillis();
        List<List<Integer>> result5_2 = permuteBacktrackingSwap(nums5.clone());
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Backtracking Swap: " + result5_2.size() + " permutations in " + time2 + "ms");
        
        start = System.currentTimeMillis();
        List<List<Integer>> result5_3 = permuteIterative(nums5);
        long time3 = System.currentTimeMillis() - start;
        System.out.println("Iterative: " + result5_3.size() + " permutations in " + time3 + "ms");
        
        // Verify all methods produce same result
        boolean same1 = compareResults(result5_1, result5_2);
        boolean same2 = compareResults(result5_1, result5_3);
        System.out.println("All methods produce same result: " + (same1 && same2));
        System.out.println();

        // Test Case 6: Larger array [1,2,3,4,5] - show exponential growth
        int[] nums6 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 6: " + Arrays.toString(nums6) + " (factorial growth demo)");
        
        start = System.currentTimeMillis();
        List<List<Integer>> result6 = permuteBacktrackingUsed(nums6);
        long time6 = System.currentTimeMillis() - start;
        System.out.println("Backtracking Used: " + result6.size() + " permutations in " + time6 + "ms");
        System.out.println("Expected: 5! = 120 permutations");
        System.out.println("First 5 permutations: " + result6.subList(0, 5));
    }
} 