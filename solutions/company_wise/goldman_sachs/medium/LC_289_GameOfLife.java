package solutions.company_wise.goldman_sachs.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_289_GameOfLife implements Solution {

  // ✅ Optimized In-Place Encoding Approach (O(m*n) time, O(1) space)
  public void gameOfLife(int[][] board) {
    int m = board.length;
    int n = board[0].length;

    // 8 possible directions for neighbors
    int[][] directions = {
        { -1, -1 }, { -1, 0 }, { -1, 1 },
        { 0, -1 }, { 0, 1 },
        { 1, -1 }, { 1, 0 }, { 1, 1 }
    };

    // Step 1: Apply rules and mark changes using placeholders
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int liveNeighbors = countLiveNeighbors(board, i, j, directions, m, n);

        // Rule 1 & 3: Live cell dies (Underpopulation / Overpopulation)
        if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
          board[i][j] = -1; // Mark as a live cell that will die
        }
        // Rule 4: Dead cell becomes live (Reproduction)
        else if (board[i][j] == 0 && liveNeighbors == 3) {
          board[i][j] = 2; // Mark as a dead cell that will come to life
        }
      }
    }

    // Step 2: Convert placeholder values back to 0 or 1
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == -1)
          board[i][j] = 0; // Convert dead mark back to 0
        if (board[i][j] == 2)
          board[i][j] = 1; // Convert live mark back to 1
      }
    }
  }

  private int countLiveNeighbors(int[][] board, int i, int j, int[][] directions, int m, int n) {
    int count = 0;
    for (int[] dir : directions) {
      int newRow = i + dir[0];
      int newCol = j + dir[1];

      // Check if inside bounds & count only original live cells (1 or -1)
      if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && Math.abs(board[newRow][newCol]) == 1) {
        count++;
      }
    }
    return count;
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    int[][] board1 = {
        { 0, 1, 0 },
        { 0, 0, 1 },
        { 1, 1, 1 },
        { 0, 0, 0 }
    };
    System.out.println("Input:");
    ArrayUtils.print2DArray("Board", board1);
    gameOfLife(board1);
    System.out.println("Output:");
    ArrayUtils.print2DArray("Board", board1);

    // 🔹 Test Case 2
    int[][] board2 = {
        { 1, 1 },
        { 1, 0 }
    };
    System.out.println("\nInput:");
    ArrayUtils.print2DArray("Board", board2);
    gameOfLife(board2);
    System.out.println("Output:");
    ArrayUtils.print2DArray("Board", board2);
  }
}