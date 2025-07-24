/*
 * LeetCode Problem 1046: Last Stone Weight
 * URL: https://leetcode.com/problems/last-stone-weight/
 * Difficulty: Easy
 *
 * Approach: Max Heap (PriorityQueue)
 * - Use a max heap to repeatedly remove the two largest stones.
 * - If they’re not equal, insert the difference back into the heap.
 * - Continue until one or no stone is left.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
package solutions.pareto_problem_set.easy;

import java.util.Collections;
import java.util.PriorityQueue;

import common.ArrayUtils;
import common.Solution;

public class LC_1046_LastStoneWeight implements Solution {

  public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (int stone : stones) {
      maxHeap.offer(stone);
    }

    while (maxHeap.size() > 1) {
      int first = maxHeap.poll();
      int second = maxHeap.poll();
      if (first != second) {
        maxHeap.offer(first - second);
      }
    }

    return maxHeap.isEmpty() ? 0 : maxHeap.poll();
  }

  @Override
  public void run() {
    int[] stones1 = { 2, 7, 4, 1, 8, 1 };
    ArrayUtils.printArray("Input", stones1);
    System.out.println("Last Stone Weight: " + lastStoneWeight(stones1));

    int[] stones2 = { 10 };
    ArrayUtils.printArray("Input", stones2);
    System.out.println("Last Stone Weight: " + lastStoneWeight(stones2));

    int[] stones3 = { 1, 1 };
    ArrayUtils.printArray("Input", stones3);
    System.out.println("Last Stone Weight: " + lastStoneWeight(stones3));
  }
}