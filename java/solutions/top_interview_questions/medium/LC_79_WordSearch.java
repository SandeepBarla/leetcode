package solutions.top_interview_questions.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_79_WordSearch implements Solution {

    // ✅ Approach 1: Manipulate and Restore Board to Track Visited Cells (O(1)
    // Space)
    public boolean existUsingBoard(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check if the first letter matches and apply DFS
                if (board[i][j] == word.charAt(0) && dfsUsingBoard(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsUsingBoard(char[][] board, String word, int row, int col, int index) {
        // If we found the word, return true
        if (index == word.length())
            return true;

        // Check out of bounds and character mismatch
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark cell as visited
        char temp = board[row][col];
        board[row][col] = '#';

        // Perform DFS in 4 directions
        boolean found = dfsUsingBoard(board, word, row + 1, col, index + 1) ||
                dfsUsingBoard(board, word, row - 1, col, index + 1) ||
                dfsUsingBoard(board, word, row, col + 1, index + 1) ||
                dfsUsingBoard(board, word, row, col - 1, index + 1);

        // Restore the visited cell
        board[row][col] = temp;
        return found;
    }

    // ✅ Approach 2: Using a 2D Array to Track Visited Cells (O(m×n) Space)
    public boolean existUsingVisitedArray(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] isVisited = new int[rows][cols]; // 2D array to track visited cells

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check if the first letter matches and apply DFS
                if (board[i][j] == word.charAt(0) && dfsUsingVisitedArray(board, word, i, j, 0, isVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsUsingVisitedArray(char[][] board, String word, int row, int col, int index, int[][] isVisited) {
        // If we found the word, return true
        if (index == word.length())
            return true;

        // Check out of bounds, character mismatch, and if already visited
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
                board[row][col] != word.charAt(index) || isVisited[row][col] == 1) {
            return false;
        }

        // Mark cell as visited
        isVisited[row][col] = 1;

        // Perform DFS in 4 directions
        boolean found = dfsUsingVisitedArray(board, word, row + 1, col, index + 1, isVisited) ||
                dfsUsingVisitedArray(board, word, row - 1, col, index + 1, isVisited) ||
                dfsUsingVisitedArray(board, word, row, col + 1, index + 1, isVisited) ||
                dfsUsingVisitedArray(board, word, row, col - 1, index + 1, isVisited);

        // Unmark cell as visited
        isVisited[row][col] = 0;

        return found;
    }

    @Override
    public void run() {
        char[][] board1 = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };

        String[] words = { "ABCCED", "SEE", "ABCB", "ADEE" };

        for (String word : words) {
            System.out.println("Board:");
            ArrayUtils.print2DCharArray(board1);
            System.out.println("Searching for word: " + word);
            System.out.println("Exists (Using Board): " + existUsingBoard(board1, word));
            System.out.println("Exists (Using Visited Array): " + existUsingVisitedArray(board1, word));
            System.out.println();
        }
    }
}