/*
 * LeetCode Problem 72: Edit Distance
 * URL: https://leetcode.com/problems/edit-distance/
 * Difficulty: Hard
 *
 * Approach (Bottom-Up DP):
 * - Define dp[i][j] as the minimum number of operations to convert word1[0..i-1] to word2[0..j-1]
 * - If characters match: no operation needed → dp[i][j] = dp[i-1][j-1]
 * - If not, choose the minimum of:
 *   - Insert (dp[i][j-1] + 1)
 *   - Delete (dp[i-1][j] + 1)
 *   - Replace (dp[i-1][j-1] + 1)
 * - Base case:
 *   - dp[0][j] = j → insert all j characters
 *   - dp[i][0] = i → delete all i characters
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_72_EditDistance implements Solution {

  public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    // dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
    int[][] dp = new int[m + 1][n + 1];

    // Fill base cases
    for (int i = 0; i <= m; i++)
      dp[i][0] = i; // delete all characters from word1
    for (int j = 0; j <= n; j++)
      dp[0][j] = j; // insert all characters of word2

    // Build up the DP table
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1]; // no operation needed
        } else {
          dp[i][j] = 1 + Math.min(
              dp[i - 1][j - 1], // replace
              Math.min(dp[i][j - 1], // insert
                  dp[i - 1][j]) // delete
          );
        }
      }
    }

    return dp[m][n];
  }

  @Override
  public void run() {
    String word1 = "horse";
    String word2 = "ros";

    int result = minDistance(word1, word2);
    System.out.println("Min operations to convert: " + result); // Expected: 3
  }
}