/*
 * LeetCode Problem 417: Pacific Atlantic Water Flow
 * URL: https://leetcode.com/problems/pacific-atlantic-water-flow/
 * Difficulty: Medium
 *
 * Approach: DFS from ocean borders
 * - Run DFS from all Pacific-border cells to find reachable cells.
 * - Run DFS from all Atlantic-border cells to find reachable cells.
 * - Return the intersection of both.
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */
package solutions.pareto_problem_set.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.ArrayUtils;
import common.Solution;

public class LC_417_PacificAtlanticWaterFlow implements Solution {

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int m = heights.length;
    int n = heights[0].length;

    boolean[][] pacific = new boolean[m][n];
    boolean[][] atlantic = new boolean[m][n];

    for (int i = 0; i < m; i++) {
      dfs(heights, pacific, i, 0, heights[i][0]);
      dfs(heights, atlantic, i, n - 1, heights[i][n - 1]);
    }

    for (int j = 0; j < n; j++) {
      dfs(heights, pacific, 0, j, heights[0][j]);
      dfs(heights, atlantic, m - 1, j, heights[m - 1][j]);
    }

    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          result.add(Arrays.asList(i, j));
        }
      }
    }

    return result;
  }

  private void dfs(int[][] heights, boolean[][] visited, int row, int col, int prevHeight) {
    int m = heights.length, n = heights[0].length;

    if (row < 0 || col < 0 || row >= m || col >= n)
      return;
    if (visited[row][col])
      return;
    if (heights[row][col] < prevHeight)
      return;

    visited[row][col] = true;

    dfs(heights, visited, row + 1, col, heights[row][col]);
    dfs(heights, visited, row - 1, col, heights[row][col]);
    dfs(heights, visited, row, col + 1, heights[row][col]);
    dfs(heights, visited, row, col - 1, heights[row][col]);
  }

  @Override
  public void run() {
    int[][] heights = {
        { 1, 2, 2, 3, 5 },
        { 3, 2, 3, 4, 4 },
        { 2, 4, 5, 3, 1 },
        { 6, 7, 1, 4, 5 },
        { 5, 1, 1, 2, 4 }
    };
    ArrayUtils.print2DArray("input", heights);

    List<List<Integer>> result = pacificAtlantic(heights);

    ArrayUtils.print2DList("Pacific Atlantic reachable coordinates:", result);
  }
}