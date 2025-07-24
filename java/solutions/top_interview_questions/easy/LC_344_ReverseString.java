/*
 * LeetCode Problem 344: Reverse String
 * URL: https://leetcode.com/problems/reverse-string/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. Two Pointers (Optimal) - O(n) time, O(1) space
 * 2. Recursion - O(n) time, O(n) space
 * 3. Stack Approach - O(n) time, O(n) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass with two pointers
 * - Approach 2: O(n) - recursive calls for each character
 * - Approach 3: O(n) - push all to stack, then pop all
 *
 * Space Complexity:
 * - Approach 1: O(1) - in-place swapping
 * - Approach 2: O(n) - recursion call stack
 * - Approach 3: O(n) - stack storage
 */

package solutions.top_interview_questions.easy;

import java.util.Stack;

import common.Solution;

public class LC_344_ReverseString implements Solution {

    // Approach 1: Two Pointers (Optimal)
    public void reverseStringTwoPointers(char[] s) {
        int left = 0;
        int right = s.length - 1;
        
        while (left < right) {
            // Swap characters at left and right positions
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            left++;
            right--;
        }
    }

    // Approach 2: Recursion
    public void reverseStringRecursion(char[] s) {
        reverseHelper(s, 0, s.length - 1);
    }
    
    private void reverseHelper(char[] s, int left, int right) {
        if (left >= right) {
            return; // Base case: pointers meet or cross
        }
        
        // Swap characters
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        
        // Recursive call for inner elements
        reverseHelper(s, left + 1, right - 1);
    }

    // Approach 3: Stack Approach
    public void reverseStringStack(char[] s) {
        Stack<Character> stack = new Stack<>();
        
        // Push all characters to stack
        for (char c : s) {
            stack.push(c);
        }
        
        // Pop characters back to array (LIFO order gives reverse)
        for (int i = 0; i < s.length; i++) {
            s[i] = stack.pop();
        }
    }

    // Approach 4: Using XOR for swapping (Educational)
    public void reverseStringXOR(char[] s) {
        int left = 0;
        int right = s.length - 1;
        
        while (left < right) {
            // XOR swap (works because a ^ b ^ a = b)
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
            
            left++;
            right--;
        }
    }

    @Override
    public void run() {
        // Test Case 1: Normal string
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        char[] s1Copy1 = s1.clone();
        char[] s1Copy2 = s1.clone();
        char[] s1Copy3 = s1.clone();
        char[] s1Copy4 = s1.clone();
        
        System.out.println("Test Case 1:");
        System.out.println("Original: " + new String(s1));
        
        reverseStringTwoPointers(s1Copy1);
        System.out.println("Two Pointers: " + new String(s1Copy1)); // Expected: "olleh"
        
        reverseStringRecursion(s1Copy2);
        System.out.println("Recursion: " + new String(s1Copy2)); // Expected: "olleh"
        
        reverseStringStack(s1Copy3);
        System.out.println("Stack: " + new String(s1Copy3)); // Expected: "olleh"
        
        reverseStringXOR(s1Copy4);
        System.out.println("XOR Swap: " + new String(s1Copy4)); // Expected: "olleh"
        System.out.println();

        // Test Case 2: Even length string
        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        char[] s2Copy = s2.clone();
        
        System.out.println("Test Case 2 (Even length):");
        System.out.println("Original: " + new String(s2));
        reverseStringTwoPointers(s2Copy);
        System.out.println("Reversed: " + new String(s2Copy)); // Expected: "hannaH"
        System.out.println();

        // Test Case 3: Single character
        char[] s3 = {'A'};
        char[] s3Copy = s3.clone();
        
        System.out.println("Test Case 3 (Single character):");
        System.out.println("Original: " + new String(s3));
        reverseStringTwoPointers(s3Copy);
        System.out.println("Reversed: " + new String(s3Copy)); // Expected: "A"
        System.out.println();

        // Test Case 4: Two characters
        char[] s4 = {'a', 'b'};
        char[] s4Copy = s4.clone();
        
        System.out.println("Test Case 4 (Two characters):");
        System.out.println("Original: " + new String(s4));
        reverseStringTwoPointers(s4Copy);
        System.out.println("Reversed: " + new String(s4Copy)); // Expected: "ba"
        System.out.println();

        // Test Case 5: Palindrome
        char[] s5 = {'a', 'b', 'c', 'b', 'a'};
        char[] s5Copy = s5.clone();
        
        System.out.println("Test Case 5 (Palindrome):");
        System.out.println("Original: " + new String(s5));
        reverseStringTwoPointers(s5Copy);
        System.out.println("Reversed: " + new String(s5Copy)); // Expected: "abcba"
    }
} 