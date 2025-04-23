/*
 * LeetCode Problem 15: 3Sum
 * URL: https://leetcode.com/problems/3sum/
 * Difficulty: Medium
 *
 * Approach: Sorting + Two Pointers
 * - Sort the input array.
 * - Iterate with a fixed index and use two pointers (left/right) to find pairs that sum to -nums[i].
 * - Skip duplicates for the fixed element and the pair to avoid repeated triplets.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1) (excluding the output list)
 */
package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.ArrayUtils;
import common.Solution;

public class LC_15_ThreeSum implements Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // skip duplicates
            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;

                    while (l < r && nums[l] == nums[l - 1])
                        l++; // skip left duplicates
                    while (l < r && nums[r] == nums[r + 1])
                        r--; // skip right duplicates
                }
            }
        }

        return res;
    }

    @Override
    public void run() {
        int[] nums1 = { -1, 0, 1, 2, -1, -4 };
        ArrayUtils.printArray("Input", nums1);
        List<List<Integer>> result1 = threeSum(nums1);
        ArrayUtils.print2DList("Triplets", result1);

        int[] nums2 = { 0, 1, 1 };
        ArrayUtils.printArray("Input", nums2);
        List<List<Integer>> result2 = threeSum(nums2);
        ArrayUtils.print2DList("Triplets", result2);

        int[] nums3 = { 0, 0, 0 };
        ArrayUtils.printArray("Input", nums3);
        List<List<Integer>> result3 = threeSum(nums3);
        ArrayUtils.print2DList("Triplets", result3);
    }
}