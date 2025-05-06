/*
 * LeetCode Problem 3: Longest Substring Without Repeating Characters
 * URL: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Sliding Window + HashSet
 * 2. Sliding Window + HashMap
 * 3. Sliding Window + ASCII array (Optimized)
 *
 * Time Complexity (All): O(n)
 * Space Complexity:
 * - HashSet: O(k), where k is charset size
 * - HashMap: O(k)
 * - Array: O(1) (fixed size for ASCII)
 */
package solutions.top_interview_questions.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.Solution;

public class LC_3_LongestSubstringWithoutRepeatingCharacters implements Solution {

    // Approach 1: Sliding Window with HashSet
    public int lengthOfLongestSubstring_HashSet(String s) {
        int maxSize = 0;
        Set<Character> set = new HashSet<>();
        int left = 0;

        for (char c : s.toCharArray()) {
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            maxSize = Math.max(maxSize, set.size());
        }

        return maxSize;
    }

    // Approach 2: Sliding Window with HashMap
    public int lengthOfLongestSubstring_HashMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxSize = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxSize = Math.max(maxSize, right - left + 1);
        }

        return maxSize;
    }

    // Approach 3: Sliding Window with ASCII Array (Optimized)
    public int lengthOfLongestSubstring_AsciiArray(String s) {
        int[] charIndex = new int[128]; // ASCII size
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            left = Math.max(left, charIndex[c]);
            maxLength = Math.max(maxLength, right - left + 1);
            charIndex[c] = right + 1;
        }

        return maxLength;
    }

    @Override
    public void run() {
        List<String> testCases = Arrays.asList("abcabcbb", "bbbbb", "pwwkew", "", "abcddefgh");
        for (String input : testCases) {
            System.out.println("Input: " + input);
            System.out.println("[HashSet] Length: " + lengthOfLongestSubstring_HashSet(input));
            System.out.println("[HashMap] Length: " + lengthOfLongestSubstring_HashMap(input));
            System.out.println("[ASCII Array] Length: " + lengthOfLongestSubstring_AsciiArray(input));
            System.out.println("---");
        }
    }
}