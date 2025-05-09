/*
 * LeetCode Problem 210: Course Schedule II
 * URL: https://leetcode.com/problems/course-schedule-ii/
 * Difficulty: Medium
 *
 * Approach:
 * - Perform topological sort using Kahn's Algorithm (BFS).
 * - Track in-degree for each course.
 * - Process courses with zero in-degree and reduce neighbors' in-degrees.
 * - If all courses are processed, return the result; otherwise, return an empty array.
 *
 * Time Complexity: O(n + e), where n = numCourses and e = prerequisites.length
 * Space Complexity: O(n + e)
 */

package solutions.pareto_problem_set.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Solution;

public class LC_210_CourseScheduleII implements Solution {

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<Integer>[] graph = new ArrayList[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graph[i] = new ArrayList<>();
    }

    int[] inDegree = new int[numCourses];
    for (int[] pair : prerequisites) {
      int course = pair[0];
      int prereq = pair[1];
      graph[prereq].add(course);
      inDegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    int[] order = new int[numCourses];
    int index = 0;

    while (!queue.isEmpty()) {
      int course = queue.poll();
      order[index++] = course;

      for (int next : graph[course]) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          queue.offer(next);
        }
      }
    }

    return index == numCourses ? order : new int[] {};
  }

  @Override
  public void run() {
    int[][] prerequisites1 = { { 1, 0 } };
    int[] order1 = findOrder(2, prerequisites1);
    System.out.println("Course Order 1: " + Arrays.toString(order1));

    int[][] prerequisites2 = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
    int[] order2 = findOrder(4, prerequisites2);
    System.out.println("Course Order 2: " + Arrays.toString(order2));

    int[][] prerequisites3 = { { 1, 0 }, { 0, 1 } }; // cycle
    int[] order3 = findOrder(2, prerequisites3);
    System.out.println("Course Order 3: " + Arrays.toString(order3));
  }
}