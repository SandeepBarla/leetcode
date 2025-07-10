/*
 * LeetCode Problem 2013: Detect Squares
 * URL: https://leetcode.com/problems/detect-squares/
 * Difficulty: Medium
 *
 * Approach:
 * - Use a nested HashMap to store all added points:
 *   Map<x, Map<y, count>> — count keeps track of how many times (x, y) was added.
 * - To count squares for a given (x, y):
 *   - Iterate over all other x-values (x2 ≠ x) in the map.
 *   - For each such x2, check if (x2, y) exists (they form the base of a square).
 *   - Use distance |x2 - x| to calculate possible square height.
 *   - Check if the other two square points (x, y±dist) and (x2, y±dist) exist.
 *   - Multiply the frequencies of those three points to calculate valid squares.
 *
 * Time Complexity:
 * - add(): O(1)
 * - count(): O(400) worst case (since only 0 ≤ x, y < 1001)
 *
 * Space Complexity: O(N), where N is the number of added points
 */

package solutions.neetcode_150.medium;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_2013_DetectSquares implements Solution {

  class DetectSquares {
    private final Map<Integer, Map<Integer, Integer>> pointsMap;

    public DetectSquares() {
      pointsMap = new HashMap<>();
    }

    public void add(int[] point) {
      int x = point[0], y = point[1];
      pointsMap.putIfAbsent(x, new HashMap<>());
      Map<Integer, Integer> yMap = pointsMap.get(x);
      yMap.put(y, yMap.getOrDefault(y, 0) + 1);
    }

    public int count(int[] point) {
      int x = point[0], y = point[1];
      if (!pointsMap.containsKey(x))
        return 0;

      int total = 0;
      Map<Integer, Integer> yMap = pointsMap.get(x);

      for (int x2 : pointsMap.keySet()) {
        if (x2 == x)
          continue; // same x → skip
        int dist = Math.abs(x2 - x);
        Map<Integer, Integer> y2Map = pointsMap.get(x2);

        // Check (x2, y + dist) and (x, y + dist)
        if (y2Map.containsKey(y) && y2Map.containsKey(y + dist) && yMap.containsKey(y + dist)) {
          total += y2Map.get(y) * y2Map.get(y + dist) * yMap.get(y + dist);
        }

        // Check (x2, y - dist) and (x, y - dist)
        if (y2Map.containsKey(y) && y2Map.containsKey(y - dist) && yMap.containsKey(y - dist)) {
          total += y2Map.get(y) * y2Map.get(y - dist) * yMap.get(y - dist);
        }
      }

      return total;
    }
  }

  @Override
  public void run() {
    DetectSquares ds = new DetectSquares();
    ds.add(new int[] { 3, 10 });
    ds.add(new int[] { 11, 2 });
    ds.add(new int[] { 3, 2 });
    System.out.println(ds.count(new int[] { 11, 10 })); // Expected: 1
    System.out.println(ds.count(new int[] { 14, 8 })); // Expected: 0
  }
}