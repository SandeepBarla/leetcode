package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_33_SearchInRotatedSortedArray implements Solution {
    // Binary Search after determining which part is sorted
    public int searchUsingModifiedBinarySeach(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target)
                return mid;
            // determine which part is sorted
            if (nums[mid] >= nums[l]) {
                // left part is sorted, so compare target with l and mid
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1; // target is in the left part
                } else {
                    l = mid + 1;
                }
            } else { // right part is sorted, so compare target with mid and r
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1; // target is in the righ part
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    // Finding pivot and then finding the target
    // T.C = O(log n); n is length of the array nums
    // S.C = O(1);
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1; // Edge case: empty array
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1; // Single-element array

        int l = 0, r = nums.length - 1;

        // Find the pivot (index of the smallest element)
        while (l < r) {
            int mid = l + (r - l) / 2; // Avoid potential overflow
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        int pivot = l; // Smallest element index
        l = 0;
        r = nums.length - 1;

        // Determine which part of the array to search
        if (target >= nums[pivot] && target <= nums[r]) {
            l = pivot;
        } else {
            r = pivot - 1;
        }

        // Perform binary search in the determined range
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1; // Target not found
    }

    @Override
    public void run() {
        int[][] testCases = {
                { 4, 5, 6, 7, 0, 1, 2 }, // Rotated sorted array (target present)
                { 4, 5, 6, 7, 0, 1, 2 }, // Rotated sorted array (target missing)
                { 1, 2, 3, 4, 5, 6, 7 }, // Non-rotated sorted array
                { 1 }, // Single-element array (target present)
                { 1 }, // Single-element array (target missing)
                {} // Empty array
        };

        int[] targets = { 0, 3, 5, 1, 2, 1 }; // Corresponding targets
        int[] expectedResults = { 4, -1, 4, 0, -1, -1 }; // Expected output

        for (int i = 0; i < testCases.length; i++) {
            int actual = searchUsingModifiedBinarySeach(testCases[i], targets[i]);
            System.out.println("Test Case " + (i + 1) + ": " +
                    (actual == expectedResults[i] ? "✅ PASS" : "❌ FAIL") +
                    " | Expected: " + expectedResults[i] +
                    " | Actual: " + actual);
        }
    }
}
