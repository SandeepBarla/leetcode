/*
 * LeetCode: 323 - Number of Connected Components in an Undirected Graph
 * URL: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * Difficulty: Medium
 *
 * Approach:
 * - Use Union-Find (Disjoint Set Union - DSU) to group connected nodes.
 * - Every node starts as its own parent.
 * - For each edge, union the two nodes.
 * - Finally, count how many unique parents are left — those are your components.
 *
 * Time Complexity: O(n + e*α(n)) where α(n) is inverse Ackermann (almost constant)
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_323_NumberOfConnectedComponentsInAnUndirectedGraph implements Solution {

  @Override
  public void run() {
    int[][] edges = new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 }, { 1, 2 }, { 3, 4 } };
    int n = 6;
    System.out.println("Components: " + countComponents(n, edges)); // Output: 1
  }

  public int countComponents(int n, int[][] edges) {
    int[] parent = new int[n];
    int[] rank = new int[n];

    // Initially, each node is its own parent
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }

    // Union each edge
    for (int[] edge : edges) {
      union(edge[0], edge[1], parent, rank);
    }

    // Count unique parents
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (parent[i] == i)
        count++;
    }

    return count;
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

  private int find(int x, int[] parent) {
    if (parent[x] != x) {
      parent[x] = find(parent[x], parent); // Path compression
    }
    return parent[x];
  }
}