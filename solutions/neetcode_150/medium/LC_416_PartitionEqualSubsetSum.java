/*
 * LeetCode Problem 416: Partition Equal Subset Sum
 * URL: https://leetcode.com/problems/partition-equal-subset-sum/
 * Difficulty: Medium
 *
 * Approach:
 * - First, check if total sum is even. If it's odd, partitioning is impossible.
 * - Let the target be totalSum / 2. The problem reduces to:
 *     "Can we pick a subset of nums that sums up to target?"
 * - Use bottom-up 1D DP to solve subset sum:
 *     dp[i] = true if there's a subset that sums to 'i'
 * - For each number, iterate from target → num (in reverse)
 *     and update dp[sum] |= dp[sum - num]
 *
 * Time Complexity: O(n * sum/2) where n = nums.length
 * Space Complexity: O(sum/2) using 1D DP array
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_416_PartitionEqualSubsetSum implements Solution {

  public boolean canPartition(int[] nums) {
    int totalSum = 0;

    // Calculate total sum
    for (int num : nums) {
      totalSum += num;
    }

    // If total sum is odd, we can't split it equally
    if (totalSum % 2 != 0) {
      return false;
    }

    int target = totalSum / 2;

    // dp[i] = true if subset with sum 'i' is possible
    boolean[] dp = new boolean[target + 1];
    dp[0] = true; // base case: zero sum is always achievable

    for (int num : nums) {
      for (int sum = target; sum >= num; sum--) {
        dp[sum] |= dp[sum - num];
      }
    }

    return dp[target];
  }

  @Override
  public void run() {
    int[] nums = { 1, 5, 11, 5 };
    boolean result = canPartition(nums);
    System.out.println("Can partition? " + result); // Expected: true
  }
}