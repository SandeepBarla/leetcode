/*
 * LeetCode Problem 684: Redundant Connection
 * URL: https://leetcode.com/problems/redundant-connection/
 * Difficulty: Medium
 *
 * Approach 1: Union-Find with Path Compression + Union by Rank (Optimized)
 * - Maintain a `parent` array and `rank` array.
 * - For each edge, check if the two nodes belong to the same set using `find()`.
 * - If yes, it's the redundant edge.
 * - If not, union the sets using `union()` with rank-based optimization.
 *
 * Approach 2: Naive Union-Find (No path compression or rank)
 * - Maintain a simple `parent` array.
 * - Each union simply assigns one root to another.
 * - Slower in dense graphs due to deeper trees.
 *
 * Time Complexity:
 * - Optimized: O(n * α(n)) ≈ O(n) per operation
 * - Naive: O(n²) worst case due to deep trees
 *
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_684_RedundantConnection implements Solution {

  // === Optimized DSU ===
  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    int[] parent = new int[n + 1];
    int[] rank = new int[n + 1];

    // Initialize each node as its own parent
    for (int i = 1; i <= n; i++)
      parent[i] = i;

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      if (find(u, parent) == find(v, parent))
        return edge;
      union(u, v, parent, rank);
    }
    return new int[0];
  }

  private int find(int x, int[] parent) {
    if (parent[x] != x) {
      parent[x] = find(parent[x], parent); // Path compression
    }
    return parent[x];
  }

  private void union(int x, int y, int[] parent, int[] rank) {
    int rootX = find(x, parent);
    int rootY = find(y, parent);
    if (rootX == rootY)
      return;

    if (rank[rootX] > rank[rootY]) {
      parent[rootY] = rootX;
    } else if (rank[rootX] < rank[rootY]) {
      parent[rootX] = rootY;
    } else {
      parent[rootY] = rootX;
      rank[rootX]++;
    }
  }

  // === Naive DSU ===
  public int[] findRedundantConnectionNaive(int[][] edges) {
    int n = edges.length;
    int[] parent = new int[n + 1];
    for (int i = 1; i <= n; i++)
      parent[i] = i;

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      int pu = findNaive(u, parent);
      int pv = findNaive(v, parent);
      if (pu == pv)
        return edge;
      parent[pu] = pv; // Simple union (no rank)
    }
    return new int[0];
  }

  private int findNaive(int x, int[] parent) {
    while (parent[x] != x)
      x = parent[x];
    return x;
  }

  @Override
  public void run() {
    int[][] edges = {
        { 1, 2 },
        { 1, 3 },
        { 2, 3 }
    };

    int[] result1 = findRedundantConnection(edges);
    System.out.println("Optimized DSU Result: [" + result1[0] + "," + result1[1] + "]");

    int[] result2 = findRedundantConnectionNaive(edges);
    System.out.println("Naive DSU Result: [" + result2[0] + "," + result2[1] + "]");
  }
}