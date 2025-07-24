/*
 * LeetCode: 338 - Counting Bits
 * URL: https://leetcode.com/problems/counting-bits/
 * Difficulty: Medium
 *
 * Two Approaches:
 *
 * 1. O(n log n) - Brute Force:
 *    For each number from 0 to n, count number of 1's using bit shifts.
 *
 * 2. O(n) - Optimized DP:
 *    countBits[i] = countBits[i >> 1] + (i & 1)
 *    - i >> 1 = drop the least significant bit (i / 2)
 *    - (i & 1) = 1 if LSB is 1, else 0
 *
 * Time Complexity:
 * - O(n log n) for brute force (counting bits for each number)
 * - O(n) for optimized version
 *
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.easy;

import java.util.Arrays;

import common.Solution;

public class LC_338_CountingBits implements Solution {

  @Override
  public void run() {
    int n = 5;
    System.out.println("Brute Force: " + Arrays.toString(countBitsBrute(n))); // [0, 1, 1, 2, 1, 2]
    System.out.println("Optimized DP: " + Arrays.toString(countBitsDP(n))); // [0, 1, 1, 2, 1, 2]
  }

  // Approach 1: O(n log n) - Brute Force
  public int[] countBitsBrute(int n) {
    int[] result = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      result[i] = countOnes(i);
    }
    return result;
  }

  private int countOnes(int num) {
    int count = 0;
    while (num > 0) {
      count += num & 1;
      num >>= 1;
    }
    return count;
  }

  // Approach 2: O(n) - Optimized using DP
  public int[] countBitsDP(int n) {
    int[] result = new int[n + 1];
    result[0] = 0;

    for (int i = 1; i <= n; i++) {
      result[i] = result[i >> 1] + (i & 1);
    }

    return result;
  }
}