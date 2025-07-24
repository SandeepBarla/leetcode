/*
 * LeetCode Problem 695: Max Area of Island
 * URL: https://leetcode.com/problems/max-area-of-island/
 * Difficulty: Medium
 *
 * Approach: DFS with grid traversal.
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n) in worst-case due to recursion stack.
 */
package solutions.pareto_problem_set.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_695_MaxAreaOfIsland implements Solution {

  public int maxAreaOfIsland(int[][] grid) {
    // Edge case: if grid is null or empty, return 0
    if (grid == null || grid.length == 0)
      return 0;
    int rows = grid.length;
    int cols = grid[0].length;
    int maxArea = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          int area = dfs(i, j, rows, cols, grid);
          maxArea = Math.max(maxArea, area);
        }
      }
    }
    return maxArea;
  }

  private int dfs(int i, int j, int rows, int cols, int[][] grid) {
    if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0)
      return 0;
    grid[i][j] = 0; // mark as visited
    return 1 + dfs(i + 1, j, rows, cols, grid)
        + dfs(i - 1, j, rows, cols, grid)
        + dfs(i, j + 1, rows, cols, grid)
        + dfs(i, j - 1, rows, cols, grid);
  }

  @Override
  public void run() {
    // Test Case 1
    int[][] grid1 = {
        { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
        { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
        { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }
    };
    ArrayUtils.print2DArray("input1", grid1);
    System.out.println("Max Area of Island (Test Case 1): " + maxAreaOfIsland(grid1)); // Expected: 6

    // Test Case 2: Single cell island
    int[][] grid2 = {
        { 1 }
    };
    ArrayUtils.print2DArray("input2", grid2);
    System.out.println("Max Area of Island (Test Case 2): " + maxAreaOfIsland(grid2)); // Expected: 1

    // Test Case 3: No island
    int[][] grid3 = {
        { 0, 0, 0 },
        { 0, 0, 0 },
        { 0, 0, 0 }
    };
    ArrayUtils.print2DArray("input3", grid3);
    System.out.println("Max Area of Island (Test Case 3): " + maxAreaOfIsland(grid3)); // Expected: 0
  }
}