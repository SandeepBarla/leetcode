package solutions.top_interview_questions.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_130_SurroundedRegions implements Solution {

  // ✅ Approach 1: Using Extra Visited Array (O(m × n) Space)
  public void solveUsingVisitedArray(char[][] board) {
    int m = board.length;
    int n = board[0].length;
    int[][] visited = new int[m][n];

    // Step 1: Mark all boundary 'O' and their connected regions
    for (int i = 0; i < m; i++) {
      dfsWithVisited(i, 0, board, visited); // Left border
      dfsWithVisited(i, n - 1, board, visited); // Right border
    }
    for (int j = 1; j < n - 1; j++) {
      dfsWithVisited(0, j, board, visited); // Top border
      dfsWithVisited(m - 1, j, board, visited); // Bottom border
    }

    // Step 2: Convert unvisited 'O' to 'X' and restore visited 'O'
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O' && visited[i][j] == 0) {
          board[i][j] = 'X';
        }
      }
    }
  }

  private void dfsWithVisited(int i, int j, char[][] board, int[][] visited) {
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
        || visited[i][j] == 1 || board[i][j] == 'X') {
      return;
    }

    visited[i][j] = 1;

    // Explore all 4 directions
    dfsWithVisited(i + 1, j, board, visited);
    dfsWithVisited(i - 1, j, board, visited);
    dfsWithVisited(i, j + 1, board, visited);
    dfsWithVisited(i, j - 1, board, visited);
  }

  // ✅ Approach 2: Optimized In-Place DFS (O(1) Space)
  public void solveUsingInPlaceDFS(char[][] board) {
    int m = board.length;
    int n = board[0].length;

    // Step 1: Mark all boundary-connected 'O' as 'T' (temporary)
    for (int i = 0; i < m; i++) {
      dfsInPlace(i, 0, board); // Left border
      dfsInPlace(i, n - 1, board); // Right border
    }
    for (int j = 1; j < n - 1; j++) {
      dfsInPlace(0, j, board); // Top border
      dfsInPlace(m - 1, j, board); // Bottom border
    }

    // Step 2: Convert all 'O' to 'X' and revert 'T' back to 'O'
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X'; // Captured region
        } else if (board[i][j] == 'T') {
          board[i][j] = 'O'; // Restore safe 'O'
        }
      }
    }
  }

  private void dfsInPlace(int i, int j, char[][] board) {
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') {
      return;
    }

    board[i][j] = 'T'; // Temporarily mark as safe

    // Explore all 4 directions
    dfsInPlace(i + 1, j, board);
    dfsInPlace(i - 1, j, board);
    dfsInPlace(i, j + 1, board);
    dfsInPlace(i, j - 1, board);
  }

  @Override
  public void run() {
    char[][] board1 = {
        { 'X', 'X', 'X', 'X' },
        { 'X', 'O', 'O', 'X' },
        { 'X', 'X', 'O', 'X' },
        { 'X', 'O', 'X', 'X' }
    };

    char[][] board2 = {
        { 'X', 'X', 'X', 'X' },
        { 'X', 'O', 'O', 'X' },
        { 'X', 'X', 'O', 'X' },
        { 'X', 'O', 'X', 'X' }
    };

    System.out.println("Original Board:");
    ArrayUtils.print2DCharArray(board1);

    System.out.println("\nUsing Visited Array (O(m × n) Space):");
    solveUsingVisitedArray(board1);
    ArrayUtils.print2DCharArray(board1);

    System.out.println("\nUsing In-Place DFS (O(1) Space):");
    solveUsingInPlaceDFS(board2);
    ArrayUtils.print2DCharArray(board2);
  }
}