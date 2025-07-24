/*
 * LeetCode Problem 621: Task Scheduler
 * URL: https://leetcode.com/problems/task-scheduler/
 * Difficulty: Medium
 *
 * Approach:
 * - Count the frequency of each task using an array (since tasks are uppercase English letters A-Z).
 * - Find the task with maximum frequency (maxFreq).
 * - Count how many tasks have this maxFreq (maxFreqCount).
 * 
 * - To schedule the tasks with required cooldown 'n', spread the most frequent tasks:
 *     Minimum required time = (maxFreq - 1) * (n + 1) + maxFreqCount
 * 
 * - This gives enough space between repeating tasks and places all max-freq tasks.
 * - If there are more tasks that can fill the idle slots, total time is just tasks.length.
 * - So, return Math.max(calculated time, tasks.length)
 *
 * Time Complexity: O(N), where N = number of tasks
 * Space Complexity: O(1), fixed 26-element array for task frequencies
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_621_TaskScheduler implements Solution {

  public int leastInterval(char[] tasks, int n) {
    int[] freq = new int[26];

    // Count frequency of each task
    for (char task : tasks) {
      freq[task - 'A']++;
    }

    // Find the task with the highest frequency
    int maxFreq = 0;
    for (int count : freq) {
      maxFreq = Math.max(maxFreq, count);
    }

    // Count how many tasks have this highest frequency
    int maxFreqCount = 0;
    for (int count : freq) {
      if (count == maxFreq) {
        maxFreqCount++;
      }
    }

    // Calculate the minimum required time to schedule all tasks with cooldowns
    int partCount = maxFreq - 1;
    int partLength = n + 1;
    int minTime = partCount * partLength + maxFreqCount;

    // Return max of minTime and total number of tasks (if no idle time is needed)
    return Math.max(minTime, tasks.length);
  }

  @Override
  public void run() {
    char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
    int n = 2;
    int result = leastInterval(tasks, n);
    System.out.println("Minimum intervals needed: " + result); // Expected: 8
  }
}