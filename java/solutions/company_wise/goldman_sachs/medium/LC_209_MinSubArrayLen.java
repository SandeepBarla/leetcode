package solutions.company_wise.goldman_sachs.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_209_MinSubArrayLen implements Solution {

  // ✅ Optimized Sliding Window Approach (O(N) time, O(1) space)
  public int minSubArrayLen(int target, int[] nums) {
    int start = 0;
    int min = Integer.MAX_VALUE;
    int sum = 0;

    for (int end = 0; end < nums.length; end++) {
      sum += nums[end];

      // Shrink the window while sum >= target
      while (sum >= target) {
        min = Math.min(min, end - start + 1); // Update minimum length
        sum -= nums[start++]; // Reduce window from the left
      }
    }

    return min != Integer.MAX_VALUE ? min : 0;
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    int target1 = 7;
    int[] nums1 = { 2, 3, 1, 2, 4, 3 };
    System.out.println("Input: target = " + target1);
    ArrayUtils.printArray("nums", nums1);
    System.out.println("Output: " + minSubArrayLen(target1, nums1)); // Expected: 2

    // 🔹 Test Case 2
    int target2 = 4;
    int[] nums2 = { 1, 4, 4 };
    System.out.println("\nInput: target = " + target2);
    ArrayUtils.printArray("nums", nums2);
    System.out.println("Output: " + minSubArrayLen(target2, nums2)); // Expected: 1

    // 🔹 Test Case 3
    int target3 = 11;
    int[] nums3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
    System.out.println("\nInput: target = " + target3);
    ArrayUtils.printArray("nums", nums3);
    System.out.println("Output: " + minSubArrayLen(target3, nums3)); // Expected: 0 (No valid subarray)
  }
}