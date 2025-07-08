/*
 * LeetCode Problem 40: Combination Sum II
 * URL: https://leetcode.com/problems/combination-sum-ii/
 * Difficulty: Medium
 *
 * Approach:
 * - Sort the array to handle duplicates and enable pruning.
 * - Use backtracking to explore combinations.
 * - Skip duplicate elements at the same recursion level.
 * - Reduce the target sum at each step.
 * - Only move forward (i+1) since each number can be used once.
 *
 * Time Complexity: O(2^n) in the worst case
 * Space Complexity: O(n) for recursion stack
 */

package solutions.neetcode_150.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Solution;

public class LC_40_CombinationSumII implements Solution {

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    backtrack(candidates, target, 0, new ArrayList<>(), result);
    return result;
  }

  private void backtrack(int[] candidates, int target, int index,
      List<Integer> current, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = index; i < candidates.length; i++) {
      if (i > index && candidates[i] == candidates[i - 1])
        continue;
      if (candidates[i] > target)
        break;

      current.add(candidates[i]);
      backtrack(candidates, target - candidates[i], i + 1, current, result);
      current.remove(current.size() - 1);
    }
  }

  @Override
  public void run() {
    int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
    int target = 8;

    List<List<Integer>> output = combinationSum2(candidates, target);
    System.out.println("Combinations: " + output);
    // Expected: [[1,1,6],[1,2,5],[1,7],[2,6]]
  }
}