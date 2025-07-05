/*
 * LeetCode Problem 435: Non-overlapping Intervals
 * URL: https://leetcode.com/problems/non-overlapping-intervals/
 * Difficulty: Medium
 *
 * Approach:
 * - Sort intervals by their end time.
 * - Initialize prevEnd to the end of the first interval.
 * - Traverse the rest:
 *     - If current start < prevEnd → overlap → increment removal count.
 *     - Else → update prevEnd.
 *
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(1) extra (in-place sort + constant vars)
 */

package solutions.neetcode_150.medium;

import java.util.Arrays;

import common.ArrayUtils;
import common.Solution;

public class LC_435_NonOverlappingIntervals implements Solution {

  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length == 0)
      return 0;

    // Step 1: Sort intervals by their end time
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

    int removed = 0;
    int prevEnd = intervals[0][1];

    // Step 2: Iterate through intervals, check for overlaps
    for (int i = 1; i < intervals.length; i++) {
      int currStart = intervals[i][0];
      int currEnd = intervals[i][1];

      if (currStart < prevEnd) {
        // Overlap → we "remove" this interval by skipping update
        removed++;
      } else {
        // No overlap → keep this interval, update prevEnd
        prevEnd = currEnd;
      }
    }

    return removed;
  }

  @Override
  public void run() {
    int[][] intervals1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } }; // remove [1,3]
    int[][] intervals2 = { { 1, 2 }, { 1, 2 }, { 1, 2 } }; // remove 2
    int[][] intervals3 = { { 1, 2 }, { 2, 3 } }; // none to remove

    System.out.println("Removed intervals: " + eraseOverlapIntervals(intervals1)); // 1
    System.out.println("Removed intervals: " + eraseOverlapIntervals(intervals2)); // 2
    System.out.println("Removed intervals: " + eraseOverlapIntervals(intervals3)); // 0

    // Optional: print sorted version to verify sorting by end
    Arrays.sort(intervals1, (a, b) -> Integer.compare(a[1], b[1]));
    ArrayUtils.print2DArray("Sorted by end", intervals1);
  }
}