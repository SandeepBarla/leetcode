package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.List;

import common.ArrayUtils;
import common.Solution;

public class LC_78_Subsets implements Solution {

  // Backtracking approach to generate all subsets
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    backtrack(nums, 0, new ArrayList<>(), res);
    return res;
  }

  private void backtrack(int[] nums, int index, List<Integer> comb, List<List<Integer>> res) {
    res.add(new ArrayList<>(comb)); // Add current combination to result
    for (int i = index; i < nums.length; i++) {
      comb.add(nums[i]); // Include the current element
      backtrack(nums, i + 1, comb, res); // Recur for next index
      comb.remove(comb.size() - 1); // Backtrack (remove last added element)
    }
  }

  @Override
  public void run() {
    int[] nums = { 1, 2, 3 };

    // Print input array
    System.out.println("Input Array:");
    ArrayUtils.printArray("nums", nums);

    // Generate subsets
    List<List<Integer>> result = subsets(nums);

    // Print result
    System.out.println("All Subsets:");
    ArrayUtils.print2DList("Subsets", result);
  }
}