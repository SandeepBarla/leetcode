/*
 * LeetCode Problem 207: Course Schedule
 * URL: https://leetcode.com/problems/course-schedule/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. DFS for cycle detection
 * 2. BFS (Topological Sort via Kahn's Algorithm)
 *
 * Time Complexity (Both): O(numCourses + prerequisites.length)
 * Space Complexity (Both): O(numCourses + prerequisites.length)
 */
package solutions.pareto_problem_set.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Solution;

public class LC_207_CourseSchedule implements Solution {

  // Approach 1: DFS for Cycle Detection
  public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
    List<Integer>[] graph = new ArrayList[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] prereq : prerequisites) {
      graph[prereq[0]].add(prereq[1]);
    }

    boolean[] isCompleted = new boolean[numCourses];
    boolean[] isVisited = new boolean[numCourses];

    for (int course = 0; course < numCourses; course++) {
      if (hasCycle(graph, course, isCompleted, isVisited)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasCycle(List<Integer>[] graph, int course, boolean[] isCompleted, boolean[] isVisited) {
    if (isVisited[course])
      return true;
    if (isCompleted[course])
      return false;

    isVisited[course] = true;
    isCompleted[course] = true;

    for (int prereq : graph[course]) {
      if (hasCycle(graph, prereq, isCompleted, isVisited)) {
        return true;
      }
    }

    isVisited[course] = false;
    return false;
  }

  // Approach 2: BFS (Kahn's Algorithm)
  public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
    List<Integer>[] graph = new ArrayList[numCourses];
    int[] inDegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] prereq : prerequisites) {
      int course = prereq[0];
      int prereqCourse = prereq[1];
      graph[prereqCourse].add(course);
      inDegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0)
        queue.offer(i);
    }

    int completedCourses = 0;
    while (!queue.isEmpty()) {
      int curr = queue.poll();
      completedCourses++;
      for (int next : graph[curr]) {
        inDegree[next]--;
        if (inDegree[next] == 0)
          queue.offer(next);
      }
    }

    return completedCourses == numCourses;
  }

  @Override
  public void run() {
    int numCourses1 = 6;
    int[][] prerequisites1 = {
        { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 }, { 4, 3 }, { 5, 4 }
    };

    System.out.println("[DFS] Can finish courses (Test Case 1): " + canFinishDFS(numCourses1, prerequisites1));
    System.out.println("[BFS] Can finish courses (Test Case 1): " + canFinishBFS(numCourses1, prerequisites1));

    int numCourses2 = 2;
    int[][] prerequisites2 = {
        { 1, 0 }, { 0, 1 }
    };

    System.out.println("[DFS] Can finish courses (Test Case 2): " + canFinishDFS(numCourses2, prerequisites2));
    System.out.println("[BFS] Can finish courses (Test Case 2): " + canFinishBFS(numCourses2, prerequisites2));
  }
}