// Double Binary Search
class Solution {
    public int[] searchRange(int[] nums, int target) {
        // perform binary search to find the starting index of target by performing left search when target found
        int left = binarySearch(nums, target, true);
        // perform binary search to find the ending index of target by performing right search when target found
        int right = binarySearch(nums, target, false);
        return new int[]{left,right};
    }
    private int binarySearch(int[] nums, int target, boolean isLeftSearch){
        int l=0, r=nums.length-1, result=-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(target==nums[mid]){
                result = mid;
                // when looking for starting index of target, perform another search on left array
                if(isLeftSearch) r=mid-1;
                // when looking for ending index of target, perform another search on right array
                else l=mid+1; 
            }else if(target<nums[mid]) r=mid-1;
            else l=mid+1;
        }
        return result;
    }
}