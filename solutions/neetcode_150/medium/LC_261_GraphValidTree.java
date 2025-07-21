/*
 * LeetCode: 261 - Graph Valid Tree
 * URL: https://leetcode.com/problems/graph-valid-tree/
 * Difficulty: Medium
 *
 * A graph is a valid tree if:
 * 1. It has exactly n - 1 edges (no cycles).
 * 2. It is fully connected (only one connected component).
 *
 * Approach:
 * - Use Union-Find (Disjoint Set Union - DSU).
 * - Check the number of edges first. If not exactly n - 1, return false.
 * - Then union all edges.
 * - Count number of unique parents. If only one, it's a valid tree.
 *
 * Time Complexity: O(n + e*α(n))
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_261_GraphValidTree implements Solution {

  @Override
  public void run() {
    int n = 5;
    int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } };
    System.out.println("Is valid tree? " + validTree(n, edges)); // true
  }

  public boolean validTree(int n, int[][] edges) {
    // Must have exactly n-1 edges to form a valid tree
    if (edges.length != n - 1)
      return false;

    int[] parent = new int[n];
    int[] rank = new int[n];

    // Initialize each node's parent to itself
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }

    // Union all edges
    for (int[] edge : edges) {
      int a = edge[0];
      int b = edge[1];
      union(a, b, parent, rank);
    }

    // Count unique parents (i.e., connected components)
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (parent[i] == i)
        count++;
    }

    return count == 1;
  }

  private void union(int a, int b, int[] parent, int[] rank) {
    int rootA = find(a, parent);
    int rootB = find(b, parent);

    if (rootA == rootB)
      return;

    // Union by rank
    if (rank[rootA] >= rank[rootB]) {
      parent[rootB] = rootA;
      rank[rootA]++;
    } else {
      parent[rootA] = rootB;
      rank[rootB]++;
    }
  }

  private int find(int a, int[] parent) {
    if (parent[a] != a) {
      parent[a] = find(parent[a], parent); // Path compression
    }
    return parent[a];
  }
}