package common;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphUtils {

  public static void printGraph(String label, GraphNode node) {
    System.out.println(label + ":");

    if (node == null) {
      System.out.println("null");
      return;
    }

    Set<GraphNode> visited = new HashSet<>();
    Queue<GraphNode> queue = new LinkedList<>();
    queue.offer(node);
    visited.add(node);

    while (!queue.isEmpty()) {
      GraphNode curr = queue.poll();
      System.out.print("Node " + curr.val + " -> [");
      for (GraphNode neighbor : curr.neighbors) {
        System.out.print(neighbor.val + " ");
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.offer(neighbor);
        }
      }
      System.out.println("]");
    }
  }
}