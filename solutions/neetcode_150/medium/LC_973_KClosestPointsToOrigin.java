/*
 * LeetCode Problem 973: K Closest Points to Origin
 * URL: https://leetcode.com/problems/k-closest-points-to-origin/
 * Difficulty: Medium
 *
 * Approach 1: Sorting
 * - Sort points by distance from origin (x^2 + y^2)
 * - Return first k points after sorting
 *
 * Approach 2: MaxHeap
 * - Maintain a max heap of size k based on distance from origin
 * - For each point, add to heap; if size > k, remove the farthest
 * - At the end, heap contains k closest points
 *
 * Time Complexity:
 *   - Sorting: O(n log n)
 *   - MaxHeap: O(n log k)
 *
 * Space Complexity:
 *   - Sorting: O(1)
 *   - MaxHeap: O(k)
 */

package solutions.neetcode_150.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

import common.ArrayUtils;
import common.Solution;

public class LC_973_KClosestPointsToOrigin implements Solution {

  // Approach 1: Sort by distance and return first k points
  public int[][] kClosestSorted(int[][] points, int k) {
    Arrays.sort(points, (a, b) -> (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]));
    return Arrays.copyOfRange(points, 0, k);
  }

  // Approach 2: MaxHeap of size k
  public int[][] kClosestHeap(int[][] points, int k) {
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
        (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

    for (int[] point : points) {
      maxHeap.add(point);
      if (maxHeap.size() > k) {
        maxHeap.poll();
      }
    }

    int[][] result = new int[k][2];
    int i = 0;
    while (!maxHeap.isEmpty()) {
      result[i++] = maxHeap.poll();
    }
    return result;
  }

  @Override
  public void run() {
    int[][] points1 = { { 1, 3 }, { -2, 2 }, { 5, 8 }, { 0, 1 } };
    int k1 = 2;

    ArrayUtils.print2DArray("Using Sort", kClosestSorted(points1.clone(), k1));
    ArrayUtils.print2DArray("Using MaxHeap", kClosestHeap(points1.clone(), k1));

    int[][] points2 = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
    int k2 = 2;

    ArrayUtils.print2DArray("Test 2 - Sort", kClosestSorted(points2.clone(), k2));
    ArrayUtils.print2DArray("Test 2 - MaxHeap", kClosestHeap(points2.clone(), k2));
  }
}