/*
 * LeetCode Problem 36: Valid Sudoku
 * URL: https://leetcode.com/problems/valid-sudoku/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. HashSet for Each Constraint - O(1) time, O(1) space
 * 2. Boolean Arrays for Each Constraint - O(1) time, O(1) space  
 * 3. Single Pass with Set - O(1) time, O(1) space
 *
 * Time Complexity:
 * - All approaches: O(1) - fixed 9x9 grid, so 81 cells maximum
 *
 * Space Complexity:
 * - All approaches: O(1) - fixed size data structures for 9x9 grid
 */

package solutions.top_interview_questions.medium;

import java.util.HashSet;
import java.util.Set;

import common.Solution;

public class LC_36_ValidSudoku implements Solution {

    // Approach 1: HashSet for Each Constraint (Optimal)
    public boolean isValidSudokuHashSet(char[][] board) {
        Set<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char value = board[i][j];
                if (value != '.') {
                    // Create unique identifiers for each constraint
                    String row = "row" + i + "-" + value;
                    String col = "col" + j + "-" + value;
                    String box = "box" + (i / 3) + (j / 3) + "-" + value;
                    
                    // Check if any constraint is violated
                    if (!seen.add(row) || !seen.add(col) || !seen.add(box)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }

    // Approach 2: Boolean Arrays for Each Constraint
    public boolean isValidSudokuBooleanArrays(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char value = board[i][j];
                if (value != '.') {
                    int num = value - '1'; // Convert '1'-'9' to 0-8
                    int boxIndex = (i / 3) * 3 + j / 3;
                    
                    // Check if number already exists in row, column, or box
                    if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                        return false;
                    }
                    
                    // Mark as seen
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[boxIndex][num] = true;
                }
            }
        }
        
        return true;
    }

    // Approach 3: Three Separate Validation Functions
    public boolean isValidSudokuSeparateValidation(char[][] board) {
        return isValidRows(board) && isValidCols(board) && isValidBoxes(board);
    }
    
    private boolean isValidRows(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char value = board[i][j];
                if (value != '.' && !seen.add(value)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValidCols(char[][] board) {
        for (int j = 0; j < 9; j++) {
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                char value = board[i][j];
                if (value != '.' && !seen.add(value)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValidBoxes(char[][] board) {
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                Set<Character> seen = new HashSet<>();
                for (int i = boxRow * 3; i < boxRow * 3 + 3; i++) {
                    for (int j = boxCol * 3; j < boxCol * 3 + 3; j++) {
                        char value = board[i][j];
                        if (value != '.' && !seen.add(value)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // Helper method to print Sudoku board
    private void printBoard(String label, char[][] board) {
        System.out.println(label + ":");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("+-------+-------+-------+");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
        System.out.println();
    }

    @Override
    public void run() {
        // Test Case 1: Valid Sudoku
        char[][] validBoard = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        System.out.println("Test Case 1: Valid Sudoku");
        printBoard("Board", validBoard);
        System.out.println("HashSet Approach: " + isValidSudokuHashSet(validBoard)); // Expected: true
        System.out.println("Boolean Arrays: " + isValidSudokuBooleanArrays(validBoard)); // Expected: true
        System.out.println("Separate Validation: " + isValidSudokuSeparateValidation(validBoard)); // Expected: true
        System.out.println();

        // Test Case 2: Invalid Sudoku (duplicate in row)
        char[][] invalidBoard1 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        System.out.println("Test Case 2: Invalid Sudoku (duplicate 8 in first row)");
        System.out.println("HashSet Approach: " + isValidSudokuHashSet(invalidBoard1)); // Expected: false
        System.out.println("Boolean Arrays: " + isValidSudokuBooleanArrays(invalidBoard1)); // Expected: false
        System.out.println("Separate Validation: " + isValidSudokuSeparateValidation(invalidBoard1)); // Expected: false
        System.out.println();

        // Test Case 3: Invalid Sudoku (duplicate in column)
        char[][] invalidBoard2 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'5','.','.','.','8','.','.','7','9'}
        };
        
        System.out.println("Test Case 3: Invalid Sudoku (duplicate 5 in first column)");
        System.out.println("HashSet Approach: " + isValidSudokuHashSet(invalidBoard2)); // Expected: false
        System.out.println("Boolean Arrays: " + isValidSudokuBooleanArrays(invalidBoard2)); // Expected: false
        System.out.println("Separate Validation: " + isValidSudokuSeparateValidation(invalidBoard2)); // Expected: false
        System.out.println();

        // Test Case 4: Empty board (all dots)
        char[][] emptyBoard = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                emptyBoard[i][j] = '.';
            }
        }
        
        System.out.println("Test Case 4: Empty Board");
        System.out.println("HashSet Approach: " + isValidSudokuHashSet(emptyBoard)); // Expected: true
        System.out.println("Boolean Arrays: " + isValidSudokuBooleanArrays(emptyBoard)); // Expected: true
        System.out.println("Separate Validation: " + isValidSudokuSeparateValidation(emptyBoard)); // Expected: true
    }
} 