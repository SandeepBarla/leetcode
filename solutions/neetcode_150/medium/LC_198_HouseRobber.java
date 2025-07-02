/*
 * LeetCode Problem 198: House Robber
 * URL: https://leetcode.com/problems/house-robber/
 * Difficulty: Medium
 *
 * Approach:
 * - Use dynamic programming with space optimization.
 * - Track only the max profit from the last two houses using two variables.
 * - At each house, choose to either rob it (and add profit from i-2) or skip it (take profit from i-1).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_198_HouseRobber implements Solution {

  public int rob(int[] nums) {
    int prev2 = 0; // Max profit up to i-2
    int prev1 = 0; // Max profit up to i-1

    for (int amount : nums) {
      int current = Math.max(prev1, prev2 + amount); // Max of skipping or robbing current house
      prev2 = prev1;
      prev1 = current;
    }

    return prev1; // Final max profit
  }

  @Override
  public void run() {
    int[] houses1 = { 2, 7, 9, 3, 1 };
    System.out.println("Max Profit (Example 1): " + rob(houses1)); // 12

    int[] houses2 = { 1, 2, 3, 1 };
    System.out.println("Max Profit (Example 2): " + rob(houses2)); // 4

    int[] houses3 = { 5 };
    System.out.println("Max Profit (Edge Case): " + rob(houses3)); // 5
  }
}