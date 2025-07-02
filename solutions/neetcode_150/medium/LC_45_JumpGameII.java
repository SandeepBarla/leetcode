package solutions.neetcode_150.medium;

import common.Solution;

public class LC_45_JumpGameII implements Solution {

  // Main logic to find the minimum number of jumps to reach the last index
  public int jump(int[] nums) {
    int jumps = 0; // Total number of jumps made so far
    int currentEnd = 0; // End of the range for the current jump
    int farthest = 0; // Farthest index reachable within current range

    for (int i = 0; i < nums.length - 1; i++) {
      // Continuously update the farthest index we can reach from this range
      farthest = Math.max(farthest, i + nums[i]);

      // If we've reached the end of the current jump range,
      // we must make a jump and extend our range
      if (i == currentEnd) {
        jumps++;
        currentEnd = farthest;

        // ✅ Early termination: if we can already reach or pass the end
        if (currentEnd >= nums.length - 1)
          break;
      }
    }

    return jumps;
  }

  @Override
  public void run() {
    System.out.println("\n--- Running LC 45: Jump Game II ---");

    int[] nums1 = { 2, 3, 1, 1, 4 };
    System.out.println("Input: [2,3,1,1,4]");
    System.out.println("Minimum Jumps: " + jump(nums1)); // Output: 2

    int[] nums2 = { 2, 3, 0, 1, 4 };
    System.out.println("\nInput: [2,3,0,1,4]");
    System.out.println("Minimum Jumps: " + jump(nums2)); // Output: 2

    int[] nums3 = { 1, 2 };
    System.out.println("\nInput: [1,2]");
    System.out.println("Minimum Jumps: " + jump(nums3)); // Output: 1
  }
}