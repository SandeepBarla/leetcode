/*
 * LeetCode Problem 994: Rotting Oranges
 * URL: https://leetcode.com/problems/rotting-oranges/
 * Difficulty: Medium
 *
 * Approach: Breadth-First Search (BFS) on the grid.
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n) in the worst-case due to the queue.
 */
package solutions.company_wise.amazon.medium;

import java.util.LinkedList;
import java.util.Queue;

import common.ArrayUtils;
import common.Solution;

public class LC_994_RottingOranges implements Solution {

  public int orangesRotting(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int numOfFreshOranges = 0;
    Queue<int[]> queue = new LinkedList<>();

    // Add initially rotten oranges to the queue and count fresh oranges.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 2) {
          queue.add(new int[] { i, j });
        } else if (grid[i][j] == 1) {
          numOfFreshOranges++;
        }
      }
    }

    // If there are no fresh oranges, return 0.
    if (numOfFreshOranges == 0)
      return 0;

    int time = 0;
    int[][] directions = new int[][] {
        { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
    };

    // BFS traversal to rot adjacent fresh oranges.
    while (!queue.isEmpty()) {
      int size = queue.size();
      time++; // Increment time at each level of BFS.

      for (int i = 0; i < size; i++) {
        int[] index = queue.poll();
        for (int[] dir : directions) {
          int newi = index[0] + dir[0];
          int newj = index[1] + dir[1];
          if (newi >= 0 && newi < rows && newj >= 0 && newj < cols && grid[newi][newj] == 1) {
            numOfFreshOranges--;
            grid[newi][newj] = 2;
            queue.add(new int[] { newi, newj });
          }
        }
      }

      // If all fresh oranges have rotted, return the time.
      if (numOfFreshOranges == 0)
        return time;
    }

    return -1;
  }

  @Override
  public void run() {
    // Test Case 1: Expected output is 4
    int[][] grid1 = {
        { 2, 1, 1 },
        { 1, 1, 0 },
        { 0, 1, 1 }
    };
    ArrayUtils.print2DArray("input1", grid1);
    System.out.println("Minutes to rot (Test Case 1): " + orangesRotting(grid1));

    // Test Case 2: Expected output is -1 (not all oranges can rot)
    int[][] grid2 = {
        { 2, 1, 1 },
        { 0, 1, 1 },
        { 1, 0, 1 }
    };
    ArrayUtils.print2DArray("input2", grid2);
    System.out.println("Minutes to rot (Test Case 2): " + orangesRotting(grid2));

    // Test Case 3: Expected output is 0 (no fresh oranges)
    int[][] grid3 = {
        { 0, 2 }
    };
    ArrayUtils.print2DArray("input3", grid3);

    System.out.println("Minutes to rot (Test Case 3): " + orangesRotting(grid3));
  }
}