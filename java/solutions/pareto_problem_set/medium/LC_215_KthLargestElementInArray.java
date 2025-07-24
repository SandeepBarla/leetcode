/*
 * LeetCode Problem 215: Kth Largest Element in an Array
 * URL: https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Difficulty: Medium
 *
 * Approach:
 * - Use a Min Heap (PriorityQueue) of size k to keep track of the k largest elements.
 * - Iterate through all elements, adding each to the heap.
 * - If the heap exceeds size k, remove the smallest element (which is at the root of the min heap).
 * - At the end, the root of the min heap is the k-th largest element in the array.
 *
 * Time Complexity: O(n log k), where n is the number of elements in the array.
 * Space Complexity: O(k), for storing the heap.
 */

package solutions.pareto_problem_set.medium;

import java.util.PriorityQueue;

import common.Solution;

public class LC_215_KthLargestElementInArray implements Solution {

  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k + 1);

    for (int num : nums) {
      minHeap.add(num);
      if (minHeap.size() > k) {
        minHeap.poll(); // remove smallest to keep heap size at k
      }
    }

    return minHeap.poll(); // root of heap is the kth largest
  }

  @Override
  public void run() {
    int[] nums1 = { 3, 2, 1, 5, 6, 4 };
    int k1 = 2;
    System.out.println("\n--- Running LC 215: Kth Largest Element in an Array ---");
    System.out.println("Input: " + java.util.Arrays.toString(nums1) + ", k = " + k1);
    System.out.println("Output: " + findKthLargest(nums1, k1));

    int[] nums2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
    int k2 = 4;
    System.out.println("\nInput: " + java.util.Arrays.toString(nums2) + ", k = " + k2);
    System.out.println("Output: " + findKthLargest(nums2, k2));
  }
}