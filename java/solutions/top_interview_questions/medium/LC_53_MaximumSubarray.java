/*
 * LeetCode Problem 53: Maximum Subarray
 * URL: https://leetcode.com/problems/maximum-subarray/
 * Difficulty: Medium
 *
 * Approaches:
 * 1. Kadane's Algorithm - O(n) time, O(1) space
 * 2. Dynamic Programming - O(n) time, O(n) space
 * 3. Divide and Conquer - O(n log n) time, O(log n) space
 * 4. Brute Force - O(n²) time, O(1) space
 *
 * Time Complexity:
 * - Approach 1: O(n) - single pass through array
 * - Approach 2: O(n) - single pass with DP array
 * - Approach 3: O(n log n) - divide and conquer recursion
 * - Approach 4: O(n²) - check all subarrays
 *
 * Space Complexity:
 * - Approach 1: O(1) - only using variables
 * - Approach 2: O(n) - DP array storage
 * - Approach 3: O(log n) - recursion call stack
 * - Approach 4: O(1) - only using variables
 */

package solutions.top_interview_questions.medium;

import java.util.Arrays;

import common.Solution;

public class LC_53_MaximumSubarray implements Solution {

    // Approach 1: Kadane's Algorithm (Optimal)
    public int maxSubArrayKadane(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend existing subarray or start new one
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }

    // Approach 2: Dynamic Programming
    public int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = dp[0];
        
        for (int i = 1; i < n; i++) {
            // dp[i] represents max sum ending at index i
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        
        return maxSum;
    }

    // Approach 3: Divide and Conquer
    public int maxSubArrayDivideConquer(int[] nums) {
        return maxSubArrayRecursive(nums, 0, nums.length - 1);
    }
    
    private int maxSubArrayRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        // Find max subarray in left half
        int leftMax = maxSubArrayRecursive(nums, left, mid);
        
        // Find max subarray in right half
        int rightMax = maxSubArrayRecursive(nums, mid + 1, right);
        
        // Find max subarray crossing the middle
        int crossMax = maxCrossingSum(nums, left, mid, right);
        
        // Return maximum of the three
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // Find max sum for left side ending at mid
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // Find max sum for right side starting at mid+1
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        // Return sum crossing the middle
        return leftSum + rightSum;
    }

    // Approach 4: Brute Force
    public int maxSubArrayBruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        
        return maxSum;
    }

    // Bonus: Return the actual subarray (Kadane's with indices)
    public int[] maxSubArrayWithIndices(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int start = 0, end = 0, tempStart = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxEndingHere + nums[i]) {
                maxEndingHere = nums[i];
                tempStart = i;
            } else {
                maxEndingHere = maxEndingHere + nums[i];
            }
            
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }
        
        return new int[]{maxSoFar, start, end};
    }

    @Override
    public void run() {
        // Test Case 1: Mixed positive and negative numbers
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        System.out.println("Kadane's: " + maxSubArrayKadane(nums1)); // Expected: 6 ([4,-1,2,1])
        System.out.println("DP: " + maxSubArrayDP(nums1)); // Expected: 6
        System.out.println("Divide & Conquer: " + maxSubArrayDivideConquer(nums1)); // Expected: 6
        System.out.println("Brute Force: " + maxSubArrayBruteForce(nums1)); // Expected: 6
        
        int[] result1 = maxSubArrayWithIndices(nums1);
        System.out.println("Max sum: " + result1[0] + ", from index " + result1[1] + " to " + result1[2]);
        System.out.println();

        // Test Case 2: Single element
        int[] nums2 = {1};
        System.out.println("Test Case 2: " + Arrays.toString(nums2));
        System.out.println("Kadane's: " + maxSubArrayKadane(nums2)); // Expected: 1
        System.out.println("DP: " + maxSubArrayDP(nums2)); // Expected: 1
        System.out.println("Divide & Conquer: " + maxSubArrayDivideConquer(nums2)); // Expected: 1
        System.out.println("Brute Force: " + maxSubArrayBruteForce(nums2)); // Expected: 1
        System.out.println();

        // Test Case 3: All negative numbers
        int[] nums3 = {-3, -2, -1, -5};
        System.out.println("Test Case 3: " + Arrays.toString(nums3));
        System.out.println("Kadane's: " + maxSubArrayKadane(nums3)); // Expected: -1
        System.out.println("DP: " + maxSubArrayDP(nums3)); // Expected: -1
        System.out.println("Divide & Conquer: " + maxSubArrayDivideConquer(nums3)); // Expected: -1
        System.out.println("Brute Force: " + maxSubArrayBruteForce(nums3)); // Expected: -1
        System.out.println();

        // Test Case 4: All positive numbers
        int[] nums4 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 4: " + Arrays.toString(nums4));
        System.out.println("Kadane's: " + maxSubArrayKadane(nums4)); // Expected: 15 (entire array)
        System.out.println("DP: " + maxSubArrayDP(nums4)); // Expected: 15
        System.out.println("Divide & Conquer: " + maxSubArrayDivideConquer(nums4)); // Expected: 15
        System.out.println("Brute Force: " + maxSubArrayBruteForce(nums4)); // Expected: 15
        System.out.println();

        // Test Case 5: Large array (performance test)
        int[] nums5 = {5, 4, -1, 7, 8, -10, 2, -3, 6, -2, 1};
        System.out.println("Test Case 5: " + Arrays.toString(nums5) + " (performance test)");
        
        long start = System.currentTimeMillis();
        int result5_1 = maxSubArrayKadane(nums5);
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Kadane's: " + result5_1 + " in " + time1 + "ms");
        
        start = System.currentTimeMillis();
        int result5_2 = maxSubArrayDP(nums5);
        long time2 = System.currentTimeMillis() - start;
        System.out.println("DP: " + result5_2 + " in " + time2 + "ms");
        
        start = System.currentTimeMillis();
        int result5_3 = maxSubArrayDivideConquer(nums5);
        long time3 = System.currentTimeMillis() - start;
        System.out.println("Divide & Conquer: " + result5_3 + " in " + time3 + "ms");
        
        start = System.currentTimeMillis();
        int result5_4 = maxSubArrayBruteForce(nums5);
        long time4 = System.currentTimeMillis() - start;
        System.out.println("Brute Force: " + result5_4 + " in " + time4 + "ms");
        
        int[] detailed = maxSubArrayWithIndices(nums5);
        System.out.println("Subarray details: sum=" + detailed[0] + ", from index " + detailed[1] + " to " + detailed[2]);
        System.out.println();

        // Test Case 6: Edge case with zeros
        int[] nums6 = {-1, 0, -2, 3, 0, -1, 2};
        System.out.println("Test Case 6: " + Arrays.toString(nums6) + " (with zeros)");
        System.out.println("Kadane's: " + maxSubArrayKadane(nums6)); // Expected: 5 ([3,0,-1,2] or [3])
        System.out.println("DP: " + maxSubArrayDP(nums6)); // Expected: 5
        
        int[] result6 = maxSubArrayWithIndices(nums6);
        System.out.println("Max sum: " + result6[0] + ", from index " + result6[1] + " to " + result6[2]);
        
        // Extract and show the actual subarray
        System.out.print("Actual subarray: [");
        for (int i = result6[1]; i <= result6[2]; i++) {
            System.out.print(nums6[i]);
            if (i < result6[2]) System.out.print(", ");
        }
        System.out.println("]");
    }
} 