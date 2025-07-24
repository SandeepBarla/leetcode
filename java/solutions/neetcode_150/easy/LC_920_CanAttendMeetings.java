/*
 * LeetCode/LintCode: Meeting Rooms (LintCode 920)
 * URL: https://www.lintcode.com/problem/920
 * Difficulty: Easy
 *
 * Approach:
 * - Sort all intervals by their start time.
 * - Iterate through sorted intervals and check if current start < previous end.
 * - If such a case occurs, return false (meeting conflict).
 *
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(1)
 */

package solutions.neetcode_150.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import common.Interval;
import common.Solution;

public class LC_920_CanAttendMeetings implements Solution {

  public boolean canAttendMeetings(List<Interval> intervals) {
    if (intervals == null || intervals.size() <= 1)
      return true;

    // Sort intervals by start time
    Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

    for (int i = 1; i < intervals.size(); i++) {
      Interval prev = intervals.get(i - 1);
      Interval curr = intervals.get(i);

      if (curr.start < prev.end) {
        return false; // Overlap found
      }
    }

    return true; // No overlaps
  }

  @Override
  public void run() {
    List<Interval> meetings = Arrays.asList(
        new Interval(0, 30),
        new Interval(5, 10),
        new Interval(15, 20));

    boolean result = canAttendMeetings(meetings);
    System.out.println("Can attend all meetings: " + result); // false
  }
}