/*
 * LeetCode Problem 57: Insert Interval
 * URL: https://leetcode.com/problems/insert-interval/
 * Difficulty: Medium
 *
 * Approach:
 * - Loop through all existing intervals:
 *     1. Add intervals that end before newInterval starts (no overlap).
 *     2. Merge all intervals that overlap with newInterval.
 *     3. Add remaining intervals after newInterval.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

package solutions.neetcode_150.medium;

import java.util.ArrayList;
import java.util.List;

import common.ArrayUtils;
import common.Solution;

public class LC_57_InsertInterval implements Solution {

  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int n = intervals.length;

    // 1. Add all intervals before newInterval
    while (i < n && intervals[i][1] < newInterval[0]) {
      result.add(intervals[i++]);
    }

    // 2. Merge overlapping intervals with newInterval
    while (i < n && intervals[i][0] <= newInterval[1]) {
      newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
      i++;
    }
    result.add(newInterval); // merged interval

    // 3. Add remaining intervals after newInterval
    while (i < n) {
      result.add(intervals[i++]);
    }

    // Convert result to array
    return result.toArray(new int[result.size()][2]);
  }

  @Override
  public void run() {
    int[][] intervals1 = { { 1, 3 }, { 6, 9 } };
    int[] new1 = { 2, 5 };
    ArrayUtils.print2DArray("Merged Intervals (1)", insert(intervals1, new1));
    // Expected: [[1,5],[6,9]]

    int[][] intervals2 = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
    int[] new2 = { 4, 8 };
    ArrayUtils.print2DArray("Merged Intervals (2)", insert(intervals2, new2));
    // Expected: [[1,2],[3,10],[12,16]]

    int[][] intervals3 = {};
    int[] new3 = { 5, 7 };
    ArrayUtils.print2DArray("Merged Intervals (3)", insert(intervals3, new3));
    // Expected: [[5,7]]

    int[][] intervals4 = { { 1, 5 } };
    int[] new4 = { 2, 3 };
    ArrayUtils.print2DArray("Merged Intervals (4)", insert(intervals4, new4));
    // Expected: [[1,5]]

    int[][] intervals5 = { { 1, 5 } };
    int[] new5 = { 6, 8 };
    ArrayUtils.print2DArray("Merged Intervals (5)", insert(intervals5, new5));
    // Expected: [[1,5],[6,8]]
  }
}