/*
 * LeetCode Problem 518: Coin Change II
 * URL: https://leetcode.com/problems/coin-change-ii/
 * Difficulty: Medium
 *
 * Approach:
 * - Use 1D Dynamic Programming (Unbounded Knapsack pattern).
 * - dp[i] represents number of combinations to form amount i.
 * - For each coin, update dp array for amounts from coin to target.
 *
 * Time Complexity: O(n * amount), where n = number of coins
 * Space Complexity: O(amount)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_518_CoinChangeII implements Solution {

  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1; // One way to make amount 0: use no coins

    for (int coin : coins) {
      for (int target = coin; target <= amount; target++) {
        dp[target] += dp[target - coin];
      }
    }

    return dp[amount];
  }

  @Override
  public void run() {
    int amount = 5;
    int[] coins = { 1, 2, 5 };
    int result = change(amount, coins);
    System.out.println("Number of combinations to make amount " + amount + ": " + result);

    amount = 3;
    coins = new int[] { 2 };
    System.out.println("Number of combinations to make amount " + amount + ": " + change(amount, coins));

    amount = 10;
    coins = new int[] { 10 };
    System.out.println("Number of combinations to make amount " + amount + ": " + change(amount, coins));
  }
}