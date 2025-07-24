/*
 * LeetCode Problem 38: Count and Say
 * URL: https://leetcode.com/problems/count-and-say/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Iterative StringBuilder - O(n * L) time, O(L) space
 * 2. Recursive Approach - O(n * L) time, O(n * L) space
 * 3. Two Pointers - O(n * L) time, O(L) space
 *
 * Time Complexity:
 * - All approaches: O(n * L) where n is input and L is length of string at each step
 *
 * Space Complexity:
 * - Approach 1: O(L) - StringBuilder for current result
 * - Approach 2: O(n * L) - recursion call stack
 * - Approach 3: O(L) - string manipulation
 *
 * Where L grows exponentially but is bounded by practical limits
 */

package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_38_CountAndSay implements Solution {

    // Approach 1: Iterative StringBuilder (Optimal)
    public String countAndSayIterative(int n) {
        if (n <= 0) return "";
        
        String result = "1";
        
        for (int i = 1; i < n; i++) {
            result = generateNext(result);
        }
        
        return result;
    }
    
    private String generateNext(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            int count = 1;
            
            // Count consecutive same characters
            while (i + 1 < s.length() && s.charAt(i + 1) == currentChar) {
                count++;
                i++;
            }
            
            // Append count and character
            sb.append(count).append(currentChar);
            i++;
        }
        
        return sb.toString();
    }

    // Approach 2: Recursive Approach
    public String countAndSayRecursive(int n) {
        if (n == 1) return "1";
        
        String prev = countAndSayRecursive(n - 1);
        return generateNextRecursive(prev);
    }
    
    private String generateNextRecursive(String s) {
        if (s.length() == 0) return "";
        
        char firstChar = s.charAt(0);
        int count = 1;
        int i = 1;
        
        // Count consecutive characters
        while (i < s.length() && s.charAt(i) == firstChar) {
            count++;
            i++;
        }
        
        // Recursively process remaining string
        return count + "" + firstChar + generateNextRecursive(s.substring(i));
    }

    // Approach 3: Two Pointers
    public String countAndSayTwoPointers(int n) {
        if (n <= 0) return "";
        
        String current = "1";
        
        for (int i = 1; i < n; i++) {
            current = processWithTwoPointers(current);
        }
        
        return current;
    }
    
    private String processWithTwoPointers(String s) {
        StringBuilder result = new StringBuilder();
        int left = 0;
        
        while (left < s.length()) {
            int right = left;
            char currentChar = s.charAt(left);
            
            // Move right pointer to find end of current group
            while (right < s.length() && s.charAt(right) == currentChar) {
                right++;
            }
            
            // Append count and character
            int count = right - left;
            result.append(count).append(currentChar);
            
            left = right;
        }
        
        return result.toString();
    }

    // Approach 4: Character Array (Memory Optimized)
    public String countAndSayCharArray(int n) {
        if (n <= 0) return "";
        
        String current = "1";
        
        for (int i = 1; i < n; i++) {
            current = processCharArray(current);
        }
        
        return current;
    }
    
    private String processCharArray(String s) {
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();
        
        int i = 0;
        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 1;
            
            // Count consecutive characters
            while (i + 1 < chars.length && chars[i + 1] == currentChar) {
                count++;
                i++;
            }
            
            result.append(count).append(currentChar);
            i++;
        }
        
        return result.toString();
    }

    // Helper method to show the sequence generation
    public void showSequence(int n) {
        System.out.println("Count and Say sequence up to n=" + n + ":");
        String current = "1";
        System.out.println("1: " + current);
        
        for (int i = 2; i <= n; i++) {
            current = generateNext(current);
            System.out.println(i + ": " + current);
        }
        System.out.println();
    }

    @Override
    public void run() {
        // Test Case 1: Basic sequence generation
        int n1 = 4;
        System.out.println("Test Case 1: n = " + n1);
        System.out.println("Iterative: " + countAndSayIterative(n1)); // Expected: "1211"
        System.out.println("Recursive: " + countAndSayRecursive(n1)); // Expected: "1211"
        System.out.println("Two Pointers: " + countAndSayTwoPointers(n1)); // Expected: "1211"
        System.out.println("Char Array: " + countAndSayCharArray(n1)); // Expected: "1211"
        showSequence(n1);

        // Test Case 2: First element
        int n2 = 1;
        System.out.println("Test Case 2: n = " + n2);
        System.out.println("Iterative: " + countAndSayIterative(n2)); // Expected: "1"
        System.out.println("Recursive: " + countAndSayRecursive(n2)); // Expected: "1"
        System.out.println("Two Pointers: " + countAndSayTwoPointers(n2)); // Expected: "1"
        System.out.println("Char Array: " + countAndSayCharArray(n2)); // Expected: "1"
        System.out.println();

        // Test Case 3: Longer sequence
        int n3 = 6;
        System.out.println("Test Case 3: n = " + n3);
        System.out.println("Iterative: " + countAndSayIterative(n3)); // Expected: "312211"
        System.out.println("Recursive: " + countAndSayRecursive(n3)); // Expected: "312211"
        System.out.println("Two Pointers: " + countAndSayTwoPointers(n3)); // Expected: "312211"
        System.out.println("Char Array: " + countAndSayCharArray(n3)); // Expected: "312211"
        showSequence(n3);

        // Test Case 4: Performance comparison
        int n4 = 10;
        System.out.println("Test Case 4: n = " + n4 + " (performance test)");
        
        long start = System.currentTimeMillis();
        String result4_1 = countAndSayIterative(n4);
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Iterative: " + result4_1 + " (length: " + result4_1.length() + ") in " + time1 + "ms");
        
        start = System.currentTimeMillis();
        String result4_2 = countAndSayRecursive(n4);
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Recursive: " + result4_2 + " (length: " + result4_2.length() + ") in " + time2 + "ms");
        
        start = System.currentTimeMillis();
        String result4_3 = countAndSayTwoPointers(n4);
        long time3 = System.currentTimeMillis() - start;
        System.out.println("Two Pointers: " + result4_3 + " (length: " + result4_3.length() + ") in " + time3 + "ms");
        
        start = System.currentTimeMillis();
        String result4_4 = countAndSayCharArray(n4);
        long time4 = System.currentTimeMillis() - start;
        System.out.println("Char Array: " + result4_4 + " (length: " + result4_4.length() + ") in " + time4 + "ms");
        
        // Verify all methods produce same result
        boolean allSame = result4_1.equals(result4_2) && result4_2.equals(result4_3) && result4_3.equals(result4_4);
        System.out.println("All methods produce same result: " + allSame);
        System.out.println();

        // Test Case 5: Edge case
        int n5 = 0;
        System.out.println("Test Case 5: n = " + n5 + " (edge case)");
        System.out.println("Iterative: \"" + countAndSayIterative(n5) + "\""); // Expected: ""
        System.out.println();

        // Test Case 6: Show pattern growth
        System.out.println("Test Case 6: Sequence length growth pattern");
        for (int i = 1; i <= 8; i++) {
            String result = countAndSayIterative(i);
            System.out.println("n=" + i + ": length=" + result.length() + ", value=\"" + result + "\"");
        }
    }
} 