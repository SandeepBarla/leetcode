/*
 * LeetCode Problem 350: Intersection of Two Arrays II
 * URL: https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. HashMap Approach (Optimal for space) - O(m + n) time, O(min(m, n)) space
 * 2. Two Pointers with Sorting - O(m log m + n log n) time, O(1) space
 * 3. Binary Search Approach - O(m log n) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(m + n) - single pass through both arrays
 * - Approach 2: O(m log m + n log n) - sorting both arrays + linear scan
 * - Approach 3: O(m log n) - for each element in smaller array, binary search in larger
 *
 * Space Complexity:
 * - Approach 1: O(min(m, n)) - store frequency map of smaller array
 * - Approach 2: O(1) - in-place sorting, result doesn't count
 * - Approach 3: O(1) - no extra space beyond result
 */

package solutions.top_interview_questions.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;

public class LC_350_IntersectionOfTwoArraysII implements Solution {

    // Approach 1: HashMap Approach (Optimal for most cases)
    public int[] intersectHashMap(int[] nums1, int[] nums2) {
        // Use smaller array for HashMap to optimize space
        if (nums1.length > nums2.length) {
            return intersectHashMap(nums2, nums1);
        }
        
        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        // Count frequencies in smaller array
        for (int num : nums1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        // Find intersections with larger array
        for (int num : nums2) {
            if (countMap.containsKey(num) && countMap.get(num) > 0) {
                result.add(num);
                countMap.put(num, countMap.get(num) - 1);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Approach 2: Two Pointers with Sorting
    public int[] intersectTwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        return result.stream().mapToInt(k -> k).toArray();
    }

    // Approach 3: Binary Search Approach
    public int[] intersectBinarySearch(int[] nums1, int[] nums2) {
        // Use smaller array for iteration
        if (nums1.length > nums2.length) {
            return intersectBinarySearch(nums2, nums1);
        }
        
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();
        boolean[] used = new boolean[nums2.length];
        
        for (int num : nums1) {
            int index = binarySearchFirstOccurrence(nums2, used, num);
            if (index != -1) {
                result.add(num);
                used[index] = true;
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    private int binarySearchFirstOccurrence(int[] nums, boolean[] used, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target && !used[mid]) {
                result = mid;
                right = mid - 1; // Continue searching left for first occurrence
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }

    @Override
    public void run() {
        // Test Case 1: Basic intersection
        int[] nums1_1 = {1, 2, 2, 1};
        int[] nums2_1 = {2, 2};
        System.out.println("Test Case 1:");
        ArrayUtils.printArray("nums1", nums1_1);
        ArrayUtils.printArray("nums2", nums2_1);
        ArrayUtils.printArray("HashMap Result", intersectHashMap(nums1_1, nums2_1)); // Expected: [2, 2]
        ArrayUtils.printArray("Two Pointers Result", intersectTwoPointers(nums1_1.clone(), nums2_1.clone())); // Expected: [2, 2]
        ArrayUtils.printArray("Binary Search Result", intersectBinarySearch(nums1_1.clone(), nums2_1.clone())); // Expected: [2, 2]
        System.out.println();

        // Test Case 2: Different frequencies
        int[] nums1_2 = {4, 9, 5};
        int[] nums2_2 = {9, 4, 9, 8, 4};
        System.out.println("Test Case 2:");
        ArrayUtils.printArray("nums1", nums1_2);
        ArrayUtils.printArray("nums2", nums2_2);
        ArrayUtils.printArray("HashMap Result", intersectHashMap(nums1_2, nums2_2)); // Expected: [4, 9] or [9, 4]
        ArrayUtils.printArray("Two Pointers Result", intersectTwoPointers(nums1_2.clone(), nums2_2.clone())); // Expected: [4, 9]
        ArrayUtils.printArray("Binary Search Result", intersectBinarySearch(nums1_2.clone(), nums2_2.clone())); // Expected: [4, 9] or [9, 4]
        System.out.println();

        // Test Case 3: No intersection
        int[] nums1_3 = {1, 3, 5};
        int[] nums2_3 = {2, 4, 6};
        System.out.println("Test Case 3 (No intersection):");
        ArrayUtils.printArray("nums1", nums1_3);
        ArrayUtils.printArray("nums2", nums2_3);
        ArrayUtils.printArray("HashMap Result", intersectHashMap(nums1_3, nums2_3)); // Expected: []
        ArrayUtils.printArray("Two Pointers Result", intersectTwoPointers(nums1_3.clone(), nums2_3.clone())); // Expected: []
        ArrayUtils.printArray("Binary Search Result", intersectBinarySearch(nums1_3.clone(), nums2_3.clone())); // Expected: []
        System.out.println();

        // Test Case 4: One array empty
        int[] nums1_4 = {};
        int[] nums2_4 = {1, 2, 3};
        System.out.println("Test Case 4 (Empty array):");
        ArrayUtils.printArray("nums1", nums1_4);
        ArrayUtils.printArray("nums2", nums2_4);
        ArrayUtils.printArray("HashMap Result", intersectHashMap(nums1_4, nums2_4)); // Expected: []
        ArrayUtils.printArray("Two Pointers Result", intersectTwoPointers(nums1_4.clone(), nums2_4.clone())); // Expected: []
        ArrayUtils.printArray("Binary Search Result", intersectBinarySearch(nums1_4.clone(), nums2_4.clone())); // Expected: []
    }
} 