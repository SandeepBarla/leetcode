/*
 * LeetCode Problem 5: Longest Palindromic Substring
 * URL: https://leetcode.com/problems/longest-palindromic-substring/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Expand Around Centers - O(n²) time, O(1) space
 * 2. Dynamic Programming - O(n²) time, O(n²) space
 * 3. Manacher's Algorithm - O(n) time, O(n) space
 * 4. Brute Force - O(n³) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(n²) - expand around each center
 * - Approach 2: O(n²) - fill DP table
 * - Approach 3: O(n) - linear algorithm (advanced)
 * - Approach 4: O(n³) - check all substrings
 *
 * Space Complexity:
 * - Approach 1: O(1) - only using variables
 * - Approach 2: O(n²) - 2D DP table
 * - Approach 3: O(n) - auxiliary arrays
 * - Approach 4: O(1) - only using variables
 */

package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_5_LongestPalindromicSubstring implements Solution {

    // Approach 1: Expand Around Centers (Optimal for space)
    public String longestPalindromeExpandAroundCenters(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd length palindromes
            int len1 = expandAroundCenter(s, i, i);
            // Check for even length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // Approach 2: Dynamic Programming
    public String longestPalindromeDynamicProgramming(String s) {
        int n = s.length();
        if (n == 0) return "";
        
        boolean[][] dp = new boolean[n][n];
        String longest = "";
        
        // Every single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            longest = s.substring(i, i + 1);
        }
        
        // Check for palindromes of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                longest = s.substring(i, i + 2);
            }
        }
        
        // Check for palindromes of length 3 and more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    longest = s.substring(i, j + 1);
                }
            }
        }
        
        return longest;
    }

    // Approach 3: Manacher's Algorithm (Optimal)
    public String longestPalindromeManacher(String s) {
        if (s == null || s.length() == 0) return "";
        
        // Transform string to handle even length palindromes
        String T = preprocess(s);
        int n = T.length();
        int[] P = new int[n]; // P[i] = radius of palindrome centered at i
        int center = 0, right = 0; // Current center and rightmost boundary
        
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i; // Mirror of i with respect to center
            
            if (right > i) {
                P[i] = Math.min(right - i, P[mirror]);
            }
            
            // Try to expand palindrome centered at i
            try {
                while (T.charAt(i + (1 + P[i])) == T.charAt(i - (1 + P[i]))) {
                    P[i]++;
                }
            } catch (StringIndexOutOfBoundsException e) {
                // Do nothing, we've reached the boundary
            }
            
            // If palindrome centered at i extends past right, adjust center and right
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
        }
        
        // Find the longest palindrome
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        
        // Extract the longest palindrome
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
    
    private String preprocess(String s) {
        int n = s.length();
        if (n == 0) return "^$";
        
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        for (int i = 0; i < n; i++) {
            sb.append("#").append(s.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }

    // Approach 4: Brute Force
    public String longestPalindromeBruteForce(String s) {
        if (s == null || s.length() == 0) return "";
        
        String longest = "";
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substr = s.substring(i, j + 1);
                if (isPalindrome(substr) && substr.length() > longest.length()) {
                    longest = substr;
                }
            }
        }
        
        return longest;
    }
    
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Override
    public void run() {
        // Test Case 1: Standard case with odd length palindrome
        String s1 = "babad";
        System.out.println("Test Case 1: \"" + s1 + "\"");
        System.out.println("Expand Around Centers: \"" + longestPalindromeExpandAroundCenters(s1) + "\""); // Expected: "bab" or "aba"
        System.out.println("Dynamic Programming: \"" + longestPalindromeDynamicProgramming(s1) + "\"");
        System.out.println("Manacher's: \"" + longestPalindromeManacher(s1) + "\"");
        System.out.println("Brute Force: \"" + longestPalindromeBruteForce(s1) + "\"");
        System.out.println();

        // Test Case 2: Even length palindrome
        String s2 = "cbbd";
        System.out.println("Test Case 2: \"" + s2 + "\"");
        System.out.println("Expand Around Centers: \"" + longestPalindromeExpandAroundCenters(s2) + "\""); // Expected: "bb"
        System.out.println("Dynamic Programming: \"" + longestPalindromeDynamicProgramming(s2) + "\"");
        System.out.println("Manacher's: \"" + longestPalindromeManacher(s2) + "\"");
        System.out.println("Brute Force: \"" + longestPalindromeBruteForce(s2) + "\"");
        System.out.println();

        // Test Case 3: Single character
        String s3 = "a";
        System.out.println("Test Case 3: \"" + s3 + "\"");
        System.out.println("Expand Around Centers: \"" + longestPalindromeExpandAroundCenters(s3) + "\""); // Expected: "a"
        System.out.println("Dynamic Programming: \"" + longestPalindromeDynamicProgramming(s3) + "\"");
        System.out.println("Manacher's: \"" + longestPalindromeManacher(s3) + "\"");
        System.out.println("Brute Force: \"" + longestPalindromeBruteForce(s3) + "\"");
        System.out.println();

        // Test Case 4: All same characters
        String s4 = "aaaa";
        System.out.println("Test Case 4: \"" + s4 + "\"");
        System.out.println("Expand Around Centers: \"" + longestPalindromeExpandAroundCenters(s4) + "\""); // Expected: "aaaa"
        System.out.println("Dynamic Programming: \"" + longestPalindromeDynamicProgramming(s4) + "\"");
        System.out.println("Manacher's: \"" + longestPalindromeManacher(s4) + "\"");
        System.out.println("Brute Force: \"" + longestPalindromeBruteForce(s4) + "\"");
        System.out.println();

        // Test Case 5: No palindrome longer than 1
        String s5 = "abcde";
        System.out.println("Test Case 5: \"" + s5 + "\"");
        System.out.println("Expand Around Centers: \"" + longestPalindromeExpandAroundCenters(s5) + "\""); // Expected: any single char
        System.out.println("Dynamic Programming: \"" + longestPalindromeDynamicProgramming(s5) + "\"");
        System.out.println("Manacher's: \"" + longestPalindromeManacher(s5) + "\"");
        System.out.println("Brute Force: \"" + longestPalindromeBruteForce(s5) + "\"");
    }
} 