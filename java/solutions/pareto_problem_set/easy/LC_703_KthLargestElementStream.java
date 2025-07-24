/*
 * LeetCode Problem 703: Kth Largest Element in a Stream
 * URL: https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * Difficulty: Easy
 *
 * Approach:
 * - Use a min-heap of size k to track the k largest elements seen so far.
 * - The top of the heap (peek) is always the kth largest element.
 *
 * Time Complexity:
 * - Constructor: O(n log k), where n = initial array size
 * - add(): O(log k)
 *
 * Space Complexity: O(k) — heap size is capped at k
 */

package solutions.pareto_problem_set.easy;

import java.util.PriorityQueue;

import common.Solution;

public class LC_703_KthLargestElementStream implements Solution {

  static class KthLargest {
    private final PriorityQueue<Integer> minHeap;
    private final int capacity;

    public KthLargest(int k, int[] nums) {
      this.capacity = k;
      this.minHeap = new PriorityQueue<>();
      for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
          minHeap.poll();
        }
      }
    }

    public int add(int val) {
      minHeap.offer(val);
      if (minHeap.size() > capacity) {
        minHeap.poll();
      }
      return minHeap.peek();
    }
  }

  @Override
  public void run() {
    int k = 3;
    int[] nums = { 4, 5, 8, 2 };
    KthLargest kthLargest = new KthLargest(k, nums);

    System.out.println("Add 3: " + kthLargest.add(3)); // returns 4
    System.out.println("Add 5: " + kthLargest.add(5)); // returns 5
    System.out.println("Add 10: " + kthLargest.add(10)); // returns 5
    System.out.println("Add 9: " + kthLargest.add(9)); // returns 8
    System.out.println("Add 4: " + kthLargest.add(4)); // returns 8
  }
}