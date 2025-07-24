/*
 * LeetCode Problem 54: Spiral Matrix
 * URL: https://leetcode.com/problems/spiral-matrix/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Layer by Layer Traversal - O(m*n) time, O(1) space
 * 2. Direction Change with Boundaries - O(m*n) time, O(1) space
 * 3. Visited Matrix Approach - O(m*n) time, O(m*n) space
 * 4. Recursive Approach - O(m*n) time, O(m*n) space
 *
 * Time Complexity:
 * - All approaches: O(m*n) - visit every element once
 *
 * Space Complexity:
 * - Approach 1,2: O(1) - only using variables (excluding output)
 * - Approach 3: O(m*n) - visited matrix
 * - Approach 4: O(m*n) - recursion call stack
 */

package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Solution;

public class LC_54_SpiralMatrix implements Solution {

    // Approach 1: Layer by Layer Traversal (Optimal)
    public List<Integer> spiralOrderLayerByLayer(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // Traverse right along top row
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;
            
            // Traverse down along right column
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;
            
            // Traverse left along bottom row (if we still have rows)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }
            
            // Traverse up along left column (if we still have columns)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        
        return result;
    }

    // Approach 2: Direction Change with Boundaries
    public List<Integer> spiralOrderDirectionChange(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int rows = matrix.length, cols = matrix[0].length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
        int currentDir = 0;
        
        int row = 0, col = 0;
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        
        for (int i = 0; i < rows * cols; i++) {
            result.add(matrix[row][col]);
            
            // Calculate next position
            int nextRow = row + directions[currentDir][0];
            int nextCol = col + directions[currentDir][1];
            
            // Check if we need to change direction
            if (nextRow < top || nextRow > bottom || nextCol < left || nextCol > right) {
                // Adjust boundaries based on current direction
                if (currentDir == 0) top++;      // moving right, so top boundary moves down
                else if (currentDir == 1) right--; // moving down, so right boundary moves left
                else if (currentDir == 2) bottom--; // moving left, so bottom boundary moves up
                else if (currentDir == 3) left++;  // moving up, so left boundary moves right
                
                // Change direction
                currentDir = (currentDir + 1) % 4;
                nextRow = row + directions[currentDir][0];
                nextCol = col + directions[currentDir][1];
            }
            
            row = nextRow;
            col = nextCol;
        }
        
        return result;
    }

    // Approach 3: Visited Matrix Approach
    public List<Integer> spiralOrderVisited(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
        int currentDir = 0;
        
        int row = 0, col = 0;
        
        for (int i = 0; i < rows * cols; i++) {
            result.add(matrix[row][col]);
            visited[row][col] = true;
            
            // Calculate next position
            int nextRow = row + directions[currentDir][0];
            int nextCol = col + directions[currentDir][1];
            
            // Check if we need to change direction
            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || 
                visited[nextRow][nextCol]) {
                currentDir = (currentDir + 1) % 4;
                nextRow = row + directions[currentDir][0];
                nextCol = col + directions[currentDir][1];
            }
            
            row = nextRow;
            col = nextCol;
        }
        
        return result;
    }

    // Approach 4: Recursive Approach
    public List<Integer> spiralOrderRecursive(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        spiralRecursive(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, result);
        return result;
    }
    
    private void spiralRecursive(int[][] matrix, int top, int bottom, int left, int right, List<Integer> result) {
        if (top > bottom || left > right) {
            return;
        }
        
        // Traverse right along top row
        for (int col = left; col <= right; col++) {
            result.add(matrix[top][col]);
        }
        
        // Traverse down along right column
        for (int row = top + 1; row <= bottom; row++) {
            result.add(matrix[row][right]);
        }
        
        // Traverse left along bottom row (if we have more than one row)
        if (top < bottom) {
            for (int col = right - 1; col >= left; col--) {
                result.add(matrix[bottom][col]);
            }
        }
        
        // Traverse up along left column (if we have more than one column)
        if (left < right) {
            for (int row = bottom - 1; row > top; row--) {
                result.add(matrix[row][left]);
            }
        }
        
        // Recurse for inner matrix
        spiralRecursive(matrix, top + 1, bottom - 1, left + 1, right - 1, result);
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
        printMatrix("Matrix", matrix1);
        System.out.println("Layer by Layer: " + spiralOrderLayerByLayer(matrix1));
        // Expected: [1,2,3,6,9,8,7,4,5]
        System.out.println("Direction Change: " + spiralOrderDirectionChange(matrix1));
        System.out.println("Visited Matrix: " + spiralOrderVisited(matrix1));
        System.out.println("Recursive: " + spiralOrderRecursive(matrix1));
        System.out.println();

        // Test Case 2: 3x4 matrix (rectangular)
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        System.out.println("Test Case 2: 3x4 Matrix");
        printMatrix("Matrix", matrix2);
        System.out.println("Layer by Layer: " + spiralOrderLayerByLayer(matrix2));
        // Expected: [1,2,3,4,8,12,11,10,9,5,6,7]
        System.out.println("Direction Change: " + spiralOrderDirectionChange(matrix2));
        System.out.println("Visited Matrix: " + spiralOrderVisited(matrix2));
        System.out.println("Recursive: " + spiralOrderRecursive(matrix2));
        System.out.println();

        // Test Case 3: Single row
        int[][] matrix3 = {{1, 2, 3, 4}};
        
        System.out.println("Test Case 3: Single Row");
        printMatrix("Matrix", matrix3);
        System.out.println("Layer by Layer: " + spiralOrderLayerByLayer(matrix3));
        // Expected: [1,2,3,4]
        System.out.println("Direction Change: " + spiralOrderDirectionChange(matrix3));
        System.out.println();

        // Test Case 4: Single column
        int[][] matrix4 = {{1}, {2}, {3}};
        
        System.out.println("Test Case 4: Single Column");
        printMatrix("Matrix", matrix4);
        System.out.println("Layer by Layer: " + spiralOrderLayerByLayer(matrix4));
        // Expected: [1,2,3]
        System.out.println("Direction Change: " + spiralOrderDirectionChange(matrix4));
        System.out.println();

        // Test Case 5: Single element
        int[][] matrix5 = {{42}};
        
        System.out.println("Test Case 5: Single Element");
        printMatrix("Matrix", matrix5);
        System.out.println("Layer by Layer: " + spiralOrderLayerByLayer(matrix5));
        // Expected: [42]
        System.out.println();

        // Test Case 6: 4x4 matrix (performance test)
        int[][] matrix6 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        
        System.out.println("Test Case 6: 4x4 Matrix (performance test)");
        printMatrix("Matrix", matrix6);
        
        long start = System.currentTimeMillis();
        List<Integer> result6_1 = spiralOrderLayerByLayer(matrix6);
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Layer by Layer: " + result6_1 + " in " + time1 + "ms");
        
        start = System.currentTimeMillis();
        List<Integer> result6_2 = spiralOrderDirectionChange(matrix6);
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Direction Change: " + result6_2 + " in " + time2 + "ms");
        
        start = System.currentTimeMillis();
        List<Integer> result6_3 = spiralOrderVisited(matrix6);
        long time3 = System.currentTimeMillis() - start;
        System.out.println("Visited Matrix: " + result6_3 + " in " + time3 + "ms");
        
        start = System.currentTimeMillis();
        List<Integer> result6_4 = spiralOrderRecursive(matrix6);
        long time4 = System.currentTimeMillis() - start;
        System.out.println("Recursive: " + result6_4 + " in " + time4 + "ms");
        
        // Verify all methods produce same result
        boolean allSame = result6_1.equals(result6_2) && result6_2.equals(result6_3) && result6_3.equals(result6_4);
        System.out.println("All methods produce same result: " + allSame);
        System.out.println("Expected: [1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]");

        // Test Case 7: Empty matrix
        int[][] matrix7 = {};
        System.out.println("\nTest Case 7: Empty Matrix");
        System.out.println("Layer by Layer: " + spiralOrderLayerByLayer(matrix7)); // Expected: []
    }
} 