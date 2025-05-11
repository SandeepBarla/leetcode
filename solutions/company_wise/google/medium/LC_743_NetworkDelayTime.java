/*
 * LeetCode Problem 743: Network Delay Time
 * URL: https://leetcode.com/problems/network-delay-time/
 * Difficulty: Medium
 *
 * Approach:
 * - Use Dijkstra's algorithm with a min-heap to find the shortest path from node k to all other nodes.
 * - Track the minimum time to reach each node using a visited map.
 * - At the end, if all nodes are reached, return the max time among them; otherwise, return -1.
 *
 * Time Complexity: O(E log N), where E is the number of edges and N is the number of nodes.
 * Space Complexity: O(N + E) — for graph storage, priority queue, and visited map.
 */
package solutions.company_wise.google.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import common.Solution;

public class LC_743_NetworkDelayTime implements Solution {

  public int networkDelayTime(int[][] times, int n, int k) {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int i = 1; i <= n; i++) {
      graph.put(i, new ArrayList<>());
    }

    for (int[] edge : times) {
      int from = edge[0], to = edge[1], weight = edge[2];
      graph.get(from).add(new int[] { to, weight });
    }

    Map<Integer, Integer> visited = new HashMap<>();
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    minHeap.offer(new int[] { k, 0 });

    while (!minHeap.isEmpty()) {
      int[] curr = minHeap.poll();
      int node = curr[0];
      int time = curr[1];

      if (visited.containsKey(node))
        continue;
      visited.put(node, time);

      for (int[] neighbor : graph.get(node)) {
        int nextNode = neighbor[0];
        int weight = neighbor[1];
        if (!visited.containsKey(nextNode)) {
          minHeap.offer(new int[] { nextNode, time + weight });
        }
      }
    }

    if (visited.size() != n)
      return -1;

    int maxTime = 0;
    for (int t : visited.values()) {
      maxTime = Math.max(maxTime, t);
    }
    return maxTime;
  }

  @Override
  public void run() {
    int[][] times1 = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
    System.out.println("Network Delay Time (Example 1): " + networkDelayTime(times1, 4, 2)); // Expected: 2

    int[][] times2 = { { 1, 2, 1 } };
    System.out.println("Network Delay Time (Disconnected): " + networkDelayTime(times2, 2, 1)); // Expected: 1

    int[][] times3 = { { 1, 2, 1 } };
    System.out.println("Network Delay Time (Unreachable): " + networkDelayTime(times3, 2, 2)); // Expected: -1
  }
}