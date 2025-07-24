/*
 * LeetCode Problem 191: Number of 1 Bits
 * URL: https://leetcode.com/problems/number-of-1-bits/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Bit Manipulation with Right Shift - O(log n) time, O(1) space
 * 2. Brian Kernighan's Algorithm - O(k) time, O(1) space (k = number of 1s)
 * 3. Built-in Function - O(1) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(log n) - check each bit position
 * - Approach 2: O(k) - only iterate through set bits
 * - Approach 3: O(1) - built-in function
 *
 * Space Complexity:
 * - All approaches: O(1) - only using variables
 */

package solutions.top_interview_questions.easy;

import common.Solution;

public class LC_191_NumberOf1Bits implements Solution {

    // Approach 1: Bit Manipulation with Right Shift
    public int hammingWeightRightShift(int n) {
        int count = 0;
        
        while (n != 0) {
            count += (n & 1); // Add 1 if LSB is 1
            n >>>= 1; // Unsigned right shift to handle negative numbers
        }
        
        return count;
    }

    // Approach 2: Brian Kernighan's Algorithm (Optimal)
    public int hammingWeightBrianKernighan(int n) {
        int count = 0;
        
        while (n != 0) {
            count++;
            n &= (n - 1); // Remove the rightmost set bit
        }
        
        return count;
    }

    // Approach 3: Built-in Function
    public int hammingWeightBuiltIn(int n) {
        return Integer.bitCount(n);
    }

    // Approach 4: Bit Manipulation with Mask
    public int hammingWeightMask(int n) {
        int count = 0;
        int mask = 1;
        
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1; // Shift mask to check next bit
        }
        
        return count;
    }

    @Override
    public void run() {
        // Test Case 1: Number with multiple 1 bits
        int n1 = 0b00000000000000000000000000001011; // 11 in binary (3 ones)
        System.out.println("Test Case 1: " + Integer.toBinaryString(n1) + " (" + n1 + ")");
        System.out.println("Right Shift: " + hammingWeightRightShift(n1)); // Expected: 3
        System.out.println("Brian Kernighan: " + hammingWeightBrianKernighan(n1)); // Expected: 3
        System.out.println("Built-in: " + hammingWeightBuiltIn(n1)); // Expected: 3
        System.out.println("Mask: " + hammingWeightMask(n1)); // Expected: 3
        System.out.println();

        // Test Case 2: Number with single 1 bit
        int n2 = 0b00000000000000000000000010000000; // 128 in binary (1 one)
        System.out.println("Test Case 2: " + Integer.toBinaryString(n2) + " (" + n2 + ")");
        System.out.println("Right Shift: " + hammingWeightRightShift(n2)); // Expected: 1
        System.out.println("Brian Kernighan: " + hammingWeightBrianKernighan(n2)); // Expected: 1
        System.out.println("Built-in: " + hammingWeightBuiltIn(n2)); // Expected: 1
        System.out.println("Mask: " + hammingWeightMask(n2)); // Expected: 1
        System.out.println();

        // Test Case 3: Negative number (all 1s in two's complement)
        int n3 = -3; // 11111111111111111111111111111101 in binary (30 ones)
        System.out.println("Test Case 3: " + Integer.toBinaryString(n3) + " (" + n3 + ")");
        System.out.println("Right Shift: " + hammingWeightRightShift(n3)); // Expected: 30
        System.out.println("Brian Kernighan: " + hammingWeightBrianKernighan(n3)); // Expected: 30
        System.out.println("Built-in: " + hammingWeightBuiltIn(n3)); // Expected: 30
        System.out.println("Mask: " + hammingWeightMask(n3)); // Expected: 30
        System.out.println();

        // Test Case 4: Zero
        int n4 = 0;
        System.out.println("Test Case 4: " + Integer.toBinaryString(n4) + " (" + n4 + ")");
        System.out.println("Right Shift: " + hammingWeightRightShift(n4)); // Expected: 0
        System.out.println("Brian Kernighan: " + hammingWeightBrianKernighan(n4)); // Expected: 0
        System.out.println("Built-in: " + hammingWeightBuiltIn(n4)); // Expected: 0
        System.out.println("Mask: " + hammingWeightMask(n4)); // Expected: 0
        System.out.println();

        // Test Case 5: Maximum positive integer
        int n5 = Integer.MAX_VALUE; // 01111111111111111111111111111111 (31 ones)
        System.out.println("Test Case 5: Integer.MAX_VALUE (" + n5 + ")");
        System.out.println("Right Shift: " + hammingWeightRightShift(n5)); // Expected: 31
        System.out.println("Brian Kernighan: " + hammingWeightBrianKernighan(n5)); // Expected: 31
        System.out.println("Built-in: " + hammingWeightBuiltIn(n5)); // Expected: 31
        System.out.println("Mask: " + hammingWeightMask(n5)); // Expected: 31
    }
} 