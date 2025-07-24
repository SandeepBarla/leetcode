/*
 * LeetCode Problem 387: First Unique Character in a String
 * URL: https://leetcode.com/problems/first-unique-character-in-a-string/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Bucket Array (Frequency Array) - O(n) time, O(1) space
 * 2. HashMap - O(n) time, O(1) space (at most 26 unique characters)
 * 3. Brute Force - O(n²) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1 & 2: O(n) - two passes through string
 * - Approach 3: O(n²) - nested loops for each character
 *
 * Space Complexity:
 * - Approach 1: O(1) - fixed size array of 26
 * - Approach 2: O(1) - at most 26 unique lowercase letters
 * - Approach 3: O(1) - no extra space
 */

package solutions.top_interview_questions.easy;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_387_FirstUniqueCharacterInAString implements Solution {

    // Approach 1: Bucket Array (Frequency Array) - Optimal
    public int firstUniqCharBucketArray(String s) {
        // 0 -> 25 mapping for 'a' -> 'z'
        int[] freq = new int[26];
        
        // First pass: record frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Second pass: find first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        
        return -1;
    }

    // Approach 2: HashMap
    public int firstUniqCharHashMap(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        
        // Record frequencies
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Find first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (freqMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        
        return -1;
    }

    // Approach 3: Brute Force
    public int firstUniqCharBruteForce(String s) {
        // Check each character against all others
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            
            // Compare with all other characters
            for (int j = 0; j < s.length(); j++) {
                if (i != j) {
                    if (s.charAt(i) != s.charAt(j)) {
                        count++;
                    } else {
                        break; // Found duplicate, not unique
                    }
                }
            }
            
            // If character doesn't match with any other, it's unique
            if (count == s.length() - 1) {
                return i;
            }
        }
        
        return -1;
    }

    @Override
    public void run() {
        // Test Case 1: Normal case with unique character
        String s1 = "leetcode";
        System.out.println("Input: " + s1);
        System.out.println("Bucket Array: " + firstUniqCharBucketArray(s1)); // Expected: 0
        System.out.println("HashMap: " + firstUniqCharHashMap(s1)); // Expected: 0
        System.out.println("Brute Force: " + firstUniqCharBruteForce(s1)); // Expected: 0
        System.out.println();

        // Test Case 2: No unique character
        String s2 = "loveleetcode";
        System.out.println("Input: " + s2);
        System.out.println("Bucket Array: " + firstUniqCharBucketArray(s2)); // Expected: 2
        System.out.println("HashMap: " + firstUniqCharHashMap(s2)); // Expected: 2
        System.out.println("Brute Force: " + firstUniqCharBruteForce(s2)); // Expected: 2
        System.out.println();

        // Test Case 3: All characters repeated
        String s3 = "aabb";
        System.out.println("Input: " + s3);
        System.out.println("Bucket Array: " + firstUniqCharBucketArray(s3)); // Expected: -1
        System.out.println("HashMap: " + firstUniqCharHashMap(s3)); // Expected: -1
        System.out.println("Brute Force: " + firstUniqCharBruteForce(s3)); // Expected: -1
    }
} 