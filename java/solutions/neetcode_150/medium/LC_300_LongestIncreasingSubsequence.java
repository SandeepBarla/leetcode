/*
 * LeetCode Problem 300: Longest Increasing Subsequence
 * URL: https://leetcode.com/problems/longest-increasing-subsequence/
 * Difficulty: Medium
 *
 * Approach 1 (DP - O(n^2)):
 * - Use dp[i] to store the length of LIS ending at index i.
 * - For each i, look at all j < i where nums[j] < nums[i], and update dp[i] accordingly.
 * - Result is the max in the dp array.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * Approach 2 (Binary Search - O(n log n)):
 * - Maintain a list that builds the increasing subsequence (not actual LIS but same length).
 * - For each num in nums:
 *   - If num > last element, append it.
 *   - Else, binary search to replace the first element ≥ num (to maintain a lower tail).
 * - Result is the length of the list.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.Solution;

public class LC_300_LongestIncreasingSubsequence implements Solution {

  // O(n^2) DP approach
  public int lengthOfLIS_DP(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    int maxLen = 0;

    for (int i = 0; i < n; i++) {
      dp[i] = 1; // Base case: each number is a subsequence of length 1
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      maxLen = Math.max(maxLen, dp[i]);
    }

    return maxLen;
  }

  // O(n log n) Binary Search approach
  public int lengthOfLIS_BS(int[] nums) {
    List<Integer> tails = new ArrayList<>();

    for (int num : nums) {
      int index = Collections.binarySearch(tails, num);
      if (index < 0) {
        index = -(index + 1); // insertion point
      }

      if (index == tails.size()) {
        tails.add(num); // new largest element extends LIS
      } else {
        tails.set(index, num); // replace to maintain smallest tail
      }
    }

    return tails.size();
  }

  @Override
  public void run() {
    int[] input = { 10, 9, 2, 5, 3, 7, 101, 18 };
    System.out.println("LIS Length (DP): " + lengthOfLIS_DP(input)); // Expected: 4
    System.out.println("LIS Length (Binary Search): " + lengthOfLIS_BS(input)); // Expected: 4
  }
}