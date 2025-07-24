package solutions.neetcode_150.medium;

import java.util.Stack;

import common.Solution;

/**
 * Leetcode 150: Evaluate Reverse Polish Notation
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are: +, -, *, /.
 * Each operand may be an integer or another expression.
 *
 * Example:
 * Input: ["2","1","+","3","*"]
 * Output: 9 → ((2 + 1) * 3)
 *
 * Time Complexity: O(n) – process each token once
 * Space Complexity: O(n) – stack to store intermediate operands
 */
public class LC_150_EvaluateReversePolishNotation extends Solution {

    public int evalRPN(String[] tokens) {
        // Stack to hold operands for evaluation
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            // Check if token is a number (positive or negative)
            if (Character.isDigit(token.charAt(0)) || 
                (token.length() > 1 && Character.isDigit(token.charAt(1)))) {
                stack.push(Integer.parseInt(token));
                continue;
            }

            // Token is an operator → pop two operands from stack
            int b = stack.pop();
            int a = stack.pop();
            int result = 0;

            // Perform the corresponding arithmetic operation
            switch (token) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b; // integer division truncates towards 0
                    break;
            }

            // Push the result back to stack
            stack.push(result);
        }

        // Final result will be at the top of the stack
        return stack.pop();
    }
}