package solutions.company_wise.amazon.medium;

import common.Solution;

public class LC_63_UniquePathsII implements Solution {

  public int uniquePathsWithObstaclesInPlace(int[][] obstacleGrid) {
    // handle base case when first cell is obstacle
    if (obstacleGrid[0][0] == 1)
      return 0;
    obstacleGrid[0][0] = 1;
    // declare rows, cols
    int rows = obstacleGrid.length;
    int cols = obstacleGrid[0].length;
    // fill first row
    for (int j = 1; j < cols; j++) {
      // obstacle detected -> 0 paths
      if (obstacleGrid[0][j] == 1)
        obstacleGrid[0][j] = 0;
      // space detected -> fill the number of paths based on the previous cell
      else
        obstacleGrid[0][j] = obstacleGrid[0][j - 1];
    }
    // fill first col
    for (int i = 1; i < rows; i++) {
      // obstacle detected -> 0 paths
      if (obstacleGrid[i][0] == 1)
        obstacleGrid[i][0] = 0;
      // space detected -> fill the number of paths based on the previous cell
      else
        obstacleGrid[i][0] = obstacleGrid[i - 1][0];
    }
    // fill the remaining cells
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        // space detected -> add the number of paths to reach top cell and left cell
        if (obstacleGrid[i][j] == 0) {
          obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
        }
        // obstacle detected -> 0 paths
        else {
          obstacleGrid[i][j] = 0;
        }
      }
    }
    return obstacleGrid[rows - 1][cols - 1];
  }

  @Override
  public void run() {
    // ✅ Test Case 1
    int[][] grid1 = {
        { 0, 0, 0 },
        { 0, 1, 0 },
        { 0, 0, 0 }
    };
    int expected1 = 2;
    int actual1 = uniquePathsWithObstaclesInPlace(grid1);
    System.out.println("Test Case 1: " + (actual1 == expected1 ? "✅ PASS" : "❌ FAIL") +
        " | Expected: " + expected1 + " | Actual: " + actual1);

    // ✅ Test Case 2
    int[][] grid2 = {
        { 0, 0 },
        { 1, 0 }
    };
    int expected2 = 1;
    int actual2 = uniquePathsWithObstaclesInPlace(grid2);
    System.out.println("Test Case 2: " + (actual2 == expected2 ? "✅ PASS" : "❌ FAIL") +
        " | Expected: " + expected2 + " | Actual: " + actual2);
  }

}