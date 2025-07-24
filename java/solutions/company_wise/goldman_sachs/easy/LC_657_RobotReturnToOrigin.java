package solutions.company_wise.goldman_sachs.easy;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_657_RobotReturnToOrigin implements Solution {

  // ✅ Solution 1: Using x, y Coordinate Tracking (O(N) time, O(1) space)
  public boolean judgeCircleXY(String moves) {
    int x = 0, y = 0;
    for (char move : moves.toCharArray()) {
      switch (move) {
        case 'R':
          x++;
          break;
        case 'L':
          x--;
          break;
        case 'U':
          y++;
          break;
        case 'D':
          y--;
          break;
      }
    }
    return x == 0 && y == 0;
  }

  // ✅ Solution 2: Using HashMap for Counting Moves (O(N) time, O(1) space)
  public boolean judgeCircleMap(String moves) {
    Map<Character, Integer> moveCount = new HashMap<>();

    // Count occurrences of each move
    for (char move : moves.toCharArray()) {
      moveCount.put(move, moveCount.getOrDefault(move, 0) + 1);
    }

    // Check if horizontal and vertical movements cancel out
    return moveCount.getOrDefault('L', 0).equals(moveCount.getOrDefault('R', 0)) &&
        moveCount.getOrDefault('U', 0).equals(moveCount.getOrDefault('D', 0));
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    String moves1 = "UD";
    System.out.println("Moves: " + moves1);
    System.out.println("Returns to Origin (XY Tracking): " + judgeCircleXY(moves1)); // Expected: true
    System.out.println("Returns to Origin (HashMap): " + judgeCircleMap(moves1)); // Expected: true

    // 🔹 Test Case 2
    String moves2 = "LL";
    System.out.println("\nMoves: " + moves2);
    System.out.println("Returns to Origin (XY Tracking): " + judgeCircleXY(moves2)); // Expected: false
    System.out.println("Returns to Origin (HashMap): " + judgeCircleMap(moves2)); // Expected: false

    // 🔹 Test Case 3
    String moves3 = "RRDDLLUU";
    System.out.println("\nMoves: " + moves3);
    System.out.println("Returns to Origin (XY Tracking): " + judgeCircleXY(moves3)); // Expected: true
    System.out.println("Returns to Origin (HashMap): " + judgeCircleMap(moves3)); // Expected: true
  }
}