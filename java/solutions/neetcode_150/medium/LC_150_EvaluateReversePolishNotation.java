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
public class LC_150_EvaluateReversePolishNotation implements Solution {

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

    @Override
    public void run() {
        System.out.println("Testing LC_150_EvaluateReversePolishNotation...");
        
        // Test case 1: Basic arithmetic
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        int result1 = evalRPN(tokens1);
        System.out.println("Test 1 - tokens: [\"2\",\"1\",\"+\",\"3\",\"*\"]");
        System.out.println("Expected: 9, Got: " + result1);
        System.out.println("Correct: " + (result1 == 9 ? "✓" : "✗"));
        System.out.println();
        
        // Test case 2: Division with truncation
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        int result2 = evalRPN(tokens2);
        System.out.println("Test 2 - tokens: [\"4\",\"13\",\"5\",\"/\",\"+\"]");
        System.out.println("Expected: 6, Got: " + result2);
        System.out.println("Correct: " + (result2 == 6 ? "✓" : "✗"));
        System.out.println();
        
        // Test case 3: Negative numbers and subtraction
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int result3 = evalRPN(tokens3);
        System.out.println("Test 3 - Complex expression with negative numbers");
        System.out.println("Expected: 22, Got: " + result3);
        System.out.println("Correct: " + (result3 == 22 ? "✓" : "✗"));
        System.out.println();
        
        // Test case 4: Simple single number
        String[] tokens4 = {"18"};
        int result4 = evalRPN(tokens4);
        System.out.println("Test 4 - Single number: [\"18\"]");
        System.out.println("Expected: 18, Got: " + result4);
        System.out.println("Correct: " + (result4 == 18 ? "✓" : "✗"));
        System.out.println();
        
        // Test case 5: Negative result
        String[] tokens5 = {"4", "3", "-"};
        int result5 = evalRPN(tokens5);
        System.out.println("Test 5 - Subtraction: [\"4\",\"3\",\"-\"]");
        System.out.println("Expected: 1, Got: " + result5);
        System.out.println("Correct: " + (result5 == 1 ? "✓" : "✗"));
    }
}