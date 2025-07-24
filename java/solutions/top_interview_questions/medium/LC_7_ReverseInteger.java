/*
 * LeetCode Problem 7: Reverse Integer
 * URL: https://leetcode.com/problems/reverse-integer/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. String Reversal with Overflow Check - O(log n) time, O(log n) space
 * 2. Mathematical Reversal with Overflow Check - O(log n) time, O(1) space
 * 3. Long Integer Approach - O(log n) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(log n) - number of digits in input
 *
 * Space Complexity:
 * - Approach 1: O(log n) - string storage
 * - Approach 2: O(1) - only using variables
 * - Approach 3: O(1) - only using variables
 */

package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_7_ReverseInteger implements Solution {

    // Approach 1: String Reversal with Overflow Check
    public int reverseStringApproach(int x) {
        boolean isNegative = x < 0;
        String str = String.valueOf(Math.abs((long) x)); // Use long to handle -2^31
        
        StringBuilder sb = new StringBuilder(str);
        String reversed = sb.reverse().toString();
        
        try {
            long result = Long.parseLong(reversed);
            if (isNegative) result = -result;
            
            // Check for 32-bit integer overflow
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
            
            return (int) result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Approach 2: Mathematical Reversal with Overflow Check (Optimal)
    public int reverseMathematical(int x) {
        int result = 0;
        
        while (x != 0) {
            int digit = x % 10;
            
            // Check for overflow before multiplying by 10
            if (result > Integer.MAX_VALUE / 10 || 
                (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || 
                (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            
            result = result * 10 + digit;
            x /= 10;
        }
        
        return result;
    }

    // Approach 3: Long Integer Approach
    public int reverseLongApproach(int x) {
        long result = 0;
        
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        
        // Check if result is within 32-bit signed integer range
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int) result;
    }

    @Override
    public void run() {
        // Test Case 1: Positive number
        int x1 = 123;
        System.out.println("Test Case 1: " + x1);
        System.out.println("String Approach: " + reverseStringApproach(x1)); // Expected: 321
        System.out.println("Mathematical: " + reverseMathematical(x1)); // Expected: 321
        System.out.println("Long Approach: " + reverseLongApproach(x1)); // Expected: 321
        System.out.println();

        // Test Case 2: Negative number
        int x2 = -123;
        System.out.println("Test Case 2: " + x2);
        System.out.println("String Approach: " + reverseStringApproach(x2)); // Expected: -321
        System.out.println("Mathematical: " + reverseMathematical(x2)); // Expected: -321
        System.out.println("Long Approach: " + reverseLongApproach(x2)); // Expected: -321
        System.out.println();

        // Test Case 3: Number with trailing zeros
        int x3 = 120;
        System.out.println("Test Case 3: " + x3);
        System.out.println("String Approach: " + reverseStringApproach(x3)); // Expected: 21
        System.out.println("Mathematical: " + reverseMathematical(x3)); // Expected: 21
        System.out.println("Long Approach: " + reverseLongApproach(x3)); // Expected: 21
        System.out.println();

        // Test Case 4: Overflow (positive)
        int x4 = 1534236469; // Reverses to 9646324351, which overflows
        System.out.println("Test Case 4: " + x4 + " (overflow case)");
        System.out.println("String Approach: " + reverseStringApproach(x4)); // Expected: 0
        System.out.println("Mathematical: " + reverseMathematical(x4)); // Expected: 0
        System.out.println("Long Approach: " + reverseLongApproach(x4)); // Expected: 0
        System.out.println();

        // Test Case 5: Edge case - minimum integer
        int x5 = Integer.MIN_VALUE; // -2147483648
        System.out.println("Test Case 5: " + x5 + " (Integer.MIN_VALUE)");
        System.out.println("String Approach: " + reverseStringApproach(x5)); // Expected: 0
        System.out.println("Mathematical: " + reverseMathematical(x5)); // Expected: 0
        System.out.println("Long Approach: " + reverseLongApproach(x5)); // Expected: 0
        System.out.println();

        // Test Case 6: Single digit
        int x6 = 9;
        System.out.println("Test Case 6: " + x6);
        System.out.println("String Approach: " + reverseStringApproach(x6)); // Expected: 9
        System.out.println("Mathematical: " + reverseMathematical(x6)); // Expected: 9
        System.out.println("Long Approach: " + reverseLongApproach(x6)); // Expected: 9
        System.out.println();

        // Test Case 7: Zero
        int x7 = 0;
        System.out.println("Test Case 7: " + x7);
        System.out.println("String Approach: " + reverseStringApproach(x7)); // Expected: 0
        System.out.println("Mathematical: " + reverseMathematical(x7)); // Expected: 0
        System.out.println("Long Approach: " + reverseLongApproach(x7)); // Expected: 0
    }
} 