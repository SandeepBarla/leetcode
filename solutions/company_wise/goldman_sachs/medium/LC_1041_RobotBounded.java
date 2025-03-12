package solutions.company_wise.goldman_sachs.medium;

import common.Solution;

public class LC_1041_RobotBounded implements Solution {
  public boolean isRobotBounded(String instructions) {
    int x = 0, y = 0, dir = 0;
    int[][] directions = {
        { 0, 1 }, // ↑ North
        { 1, 0 }, // → East
        { 0, -1 }, // ↓ South
        { -1, 0 } // ← West
    };

    for (char c : instructions.toCharArray()) {
      if (c == 'L') {
        dir = (dir + 3) % 4; // Turn Left
      } else if (c == 'R') {
        dir = (dir + 1) % 4; // Turn Right
      } else {
        x += directions[dir][0]; // Move in X
        y += directions[dir][1]; // Move in Y
      }
    }

    return (x == 0 && y == 0) || dir != 0; // Returns to (0,0) OR not facing north
  }

  @Override
  public void run() {
    // Test Cases
    System.out.println("Test 1 (GGLLGG): " + isRobotBounded("GGLLGG")); // true
    System.out.println("Test 2 (GG): " + isRobotBounded("GG")); // false
    System.out.println("Test 3 (GLGLGGLGL): " + isRobotBounded("GLGLGGLGL")); // true
  }
}