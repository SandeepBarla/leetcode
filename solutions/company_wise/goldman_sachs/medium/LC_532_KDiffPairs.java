package solutions.company_wise.goldman_sachs.medium;

import java.util.HashMap;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;

public class LC_532_KDiffPairs implements Solution {

  // ✅ Optimized Approach: HashMap (O(N) time, O(N) space)
  public int findPairs(int[] nums, int k) {
    if (k < 0)
      return 0; // Absolute difference cannot be negative

    // Step 1: Count frequencies of each number in nums[]
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    int count = 0;

    // Step 2: Iterate over unique numbers in HashMap and check for valid pairs
    for (int num : freqMap.keySet()) {
      if ((k > 0 && freqMap.containsKey(num + k)) || (k == 0 && freqMap.get(num) > 1)) {
        count++;
      }
    }

    return count;
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    int[] nums1 = { 3, 1, 4, 1, 5 };
    int k1 = 2;
    ArrayUtils.printArray("Input Array", nums1);
    System.out.println("K = " + k1);
    System.out.println("Output: " + findPairs(nums1, k1));

    // 🔹 Test Case 2
    int[] nums2 = { 1, 2, 3, 4, 5 };
    int k2 = 1;
    ArrayUtils.printArray("Input Array", nums2);
    System.out.println("K = " + k2);
    System.out.println("Output: " + findPairs(nums2, k2));

    // 🔹 Test Case 3 (Handling k == 0)
    int[] nums3 = { 1, 3, 1, 5, 4, 1 };
    int k3 = 0;
    ArrayUtils.printArray("Input Array", nums3);
    System.out.println("K = " + k3);
    System.out.println("Output: " + findPairs(nums3, k3));
  }
}