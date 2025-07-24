/*
 * LeetCode Problem 29: Divide Two Integers
 * URL: https://leetcode.com/problems/divide-two-integers/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Bit Manipulation with Exponential Search - O(log²n) time, O(1) space
 * 2. Binary Search - O(log²n) time, O(1) space
 * 3. Subtraction (Time Limit Exceeded) - O(n) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(log²n) - logarithmic doubling + binary search
 * - Approach 2: O(log²n) - binary search approach
 * - Approach 3: O(n) - repeated subtraction (inefficient)
 *
 * Space Complexity:
 * - All approaches: O(1) - only using variables
 *
 * Note: No multiplication, division, or mod operators allowed
 */

package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_29_DivideTwoIntegers implements Solution {

    // Approach 1: Bit Manipulation with Exponential Search (Optimal)
    public int divideBitManipulation(int dividend, int divisor) {
        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Determine sign of result
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // Work with positive values using long to avoid overflow
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        
        long result = 0;
        
        // Use exponential search to find multiples of divisor
        while (absDividend >= absDivisor) {
            long temp = absDivisor;
            long multiple = 1;
            
            // Find the largest multiple of divisor that fits in dividend
            while (absDividend >= (temp << 1)) {
                temp <<= 1;  // temp *= 2
                multiple <<= 1;  // multiple *= 2
            }
            
            absDividend -= temp;
            result += multiple;
        }
        
        // Apply sign and handle overflow
        result = negative ? -result : result;
        return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : 
               result < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) result;
    }

    // Approach 2: Binary Search
    public int divideBinarySearch(int dividend, int divisor) {
        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // Work with positive values
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        
        // Binary search for the quotient
        long left = 0, right = absDividend;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long product = mid * absDivisor;
            
            if (product == absDividend) {
                long result = negative ? -mid : mid;
                return (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, result));
            } else if (product < absDividend) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        long result = negative ? -right : right;
        return (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, result));
    }

    // Approach 3: Repeated Subtraction (Educational - will TLE for large inputs)
    public int divideSubtraction(int dividend, int divisor) {
        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // Work with positive values
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        
        int result = 0;
        
        // Repeatedly subtract divisor from dividend
        while (absDividend >= absDivisor) {
            absDividend -= absDivisor;
            result++;
        }
        
        return negative ? -result : result;
    }

    // Approach 4: Optimized Subtraction with Doubling
    public int divideOptimizedSubtraction(int dividend, int divisor) {
        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // Work with positive values
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        
        long result = 0;
        
        while (absDividend >= absDivisor) {
            long currentDivisor = absDivisor;
            long currentQuotient = 1;
            
            // Double the divisor until it's larger than remaining dividend
            while (absDividend >= (currentDivisor << 1)) {
                currentDivisor <<= 1;
                currentQuotient <<= 1;
            }
            
            absDividend -= currentDivisor;
            result += currentQuotient;
        }
        
        result = negative ? -result : result;
        return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : 
               result < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) result;
    }

    @Override
    public void run() {
        // Test Case 1: Basic division
        int dividend1 = 10, divisor1 = 3;
        System.out.println("Test Case 1: " + dividend1 + " / " + divisor1);
        System.out.println("Bit Manipulation: " + divideBitManipulation(dividend1, divisor1)); // Expected: 3
        System.out.println("Binary Search: " + divideBinarySearch(dividend1, divisor1)); // Expected: 3
        System.out.println("Subtraction: " + divideSubtraction(dividend1, divisor1)); // Expected: 3
        System.out.println("Optimized Subtraction: " + divideOptimizedSubtraction(dividend1, divisor1)); // Expected: 3
        System.out.println();

        // Test Case 2: Negative dividend
        int dividend2 = 7, divisor2 = -3;
        System.out.println("Test Case 2: " + dividend2 + " / " + divisor2);
        System.out.println("Bit Manipulation: " + divideBitManipulation(dividend2, divisor2)); // Expected: -2
        System.out.println("Binary Search: " + divideBinarySearch(dividend2, divisor2)); // Expected: -2
        System.out.println("Subtraction: " + divideSubtraction(dividend2, divisor2)); // Expected: -2
        System.out.println("Optimized Subtraction: " + divideOptimizedSubtraction(dividend2, divisor2)); // Expected: -2
        System.out.println();

        // Test Case 3: Overflow case
        int dividend3 = Integer.MIN_VALUE, divisor3 = -1;
        System.out.println("Test Case 3: " + dividend3 + " / " + divisor3 + " (overflow case)");
        System.out.println("Bit Manipulation: " + divideBitManipulation(dividend3, divisor3)); // Expected: 2147483647
        System.out.println("Binary Search: " + divideBinarySearch(dividend3, divisor3)); // Expected: 2147483647
        System.out.println("Subtraction: " + divideSubtraction(dividend3, divisor3)); // Expected: 2147483647
        System.out.println("Optimized Subtraction: " + divideOptimizedSubtraction(dividend3, divisor3)); // Expected: 2147483647
        System.out.println();

        // Test Case 4: Divide by 1
        int dividend4 = -123, divisor4 = 1;
        System.out.println("Test Case 4: " + dividend4 + " / " + divisor4);
        System.out.println("Bit Manipulation: " + divideBitManipulation(dividend4, divisor4)); // Expected: -123
        System.out.println("Binary Search: " + divideBinarySearch(dividend4, divisor4)); // Expected: -123
        System.out.println();

        // Test Case 5: Large numbers (performance test)
        int dividend5 = 2147483647, divisor5 = 2;
        System.out.println("Test Case 5: " + dividend5 + " / " + divisor5 + " (performance test)");
        
        long start = System.currentTimeMillis();
        int result5_1 = divideBitManipulation(dividend5, divisor5);
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Bit Manipulation: " + result5_1 + " in " + time1 + "ms"); // Expected: 1073741823
        
        start = System.currentTimeMillis();
        int result5_2 = divideBinarySearch(dividend5, divisor5);
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Binary Search: " + result5_2 + " in " + time2 + "ms"); // Expected: 1073741823
        
        start = System.currentTimeMillis();
        int result5_4 = divideOptimizedSubtraction(dividend5, divisor5);
        long time4 = System.currentTimeMillis() - start;
        System.out.println("Optimized Subtraction: " + result5_4 + " in " + time4 + "ms"); // Expected: 1073741823
        
        // Note: Skipping regular subtraction for large numbers as it would be too slow
        System.out.println();

        // Test Case 6: Equal numbers
        int dividend6 = 42, divisor6 = 42;
        System.out.println("Test Case 6: " + dividend6 + " / " + divisor6);
        System.out.println("Bit Manipulation: " + divideBitManipulation(dividend6, divisor6)); // Expected: 1
        System.out.println();

        // Test Case 7: Dividend smaller than divisor
        int dividend7 = 5, divisor7 = 10;
        System.out.println("Test Case 7: " + dividend7 + " / " + divisor7);
        System.out.println("Bit Manipulation: " + divideBitManipulation(dividend7, divisor7)); // Expected: 0
        System.out.println("Binary Search: " + divideBinarySearch(dividend7, divisor7)); // Expected: 0
        System.out.println();

        // Test Case 8: Both negative
        int dividend8 = -50, divisor8 = -5;
        System.out.println("Test Case 8: " + dividend8 + " / " + divisor8);
        System.out.println("Bit Manipulation: " + divideBitManipulation(dividend8, divisor8)); // Expected: 10
        System.out.println("Binary Search: " + divideBinarySearch(dividend8, divisor8)); // Expected: 10
    }
} 