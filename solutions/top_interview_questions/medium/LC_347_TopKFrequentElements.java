package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import common.ArrayUtils;
import common.Solution;

public class LC_347_TopKFrequentElements implements Solution {

  // Solution 1: Using Max Heap (`O(n log n)`)
  public int[] topKFrequentUsingMaxHeap(int[] nums, int k) {
    Map<Integer, Integer> freqMap = new HashMap<>();

    // Step 1: Count frequency of each number
    for (int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    // Step 2: Use Max Heap (Sort by Highest Frequency First)
    PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(b.getValue(), a.getValue()));

    // Step 3: Insert all elements into the maxHeap
    maxHeap.addAll(freqMap.entrySet());

    // Step 4: Extract top k frequent elements
    int[] res = new int[k];
    for (int i = 0; i < k; i++) {
      res[i] = maxHeap.poll().getKey(); // Remove highest frequency element
    }

    return res;
  }

  // 🔹 Solution 2: Using Bucket Sort (`O(n)`)
  public int[] topKFrequentUsingBucketSort(int[] nums, int k) {
    Map<Integer, Integer> freqMap = new HashMap<>();

    // Step 1: Count frequency of each number
    for (int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    // Step 2: Create buckets (Array of Lists)
    List<Integer>[] buckets = new List[nums.length + 1];
    for (int key : freqMap.keySet()) {
      int frequency = freqMap.get(key);
      if (buckets[frequency] == null) {
        buckets[frequency] = new ArrayList<>();
      }
      buckets[frequency].add(key);
    }

    // Step 3: Collect top k frequent elements from the buckets
    int[] res = new int[k];
    int index = 0;

    // Iterate from highest frequency to lowest
    for (int i = nums.length; i > 0 && index < k; i--) {
      if (buckets[i] != null) {
        for (int num : buckets[i]) {
          res[index++] = num;
          if (index == k)
            return res; // Stop when we collect k elements
        }
      }
    }

    return res;
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    int[] nums1 = { 1, 1, 1, 2, 2, 3 };
    int k1 = 2;
    ArrayUtils.printArray("Input Array", nums1);

    // Max Heap Solution
    System.out.println("Top " + k1 + " Frequent Elements (Max Heap): "
        + Arrays.toString(topKFrequentUsingMaxHeap(nums1, k1)));

    // Bucket Sort Solution
    System.out.println("Top " + k1 + " Frequent Elements (Bucket Sort): "
        + Arrays.toString(topKFrequentUsingBucketSort(nums1, k1)));

    // 🔹 Test Case 2
    int[] nums2 = { 4, 4, 4, 5, 5, 5, 6 };
    int k2 = 1;
    ArrayUtils.printArray("Input Array", nums2);

    // Max Heap Solution
    System.out.println("Top " + k2 + " Frequent Elements (Max Heap): "
        + Arrays.toString(topKFrequentUsingMaxHeap(nums2, k2)));

    // Bucket Sort Solution
    System.out.println("Top " + k2 + " Frequent Elements (Bucket Sort): "
        + Arrays.toString(topKFrequentUsingBucketSort(nums2, k2)));
  }
}