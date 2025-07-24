/*
 * LeetCode Problem 153: Find Minimum in Rotated Sorted Array
 * URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Difficulty: Medium
 *
 * Approach: Modified Binary Search
 * - If the array from lo to hi is already sorted, return nums[lo].
 * - Otherwise, find mid:
 *   - If nums[mid] >= nums[lo], the minimum is in the right half → move lo to mid + 1.
 *   - Else, the minimum is in the left half or at mid → move hi to mid.
 * - At the end, lo will point to the minimum element.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
package solutions.pareto_problem_set.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_153_FindMinimumInRotatedSortedArray implements Solution {

  public int findMin(int[] nums) {
    int lo = 0;
    int hi = nums.length - 1;

    while (lo < hi) {
      if (nums[lo] < nums[hi])
        return nums[lo];

      int mid = lo + (hi - lo) / 2;

      if (nums[mid] >= nums[lo]) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }

    return nums[lo];
  }

  @Override
  public void run() {
    int[] nums1 = { 3, 4, 5, 1, 2 };
    ArrayUtils.printArray("Input Array", nums1);
    System.out.println("Minimum Element: " + findMin(nums1));

    int[] nums2 = { 4, 5, 6, 7, 0, 1, 2 };
    ArrayUtils.printArray("Input Array", nums2);
    System.out.println("Minimum Element: " + findMin(nums2));

    int[] nums3 = { 11, 13, 15, 17 };
    ArrayUtils.printArray("Input Array", nums3);
    System.out.println("Minimum Element: " + findMin(nums3));
  }
}