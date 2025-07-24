/*
 * LeetCode Problem 125: Valid Palindrome
 * URL: https://leetcode.com/problems/valid-palindrome/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Two Pointers with Character Class Methods - O(n) time, O(1) space
 * 2. String Preprocessing with Two Pointers - O(n) time, O(n) space
 * 3. StringBuilder Reverse Comparison - O(n) time, O(n) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass with two pointers
 * - Approach 2: O(n) - preprocessing + comparison
 * - Approach 3: O(n) - string building and comparison
 *
 * Space Complexity:
 * - Approach 1: O(1) - no extra space
 * - Approach 2: O(n) - cleaned string storage
 * - Approach 3: O(n) - StringBuilder storage
 */

package solutions.top_interview_questions.easy;

import common.Solution;

public class LC_125_ValidPalindrome implements Solution {

    // Approach 1: Two Pointers with Character Class Methods (Optimal)
    public boolean isPalindromeOptimal(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }

    // Approach 2: String Preprocessing with Two Pointers
    public boolean isPalindromePreprocessing(String s) {
        // Clean string: remove non-alphanumeric and convert to lowercase
        String cleaned = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        int left = 0;
        int right = cleaned.length() - 1;
        
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

    // Approach 3: StringBuilder Reverse Comparison
    public boolean isPalindromeStringBuilder(String s) {
        // Clean and build the string
        String cleaned = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        if (cleaned.length() == 0) {
            return true;
        }
        
        // Create reverse using StringBuilder
        String firstHalf = cleaned.substring(0, cleaned.length() / 2);
        String secondHalf = cleaned.substring((cleaned.length() + 1) / 2);
        String reversed = new StringBuilder(secondHalf).reverse().toString();
        
        return firstHalf.equals(reversed);
    }

    @Override
    public void run() {
        // Test Case 1: Valid palindrome with mixed case and punctuation
        String s1 = "A man, a plan, a canal: Panama";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Optimal: " + isPalindromeOptimal(s1)); // Expected: true
        System.out.println("Preprocessing: " + isPalindromePreprocessing(s1)); // Expected: true
        System.out.println("StringBuilder: " + isPalindromeStringBuilder(s1)); // Expected: true
        System.out.println();

        // Test Case 2: Not a palindrome
        String s2 = "race a car";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Optimal: " + isPalindromeOptimal(s2)); // Expected: false
        System.out.println("Preprocessing: " + isPalindromePreprocessing(s2)); // Expected: false
        System.out.println("StringBuilder: " + isPalindromeStringBuilder(s2)); // Expected: false
        System.out.println();

        // Test Case 3: Empty string (edge case)
        String s3 = " ";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Optimal: " + isPalindromeOptimal(s3)); // Expected: true
        System.out.println("Preprocessing: " + isPalindromePreprocessing(s3)); // Expected: true
        System.out.println("StringBuilder: " + isPalindromeStringBuilder(s3)); // Expected: true
        System.out.println();

        // Test Case 4: Single character
        String s4 = "a";
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("Optimal: " + isPalindromeOptimal(s4)); // Expected: true
        System.out.println("Preprocessing: " + isPalindromePreprocessing(s4)); // Expected: true
        System.out.println("StringBuilder: " + isPalindromeStringBuilder(s4)); // Expected: true
    }
} 