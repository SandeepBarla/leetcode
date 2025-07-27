/*
 * LeetCode Problem: 287. Find the Duplicate Number
 * URL: https://leetcode.com/problems/find-the-duplicate-number/
 * Difficulty: Medium
 * 
 * Problem Description:
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * 
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * 
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * 
 * Example 3:
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 * 
 * Constraints:
 * - 1 <= n <= 10^5
 * - nums.length == n + 1
 * - 1 <= nums[i] <= n
 * - All the integers in nums appear only once except for precisely one integer which appears two or more times.
 * 
 * Follow up:
 * - How can we prove that at least one duplicate number must exist in nums?
 * - Can you solve the problem in linear runtime complexity?
 */

package solutions.neetcode_150.medium;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import common.Solution;

public class LC_287_FindTheDuplicateNumber implements Solution {
    
    /**
     * Approach 1: Floyd's Cycle Detection (Tortoise and Hare) - Optimized Original
     * 
     * Algorithm:
     * 1. Treat array as a linked list where nums[i] points to index nums[i]
     * 2. Since there's a duplicate, there must be a cycle in this "linked list"
     * 3. Use Floyd's algorithm: slow moves 1 step, fast moves 2 steps
     * 4. When they meet, reset one pointer to start and move both 1 step at a time
     * 5. They will meet at the cycle entrance (duplicate number)
     * 
     * Time Complexity: O(n) - each element visited at most a few times
     * Space Complexity: O(1) - only using two pointers
     */
    public int findDuplicateFloyd(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        // Phase 1: Find intersection point in the cycle
        int slow = nums[0];
        int fast = nums[0];
        
        // Move slow one step and fast two steps
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Phase 2: Find the entrance to the cycle (duplicate number)
        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return fast;
    }
    
    /**
     * Approach 2: Binary Search on Answer
     * 
     * Algorithm:
     * 1. Binary search on the range [1, n] to find the duplicate
     * 2. For each candidate mid, count numbers <= mid in the array
     * 3. If count > mid, duplicate is in [1, mid], else in [mid+1, n]
     * 4. This works because if no duplicates exist, count would equal mid
     * 
     * Time Complexity: O(n log n) - binary search * counting
     * Space Complexity: O(1) - only using variables
     */
    public int findDuplicateBinarySearch(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        int left = 1;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countNumbersLessOrEqual(nums, mid);
            
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Approach 3: HashSet (Uses Extra Space)
     * 
     * Algorithm:
     * 1. Iterate through array and add each number to HashSet
     * 2. If number already exists in set, it's the duplicate
     * 3. Simple but violates the "constant space" constraint
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(n) - HashSet storage
     * Note: Violates space constraint but included for comparison
     */
    public int findDuplicateHashSet(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        
        return -1; // Should never reach here with valid input
    }
    
    /**
     * Approach 4: Sorting (Modifies Array)
     * 
     * Algorithm:
     * 1. Sort the array (modifies original array)
     * 2. Iterate through and find first pair of adjacent equal elements
     * 3. Violates "no modification" constraint but efficient
     * 
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(1) - if using in-place sorting
     * Note: Violates modification constraint but included for comparison
     */
    public int findDuplicateSorting(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        // Make a copy to avoid modifying original array
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        
        for (int i = 1; i < sortedNums.length; i++) {
            if (sortedNums[i] == sortedNums[i - 1]) {
                return sortedNums[i];
            }
        }
        
        return -1; // Should never reach here with valid input
    }
    
    /**
     * Approach 5: Negative Marking (Modifies Array)
     * 
     * Algorithm:
     * 1. Use array indices as hash table
     * 2. For each number, mark nums[abs(num)] as negative
     * 3. If nums[abs(num)] is already negative, abs(num) is duplicate
     * 4. Restore array to original state after finding duplicate
     * 
     * Time Complexity: O(n) - linear scan
     * Space Complexity: O(1) - only using variables
     * Note: Modifies array temporarily but restores it
     */
    public int findDuplicateNegativeMarking(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        int duplicate = -1;
        
        // Find the duplicate
        for (int num : nums) {
            int index = Math.abs(num);
            if (nums[index] < 0) {
                duplicate = index;
                break;
            }
            nums[index] = -nums[index];
        }
        
        // Restore the array to original state
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        
        return duplicate;
    }
    
    // Helper method for binary search approach
    private int countNumbersLessOrEqual(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num <= target) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public void run() {
        System.out.println("Testing Find the Duplicate Number Implementations");
        System.out.println("===============================================");
        
        // Test Case 1: Example from problem
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("Test Case 1:");
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Expected: 2");
        System.out.println("Floyd's Cycle: " + findDuplicateFloyd(nums1.clone()));
        System.out.println("Binary Search: " + findDuplicateBinarySearch(nums1.clone()));
        System.out.println("HashSet: " + findDuplicateHashSet(nums1.clone()));
        System.out.println("Sorting: " + findDuplicateSorting(nums1.clone()));
        System.out.println("Negative Marking: " + findDuplicateNegativeMarking(nums1.clone()));
        System.out.println();
        
        // Test Case 2: Example from problem
        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println("Test Case 2:");
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Expected: 3");
        System.out.println("Floyd's Cycle: " + findDuplicateFloyd(nums2.clone()));
        System.out.println("Binary Search: " + findDuplicateBinarySearch(nums2.clone()));
        System.out.println("HashSet: " + findDuplicateHashSet(nums2.clone()));
        System.out.println("Sorting: " + findDuplicateSorting(nums2.clone()));
        System.out.println("Negative Marking: " + findDuplicateNegativeMarking(nums2.clone()));
        System.out.println();
        
        // Test Case 3: All same elements
        int[] nums3 = {3, 3, 3, 3, 3};
        System.out.println("Test Case 3:");
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("Expected: 3");
        System.out.println("Floyd's Cycle: " + findDuplicateFloyd(nums3.clone()));
        System.out.println("Binary Search: " + findDuplicateBinarySearch(nums3.clone()));
        System.out.println("HashSet: " + findDuplicateHashSet(nums3.clone()));
        System.out.println("Sorting: " + findDuplicateSorting(nums3.clone()));
        System.out.println("Negative Marking: " + findDuplicateNegativeMarking(nums3.clone()));
        System.out.println();
        
        // Test Case 4: Duplicate at beginning
        int[] nums4 = {1, 1, 2, 3, 4};
        System.out.println("Test Case 4:");
        System.out.println("Array: " + Arrays.toString(nums4));
        System.out.println("Expected: 1");
        System.out.println("Floyd's Cycle: " + findDuplicateFloyd(nums4.clone()));
        System.out.println("Binary Search: " + findDuplicateBinarySearch(nums4.clone()));
        System.out.println("HashSet: " + findDuplicateHashSet(nums4.clone()));
        System.out.println("Sorting: " + findDuplicateSorting(nums4.clone()));
        System.out.println("Negative Marking: " + findDuplicateNegativeMarking(nums4.clone()));
        System.out.println();
        
        // Test Case 5: Duplicate at end
        int[] nums5 = {1, 2, 3, 4, 4};
        System.out.println("Test Case 5:");
        System.out.println("Array: " + Arrays.toString(nums5));
        System.out.println("Expected: 4");
        System.out.println("Floyd's Cycle: " + findDuplicateFloyd(nums5.clone()));
        System.out.println("Binary Search: " + findDuplicateBinarySearch(nums5.clone()));
        System.out.println("HashSet: " + findDuplicateHashSet(nums5.clone()));
        System.out.println("Sorting: " + findDuplicateSorting(nums5.clone()));
        System.out.println("Negative Marking: " + findDuplicateNegativeMarking(nums5.clone()));
        System.out.println();
        
        // Test Case 6: Large array with duplicate in middle
        int[] nums6 = {1, 2, 3, 4, 5, 6, 7, 8, 5};
        System.out.println("Test Case 6:");
        System.out.println("Array: " + Arrays.toString(nums6));
        System.out.println("Expected: 5");
        System.out.println("Floyd's Cycle: " + findDuplicateFloyd(nums6.clone()));
        System.out.println("Binary Search: " + findDuplicateBinarySearch(nums6.clone()));
        System.out.println("HashSet: " + findDuplicateHashSet(nums6.clone()));
        System.out.println("Sorting: " + findDuplicateSorting(nums6.clone()));
        System.out.println("Negative Marking: " + findDuplicateNegativeMarking(nums6.clone()));
        System.out.println();
        
        // Test Case 7: Two elements
        int[] nums7 = {1, 1};
        System.out.println("Test Case 7:");
        System.out.println("Array: " + Arrays.toString(nums7));
        System.out.println("Expected: 1");
        System.out.println("Floyd's Cycle: " + findDuplicateFloyd(nums7.clone()));
        System.out.println("Binary Search: " + findDuplicateBinarySearch(nums7.clone()));
        System.out.println("HashSet: " + findDuplicateHashSet(nums7.clone()));
        System.out.println("Sorting: " + findDuplicateSorting(nums7.clone()));
        System.out.println("Negative Marking: " + findDuplicateNegativeMarking(nums7.clone()));
        
        System.out.println();
        System.out.println("Performance Analysis:");
        System.out.println("=====================");
        System.out.println("Floyd's Cycle Detection: O(n) time, O(1) space - OPTIMAL for constraints");
        System.out.println("Binary Search: O(n log n) time, O(1) space - Good alternative");
        System.out.println("HashSet: O(n) time, O(n) space - Fast but violates space constraint");
        System.out.println("Sorting: O(n log n) time, O(1) space - Violates no-modification constraint");
        System.out.println("Negative Marking: O(n) time, O(1) space - Temporarily modifies array");
    }
} 