/*
 * LeetCode Problem 238: Product of Array Except Self
 * URL: https://leetcode.com/problems/product-of-array-except-self/
 * Difficulty: Medium
 *
 * Approach: Prefix and Suffix Product (Optimized with O(1) extra space)
 * - First pass builds prefix products directly into the result array.
 * - Second pass accumulates suffix products in a variable and multiplies it into the result.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) (excluding output array)
 */
package solutions.pareto_problem_set.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_238_ProductOfArrayExceptSelf implements Solution {

  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];

    // Prefix products
    res[0] = 1;
    for (int i = 1; i < n; i++) {
      res[i] = res[i - 1] * nums[i - 1];
    }

    // Multiply with suffix products
    int suffix = 1;
    for (int i = n - 1; i >= 0; i--) {
      res[i] *= suffix;
      suffix *= nums[i];
    }

    return res;
  }

  @Override
  public void run() {
    // Test Case 1
    int[] nums1 = { 1, 2, 3, 4 };
    ArrayUtils.printArray("Input", nums1);
    int[] result1 = productExceptSelf(nums1);
    ArrayUtils.printArray("Output", result1);

    // Test Case 2
    int[] nums2 = { -1, 1, 0, -3, 3 };
    ArrayUtils.printArray("Input", nums2);
    int[] result2 = productExceptSelf(nums2);
    ArrayUtils.printArray("Output", result2);
  }
}