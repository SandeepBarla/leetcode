/*
 * LeetCode Problem 20: Valid Parentheses
 * URL: https://leetcode.com/problems/valid-parentheses/
 * Difficulty: Easy
 *
 * Approach: Deque + HashMap
 * - Use an ArrayDeque to track opening brackets.
 * - On encountering a closing bracket, verify it matches the top of the deque.
 * - If mismatched or deque is empty, return false.
 * - At the end, return true if deque is empty.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
package solutions.top_interview_questions.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_20_ValidParentheses implements Solution {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (map.containsValue(c)) {
                stack.offerLast(c);
            } else {
                if (stack.isEmpty() || stack.pollLast() != map.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    @Override
    public void run() {
        String s1 = "()";
        System.out.println("Input: " + s1);
        System.out.println("Valid: " + isValid(s1));

        String s2 = "()[]{}";
        System.out.println("Input: " + s2);
        System.out.println("Valid: " + isValid(s2));

        String s3 = "(]";
        System.out.println("Input: " + s3);
        System.out.println("Valid: " + isValid(s3));

        String s4 = "([)]";
        System.out.println("Input: " + s4);
        System.out.println("Valid: " + isValid(s4));

        String s5 = "{[]}";
        System.out.println("Input: " + s5);
        System.out.println("Valid: " + isValid(s5));
    }
}