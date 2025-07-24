/*
 * LeetCode Problem 34: Find First and Last Position of Element in Sorted Array
 * URL: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Two Binary Searches (Optimal) - O(log n) time, O(1) space
 * 2. Linear Search - O(n) time, O(1) space
 * 3. Single Binary Search + Expand - O(log n + k) time, O(1) space (k = range size)
 *
 * Time Complexity:
 * - Approach 1: O(log n) - two binary searches
 * - Approach 2: O(n) - linear scan
 * - Approach 3: O(log n + k) - binary search + expand around target
 *
 * Space Complexity:
 * - All approaches: O(1) - only using variables
 */

package solutions.top_interview_questions.medium;

import java.util.Arrays;

import common.Solution;

public class LC_34_FindFirstAndLastPositionOfElementInSortedArray implements Solution {

    // Approach 1: Two Binary Searches (Optimal)
    public int[] searchRangeBinarySearch(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }
    
    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int firstPos = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                firstPos = mid;
                right = mid - 1; // Continue searching in left half
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return firstPos;
    }
    
    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int lastPos = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                lastPos = mid;
                left = mid + 1; // Continue searching in right half
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return lastPos;
    }

    // Approach 2: Linear Search
    public int[] searchRangeLinear(int[] nums, int target) {
        int first = -1, last = -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) {
                    first = i; // First occurrence
                }
                last = i; // Keep updating last occurrence
            }
        }
        
        return new int[]{first, last};
    }

    // Approach 3: Single Binary Search + Expand
    public int[] searchRangeExpandFromCenter(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        
        // Find any occurrence using binary search
        int anyPos = binarySearchAny(nums, target);
        if (anyPos == -1) return new int[]{-1, -1};
        
        // Expand left and right from found position
        int left = anyPos, right = anyPos;
        
        while (left > 0 && nums[left - 1] == target) {
            left--;
        }
        
        while (right < nums.length - 1 && nums[right + 1] == target) {
            right++;
        }
        
        return new int[]{left, right};
    }
    
    private int binarySearchAny(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    @Override
    public void run() {
        // Test Case 1: Target exists multiple times
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        System.out.println("Test Case 1: " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Binary Search: " + Arrays.toString(searchRangeBinarySearch(nums1, target1))); // Expected: [3, 4]
        System.out.println("Linear: " + Arrays.toString(searchRangeLinear(nums1, target1))); // Expected: [3, 4]
        System.out.println("Expand: " + Arrays.toString(searchRangeExpandFromCenter(nums1, target1))); // Expected: [3, 4]
        System.out.println();

        // Test Case 2: Target exists once
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        System.out.println("Test Case 2: " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Binary Search: " + Arrays.toString(searchRangeBinarySearch(nums2, target2))); // Expected: [-1, -1]
        System.out.println("Linear: " + Arrays.toString(searchRangeLinear(nums2, target2))); // Expected: [-1, -1]
        System.out.println("Expand: " + Arrays.toString(searchRangeExpandFromCenter(nums2, target2))); // Expected: [-1, -1]
        System.out.println();

        // Test Case 3: Empty array
        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Test Case 3: " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Binary Search: " + Arrays.toString(searchRangeBinarySearch(nums3, target3))); // Expected: [-1, -1]
        System.out.println("Linear: " + Arrays.toString(searchRangeLinear(nums3, target3))); // Expected: [-1, -1]
        System.out.println("Expand: " + Arrays.toString(searchRangeExpandFromCenter(nums3, target3))); // Expected: [-1, -1]
        System.out.println();

        // Test Case 4: Single element array (target found)
        int[] nums4 = {1};
        int target4 = 1;
        System.out.println("Test Case 4: " + Arrays.toString(nums4) + ", target = " + target4);
        System.out.println("Binary Search: " + Arrays.toString(searchRangeBinarySearch(nums4, target4))); // Expected: [0, 0]
        System.out.println("Linear: " + Arrays.toString(searchRangeLinear(nums4, target4))); // Expected: [0, 0]
        System.out.println("Expand: " + Arrays.toString(searchRangeExpandFromCenter(nums4, target4))); // Expected: [0, 0]
        System.out.println();

        // Test Case 5: All elements are the same
        int[] nums5 = {2, 2, 2, 2, 2};
        int target5 = 2;
        System.out.println("Test Case 5: " + Arrays.toString(nums5) + ", target = " + target5);
        System.out.println("Binary Search: " + Arrays.toString(searchRangeBinarySearch(nums5, target5))); // Expected: [0, 4]
        System.out.println("Linear: " + Arrays.toString(searchRangeLinear(nums5, target5))); // Expected: [0, 4]
        System.out.println("Expand: " + Arrays.toString(searchRangeExpandFromCenter(nums5, target5))); // Expected: [0, 4]
    }
} 