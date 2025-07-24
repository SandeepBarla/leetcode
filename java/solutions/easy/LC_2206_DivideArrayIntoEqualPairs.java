package solutions.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;

public class LC_2206_DivideArrayIntoEqualPairs implements Solution {

  // Approach 1: HashMap (Handles Full Integer Range)
  public boolean divideArrayUsingHashMap(int[] nums) {
    Map<Integer, Integer> freqMap = new HashMap<>();

    // Count frequency of each number
    for (int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    // Check if all numbers appear an even number of times
    for (int count : freqMap.values()) {
      if (count % 2 != 0)
        return false;
    }

    return true;
  }

  // Approach 2: Sorting + Checking Adjacent Pairs
  public boolean divideArrayUsingSorting(int[] nums) {
    Arrays.sort(nums); // O(N log N)

    for (int i = 0; i < nums.length; i += 2) {
      if (nums[i] != nums[i + 1])
        return false;
    }

    return true;
  }

  @Override
  public void run() {
    // Test Case 1: Evenly Paired Numbers
    int[] nums1 = { 3, 2, 3, 2, 2, 2 };
    ArrayUtils.printArray("Input", nums1);
    System.out.println("HashMap Approach: " + divideArrayUsingHashMap(nums1)); // Expected: true
    System.out.println("Sorting Approach: " + divideArrayUsingSorting(nums1)); // Expected: true

    // Test Case 2: Odd Count of Numbers
    int[] nums2 = { 1, 2, 3, 4, 5 };
    ArrayUtils.printArray("Input", nums2);
    System.out.println("HashMap Approach: " + divideArrayUsingHashMap(nums2)); // Expected: false
    System.out.println("Sorting Approach: " + divideArrayUsingSorting(nums2)); // Expected: false

    // Test Case 3: Large Integers
    int[] nums3 = { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };
    ArrayUtils.printArray("Input", nums3);
    System.out.println("HashMap Approach: " + divideArrayUsingHashMap(nums3)); // Expected: true
    System.out.println("Sorting Approach: " + divideArrayUsingSorting(nums3)); // Expected: true
  }
}
