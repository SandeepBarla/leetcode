public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int count = 1; // Consider first element of nums as first element of result array
        for(int i=1; i<nums.Length; i++){ // Start from 2nd element as we already considered first element
            if(nums[i]!=nums[i-1]){
                nums[count] = nums[i];
                count++;
            }
        }
        return count;
    }
}