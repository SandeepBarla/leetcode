/*
 * LeetCode Problem 171: Excel Sheet Column Number
 * URL: https://leetcode.com/problems/excel-sheet-column-number/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Base-26 Conversion with ASCII - O(n) time, O(1) space
 * 2. Iterative Multiplication - O(n) time, O(1) space
 * 3. Mathematical Formula - O(n) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(n) - single pass through string
 *
 * Space Complexity:
 * - All approaches: O(1) - only using variables
 */

package solutions.top_interview_questions.easy;

import common.Solution;

public class LC_171_ExcelSheetColumnNumber implements Solution {

    // Approach 1: Base-26 Conversion with ASCII Values
    public int titleToNumberASCII(String columnTitle) {
        int result = 0;
        int n = columnTitle.length();
        
        for (int i = 0; i < n; i++) {
            int charValue = columnTitle.charAt(i) - 'A' + 1; // A=1, B=2, ..., Z=26
            result += charValue * Math.pow(26, n - i - 1);
        }
        
        return result;
    }

    // Approach 2: Iterative Multiplication (Optimal)
    public int titleToNumberIterative(String columnTitle) {
        int result = 0;
        
        for (char c : columnTitle.toCharArray()) {
            result = result * 26 + (c - 'A' + 1);
        }
        
        return result;
    }

    // Approach 3: Mathematical Formula (Right to Left)
    public int titleToNumberMathematical(String columnTitle) {
        int result = 0;
        int power = 0;
        
        // Process from right to left
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int charValue = columnTitle.charAt(i) - 'A' + 1;
            result += charValue * Math.pow(26, power);
            power++;
        }
        
        return result;
    }

    @Override
    public void run() {
        // Test Case 1: Single character
        String col1 = "A";
        System.out.println("Test Case 1: " + col1);
        System.out.println("ASCII: " + titleToNumberASCII(col1)); // Expected: 1
        System.out.println("Iterative: " + titleToNumberIterative(col1)); // Expected: 1
        System.out.println("Mathematical: " + titleToNumberMathematical(col1)); // Expected: 1
        System.out.println();

        // Test Case 2: Single character at end
        String col2 = "Z";
        System.out.println("Test Case 2: " + col2);
        System.out.println("ASCII: " + titleToNumberASCII(col2)); // Expected: 26
        System.out.println("Iterative: " + titleToNumberIterative(col2)); // Expected: 26
        System.out.println("Mathematical: " + titleToNumberMathematical(col2)); // Expected: 26
        System.out.println();

        // Test Case 3: Two characters
        String col3 = "AB";
        System.out.println("Test Case 3: " + col3);
        System.out.println("ASCII: " + titleToNumberASCII(col3)); // Expected: 28
        System.out.println("Iterative: " + titleToNumberIterative(col3)); // Expected: 28
        System.out.println("Mathematical: " + titleToNumberMathematical(col3)); // Expected: 28
        System.out.println("Calculation: A(1)*26^1 + B(2)*26^0 = 26 + 2 = 28");
        System.out.println();

        // Test Case 4: Three characters
        String col4 = "ZY";
        System.out.println("Test Case 4: " + col4);
        System.out.println("ASCII: " + titleToNumberASCII(col4)); // Expected: 701
        System.out.println("Iterative: " + titleToNumberIterative(col4)); // Expected: 701
        System.out.println("Mathematical: " + titleToNumberMathematical(col4)); // Expected: 701
        System.out.println("Calculation: Z(26)*26^1 + Y(25)*26^0 = 676 + 25 = 701");
        System.out.println();

        // Test Case 5: Longer string
        String col5 = "FXSHRXW";
        System.out.println("Test Case 5: " + col5);
        System.out.println("ASCII: " + titleToNumberASCII(col5)); // Expected: 2147483647
        System.out.println("Iterative: " + titleToNumberIterative(col5)); // Expected: 2147483647
        System.out.println("Mathematical: " + titleToNumberMathematical(col5)); // Expected: 2147483647
        System.out.println();

        // Test Case 6: Edge cases
        String col6 = "AA";
        System.out.println("Test Case 6: " + col6);
        System.out.println("ASCII: " + titleToNumberASCII(col6)); // Expected: 27
        System.out.println("Iterative: " + titleToNumberIterative(col6)); // Expected: 27
        System.out.println("Mathematical: " + titleToNumberMathematical(col6)); // Expected: 27
        System.out.println("Calculation: A(1)*26^1 + A(1)*26^0 = 26 + 1 = 27");
    }
} 