package solutions.pareto_problem_set.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_167_TwoSumII implements Solution {
  public int[] twoSum(int[] numbers, int target) {
    int l = 0, r = numbers.length - 1;

    while (l < r) {
      int sum = numbers[l] + numbers[r];

      if (sum == target) {
        return new int[] { l + 1, r + 1 }; // Return 1-based index
      } else if (sum > target) {
        r--; // Move right pointer left to decrease sum
      } else {
        l++; // Move left pointer right to increase sum
      }
    }
    return new int[] { -1, -1 }; // No valid pair found
  }

  @Override
  public void run() {
    // Test Case 1
    int[] numbers1 = { 2, 7, 11, 15 };
    int target1 = 9;
    ArrayUtils.printArray("Numbers", numbers1);
    int[] result1 = twoSum(numbers1, target1);
    ArrayUtils.printArray("Output", result1); // Expected: [1, 2]

    // Test Case 2
    int[] numbers2 = { 1, 3, 4, 5, 7, 10 };
    int target2 = 8;
    ArrayUtils.printArray("\nNumbers", numbers2);
    int[] result2 = twoSum(numbers2, target2);
    ArrayUtils.printArray("Output", result2); // Expected: [2, 4]
  }
}