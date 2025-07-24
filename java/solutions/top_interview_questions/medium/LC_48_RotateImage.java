/*
 * LeetCode Problem 48: Rotate Image
 * URL: https://leetcode.com/problems/rotate-image/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Transpose + Reverse (Optimal) - O(n²) time, O(1) space
 * 2. Four-way Swap in Layers - O(n²) time, O(1) space
 * 3. Extra Space Approach - O(n²) time, O(n²) space
 *
 * Time Complexity:
 * - All approaches: O(n²) - must visit every element
 *
 * Space Complexity:
 * - Approach 1: O(1) - in-place modification
 * - Approach 2: O(1) - in-place modification
 * - Approach 3: O(n²) - extra matrix storage
 */

package solutions.top_interview_questions.medium;

import java.util.Arrays;

import common.Solution;

public class LC_48_RotateImage implements Solution {

    // Approach 1: Transpose + Reverse (Optimal)
    public void rotateTransposeReverse(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    // Approach 2: Four-way Swap in Layers
    public void rotateFourWaySwap(int[][] matrix) {
        int n = matrix.length;
        
        // Process layer by layer from outside to inside
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            
            for (int i = first; i < last; i++) {
                int offset = i - first;
                
                // Save top element
                int top = matrix[first][i];
                
                // Top = Left
                matrix[first][i] = matrix[last - offset][first];
                
                // Left = Bottom
                matrix[last - offset][first] = matrix[last][last - offset];
                
                // Bottom = Right
                matrix[last][last - offset] = matrix[i][last];
                
                // Right = Top (saved)
                matrix[i][last] = top;
            }
        }
    }

    // Approach 3: Extra Space Approach (for comparison)
    public void rotateExtraSpace(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        
        // Create rotated matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }
        
        // Copy back to original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rotated[i][j];
            }
        }
    }

    // Helper method to create a copy of matrix for testing
    private int[][] copyMatrix(int[][] matrix) {
        int n = matrix.length;
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

    // Helper method to print matrix
    private void printMatrix(String label, int[][] matrix) {
        System.out.println(label + ":");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    @Override
    public void run() {
        // Test Case 1: 3x3 matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Test Case 1: 3x3 Matrix");
        printMatrix("Original", matrix1);
        
        int[][] copy1 = copyMatrix(matrix1);
        rotateTransposeReverse(copy1);
        printMatrix("Transpose + Reverse", copy1);
        
        copy1 = copyMatrix(matrix1);
        rotateFourWaySwap(copy1);
        printMatrix("Four-way Swap", copy1);
        
        copy1 = copyMatrix(matrix1);
        rotateExtraSpace(copy1);
        printMatrix("Extra Space", copy1);
        
        // Test Case 2: 4x4 matrix
        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        
        System.out.println("Test Case 2: 4x4 Matrix");
        printMatrix("Original", matrix2);
        
        int[][] copy2 = copyMatrix(matrix2);
        rotateTransposeReverse(copy2);
        printMatrix("Transpose + Reverse", copy2);
        
        copy2 = copyMatrix(matrix2);
        rotateFourWaySwap(copy2);
        printMatrix("Four-way Swap", copy2);
        
        copy2 = copyMatrix(matrix2);
        rotateExtraSpace(copy2);
        printMatrix("Extra Space", copy2);
        
        // Test Case 3: 2x2 matrix
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        
        System.out.println("Test Case 3: 2x2 Matrix");
        printMatrix("Original", matrix3);
        
        int[][] copy3 = copyMatrix(matrix3);
        rotateTransposeReverse(copy3);
        printMatrix("Transpose + Reverse", copy3);
        
        // Test Case 4: 1x1 matrix
        int[][] matrix4 = {{1}};
        
        System.out.println("Test Case 4: 1x1 Matrix");
        printMatrix("Original", matrix4);
        
        int[][] copy4 = copyMatrix(matrix4);
        rotateTransposeReverse(copy4);
        printMatrix("After Rotation", copy4);
    }
} 