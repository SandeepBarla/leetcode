/*
 * LeetCode Problem 647: Palindromic Substrings
 * URL: https://leetcode.com/problems/palindromic-substrings/
 * Difficulty: Medium
 *
 * Approach:
 * - Use bottom-up DP to count all substrings that are palindromes.
 * - dp[i][j] = true if s[i..j] is a palindrome.
 * - A string s[i..j] is a palindrome if:
 *     - s[i] == s[j]
 *     - and (j - i < 3 OR dp[i+1][j-1] == true)
 * - Iterate from end to start for i, and i to end for j to fill dp table properly.
 * - For each true dp[i][j], increment the result count.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_647_PalindromicSubstrings implements Solution {

  public int countSubstrings(String s) {
    int n = s.length();
    boolean[][] dp = new boolean[n][n];
    int count = 0;

    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          if (j - i < 3 || dp[i + 1][j - 1]) {
            dp[i][j] = true;
            count++;
          }
        }
      }
    }

    return count;
  }

  @Override
  public void run() {
    String input = "aaa";
    int result = countSubstrings(input);
    System.out.println("Total palindromic substrings: " + result); // Expected: 6
  }
}