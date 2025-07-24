/*
 * LeetCode Problem 134: Gas Station
 * URL: https://leetcode.com/problems/gas-station/
 * Difficulty: Medium
 *
 * Approach:
 * - If total gas is less than total cost, it's impossible → return -1.
 * - Traverse the array, maintaining:
 *   - totalSum: total gas - total cost (to check if a solution is possible)
 *   - currSum: current segment sum from the candidate starting index
 *   - startIndex: candidate index where we might start the trip
 * - If currSum becomes negative, reset the start index to i + 1 and currSum to 0.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_134_GasStation implements Solution {

  public int canCompleteCircuit(int[] gas, int[] cost) {
    int totalSum = 0;
    int currSum = 0;
    int startIndex = 0;

    for (int i = 0; i < gas.length; i++) {
      int diff = gas[i] - cost[i];
      totalSum += diff;
      currSum += diff;

      if (currSum < 0) {
        // can't reach the next station, try the next index as the start
        startIndex = i + 1;
        currSum = 0;
      }
    }

    return totalSum >= 0 ? startIndex : -1;
  }

  @Override
  public void run() {
    int[] gas = { 1, 2, 3, 4, 5 };
    int[] cost = { 3, 4, 5, 1, 2 };

    int start = canCompleteCircuit(gas, cost);
    System.out.println("Start Index: " + start); // Expected: 3
  }
}