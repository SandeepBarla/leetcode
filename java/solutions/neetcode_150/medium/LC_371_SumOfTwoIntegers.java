/*
 * LeetCode Problem 371: Sum of Two Integers
 * URL: https://leetcode.com/problems/sum-of-two-integers/
 * Difficulty: Medium
 *
 * Approach 1: Iterative Bit Manipulation
 * - XOR (^) gives sum without carry.
 * - AND (&) gives carry bits; shift left to apply in next iteration.
 * - Repeat until carry becomes 0.
 *
 * Approach 2: Recursive Bit Manipulation
 * - Same logic as iterative, done via recursion.
 * - Base case: if carry is 0, return sum.
 *
 * Time Complexity: O(1) — bounded by 32 bits
 * Space Complexity:
 *   - Iterative: O(1)
 *   - Recursive: O(1) expected, O(32) max stack
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_371_SumOfTwoIntegers implements Solution {

  // ✅ Iterative version using bitwise operations
  public int getSumIterative(int a, int b) {
    while (b != 0) {
      int carry = a & b; // common bits → carry
      a = a ^ b; // add without carry
      b = carry << 1; // carry applied to next higher bit
    }
    return a;
  }

  // ✅ Recursive version using the same logic
  public int getSumRecursive(int a, int b) {
    if (b == 0)
      return a; // no carry → return result
    return getSumRecursive(a ^ b, (a & b) << 1); // recurse with sum and carry
  }

  @Override
  public void run() {
    System.out.println("Iterative getSum(2, 3): " + getSumIterative(2, 3)); // 5
    System.out.println("Iterative getSum(-1, 1): " + getSumIterative(-1, 1)); // 0
    System.out.println("Iterative getSum(-3, -4): " + getSumIterative(-3, -4)); // -7

    System.out.println("Recursive getSum(2, 3): " + getSumRecursive(2, 3)); // 5
    System.out.println("Recursive getSum(-1, 1): " + getSumRecursive(-1, 1)); // 0
    System.out.println("Recursive getSum(-3, -4): " + getSumRecursive(-3, -4)); // -7
  }
}