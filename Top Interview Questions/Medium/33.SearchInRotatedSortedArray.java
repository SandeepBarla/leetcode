// Binary Search after determining which part is sorted
class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(nums[mid]==target) return mid;
            // determine which part is sorted
            if(nums[mid]>=nums[l]){
                // left part is sorted, so compare target with l and mid
                if(target>=nums[l] && target<nums[mid]){
                    r = mid-1; // target is in the left part
                }else{
                    l = mid+1;
                }
            }else{ // right part is sorted, so compare target with mid and r
                if(target>nums[mid] && target<=nums[r]){
                    l = mid+1; // target is in the righ part
                }else{
                    r = mid-1;
                }
            }
        }
        return -1;
    }
}


// Finding pivot and then finding the target
// T.C = O(log n); n is length of the array nums
// S.C = O(1);
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1; // Edge case: empty array
        if (nums.length == 1) return nums[0] == target ? 0 : -1; // Single-element array

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
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1; // Target not found
    }
}
