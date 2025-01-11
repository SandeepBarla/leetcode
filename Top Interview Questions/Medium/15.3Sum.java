// Two pointer approach
// T.C = O(n^2); n is length of the array nums
// S.C = O(n); extra space for sorting
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            // skip duplicates of i
            if(i>0 && nums[i]==nums[i-1]) continue;
            int l = i+1;
            int r = nums.length-1;
            while(l<r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum==0){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    // skip duplicates of l
                    while(l<r && nums[l]==nums[l-1]) l++;
                    // skip duplicates of r
                    while(l<r && nums[r]==nums[r+1]) r--;
                }else if(sum<0) l++;
                else r--;
            }
        }
        return res;
    }
}