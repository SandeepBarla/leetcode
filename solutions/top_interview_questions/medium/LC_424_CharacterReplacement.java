/*
 * LeetCode Problem 424: Longest Repeating Character Replacement
 * URL: https://leetcode.com/problems/longest-repeating-character-replacement/
 * Difficulty: Medium
 *
 * Approach: Sliding Window + Frequency Array
 * - Expand the window while the number of characters to replace is <= k.
 * - Track the frequency of the most common character in the window.
 * - If (window size - max frequency) > k, shrink the window from the left.
 * - Keep updating the maximum window size.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) (fixed size 26 array for uppercase letters)
 */
package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_424_CharacterReplacement implements Solution {

  public int characterReplacement(String s, int k) {
    int left = 0;
    int[] freq = new int[26];
    int maxFreq = 0;
    int maxLength = 0;

    for (int right = 0; right < s.length(); right++) {
      char c = s.charAt(right);
      freq[c - 'A']++;
      maxFreq = Math.max(maxFreq, freq[c - 'A']);

      while (right - left + 1 - maxFreq > k) {
        freq[s.charAt(left) - 'A']--;
        left++;
      }

      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  @Override
  public void run() {
    String s1 = "ABAB";
    int k1 = 2;
    System.out.println("Input: " + s1 + ", k = " + k1);
    System.out.println("Output: " + characterReplacement(s1, k1));

    String s2 = "AABABBA";
    int k2 = 1;
    System.out.println("Input: " + s2 + ", k = " + k2);
    System.out.println("Output: " + characterReplacement(s2, k2));

    String s3 = "ABABCBBBBBBBBB";
    int k3 = 2;
    System.out.println("Input: " + s3 + ", k = " + k3);
    System.out.println("Output: " + characterReplacement(s3, k3));
  }
}