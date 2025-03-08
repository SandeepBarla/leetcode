package solutions.company_wise.goldman_sachs.easy;

import common.ArrayUtils;
import common.Solution;

public class LC_942_DIStringMatch implements Solution {

  // ✅ Optimized Two-Pointer Greedy Approach (O(N) time, O(N) space)
  public int[] diStringMatch(String s) {
    int n = s.length();
    int lowest = 0;
    int highest = n;
    int[] res = new int[n + 1]; // Result array with n+1 elements

    // Process each character in the string
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == 'I') {
        res[i] = lowest++; // Assign lowest available number, then increment
      } else {
        res[i] = highest--; // Assign highest available number, then decrement
      }
    }
    res[n] = lowest; // At the end, lowest == highest, so we assign it to res[n]
    return res;
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    String s1 = "IDID";
    int[] result1 = diStringMatch(s1);
    System.out.println("Input: " + s1);
    ArrayUtils.printArray("Output", result1); // Expected: [0, 4, 1, 3, 2] or similar valid permutation

    // 🔹 Test Case 2
    String s2 = "III";
    int[] result2 = diStringMatch(s2);
    System.out.println("\nInput: " + s2);
    ArrayUtils.printArray("Output", result2); // Expected: [0, 1, 2, 3]

    // 🔹 Test Case 3
    String s3 = "DDD";
    int[] result3 = diStringMatch(s3);
    System.out.println("\nInput: " + s3);
    ArrayUtils.printArray("Output", result3); // Expected: [3, 2, 1, 0]

    // 🔹 Edge Case: Single Character
    String s4 = "I";
    int[] result4 = diStringMatch(s4);
    System.out.println("\nInput: " + s4);
    ArrayUtils.printArray("Output", result4); // Expected: [0, 1]
  }
}