/*
 * LeetCode: 295 - Find Median from Data Stream
 * URL: https://leetcode.com/problems/find-median-from-data-stream
 * Difficulty: Hard
 *
 * Approach:
 * - Use two heaps:
 *   1. Max Heap (stores the smaller half of the numbers)
 *   2. Min Heap (stores the larger half)
 * - Invariant: maxHeap.size() >= minHeap.size()
 * - When adding a number:
 *   - Add to maxHeap first
 *   - Move the largest from maxHeap to minHeap
 *   - If minHeap is now larger, move its top back to maxHeap
 * - When finding median:
 *   - If sizes are equal, return average of tops
 *   - If not, return top of maxHeap
 *
 * Time Complexity:
 * - addNum: O(log n)
 * - findMedian: O(1)
 */

package solutions.neetcode_150.hard;

import java.util.PriorityQueue;

import common.Solution;

public class LC_295_FindMedianFromDataStream implements Solution {

  public static class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // lower half (as max heap)
    private PriorityQueue<Integer> minHeap; // upper half (as min heap)

    public MedianFinder() {
      maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
      minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
      maxHeap.add(num); // Add to maxHeap first
      minHeap.add(maxHeap.poll()); // Move largest to minHeap

      if (minHeap.size() > maxHeap.size()) {
        maxHeap.add(minHeap.poll()); // Rebalance
      }
    }

    public double findMedian() {
      if (maxHeap.size() > minHeap.size()) {
        return maxHeap.peek();
      } else {
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
      }
    }
  }

  @Override
  public void run() {
    MedianFinder mf = new MedianFinder();
    mf.addNum(1);
    mf.addNum(2);
    System.out.println("Median: " + mf.findMedian()); // 1.5

    mf.addNum(3);
    System.out.println("Median: " + mf.findMedian()); // 2.0
  }
}