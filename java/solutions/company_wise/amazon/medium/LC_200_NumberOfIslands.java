package solutions.company_wise.amazon.medium;

import common.Solution;

public class LC_200_NumberOfIslands implements Solution {

  // 🔹 Optimized DFS Solution (In-place Modification)
  // T.C = O(m * n); each cell is visited once
  // S.C = O(1); modifies grid directly (no extra space used)
  public int numIslands(char[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          count++; // Found a new island
          expandIsland(i, j, grid, rows, cols);
        }
      }
    }
    return count;
  }

  private void expandIsland(int i, int j, char[][] grid, int rows, int cols) {
    // 🔹 Base case: Check boundaries and if already visited
    if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') {
      return;
    }

    grid[i][j] = '0'; // Mark cell as visited by modifying grid directly

    // 🔹 Explore all 4 directions (DFS)
    expandIsland(i + 1, j, grid, rows, cols); // Down
    expandIsland(i - 1, j, grid, rows, cols); // Up
    expandIsland(i, j + 1, grid, rows, cols); // Right
    expandIsland(i, j - 1, grid, rows, cols); // Left
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    char[][] grid1 = {
        { '1', '1', '1', '1', '0' },
        { '1', '1', '0', '1', '0' },
        { '1', '1', '0', '0', '0' },
        { '0', '0', '0', '0', '0' }
    };
    System.out.println("Number of Islands (Test Case 1): " + numIslands(grid1));

    // 🔹 Test Case 2
    char[][] grid2 = {
        { '1', '1', '0', '0', '0' },
        { '1', '1', '0', '0', '0' },
        { '0', '0', '1', '0', '0' },
        { '0', '0', '0', '1', '1' }
    };
    System.out.println("Number of Islands (Test Case 2): " + numIslands(grid2));

    // 🔹 Test Case 3 (Single Island)
    char[][] grid3 = {
        { '1', '1', '1' },
        { '1', '1', '1' },
        { '1', '1', '1' }
    };
    System.out.println("Number of Islands (Test Case 3): " + numIslands(grid3));

    // 🔹 Test Case 4 (No Islands)
    char[][] grid4 = {
        { '0', '0', '0' },
        { '0', '0', '0' },
        { '0', '0', '0' }
    };
    System.out.println("Number of Islands (Test Case 4): " + numIslands(grid4));
  }
}