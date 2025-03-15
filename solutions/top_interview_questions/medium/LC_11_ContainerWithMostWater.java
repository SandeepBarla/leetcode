package solutions.top_interview_questions.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_11_ContainerWithMostWater implements Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;

        while (l < r) {
            int area = (Math.min(height[l], height[r])) * (r - l);
            maxArea = Math.max(area, maxArea);

            if (height[l] < height[r]) {
                l++; // Move left pointer forward
            } else {
                r--; // Move right pointer backward
            }
        }
        return maxArea;
    }

    @Override
    public void run() {
        // Test Case 1
        int[] heights1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        ArrayUtils.printArray("Heights", heights1);
        System.out.println("Max Area: " + maxArea(heights1)); // Expected: 49

        // Test Case 2
        int[] heights2 = { 1, 1 };
        ArrayUtils.printArray("\nHeights", heights2);
        System.out.println("Max Area: " + maxArea(heights2)); // Expected: 1
    }
}