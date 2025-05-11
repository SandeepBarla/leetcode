/*
 * Custom Problem: Minimum Time to Reach Bottom-Right Cell in Grid with Delay
 *
 * Description:
 * - Given a grid where each cell has a `moveTime[i][j]` value representing
 *   the earliest time you can enter that cell, find the minimum time to reach
 *   the bottom-right cell (n-1, m-1) from the top-left (0,0).
 * - You can move in 4 directions (up, down, left, right), each move costs 1 time unit.
 * - You must wait at the cell until its `moveTime` is satisfied.
 *
 * Approach:
 * - Use Dijkstra's algorithm with a min-heap (priority queue).
 * - Track the earliest time to reach each cell.
 *
 * Time Complexity: O(n * m * log(n * m))
 * Space Complexity: O(n * m)
 */

package solutions.company_wise.google.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import common.Solution;

public class LC_3341_FindMinimumTimeToReachLastRoomI implements Solution {

  public int minTimeToReach(int[][] moveTime) {
    int n = moveTime.length;
    int m = moveTime[0].length;

    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    int[][] visited = new int[n][m];
    for (int[] row : visited)
      Arrays.fill(row, Integer.MAX_VALUE);

    minHeap.offer(new int[] { 0, 0, moveTime[0][0] });

    while (!minHeap.isEmpty()) {
      int[] cell = minHeap.poll();
      int i = cell[0], j = cell[1], currTime = cell[2];

      if (visited[i][j] != Integer.MAX_VALUE)
        continue;
      visited[i][j] = currTime;

      if (i == n - 1 && j == m - 1)
        break;

      check(i + 1, j, currTime, n, m, visited, minHeap, moveTime);
      check(i - 1, j, currTime, n, m, visited, minHeap, moveTime);
      check(i, j + 1, currTime, n, m, visited, minHeap, moveTime);
      check(i, j - 1, currTime, n, m, visited, minHeap, moveTime);
    }

    return visited[n - 1][m - 1];
  }

  private void check(int i, int j, int time, int n, int m, int[][] visited,
      PriorityQueue<int[]> heap, int[][] grid) {
    if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j] != Integer.MAX_VALUE)
      return;
    int waitTime = Math.max(time, grid[i][j]) + 1;
    heap.offer(new int[] { i, j, waitTime });
  }

  @Override
  public void run() {
    int[][] grid1 = {
        { 0, 2, 1 },
        { 1, 3, 4 },
        { 2, 1, 0 }
    };
    System.out.println("Minimum time to reach bottom-right: " + minTimeToReach(grid1));

    int[][] grid2 = {
        { 0, 100 },
        { 1, 0 }
    };
    System.out.println("Minimum time to reach bottom-right: " + minTimeToReach(grid2));
  }
}