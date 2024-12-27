// Using Sum of first n integers formula
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (n*(n+1))/2;
        for(int i: nums){
            sum-=i;
        }   
        return sum;
    }
}

// Using Arrays sort
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i=0; i<nums.length; i++){
            if(i!=nums[i]) return i;
        }
        return nums.length;
    }
}