/*
 * LeetCode Problem 213: House Robber II
 * URL: https://leetcode.com/problems/house-robber-ii/
 * Difficulty: Medium
 *
 * Problem Summary:
 * - You cannot rob two adjacent houses.
 * - Houses are arranged in a circle — first and last are adjacent.
 *
 * Strategy:
 * - Since the array is circular, we break it into two cases:
 *   1. Rob from index 0 to n-2 (exclude last house)
 *   2. Rob from index 1 to n-1 (exclude first house)
 * - Apply House Robber I optimized DP (O(1) space) to both cases.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_213_HouseRobberII implements Solution {

  public int rob(int[] nums) {
    int n = nums.length;

    // Base case: only one house
    if (n == 1)
      return nums[0];

    // Case 1: Rob houses from index 0 to n-2
    int max1 = robLinear(nums, 0, n - 2);

    // Case 2: Rob houses from index 1 to n-1
    int max2 = robLinear(nums, 1, n - 1);

    // Return the better of the two scenarios
    return Math.max(max1, max2);
  }

  // Helper method to solve House Robber I logic on linear subarray
  private int robLinear(int[] nums, int start, int end) {
    int prev2 = 0; // Profit from i-2 house
    int prev1 = 0; // Profit from i-1 house

    for (int i = start; i <= end; i++) {
      // Decide whether to rob this house or skip it
      int curr = Math.max(prev1, prev2 + nums[i]);

      // Slide the window forward
      prev2 = prev1;
      prev1 = curr;
    }

    return prev1;
  }

  @Override
  public void run() {
    int[] nums1 = { 2, 3, 2 }; // Expected: 3
    int[] nums2 = { 1, 2, 3, 1 }; // Expected: 4
    int[] nums3 = { 1, 2, 3 }; // Expected: 3

    System.out.println("Max profit 1: " + rob(nums1));
    System.out.println("Max profit 2: " + rob(nums2));
    System.out.println("Max profit 3: " + rob(nums3));
  }
}