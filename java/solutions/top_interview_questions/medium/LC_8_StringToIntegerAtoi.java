/*
 * LeetCode Problem 8: String to Integer (atoi)
 * URL: https://leetcode.com/problems/string-to-integer-atoi/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. State Machine Approach - O(n) time, O(1) space
 * 2. Character by Character Processing - O(n) time, O(1) space
 * 3. Regular Expression + Long - O(n) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(n) - single pass through string
 *
 * Space Complexity:
 * - All approaches: O(1) - only using variables
 */

package solutions.top_interview_questions.medium;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Solution;

public class LC_8_StringToIntegerAtoi implements Solution {

    // Approach 1: State Machine Approach (Optimal)
    public int myAtoiStateMachine(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int i = 0, n = s.length();
        int sign = 1;
        long result = 0;
        
        // Step 1: Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        
        // Step 2: Check sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        
        // Step 3: Convert digits and handle overflow
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            
            // Check for overflow before adding digit
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            result = result * 10 + digit;
            i++;
        }
        
        return (int) (sign * result);
    }

    // Approach 2: Character by Character Processing
    public int myAtoiCharByChar(String s) {
        if (s == null || s.length() == 0) return 0;
        
        s = s.trim(); // Remove leading/trailing whitespaces
        if (s.length() == 0) return 0;
        
        int sign = 1;
        int start = 0;
        long result = 0;
        
        // Check for sign
        char firstChar = s.charAt(0);
        if (firstChar == '+') {
            sign = 1;
            start = 1;
        } else if (firstChar == '-') {
            sign = -1;
            start = 1;
        }
        
        // Process digits
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                break; // Stop at first non-digit
            }
            
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
            
            // Check for overflow
            if (sign * result >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * result <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        
        return (int) (sign * result);
    }

    // Approach 3: Regular Expression + Long
    public int myAtoiRegex(String s) {
        if (s == null || s.length() == 0) return 0;
        
        // Pattern: optional whitespace, optional sign, digits
        Pattern pattern = Pattern.compile("^\\s*([+-]?\\d+)");
        Matcher matcher = pattern.matcher(s.trim());
        
        if (!matcher.find()) {
            return 0;
        }
        
        try {
            long result = Long.parseLong(matcher.group(1));
            
            // Clamp to 32-bit integer range
            if (result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            
            return (int) result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void run() {
        // Test Case 1: Basic positive number
        String s1 = "42";
        System.out.println("Test Case 1: \"" + s1 + "\"");
        System.out.println("State Machine: " + myAtoiStateMachine(s1)); // Expected: 42
        System.out.println("Char by Char: " + myAtoiCharByChar(s1)); // Expected: 42
        System.out.println("Regex: " + myAtoiRegex(s1)); // Expected: 42
        System.out.println();

        // Test Case 2: Leading whitespace and sign
        String s2 = "   -42";
        System.out.println("Test Case 2: \"" + s2 + "\"");
        System.out.println("State Machine: " + myAtoiStateMachine(s2)); // Expected: -42
        System.out.println("Char by Char: " + myAtoiCharByChar(s2)); // Expected: -42
        System.out.println("Regex: " + myAtoiRegex(s2)); // Expected: -42
        System.out.println();

        // Test Case 3: With trailing non-digits
        String s3 = "4193 with words";
        System.out.println("Test Case 3: \"" + s3 + "\"");
        System.out.println("State Machine: " + myAtoiStateMachine(s3)); // Expected: 4193
        System.out.println("Char by Char: " + myAtoiCharByChar(s3)); // Expected: 4193
        System.out.println("Regex: " + myAtoiRegex(s3)); // Expected: 4193
        System.out.println();

        // Test Case 4: Starts with non-digit
        String s4 = "words and 987";
        System.out.println("Test Case 4: \"" + s4 + "\"");
        System.out.println("State Machine: " + myAtoiStateMachine(s4)); // Expected: 0
        System.out.println("Char by Char: " + myAtoiCharByChar(s4)); // Expected: 0
        System.out.println("Regex: " + myAtoiRegex(s4)); // Expected: 0
        System.out.println();

        // Test Case 5: Overflow (positive)
        String s5 = "91283472332";
        System.out.println("Test Case 5: \"" + s5 + "\" (overflow)");
        System.out.println("State Machine: " + myAtoiStateMachine(s5)); // Expected: 2147483647
        System.out.println("Char by Char: " + myAtoiCharByChar(s5)); // Expected: 2147483647
        System.out.println("Regex: " + myAtoiRegex(s5)); // Expected: 2147483647
        System.out.println();

        // Test Case 6: Overflow (negative)
        String s6 = "-91283472332";
        System.out.println("Test Case 6: \"" + s6 + "\" (negative overflow)");
        System.out.println("State Machine: " + myAtoiStateMachine(s6)); // Expected: -2147483648
        System.out.println("Char by Char: " + myAtoiCharByChar(s6)); // Expected: -2147483648
        System.out.println("Regex: " + myAtoiRegex(s6)); // Expected: -2147483648
        System.out.println();

        // Test Case 7: Empty string
        String s7 = "";
        System.out.println("Test Case 7: \"" + s7 + "\" (empty)");
        System.out.println("State Machine: " + myAtoiStateMachine(s7)); // Expected: 0
        System.out.println("Char by Char: " + myAtoiCharByChar(s7)); // Expected: 0
        System.out.println("Regex: " + myAtoiRegex(s7)); // Expected: 0
        System.out.println();

        // Test Case 8: Only whitespace
        String s8 = "   ";
        System.out.println("Test Case 8: \"" + s8 + "\" (whitespace only)");
        System.out.println("State Machine: " + myAtoiStateMachine(s8)); // Expected: 0
        System.out.println("Char by Char: " + myAtoiCharByChar(s8)); // Expected: 0
        System.out.println("Regex: " + myAtoiRegex(s8)); // Expected: 0
        System.out.println();

        // Test Case 9: Sign only
        String s9 = "+-12";
        System.out.println("Test Case 9: \"" + s9 + "\" (invalid sign)");
        System.out.println("State Machine: " + myAtoiStateMachine(s9)); // Expected: 0
        System.out.println("Char by Char: " + myAtoiCharByChar(s9)); // Expected: 0
        System.out.println("Regex: " + myAtoiRegex(s9)); // Expected: 0
    }
} 