/*
 * LeetCode Problem 1143: Longest Common Subsequence
 * URL: https://leetcode.com/problems/longest-common-subsequence/
 * Difficulty: Medium
 *
 * Approach:
 * - Use bottom-up dynamic programming to build a 2D dp table.
 * - dp[i][j] stores the LCS of text1[0...i-1] and text2[0...j-1].
 * - If characters match: dp[i][j] = 1 + dp[i-1][j-1]
 * - Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 *
 * Time Complexity: O(m * n), where m = length of text1, n = length of text2
 * Space Complexity: O(m * n), for the dp table
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_1143_LongestCommonSubsequence implements Solution {

  public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(); // length of first string
    int n = text2.length(); // length of second string

    int[][] dp = new int[m + 1][n + 1]; // dp[i][j] represents LCS of text1[0..i-1], text2[0..j-1]

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1]; // characters match → extend the LCS
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // take best from skipping one char
        }
      }
    }

    return dp[m][n]; // final LCS length
  }

  @Override
  public void run() {
    String text1 = "abcde";
    String text2 = "ace";
    int result = longestCommonSubsequence(text1, text2);
    System.out.println("LCS length between \"" + text1 + "\" and \"" + text2 + "\": " + result);

    text1 = "aggtab";
    text2 = "gxtxayb";
    result = longestCommonSubsequence(text1, text2);
    System.out.println("LCS length between \"" + text1 + "\" and \"" + text2 + "\": " + result);

    text1 = "abc";
    text2 = "def";
    result = longestCommonSubsequence(text1, text2);
    System.out.println("LCS length between \"" + text1 + "\" and \"" + text2 + "\": " + result);
  }
}