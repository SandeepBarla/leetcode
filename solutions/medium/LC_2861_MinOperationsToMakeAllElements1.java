/*
 * LeetCode Problem 2861: Maximum Number of Alloys
 * URL: https://leetcode.com/problems/apply-operations-to-make-all-array-elements-equal-to-one/
 * Difficulty: Medium
 *
 * Approach: Greedy + Bit Manipulation
 * - Iterate through the array.
 * - When a 0 is found, flip the current and next two elements to attempt eliminating 0s.
 * - Return the number of operations if the array ends in all 1s, else return -1.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
package solutions.medium;

import common.Solution;

public class LC_2861_MinOperationsToMakeAllElements1 implements Solution {

  public int minOperations(int[] nums) {
    int operations = 0;

    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] == 0) {
        nums[i] = 1;
        nums[i + 1] ^= 1;
        nums[i + 2] ^= 1;
        operations++;
      }
    }

    return (nums[nums.length - 1] == 1 && nums[nums.length - 2] == 1) ? operations : -1;
  }

  @Override
  public void run() {
    // Test Case 1: Expected output = 3
    int[] nums1 = { 0, 1, 0, 1, 0 };
    System.out.println("Min operations (Test Case 1): " + minOperations(nums1));

    // Test Case 2: Expected output = -1
    int[] nums2 = { 0, 1, 0, 1, 1 };
    System.out.println("Min operations (Test Case 2): " + minOperations(nums2));

    // Test Case 3: Expected output = 0
    int[] nums3 = { 1, 1, 1, 1 };
    System.out.println("Min operations (Test Case 3): " + minOperations(nums3));
  }
}