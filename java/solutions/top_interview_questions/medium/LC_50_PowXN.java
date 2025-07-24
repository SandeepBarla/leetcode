/*
 * LeetCode Problem 50: Pow(x, n)
 * URL: https://leetcode.com/problems/powx-n/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Fast Exponentiation (Iterative) - O(log n) time, O(1) space
 * 2. Fast Exponentiation (Recursive) - O(log n) time, O(log n) space
 * 3. Brute Force - O(n) time, O(1) space
 * 4. Built-in Function - O(log n) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1,2: O(log n) - divide exponent by 2 each iteration
 * - Approach 3: O(n) - multiply x n times
 * - Approach 4: O(log n) - built-in implementation
 *
 * Space Complexity:
 * - Approach 1,3,4: O(1) - only using variables
 * - Approach 2: O(log n) - recursion call stack
 */

package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_50_PowXN implements Solution {

    // Approach 1: Fast Exponentiation (Iterative) - Optimal
    public double myPowIterative(double x, int n) {
        if (n == 0) return 1.0;
        
        long longN = Math.abs((long) n); // Use long to handle Integer.MIN_VALUE
        double result = 1.0;
        double currentPower = x;
        
        while (longN > 0) {
            if (longN % 2 == 1) {
                result *= currentPower;
            }
            currentPower *= currentPower;
            longN /= 2;
        }
        
        return n < 0 ? 1.0 / result : result;
    }

    // Approach 2: Fast Exponentiation (Recursive)
    public double myPowRecursive(double x, int n) {
        if (n == 0) return 1.0;
        
        long longN = Math.abs((long) n);
        double result = fastPow(x, longN);
        
        return n < 0 ? 1.0 / result : result;
    }
    
    private double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        
        double half = fastPow(x, n / 2);
        
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    // Approach 3: Brute Force (for comparison - will TLE for large n)
    public double myPowBruteForce(double x, int n) {
        if (n == 0) return 1.0;
        
        long longN = Math.abs((long) n);
        double result = 1.0;
        
        for (long i = 0; i < longN; i++) {
            result *= x;
        }
        
        return n < 0 ? 1.0 / result : result;
    }

    // Approach 4: Built-in Function (for comparison)
    public double myPowBuiltIn(double x, int n) {
        return Math.pow(x, n);
    }

    // Approach 5: Bit Manipulation Approach
    public double myPowBitManipulation(double x, int n) {
        if (n == 0) return 1.0;
        
        long longN = Math.abs((long) n);
        double result = 1.0;
        double base = x;
        
        while (longN != 0) {
            if ((longN & 1) == 1) {
                result *= base;
            }
            base *= base;
            longN >>= 1;
        }
        
        return n < 0 ? 1.0 / result : result;
    }

    // Helper method to compare results with tolerance
    private boolean isEqual(double a, double b, double tolerance) {
        return Math.abs(a - b) < tolerance;
    }

    @Override
    public void run() {
        double tolerance = 1e-9;
        
        // Test Case 1: Basic positive exponent
        double x1 = 2.0;
        int n1 = 10;
        System.out.println("Test Case 1: " + x1 + "^" + n1);
        double result1_1 = myPowIterative(x1, n1);
        double result1_2 = myPowRecursive(x1, n1);
        double result1_3 = myPowBruteForce(x1, n1);
        double result1_4 = myPowBuiltIn(x1, n1);
        double result1_5 = myPowBitManipulation(x1, n1);
        
        System.out.println("Iterative: " + result1_1); // Expected: 1024.0
        System.out.println("Recursive: " + result1_2); // Expected: 1024.0
        System.out.println("Brute Force: " + result1_3); // Expected: 1024.0
        System.out.println("Built-in: " + result1_4); // Expected: 1024.0
        System.out.println("Bit Manipulation: " + result1_5); // Expected: 1024.0
        
        boolean allEqual1 = isEqual(result1_1, result1_2, tolerance) && 
                           isEqual(result1_2, result1_3, tolerance) &&
                           isEqual(result1_3, result1_4, tolerance) &&
                           isEqual(result1_4, result1_5, tolerance);
        System.out.println("All methods agree: " + allEqual1);
        System.out.println();

        // Test Case 2: Negative exponent
        double x2 = 2.0;
        int n2 = -2;
        System.out.println("Test Case 2: " + x2 + "^" + n2);
        double result2_1 = myPowIterative(x2, n2);
        double result2_2 = myPowRecursive(x2, n2);
        double result2_4 = myPowBuiltIn(x2, n2);
        
        System.out.println("Iterative: " + result2_1); // Expected: 0.25
        System.out.println("Recursive: " + result2_2); // Expected: 0.25
        System.out.println("Built-in: " + result2_4); // Expected: 0.25
        System.out.println();

        // Test Case 3: Fractional base
        double x3 = 2.1;
        int n3 = 3;
        System.out.println("Test Case 3: " + x3 + "^" + n3);
        double result3_1 = myPowIterative(x3, n3);
        double result3_2 = myPowRecursive(x3, n3);
        double result3_4 = myPowBuiltIn(x3, n3);
        
        System.out.println("Iterative: " + result3_1); // Expected: 9.261
        System.out.println("Recursive: " + result3_2); // Expected: 9.261
        System.out.println("Built-in: " + result3_4); // Expected: 9.261
        System.out.println();

        // Test Case 4: Zero exponent
        double x4 = 2.0;
        int n4 = 0;
        System.out.println("Test Case 4: " + x4 + "^" + n4);
        double result4_1 = myPowIterative(x4, n4);
        System.out.println("Iterative: " + result4_1); // Expected: 1.0
        System.out.println();

        // Test Case 5: Edge case - Integer.MIN_VALUE
        double x5 = 2.0;
        int n5 = Integer.MIN_VALUE;
        System.out.println("Test Case 5: " + x5 + "^" + n5 + " (Integer.MIN_VALUE)");
        double result5_1 = myPowIterative(x5, n5);
        double result5_2 = myPowRecursive(x5, n5);
        
        System.out.println("Iterative: " + result5_1); // Expected: very small number
        System.out.println("Recursive: " + result5_2); // Expected: very small number
        System.out.println();

        // Test Case 6: Performance comparison
        double x6 = 2.0;
        int n6 = 1000000;
        System.out.println("Test Case 6: Performance test " + x6 + "^" + n6);
        
        long start = System.currentTimeMillis();
        double result6_1 = myPowIterative(x6, n6);
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Iterative: " + (Double.isInfinite(result6_1) ? "Infinity" : "Large number") + " in " + time1 + "ms");
        
        start = System.currentTimeMillis();
        double result6_2 = myPowRecursive(x6, n6);
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Recursive: " + (Double.isInfinite(result6_2) ? "Infinity" : "Large number") + " in " + time2 + "ms");
        
        start = System.currentTimeMillis();
        double result6_4 = myPowBuiltIn(x6, n6);
        long time4 = System.currentTimeMillis() - start;
        System.out.println("Built-in: " + (Double.isInfinite(result6_4) ? "Infinity" : "Large number") + " in " + time4 + "ms");
        
        start = System.currentTimeMillis();
        double result6_5 = myPowBitManipulation(x6, n6);
        long time5 = System.currentTimeMillis() - start;
        System.out.println("Bit Manipulation: " + (Double.isInfinite(result6_5) ? "Infinity" : "Large number") + " in " + time5 + "ms");
        
        // Note: Skipping brute force for large numbers as it would be too slow
        System.out.println();

        // Test Case 7: Negative base
        double x7 = -2.0;
        int n7 = 3;
        System.out.println("Test Case 7: " + x7 + "^" + n7);
        double result7_1 = myPowIterative(x7, n7);
        double result7_2 = myPowRecursive(x7, n7);
        
        System.out.println("Iterative: " + result7_1); // Expected: -8.0
        System.out.println("Recursive: " + result7_2); // Expected: -8.0
        System.out.println();

        // Test Case 8: Small numbers
        double x8 = 0.5;
        int n8 = 5;
        System.out.println("Test Case 8: " + x8 + "^" + n8);
        double result8_1 = myPowIterative(x8, n8);
        double result8_2 = myPowRecursive(x8, n8);
        
        System.out.println("Iterative: " + result8_1); // Expected: 0.03125
        System.out.println("Recursive: " + result8_2); // Expected: 0.03125
        System.out.println();

        // Test Case 9: Special cases
        System.out.println("Test Case 9: Special cases");
        System.out.println("1^1000000: " + myPowIterative(1.0, 1000000)); // Expected: 1.0
        System.out.println("(-1)^2: " + myPowIterative(-1.0, 2)); // Expected: 1.0
        System.out.println("(-1)^3: " + myPowIterative(-1.0, 3)); // Expected: -1.0
    }
} 