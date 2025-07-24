/*
 * LeetCode Problem 202: Happy Number
 * URL: https://leetcode.com/problems/happy-number/
 * Difficulty: Easy
 *
 * Approaches:
 * 1. HashSet Cycle Detection - O(log n) time, O(log n) space
 * 2. Floyd's Cycle Detection (Two Pointers) - O(log n) time, O(1) space
 * 3. Mathematical Approach with Known Cycles - O(log n) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(log n) - number of digits decreases rapidly
 *
 * Space Complexity:
 * - Approach 1: O(log n) - HashSet storage for cycle detection
 * - Approach 2: O(1) - only using two pointers
 * - Approach 3: O(1) - no extra space
 */

package solutions.top_interview_questions.easy;

import java.util.HashSet;
import java.util.Set;

import common.Solution;

public class LC_202_HappyNumber implements Solution {

    // Approach 1: HashSet Cycle Detection
    public boolean isHappyHashSet(int n) {
        Set<Integer> seen = new HashSet<>();
        
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getSumOfSquares(n);
        }
        
        return n == 1;
    }

    // Approach 2: Floyd's Cycle Detection (Two Pointers) - Optimal
    public boolean isHappyFloyd(int n) {
        int slow = n;
        int fast = n;
        
        do {
            slow = getSumOfSquares(slow);
            fast = getSumOfSquares(getSumOfSquares(fast));
        } while (slow != fast);
        
        return slow == 1;
    }

    // Approach 3: Mathematical Approach with Known Cycles
    public boolean isHappyMathematical(int n) {
        while (n != 1 && n != 4) {
            n = getSumOfSquares(n);
        }
        return n == 1;
    }

    // Helper method to calculate sum of squares of digits
    private int getSumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    @Override
    public void run() {
        // Test Case 1: Happy number
        int n1 = 19;
        System.out.println("Test Case 1: " + n1);
        System.out.println("HashSet: " + isHappyHashSet(n1)); // Expected: true
        System.out.println("Floyd: " + isHappyFloyd(n1)); // Expected: true
        System.out.println("Mathematical: " + isHappyMathematical(n1)); // Expected: true
        System.out.println("Process: " + getHappyProcess(n1));
        System.out.println();

        // Test Case 2: Not happy number
        int n2 = 2;
        System.out.println("Test Case 2: " + n2);
        System.out.println("HashSet: " + isHappyHashSet(n2)); // Expected: false
        System.out.println("Floyd: " + isHappyFloyd(n2)); // Expected: false
        System.out.println("Mathematical: " + isHappyMathematical(n2)); // Expected: false
        System.out.println("Process: " + getHappyProcess(n2));
        System.out.println();

        // Test Case 3: Single digit happy number
        int n3 = 1;
        System.out.println("Test Case 3: " + n3);
        System.out.println("HashSet: " + isHappyHashSet(n3)); // Expected: true
        System.out.println("Floyd: " + isHappyFloyd(n3)); // Expected: true
        System.out.println("Mathematical: " + isHappyMathematical(n3)); // Expected: true
        System.out.println();

        // Test Case 4: Single digit unhappy number
        int n4 = 4;
        System.out.println("Test Case 4: " + n4);
        System.out.println("HashSet: " + isHappyHashSet(n4)); // Expected: false
        System.out.println("Floyd: " + isHappyFloyd(n4)); // Expected: false
        System.out.println("Mathematical: " + isHappyMathematical(n4)); // Expected: false
        System.out.println();

        // Test Case 5: Large happy number
        int n5 = 100;
        System.out.println("Test Case 5: " + n5);
        System.out.println("HashSet: " + isHappyHashSet(n5)); // Expected: true
        System.out.println("Floyd: " + isHappyFloyd(n5)); // Expected: true
        System.out.println("Mathematical: " + isHappyMathematical(n5)); // Expected: true
        System.out.println("Process: " + getHappyProcess(n5));
    }
    
    // Helper method to show the process (for demonstration)
    private String getHappyProcess(int n) {
        StringBuilder process = new StringBuilder();
        Set<Integer> seen = new HashSet<>();
        
        while (n != 1 && !seen.contains(n)) {
            process.append(n).append(" → ");
            seen.add(n);
            n = getSumOfSquares(n);
        }
        
        if (n == 1) {
            process.append("1 (Happy!)");
        } else {
            process.append("... (Cycle detected)");
        }
        
        return process.toString();
    }
} 