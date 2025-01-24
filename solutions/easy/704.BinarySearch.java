// Binary Search
// T.C = O(log n); n is length of the array nums
// S.C = O(1)
class Solution{
    public int search(int[] nums, int target){
        int l=0, r=nums.length-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(target==nums[mid]) return mid;
            if(target<nums[mid]) r=mid-1;
            else l=mid+1;
        }
        return -1;
    }
}