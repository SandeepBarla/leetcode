class Solution {
    public int removeElement(int[] nums, int val) {
        int idx=0;
        for(int i=0; i<nums.length; i++){
            // when the element is not the give value, place it in idx and increment idx
            if(nums[i]!=val){
                nums[idx] = nums[i];
                idx++;
            }
        }
        return idx;
    }
}