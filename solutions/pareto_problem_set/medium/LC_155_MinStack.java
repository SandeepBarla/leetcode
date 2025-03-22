/*
 * LeetCode Problem 155: Min Stack
 * URL: https://leetcode.com/problems/min-stack/
 * Difficulty: Medium
 *
 * Approach:
 * - Use a List<int[]> where each element is a pair: [val, minSoFar]
 * - This allows push, pop, top, and getMin in O(1) time.
 *
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) for stack size n
 */
package solutions.pareto_problem_set.medium;

import java.util.ArrayList;
import java.util.List;

import common.Solution;

public class LC_155_MinStack implements Solution {

  static class MinStack {
    List<int[]> stack;

    public MinStack() {
      stack = new ArrayList<>();
    }

    public void push(int val) {
      int minVal = stack.isEmpty() ? val : Math.min(val, stack.get(stack.size() - 1)[1]);
      stack.add(new int[] { val, minVal });
    }

    public void pop() {
      stack.remove(stack.size() - 1); // Java 8-compatible
    }

    public int top() {
      return stack.get(stack.size() - 1)[0];
    }

    public int getMin() {
      return stack.get(stack.size() - 1)[1];
    }
  }

  @Override
  public void run() {
    MinStack stack = new MinStack();

    stack.push(-2);
    stack.push(0);
    stack.push(-3);
    System.out.println("getMin(): " + stack.getMin()); // -3

    stack.pop();
    System.out.println("top(): " + stack.top()); // 0
    System.out.println("getMin(): " + stack.getMin()); // -2

    stack.push(-1);
    System.out.println("top(): " + stack.top()); // -1
    System.out.println("getMin(): " + stack.getMin()); // -2
  }
}