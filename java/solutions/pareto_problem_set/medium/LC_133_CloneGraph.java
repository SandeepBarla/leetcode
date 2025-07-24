/*
 * LeetCode Problem 133: Clone Graph
 * URL: https://leetcode.com/problems/clone-graph/
 * Difficulty: Medium
 *
 * Approach: BFS + HashMap
 * - Traverse the graph using BFS.
 * - Use a HashMap<GraphNode, GraphNode> to map original nodes to their clones.
 * - Clone each neighbor and connect the clone accordingly.
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
package solutions.pareto_problem_set.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import common.GraphNode;
import common.GraphUtils;
import common.Solution;

public class LC_133_CloneGraph implements Solution {

  public GraphNode cloneGraph(GraphNode node) {
    if (node == null)
      return null;

    Map<GraphNode, GraphNode> cloneMap = new HashMap<>();
    Queue<GraphNode> queue = new LinkedList<>();

    cloneMap.put(node, new GraphNode(node.val));
    queue.offer(node);

    while (!queue.isEmpty()) {
      GraphNode curr = queue.poll();

      for (GraphNode neighbor : curr.neighbors) {
        if (!cloneMap.containsKey(neighbor)) {
          cloneMap.put(neighbor, new GraphNode(neighbor.val));
          queue.offer(neighbor);
        }
        cloneMap.get(curr).neighbors.add(cloneMap.get(neighbor));
      }
    }

    return cloneMap.get(node);
  }

  @Override
  public void run() {
    // Build sample graph:
    // 1 -- 2
    // | |
    // 4 -- 3

    GraphNode node1 = new GraphNode(1);
    GraphNode node2 = new GraphNode(2);
    GraphNode node3 = new GraphNode(3);
    GraphNode node4 = new GraphNode(4);

    node1.neighbors.addAll(Arrays.asList(node2, node4));
    node2.neighbors.addAll(Arrays.asList(node1, node3));
    node3.neighbors.addAll(Arrays.asList(node2, node4));
    node4.neighbors.addAll(Arrays.asList(node1, node3));

    GraphUtils.printGraph("Original Graph", node1);
    GraphNode cloned = cloneGraph(node1);
    GraphUtils.printGraph("Cloned Graph", cloned);

    System.out.println("Same reference? " + (node1 == cloned));
    System.out.println("Same neighbor reference? " + (node1.neighbors.get(0) == cloned.neighbors.get(0)));
  }
}