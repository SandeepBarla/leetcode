/*
 * LeetCode: 253 - Meeting Rooms II
 * URL: https://leetcode.com/problems/meeting-rooms-ii/
 * Difficulty: Medium
 *
 * Approach:
 * - Sort the intervals by start time.
 * - Use a min-heap to keep track of the earliest end time of ongoing meetings.
 * - For each meeting:
 *     - If its start time is ≥ the earliest end time (peek of heap), reuse that room (poll from heap).
 *     - Otherwise, we need a new room.
 * - Push the end time of the current meeting into the heap.
 * - The size of the heap will represent the minimum number of rooms needed.
 *
 * Time Complexity: O(n log n) due to sorting and heap operations
 * Space Complexity: O(n) for the heap
 */

package solutions.neetcode_150.medium;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import common.Interval;
import common.Solution;

public class LC_253_MeetingRoomsII implements Solution {

  @Override
  public void run() {
    List<Interval> intervals = Arrays.asList(
        new Interval(0, 30),
        new Interval(5, 10),
        new Interval(15, 20));
    System.out.println("Min rooms needed: " + minMeetingRooms(intervals)); // Output: 2
  }

  public int minMeetingRooms(List<Interval> intervals) {
    if (intervals == null || intervals.isEmpty())
      return 0;

    // Sort intervals by start time
    intervals.sort((a, b) -> Integer.compare(a.start, b.start));

    // MinHeap to track the earliest end time of ongoing meetings
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (Interval interval : intervals) {
      if (!minHeap.isEmpty() && interval.start >= minHeap.peek()) {
        minHeap.poll(); // Reuse the room
      }
      minHeap.offer(interval.end); // Add end time of current meeting
    }

    return minHeap.size(); // Number of concurrent meetings = number of rooms needed
  }
}