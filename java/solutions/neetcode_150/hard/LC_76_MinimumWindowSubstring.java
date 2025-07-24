/*
 * LeetCode: 76 - Minimum Window Substring
 * URL: https://leetcode.com/problems/minimum-window-substring/
 * Difficulty: Hard
 *
 * Approach:
 * - Use two hashmaps:
 *     - needMap → frequency count of characters in target string `t`
 *     - haveMap → running count of characters in current window of `s`
 * - Expand right pointer until we have all required characters.
 * - Then, contract left pointer while the window is still valid.
 * - Track the minimum valid window using (start, minLen).
 *
 * Time Complexity: O(s.length + t.length)
 * Space Complexity: O(1), since both maps store only lowercase English letters (bounded to 26)
 */

package solutions.neetcode_150.hard;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_76_MinimumWindowSubstring implements Solution {

  @Override
  public void run() {
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println("Minimum window: " + minWindow(s, t)); // Output: "BANC"
  }

  public String minWindow(String s, String t) {
    // Count required characters from t
    Map<Character, Integer> needMap = new HashMap<>();
    for (char c : t.toCharArray()) {
      needMap.put(c, needMap.getOrDefault(c, 0) + 1);
    }

    // Track characters found in current window
    Map<Character, Integer> haveMap = new HashMap<>();
    int have = 0; // Number of chars that match required count
    int left = 0, start = 0;
    int minLen = Integer.MAX_VALUE;

    for (int right = 0; right < s.length(); right++) {
      char c = s.charAt(right);
      haveMap.put(c, haveMap.getOrDefault(c, 0) + 1);

      if (needMap.containsKey(c) && haveMap.get(c).intValue() == needMap.get(c)) {
        have++;
      }

      // Try to contract window while valid
      while (have == needMap.size()) {
        // Check for smaller valid window
        if (right - left + 1 < minLen) {
          minLen = right - left + 1;
          start = left;
        }

        char toRemove = s.charAt(left);
        haveMap.put(toRemove, haveMap.get(toRemove) - 1);

        if (needMap.containsKey(toRemove) && haveMap.get(toRemove) < needMap.get(toRemove)) {
          have--;
        }

        left++; // Shrink window
      }
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
  }
}