/*
 * LeetCode Problem 704: Binary Search
 * URL: https://leetcode.com/problems/binary-search/
 * Difficulty: Easy
 *
 * Approach: Classic Binary Search
 * - Perform binary search on the sorted array.
 * - If target found at mid, return mid.
 * - If target is greater, search right half.
 * - If target is smaller, search left half.
 * - If not found, return -1.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
package solutions.easy;

import common.ArrayUtils;
import common.Solution;

public class LC_704_BinarySearch implements Solution {

    public int search(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                h = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    @Override
    public void run() {
        int[] nums1 = { -1, 0, 3, 5, 9, 12 };
        int target1 = 9;
        ArrayUtils.printArray("Input Array", nums1);
        System.out.println("Target: " + target1);
        System.out.println("Index: " + search(nums1, target1));

        int[] nums2 = { -1, 0, 3, 5, 9, 12 };
        int target2 = 2;
        ArrayUtils.printArray("Input Array", nums2);
        System.out.println("Target: " + target2);
        System.out.println("Index: " + search(nums2, target2));
    }
}