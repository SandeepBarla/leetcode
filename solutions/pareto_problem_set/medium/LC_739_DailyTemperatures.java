/*
 * LeetCode Problem 739: Daily Temperatures
 * URL: https://leetcode.com/problems/daily-temperatures/
 * Difficulty: Medium
 *
 * Approach: Monotonic Stack (Decreasing)
 * - Use a stack to store indices of unresolved temperatures.
 * - As we find warmer temperatures, we resolve previous indices by calculating the wait.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
package solutions.pareto_problem_set.medium;

import java.util.Stack;

import common.ArrayUtils;
import common.Solution;

public class LC_739_DailyTemperatures implements Solution {

  public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] result = new int[n];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        int prevIndex = stack.pop();
        result[prevIndex] = i - prevIndex;
      }
      stack.push(i);
    }

    return result;
  }

  @Override
  public void run() {
    // Test Case 1
    int[] temps1 = { 73, 74, 75, 71, 69, 72, 76, 73 };
    ArrayUtils.printArray("Input", temps1);
    ArrayUtils.printArray("Output", dailyTemperatures(temps1));

    // Test Case 2
    int[] temps2 = { 30, 40, 50, 60 };
    ArrayUtils.printArray("Input", temps2);
    ArrayUtils.printArray("Output", dailyTemperatures(temps2));

    // Test Case 3
    int[] temps3 = { 30, 60, 90 };
    ArrayUtils.printArray("Input", temps3);
    ArrayUtils.printArray("Output", dailyTemperatures(temps3));

    // Test Case 4
    int[] temps4 = { 90, 80, 70, 60 };
    ArrayUtils.printArray("Input", temps4);
    ArrayUtils.printArray("Output", dailyTemperatures(temps4));
  }
}