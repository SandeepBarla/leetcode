/*
 * LeetCode Problem 139: Word Break
 * URL: https://leetcode.com/problems/word-break/
 * Difficulty: Medium
 *
 * Top-Down DP (Recursion + Memoization):
 * - Try all dictionary words as prefixes.
 * - If a word matches the current prefix, recursively solve the rest.
 * - Memoize results for substrings to avoid repeated work.
 *
 * Bottom-Up DP:
 * - dp[i] = true if s[0...i) can be segmented into words in the dictionary.
 * - For each i, check if any word in the dictionary ends at i and fits.
 *
 * Time Complexity: O(n^2), where n = length of s
 * Space Complexity: O(n), for memo or dp array
 */

package solutions.neetcode_150.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.Solution;

public class LC_139_WordBreak implements Solution {

  // ✅ Top-Down DP using recursion and memoization
  public boolean wordBreakTopDown(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    return dfs(s, dict, new HashMap<>());
  }

  private boolean dfs(String s, Set<String> dict, Map<String, Boolean> memo) {
    if (s.isEmpty())
      return true;
    if (memo.containsKey(s))
      return memo.get(s);

    for (String word : dict) {
      if (s.startsWith(word)) {
        String suffix = s.substring(word.length());
        if (dfs(suffix, dict, memo)) {
          memo.put(s, true);
          return true;
        }
      }
    }

    memo.put(s, false);
    return false;
  }

  // ✅ Bottom-Up DP using tabulation
  public boolean wordBreakBottomUp(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true; // base case: empty string

    for (int i = 1; i <= n; i++) {
      for (String word : dict) {
        int len = word.length();
        if (i >= len && dp[i - len] && s.substring(i - len, i).equals(word)) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n];
  }

  @Override
  public void run() {
    String s1 = "leetcode";
    List<String> dict1 = Arrays.asList("leet", "code");
    System.out.println("Top-Down (leetcode): " + wordBreakTopDown(s1, dict1)); // true
    System.out.println("Bottom-Up (leetcode): " + wordBreakBottomUp(s1, dict1)); // true

    String s2 = "catsandog";
    List<String> dict2 = Arrays.asList("cats", "dog", "sand", "and", "cat");
    System.out.println("Top-Down (catsandog): " + wordBreakTopDown(s2, dict2)); // false
    System.out.println("Bottom-Up (catsandog): " + wordBreakBottomUp(s2, dict2)); // false

    String s3 = "applepenapple";
    List<String> dict3 = Arrays.asList("apple", "pen");
    System.out.println("Top-Down (applepenapple): " + wordBreakTopDown(s3, dict3)); // true
    System.out.println("Bottom-Up (applepenapple): " + wordBreakBottomUp(s3, dict3));// true
  }
}