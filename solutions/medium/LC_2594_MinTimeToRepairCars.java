package solutions.medium;

import java.util.Arrays;

import common.Solution;

public class LC_2594_MinTimeToRepairCars implements Solution {

  // 🔹 Binary Search + Square Root Approach
  public long repairCars(int[] ranks, int cars) {
    Arrays.sort(ranks); // Sorting helps in early termination of loops
    long left = 1, right = (long) ranks[0] * (long) cars * (long) cars;

    while (left < right) {
      long mid = left + (right - left) / 2;
      long carsRepaired = 0;

      for (int rank : ranks) {
        carsRepaired += Math.sqrt(mid / rank);
        if (carsRepaired >= cars)
          break; // Stop early if we repair enough cars
      }

      if (carsRepaired >= cars) {
        right = mid; // Try to minimize time
      } else {
        left = mid + 1; // Increase time
      }
    }

    return left;
  }

  @Override
  public void run() {
    // ✅ Test Case 1
    int[] ranks1 = { 4, 2, 3 };
    int cars1 = 5;
    long expected1 = 12;
    long actual1 = repairCars(ranks1, cars1);
    System.out.println("Test Case 1: " + (actual1 == expected1) + " | Expected: " + expected1 + ", Got: " + actual1);

    // ✅ Test Case 2
    int[] ranks2 = { 1, 2, 3 };
    int cars2 = 6;
    long expected2 = 9;
    long actual2 = repairCars(ranks2, cars2);
    System.out.println("Test Case 2: " + (actual2 == expected2) + " | Expected: " + expected2 + ", Got: " + actual2);

    // ✅ Test Case 3
    int[] ranks3 = { 5, 1, 8 };
    int cars3 = 10;
    long expected3 = 36;
    long actual3 = repairCars(ranks3, cars3);
    System.out.println("Test Case 3: " + (actual3 == expected3) + " | Expected: " + expected3 + ", Got: " + actual3);
  }
}