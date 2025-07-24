package solutions.top_interview_questions.medium;

import java.util.HashSet;
import java.util.Set;

import common.ArrayUtils;
import common.Solution;

public class LC_128_LongestConsecutiveSequence implements Solution {

  // Optimized HashSet Approach (O(n) Time | O(n) Space)
  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums)
      set.add(num); // Store all unique numbers

    int longest = 0;

    for (int num : set) {
      if (!set.contains(num - 1)) { // Start of a new sequence
        int length = 1;
        while (set.contains(num + length))
          length++; // Count consecutive numbers
        longest = Math.max(longest, length);
      }
    }
    return longest;
  }

  @Override
  public void run() {
    int[][] testCases = {
        { 100, 4, 200, 1, 3, 2 }, // Expected: 4
        { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 }, // Expected: 9
        { 9, 1, 4, 7, 3, -1, 0, 5, 8, -3, 6, -2, 2 }, // Expected: 13
        { 1, 2, 3, 4, 5 }, // Expected: 5
        { 10, 20, 30, 40 }, // Expected: 1
        {} // Expected: 0 (Empty array)
    };

    for (int[] nums : testCases) {
      ArrayUtils.printArray("Input Array", nums);
      System.out.println("Longest Consecutive Sequence Length: " + longestConsecutive(nums));
      System.out.println();
    }
  }
}