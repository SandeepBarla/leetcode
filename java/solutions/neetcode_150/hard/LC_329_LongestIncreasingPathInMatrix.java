/*
 * LeetCode Problem: 329. Longest Increasing Path in a Matrix
 * URL: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * Difficulty: Hard
 * 
 * Problem Description:
 * Given an m x n integers matrix, return the length of the longest increasing path.
 * From each cell, you can either move in four directions: left, right, up, or down. 
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 * 
 * Example 1:
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * 
 * Example 2:
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * 
 * Example 3:
 * Input: matrix = [[1]]
 * Output: 1
 * 
 * Constraints:
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 200
 * - 0 <= matrix[i][j] <= 2^31 - 1
 */

package solutions.neetcode_150.hard;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import common.Solution;

public class LC_329_LongestIncreasingPathInMatrix implements Solution {
    
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    /**
     * Approach 1: DFS with Memoization (Optimized Original)
     * 
     * Algorithm:
     * 1. For each cell in the matrix, perform DFS to find longest increasing path
     * 2. Use memoization to avoid recomputing paths for visited cells
     * 3. For each cell, explore all 4 directions where next cell value > current
     * 4. Return 1 + maximum path length from valid neighbors
     * 
     * Time Complexity: O(m * n) - each cell is computed once due to memoization
     * Space Complexity: O(m * n) - for memoization array and recursion stack
     */
    public int longestIncreasingPathDFSMemo(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int maxLength = 0;
        
        // Try starting from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pathLength = dfsWithMemo(matrix, i, j, memo);
                maxLength = Math.max(maxLength, pathLength);
            }
        }
        
        return maxLength;
    }
    
    private int dfsWithMemo(int[][] matrix, int i, int j, int[][] memo) {
        // Return memoized result if already computed
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int maxPath = 1; // At least the current cell
        
        // Explore all 4 directions
        for (int[] dir : DIRECTIONS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            
            // Check bounds and increasing condition
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && 
                matrix[newI][newJ] > matrix[i][j]) {
                int pathFromNeighbor = 1 + dfsWithMemo(matrix, newI, newJ, memo);
                maxPath = Math.max(maxPath, pathFromNeighbor);
            }
        }
        
        memo[i][j] = maxPath;
        return maxPath;
    }
    
    /**
     * Approach 2: Topological Sort (BFS-based)
     * 
     * Algorithm:
     * 1. Build a graph where edges represent increasing relationships
     * 2. Calculate in-degrees for each cell (how many cells point to it)
     * 3. Use BFS starting from cells with in-degree 0 (local minima)
     * 4. Process in topological order, updating path lengths layer by layer
     * 
     * Time Complexity: O(m * n) - each cell and edge processed once
     * Space Complexity: O(m * n) - for in-degree array and queue
     */
    public int longestIncreasingPathTopological(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] inDegree = new int[m][n];
        
        // Calculate in-degrees
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : DIRECTIONS) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    
                    if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && 
                        matrix[newI][newJ] > matrix[i][j]) {
                        inDegree[newI][newJ]++;
                    }
                }
            }
        }
        
        // Start BFS from cells with in-degree 0 (local minima)
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (inDegree[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        int maxLength = 0;
        
        // Process in topological order
        while (!queue.isEmpty()) {
            maxLength++;
            int size = queue.size();
            
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int i = curr[0];
                int j = curr[1];
                
                // Update neighbors
                for (int[] dir : DIRECTIONS) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    
                    if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && 
                        matrix[newI][newJ] > matrix[i][j]) {
                        inDegree[newI][newJ]--;
                        if (inDegree[newI][newJ] == 0) {
                            queue.offer(new int[]{newI, newJ});
                        }
                    }
                }
            }
        }
        
        return maxLength;
    }
    
    /**
     * Approach 3: DFS without Memoization (Brute Force)
     * 
     * Algorithm:
     * 1. For each cell, perform DFS to explore all possible increasing paths
     * 2. No memoization - recalculate paths multiple times
     * 3. Use visited array to avoid cycles within a single path
     * 
     * Time Complexity: O(2^(m*n)) - exponential in worst case
     * Space Complexity: O(m * n) - for visited array and recursion stack
     * Note: This is for comparison purposes; not recommended for large inputs
     */
    public int longestIncreasingPathBruteForce(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLength = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n];
                int pathLength = dfsBruteForce(matrix, i, j, visited);
                maxLength = Math.max(maxLength, pathLength);
            }
        }
        
        return maxLength;
    }
    
    private int dfsBruteForce(int[][] matrix, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        int m = matrix.length;
        int n = matrix[0].length;
        int maxPath = 1;
        
        for (int[] dir : DIRECTIONS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && 
                !visited[newI][newJ] && matrix[newI][newJ] > matrix[i][j]) {
                int pathFromNeighbor = 1 + dfsBruteForce(matrix, newI, newJ, visited);
                maxPath = Math.max(maxPath, pathFromNeighbor);
            }
        }
        
        visited[i][j] = false; // Backtrack
        return maxPath;
    }
    
    /**
     * Approach 4: Iterative DFS with Stack
     * 
     * Algorithm:
     * 1. Convert recursive DFS to iterative using explicit stack
     * 2. Use memoization to avoid recomputation
     * 3. Process nodes in DFS order using stack
     * 
     * Time Complexity: O(m * n) - each cell processed once with memoization
     * Space Complexity: O(m * n) - for memoization, stack, and visited arrays
     */
    public int longestIncreasingPathIterative(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int maxLength = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] == 0) {
                    int pathLength = dfsIterative(matrix, i, j, memo);
                    maxLength = Math.max(maxLength, pathLength);
                }
            }
        }
        
        return maxLength;
    }
    
    private int dfsIterative(int[][] matrix, int startI, int startJ, int[][] memo) {
        if (memo[startI][startJ] != 0) {
            return memo[startI][startJ];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        Stack<int[]> stack = new Stack<>();
        Stack<int[]> path = new Stack<>();
        Set<String> visited = new HashSet<>();
        
        stack.push(new int[]{startI, startJ});
        
        while (!stack.isEmpty()) {
            int[] curr = stack.peek();
            int i = curr[0];
            int j = curr[1];
            String key = i + "," + j;
            
            if (memo[i][j] != 0) {
                stack.pop();
                continue;
            }
            
            if (visited.contains(key)) {
                // All children processed, compute result
                stack.pop();
                visited.remove(key);
                
                int maxPath = 1;
                for (int[] dir : DIRECTIONS) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    
                    if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && 
                        matrix[newI][newJ] > matrix[i][j] && memo[newI][newJ] > 0) {
                        maxPath = Math.max(maxPath, 1 + memo[newI][newJ]);
                    }
                }
                memo[i][j] = maxPath;
            } else {
                visited.add(key);
                
                // Add unprocessed children to stack
                for (int[] dir : DIRECTIONS) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    
                    if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && 
                        matrix[newI][newJ] > matrix[i][j] && memo[newI][newJ] == 0) {
                        stack.push(new int[]{newI, newJ});
                    }
                }
            }
        }
        
        return memo[startI][startJ];
    }
    
    @Override
    public void run() {
        // Test Case 1: Example from problem
        int[][] matrix1 = {
            {9, 9, 4},
            {6, 6, 8},
            {2, 1, 1}
        };
        System.out.println("Test Case 1:");
        System.out.println("Matrix: " + Arrays.deepToString(matrix1));
        System.out.println("Expected: 4 (path: [1, 2, 6, 9])");
        System.out.println("DFS Memo: " + longestIncreasingPathDFSMemo(matrix1));
        System.out.println("Topological: " + longestIncreasingPathTopological(matrix1));
        System.out.println("Brute Force: " + longestIncreasingPathBruteForce(matrix1));
        System.out.println("Iterative: " + longestIncreasingPathIterative(matrix1));
        System.out.println();
        
        // Test Case 2: Example from problem
        int[][] matrix2 = {
            {3, 4, 5},
            {3, 2, 6},
            {2, 2, 1}
        };
        System.out.println("Test Case 2:");
        System.out.println("Matrix: " + Arrays.deepToString(matrix2));
        System.out.println("Expected: 4 (path: [3, 4, 5, 6])");
        System.out.println("DFS Memo: " + longestIncreasingPathDFSMemo(matrix2));
        System.out.println("Topological: " + longestIncreasingPathTopological(matrix2));
        System.out.println("Brute Force: " + longestIncreasingPathBruteForce(matrix2));
        System.out.println("Iterative: " + longestIncreasingPathIterative(matrix2));
        System.out.println();
        
        // Test Case 3: Single cell
        int[][] matrix3 = {{1}};
        System.out.println("Test Case 3:");
        System.out.println("Matrix: " + Arrays.deepToString(matrix3));
        System.out.println("Expected: 1");
        System.out.println("DFS Memo: " + longestIncreasingPathDFSMemo(matrix3));
        System.out.println("Topological: " + longestIncreasingPathTopological(matrix3));
        System.out.println("Brute Force: " + longestIncreasingPathBruteForce(matrix3));
        System.out.println("Iterative: " + longestIncreasingPathIterative(matrix3));
        System.out.println();
        
        // Test Case 4: All same values
        int[][] matrix4 = {
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5}
        };
        System.out.println("Test Case 4:");
        System.out.println("Matrix: " + Arrays.deepToString(matrix4));
        System.out.println("Expected: 1 (no increasing path possible)");
        System.out.println("DFS Memo: " + longestIncreasingPathDFSMemo(matrix4));
        System.out.println("Topological: " + longestIncreasingPathTopological(matrix4));
        System.out.println("Brute Force: " + longestIncreasingPathBruteForce(matrix4));
        System.out.println("Iterative: " + longestIncreasingPathIterative(matrix4));
        System.out.println();
        
        // Test Case 5: Strictly increasing path
        int[][] matrix5 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Test Case 5:");
        System.out.println("Matrix: " + Arrays.deepToString(matrix5));
        System.out.println("Expected: 5 (e.g., [1, 2, 3, 6, 9] or [1, 4, 5, 8, 9])");
        System.out.println("DFS Memo: " + longestIncreasingPathDFSMemo(matrix5));
        System.out.println("Topological: " + longestIncreasingPathTopological(matrix5));
        System.out.println("Brute Force: " + longestIncreasingPathBruteForce(matrix5));
        System.out.println("Iterative: " + longestIncreasingPathIterative(matrix5));
        System.out.println();
        
        // Test Case 6: Edge case - decreasing values
        int[][] matrix6 = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };
        System.out.println("Test Case 6:");
        System.out.println("Matrix: " + Arrays.deepToString(matrix6));
        System.out.println("Expected: 1 (all decreasing, no increasing path)");
        System.out.println("DFS Memo: " + longestIncreasingPathDFSMemo(matrix6));
        System.out.println("Topological: " + longestIncreasingPathTopological(matrix6));
        System.out.println("Brute Force: " + longestIncreasingPathBruteForce(matrix6));
        System.out.println("Iterative: " + longestIncreasingPathIterative(matrix6));
    }
} 