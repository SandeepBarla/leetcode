package solutions.top_interview_questions.easy;

import java.util.HashMap;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;

public class LC_1_TwoSum implements Solution {

    // ✅ Optimized HashMap Approach (O(n) time, O(n) space)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numIndexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i]; // Find the complement
            if (numIndexMap.containsKey(diff)) {
                return new int[] { numIndexMap.get(diff), i }; // Found the pair
            }
            numIndexMap.put(nums[i], i); // Store index of the current number
        }
        return new int[] { -1, -1 }; // Return {-1, -1} if no pair is found
    }

    @Override
    public void run() {
        // 🔹 Test Case 1
        int[] nums1 = { 2, 7, 11, 15 };
        int target1 = 9;
        ArrayUtils.printArray("Input", nums1);
        int[] result1 = twoSum(nums1, target1);
        ArrayUtils.printArray("Output", result1); // Expected: [0, 1]

        // 🔹 Test Case 2
        int[] nums2 = { 3, 2, 4 };
        int target2 = 6;
        ArrayUtils.printArray("\nInput", nums2);
        int[] result2 = twoSum(nums2, target2);
        ArrayUtils.printArray("Output", result2); // Expected: [1, 2]

        // 🔹 Test Case 3
        int[] nums3 = { 3, 3 };
        int target3 = 6;
        ArrayUtils.printArray("\nInput", nums3);
        int[] result3 = twoSum(nums3, target3);
        ArrayUtils.printArray("Output", result3); // Expected: [0, 1]
    }
}