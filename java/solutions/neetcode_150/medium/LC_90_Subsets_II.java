/*
 * LeetCode Problem 90: Subsets II
 * URL: https://leetcode.com/problems/subsets-ii/
 * Difficulty: Medium
 *
 * Approach:
 * - Sort the input array to bring duplicates together.
 * - Use backtracking to generate all subsets.
 * - Skip over duplicates at each level to avoid repeating the same subset.
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Solution;

public class LC_90_Subsets_II implements Solution {

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(0, nums, new ArrayList<>(), result);
    return result;
  }

  private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
    result.add(new ArrayList<>(current));

    for (int i = start; i < nums.length; i++) {
      // skip duplicates at the same level
      if (i > start && nums[i] == nums[i - 1])
        continue;

      current.add(nums[i]);
      backtrack(i + 1, nums, current, result);
      current.remove(current.size() - 1);
    }
  }

  @Override
  public void run() {
    int[] input = { 1, 2, 2 };
    System.out.println("Input: " + Arrays.toString(input));
    List<List<Integer>> subsets = subsetsWithDup(input);
    System.out.println("Unique Subsets:");
    for (List<Integer> subset : subsets) {
      System.out.println(subset);
    }
  }
}