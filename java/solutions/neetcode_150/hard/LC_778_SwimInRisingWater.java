/*
 * LeetCode Problem: 778. Swim in Rising Water
 * URL: https://leetcode.com/problems/swim-in-rising-water/
 * Difficulty: Hard
 * 
 * Problem Description:
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 
 * 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. 
 * You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 * 
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 * 
 * Example 1:
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * At time 1, the water level reaches 1, but you still cannot go anywhere.
 * At time 2, the water level reaches 2, you can move to (0, 1) but not (1, 0).
 * At time 3, the water level reaches 3, you can reach (1, 1).
 * 
 * Example 2:
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * 
 * Constraints:
 * - n == grid.length == grid[i].length
 * - 1 <= n <= 50
 * - 0 <= grid[i][j] < n²
 */

package solutions.neetcode_150.hard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import common.Solution;

public class LC_778_SwimInRisingWater implements Solution {
    
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    /**
     * Approach 1: Dijkstra's Algorithm with Priority Queue (Optimized Original)
     * 
     * Algorithm:
     * 1. Use Dijkstra's algorithm to find the minimum "maximum elevation" path
     * 2. Priority queue stores {maxElevationSoFar, row, col}
     * 3. For each cell, track the minimum time needed to reach it
     * 4. Update neighbors with max(currentTime, neighborElevation)
     * 
     * Time Complexity: O(n² log n) - each cell added to priority queue once
     * Space Complexity: O(n²) - for visited array and priority queue
     */
    public int swimInWaterDijkstra(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        
        int n = grid.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[][] visited = new boolean[n][n];
        
        // Start from top-left corner
        minHeap.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentTime = current[0];
            int row = current[1];
            int col = current[2];
            
            // Reached destination
            if (row == n - 1 && col == n - 1) {
                return currentTime;
            }
            
            // Explore all 4 directions
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (isValidCell(newRow, newCol, n) && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    int newTime = Math.max(currentTime, grid[newRow][newCol]);
                    minHeap.offer(new int[]{newTime, newRow, newCol});
                }
            }
        }
        
        return -1; // Should never reach here with valid input
    }
    
    /**
     * Approach 2: Binary Search + BFS
     * 
     * Algorithm:
     * 1. Binary search on the answer (time from 0 to max grid value)
     * 2. For each candidate time, use BFS to check if path exists
     * 3. A cell is reachable if its elevation <= current time
     * 4. Find minimum time where path exists
     * 
     * Time Complexity: O(n² log(max_value)) - binary search * BFS
     * Space Complexity: O(n²) - for BFS queue and visited array
     */
    public int swimInWaterBinarySearch(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        
        int n = grid.length;
        int left = grid[0][0];
        int right = getMaxValue(grid);
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canReachDestination(grid, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    /**
     * Approach 3: Union-Find (Disjoint Set Union)
     * 
     * Algorithm:
     * 1. Sort all cells by their elevation values
     * 2. Process cells in ascending order of elevation
     * 3. For each cell, union it with reachable neighbors
     * 4. Stop when start and end cells are connected
     * 
     * Time Complexity: O(n² log n) - sorting cells + union-find operations
     * Space Complexity: O(n²) - for union-find structure and cell list
     */
    public int swimInWaterUnionFind(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        
        int n = grid.length;
        
        // Create list of all cells with their values
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells.add(new int[]{grid[i][j], i, j});
            }
        }
        
        // Sort by elevation
        cells.sort((a, b) -> Integer.compare(a[0], b[0]));
        
        UnionFind uf = new UnionFind(n * n);
        boolean[][] processed = new boolean[n][n];
        
        for (int[] cell : cells) {
            int elevation = cell[0];
            int row = cell[1];
            int col = cell[2];
            int cellId = row * n + col;
            
            processed[row][col] = true;
            
            // Union with processed neighbors
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (isValidCell(newRow, newCol, n) && processed[newRow][newCol]) {
                    int neighborId = newRow * n + newCol;
                    uf.union(cellId, neighborId);
                }
            }
            
            // Check if start and end are connected
            if (uf.connected(0, n * n - 1)) {
                return elevation;
            }
        }
        
        return -1;
    }
    
    /**
     * Approach 4: DFS with Memoization
     * 
     * Algorithm:
     * 1. For each possible time t, use DFS to check if path exists
     * 2. Use memoization to avoid recomputing paths
     * 3. Binary search on time to find minimum viable time
     * 
     * Time Complexity: O(n² log(max_value)) - binary search * DFS with memoization
     * Space Complexity: O(n²) - for memoization and recursion stack
     */
    public int swimInWaterDFSMemo(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        
        int n = grid.length;
        int left = grid[0][0];
        int right = getMaxValue(grid);
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            boolean[][] visited = new boolean[n][n];
            if (canReachWithDFS(grid, 0, 0, mid, visited)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    // Helper methods
    private boolean isValidCell(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
    
    private int getMaxValue(int[][] grid) {
        int max = 0;
        for (int[] row : grid) {
            for (int val : row) {
                max = Math.max(max, val);
            }
        }
        return max;
    }
    
    private boolean canReachDestination(int[][] grid, int time) {
        int n = grid.length;
        if (grid[0][0] > time) return false;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            
            if (row == n - 1 && col == n - 1) {
                return true;
            }
            
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (isValidCell(newRow, newCol, n) && 
                    !visited[newRow][newCol] && 
                    grid[newRow][newCol] <= time) {
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
        
        return false;
    }
    
    private boolean canReachWithDFS(int[][] grid, int row, int col, int time, boolean[][] visited) {
        int n = grid.length;
        
        if (row == n - 1 && col == n - 1) {
            return true;
        }
        
        visited[row][col] = true;
        
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (isValidCell(newRow, newCol, n) && 
                !visited[newRow][newCol] && 
                grid[newRow][newCol] <= time) {
                if (canReachWithDFS(grid, newRow, newCol, time, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // Union-Find helper class
    private static class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
    
    @Override
    public void run() {
        System.out.println("Testing Swim in Rising Water Implementations");
        System.out.println("==========================================");
        
        // Test Case 1: Example from problem
        int[][] grid1 = {
            {0, 2},
            {1, 3}
        };
        System.out.println("Test Case 1:");
        System.out.println("Grid: " + Arrays.deepToString(grid1));
        System.out.println("Expected: 3");
        System.out.println("Dijkstra: " + swimInWaterDijkstra(grid1));
        System.out.println("Binary Search: " + swimInWaterBinarySearch(grid1));
        System.out.println("Union Find: " + swimInWaterUnionFind(grid1));
        System.out.println("DFS Memo: " + swimInWaterDFSMemo(grid1));
        System.out.println();
        
        // Test Case 2: Example from problem
        int[][] grid2 = {
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}
        };
        System.out.println("Test Case 2:");
        System.out.println("Grid: 5x5 matrix");
        System.out.println("Expected: 16");
        System.out.println("Dijkstra: " + swimInWaterDijkstra(grid2));
        System.out.println("Binary Search: " + swimInWaterBinarySearch(grid2));
        System.out.println("Union Find: " + swimInWaterUnionFind(grid2));
        System.out.println("DFS Memo: " + swimInWaterDFSMemo(grid2));
        System.out.println();
        
        // Test Case 3: Single cell
        int[][] grid3 = {{0}};
        System.out.println("Test Case 3:");
        System.out.println("Grid: " + Arrays.deepToString(grid3));
        System.out.println("Expected: 0");
        System.out.println("Dijkstra: " + swimInWaterDijkstra(grid3));
        System.out.println("Binary Search: " + swimInWaterBinarySearch(grid3));
        System.out.println("Union Find: " + swimInWaterUnionFind(grid3));
        System.out.println("DFS Memo: " + swimInWaterDFSMemo(grid3));
        System.out.println();
        
        // Test Case 4: Monotonic path
        int[][] grid4 = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
        };
        System.out.println("Test Case 4:");
        System.out.println("Grid: " + Arrays.deepToString(grid4));
        System.out.println("Expected: 8 (monotonic increasing)");
        System.out.println("Dijkstra: " + swimInWaterDijkstra(grid4));
        System.out.println("Binary Search: " + swimInWaterBinarySearch(grid4));
        System.out.println("Union Find: " + swimInWaterUnionFind(grid4));
        System.out.println("DFS Memo: " + swimInWaterDFSMemo(grid4));
        System.out.println();
        
        // Test Case 5: High starting point
        int[][] grid5 = {
            {10, 12, 4},
            {1, 2, 3},
            {5, 6, 7}
        };
        System.out.println("Test Case 5:");
        System.out.println("Grid: " + Arrays.deepToString(grid5));
        System.out.println("Expected: 10 (high starting elevation)");
        System.out.println("Dijkstra: " + swimInWaterDijkstra(grid5));
        System.out.println("Binary Search: " + swimInWaterBinarySearch(grid5));
        System.out.println("Union Find: " + swimInWaterUnionFind(grid5));
        System.out.println("DFS Memo: " + swimInWaterDFSMemo(grid5));
        System.out.println();
        
        // Test Case 6: All same values
        int[][] grid6 = {
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5}
        };
        System.out.println("Test Case 6:");
        System.out.println("Grid: " + Arrays.deepToString(grid6));
        System.out.println("Expected: 5 (all same elevation)");
        System.out.println("Dijkstra: " + swimInWaterDijkstra(grid6));
        System.out.println("Binary Search: " + swimInWaterBinarySearch(grid6));
        System.out.println("Union Find: " + swimInWaterUnionFind(grid6));
        System.out.println("DFS Memo: " + swimInWaterDFSMemo(grid6));
    }
} 