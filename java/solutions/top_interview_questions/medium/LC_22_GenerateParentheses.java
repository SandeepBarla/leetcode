/*
 * LeetCode Problem 22: Generate Parentheses
 * URL: https://leetcode.com/problems/generate-parentheses/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Backtracking/DFS - O(4^n/√n) time, O(4^n/√n) space
 * 2. Dynamic Programming - O(4^n/√n) time, O(4^n/√n) space
 * 3. Closure Method - O(4^n/√n) time, O(4^n/√n) space
 *
 * Time Complexity:
 * - All approaches: O(4^n/√n) - Catalan number formula
 *
 * Space Complexity:
 * - All approaches: O(4^n/√n) - storing all valid combinations
 */

package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.List;

import common.Solution;

public class LC_22_GenerateParentheses implements Solution {

    // Approach 1: Backtracking/DFS (Optimal)
    public List<String> generateParenthesesBacktracking(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }
    
    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // Base case: we've used all pairs
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }
        
        // Add opening parenthesis if we haven't used all
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        
        // Add closing parenthesis if it doesn't exceed opening
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    // Approach 2: Dynamic Programming
    public List<String> generateParenthesesDP(int n) {
        List<List<String>> dp = new ArrayList<>();
        
        // Base case
        dp.add(List.of(""));
        
        // Build up from smaller values
        for (int i = 1; i <= n; i++) {
            List<String> current = new ArrayList<>();
            
            for (int j = 0; j < i; j++) {
                List<String> left = dp.get(j);
                List<String> right = dp.get(i - 1 - j);
                
                for (String l : left) {
                    for (String r : right) {
                        current.add("(" + l + ")" + r);
                    }
                }
            }
            
            dp.add(current);
        }
        
        return dp.get(n);
    }

    // Approach 3: Closure Method (Alternative DP approach)
    public List<String> generateParenthesesClosure(int n) {
        if (n == 0) return List.of("");
        
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            List<String> left = generateParenthesesClosure(i);
            List<String> right = generateParenthesesClosure(n - 1 - i);
            
            for (String l : left) {
                for (String r : right) {
                    result.add("(" + l + ")" + r);
                }
            }
        }
        
        return result;
    }

    // Approach 4: Iterative with StringBuilder (Optimized)
    public List<String> generateParenthesesIterative(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        
        // Use a custom class to track state
        class State {
            StringBuilder sb;
            int open, close;
            
            State(StringBuilder sb, int open, int close) {
                this.sb = new StringBuilder(sb);
                this.open = open;
                this.close = close;
            }
        }
        
        List<State> stack = new ArrayList<>();
        stack.add(new State(new StringBuilder(), 0, 0));
        
        while (!stack.isEmpty()) {
            State current = stack.remove(stack.size() - 1);
            
            if (current.sb.length() == n * 2) {
                result.add(current.sb.toString());
                continue;
            }
            
            // Add opening parenthesis
            if (current.open < n) {
                StringBuilder newSb = new StringBuilder(current.sb);
                newSb.append('(');
                stack.add(new State(newSb, current.open + 1, current.close));
            }
            
            // Add closing parenthesis
            if (current.close < current.open) {
                StringBuilder newSb = new StringBuilder(current.sb);
                newSb.append(')');
                stack.add(new State(newSb, current.open, current.close + 1));
            }
        }
        
        return result;
    }

    @Override
    public void run() {
        // Test Case 1: n = 1
        int n1 = 1;
        System.out.println("Test Case 1: n = " + n1);
        System.out.println("Backtracking: " + generateParenthesesBacktracking(n1)); // Expected: ["()"]
        System.out.println("DP: " + generateParenthesesDP(n1)); // Expected: ["()"]
        System.out.println("Closure: " + generateParenthesesClosure(n1)); // Expected: ["()"]
        System.out.println("Iterative: " + generateParenthesesIterative(n1)); // Expected: ["()"]
        System.out.println();

        // Test Case 2: n = 2
        int n2 = 2;
        System.out.println("Test Case 2: n = " + n2);
        System.out.println("Backtracking: " + generateParenthesesBacktracking(n2)); // Expected: ["(())","()()"]
        System.out.println("DP: " + generateParenthesesDP(n2)); // Expected: ["(())","()()"]
        System.out.println("Closure: " + generateParenthesesClosure(n2)); // Expected: ["(())","()()"]
        System.out.println("Iterative: " + generateParenthesesIterative(n2)); // Expected: ["(())","()()"]
        System.out.println();

        // Test Case 3: n = 3
        int n3 = 3;
        System.out.println("Test Case 3: n = " + n3);
        List<String> result3 = generateParenthesesBacktracking(n3);
        System.out.println("Backtracking (" + result3.size() + " combinations): " + result3);
        // Expected: ["((()))","(()())","(())()","()(())","()()()"]
        
        List<String> dp3 = generateParenthesesDP(n3);
        System.out.println("DP (" + dp3.size() + " combinations): " + dp3);
        
        List<String> closure3 = generateParenthesesClosure(n3);
        System.out.println("Closure (" + closure3.size() + " combinations): " + closure3);
        
        List<String> iterative3 = generateParenthesesIterative(n3);
        System.out.println("Iterative (" + iterative3.size() + " combinations): " + iterative3);
        System.out.println();

        // Test Case 4: n = 0
        int n4 = 0;
        System.out.println("Test Case 4: n = " + n4);
        System.out.println("Backtracking: " + generateParenthesesBacktracking(n4)); // Expected: [""]
        System.out.println("DP: " + generateParenthesesDP(n4)); // Expected: [""]
        System.out.println("Closure: " + generateParenthesesClosure(n4)); // Expected: [""]
        System.out.println("Iterative: " + generateParenthesesIterative(n4)); // Expected: []
        System.out.println();

        // Test Case 5: n = 4 (performance test)
        int n5 = 4;
        System.out.println("Test Case 5: n = " + n5 + " (performance test)");
        long start = System.currentTimeMillis();
        List<String> result5 = generateParenthesesBacktracking(n5);
        long time = System.currentTimeMillis() - start;
        System.out.println("Backtracking: " + result5.size() + " combinations in " + time + "ms");
        // Expected: 14 combinations (Catalan number C_4 = 14)
        
        start = System.currentTimeMillis();
        List<String> dp5 = generateParenthesesDP(n5);
        time = System.currentTimeMillis() - start;
        System.out.println("DP: " + dp5.size() + " combinations in " + time + "ms");
    }
} 