/*
 * LeetCode Problem 190: Reverse Bits
 * URL: https://leetcode.com/problems/reverse-bits/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Bit Manipulation (LSB First) - O(1) time, O(1) space
 * 2. Bit Manipulation (MSB First) - O(1) time, O(1) space
 * 3. Built-in Function - O(1) time, O(1) space
 * 4. String Conversion - O(1) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(1) - fixed 32 iterations
 *
 * Space Complexity:
 * - Approach 1,2,3: O(1) - only using variables
 * - Approach 4: O(1) - string of fixed length 32
 */

package solutions.top_interview_questions.easy;

import common.Solution;

public class LC_190_ReverseBits implements Solution {

    // Approach 1: Bit Manipulation (LSB First) - Extract LSB and build result
    public int reverseBitsLSBFirst(int n) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            int lsb = n & 1; // Extract the least significant bit
            result = (result << 1) | lsb; // Shift result left and add the bit
            n = n >>> 1; // Unsigned right shift to get next bit
        }
        
        return result;
    }

    // Approach 2: Bit Manipulation (MSB First) - Check each position from left
    public int reverseBitsMSBFirst(int n) {
        int result = 0;
        
        for (int i = 31; i >= 0; i--) {
            int mask = 1 << i; // Create mask for position i
            int bit = n & mask; // Extract bit at position i
            
            if (bit != 0) { // If bit is 1
                int targetMask = 1 << (31 - i); // Calculate target position
                result = result | targetMask; // Set bit in result
            }
        }
        
        return result;
    }

    // Approach 3: Using Integer.reverse (Built-in)
    public int reverseBitsBuiltIn(int n) {
        return Integer.reverse(n);
    }

    // Approach 4: String Conversion Approach
    public int reverseBitsString(int n) {
        // Convert to binary string with leading zeros
        String binary = String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
        
        // Reverse the string
        String reversed = new StringBuilder(binary).reverse().toString();
        
        // Convert back to integer (handle as unsigned)
        return (int) Long.parseLong(reversed, 2);
    }

    // Approach 5: Divide and Conquer (Optimal for multiple calls)
    public int reverseBitsDivideConquer(int n) {
        // Swap adjacent bits
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);
        
        // Swap adjacent pairs
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);
        
        // Swap adjacent 4-bit groups
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);
        
        // Swap adjacent bytes
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);
        
        // Swap adjacent 16-bit groups
        n = (n >>> 16) | (n << 16);
        
        return n;
    }

    // Helper method to print binary representation
    private void printBinary(String label, int num) {
        String binary = String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0');
        System.out.println(label + ": " + binary + " (" + num + ")");
    }

    @Override
    public void run() {
        // Test Case 1: Basic example
        int n1 = 43261596; // Binary: 00000010100101000001111010011100
        System.out.println("Test Case 1:");
        printBinary("Original", n1);
        
        int result1_1 = reverseBitsLSBFirst(n1);
        int result1_2 = reverseBitsMSBFirst(n1);
        int result1_3 = reverseBitsBuiltIn(n1);
        int result1_4 = reverseBitsString(n1);
        int result1_5 = reverseBitsDivideConquer(n1);
        
        printBinary("LSB First", result1_1);
        printBinary("MSB First", result1_2);
        printBinary("Built-in", result1_3);
        printBinary("String", result1_4);
        printBinary("Divide & Conquer", result1_5);
        
        // Expected: 964176192 (Binary: 00111001011110000010100101000000)
        boolean allEqual1 = (result1_1 == result1_2) && (result1_2 == result1_3) && 
                           (result1_3 == result1_4) && (result1_4 == result1_5);
        System.out.println("All methods agree: " + allEqual1);
        System.out.println();

        // Test Case 2: All zeros
        int n2 = 0;
        System.out.println("Test Case 2: All zeros");
        printBinary("Original", n2);
        
        int result2_1 = reverseBitsLSBFirst(n2);
        printBinary("Reversed", result2_1);
        System.out.println("Expected: 0");
        System.out.println();

        // Test Case 3: All ones (unsigned interpretation)
        int n3 = -1; // All bits set to 1
        System.out.println("Test Case 3: All ones");
        printBinary("Original", n3);
        
        int result3_1 = reverseBitsLSBFirst(n3);
        printBinary("Reversed", result3_1);
        System.out.println("Expected: -1 (all ones remain all ones)");
        System.out.println();

        // Test Case 4: Single bit set
        int n4 = 1; // Binary: 00000000000000000000000000000001
        System.out.println("Test Case 4: Single bit set (rightmost)");
        printBinary("Original", n4);
        
        int result4_1 = reverseBitsLSBFirst(n4);
        printBinary("Reversed", result4_1);
        System.out.println("Expected: -2147483648 (leftmost bit set)");
        System.out.println();

        // Test Case 5: Alternating pattern
        int n5 = 0xAAAAAAAA; // Binary: 10101010101010101010101010101010
        System.out.println("Test Case 5: Alternating pattern");
        printBinary("Original", n5);
        
        int result5_1 = reverseBitsLSBFirst(n5);
        printBinary("Reversed", result5_1);
        System.out.println("Expected: 0x55555555 (opposite alternating)");
        System.out.println();

        // Test Case 6: Performance comparison
        int n6 = 123456789;
        int iterations = 1000000;
        
        System.out.println("Test Case 6: Performance test (" + iterations + " iterations)");
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            reverseBitsLSBFirst(n6);
        }
        long time1 = System.currentTimeMillis() - start;
        System.out.println("LSB First: " + time1 + "ms");
        
        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            reverseBitsMSBFirst(n6);
        }
        long time2 = System.currentTimeMillis() - start;
        System.out.println("MSB First: " + time2 + "ms");
        
        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            reverseBitsBuiltIn(n6);
        }
        long time3 = System.currentTimeMillis() - start;
        System.out.println("Built-in: " + time3 + "ms");
        
        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            reverseBitsDivideConquer(n6);
        }
        long time5 = System.currentTimeMillis() - start;
        System.out.println("Divide & Conquer: " + time5 + "ms");
        
        // Test Case 7: Edge cases
        System.out.println("\nTest Case 7: Edge cases");
        
        int maxInt = Integer.MAX_VALUE;
        System.out.println("Integer.MAX_VALUE:");
        printBinary("Original", maxInt);
        printBinary("Reversed", reverseBitsLSBFirst(maxInt));
        
        int minInt = Integer.MIN_VALUE;
        System.out.println("Integer.MIN_VALUE:");
        printBinary("Original", minInt);
        printBinary("Reversed", reverseBitsLSBFirst(minInt));
    }
} 