/*
 * LeetCode Problem 152: Maximum Product Subarray
 * URL: https://leetcode.com/problems/maximum-product-subarray/
 * Difficulty: Medium
 *
 * Approach:
 * - Use dynamic tracking of max and min product ending at each index.
 * - Because of negatives, the minimum can become max when multiplied by a negative.
 * - Reset if we encounter zero, but compare against global max before reset.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_152_MaximumProductSubarray implements Solution {

  public int maxProduct(int[] nums) {
    int maxSoFar = nums[0]; // Global maximum product
    int currMax = nums[0]; // Current max ending at index i
    int currMin = nums[0]; // Current min ending at index i

    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];

      // If num is negative, swapping max and min handles the sign flip
      if (num < 0) {
        int temp = currMax;
        currMax = currMin;
        currMin = temp;
      }

      // Update current max/min
      currMax = Math.max(num, num * currMax);
      currMin = Math.min(num, num * currMin);

      // Update global max
      maxSoFar = Math.max(maxSoFar, currMax);
    }

    return maxSoFar;
  }

  @Override
  public void run() {
    int[] nums1 = { 2, 3, -2, 4 };
    int[] nums2 = { -2, 0, -1 };
    int[] nums3 = { -2, 3, -4 };
    int[] nums4 = { 0, 2 };

    System.out.println("Max Product: " + maxProduct(nums1)); // 6
    System.out.println("Max Product: " + maxProduct(nums2)); // 0
    System.out.println("Max Product: " + maxProduct(nums3)); // 24
    System.out.println("Max Product: " + maxProduct(nums4)); // 2
  }
}