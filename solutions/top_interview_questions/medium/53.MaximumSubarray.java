// T.C = O(n); n is length of the array nums
// S.C = O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        // iterate through all the elements
        for(int i: nums){
            // add the current element to sum
            sum+=i;
            // when we find sum that is greater than the existing, replace the res with sum
            if(sum>=res) res = sum;
            // if the sum is negative, this sub array is not going to add value to us in the future
            // so reset the sum, to calculate it fresh from next element
            if(sum<0) sum=0;
        }
        return res;
    }
}

// Bruteforce approach
// T.C = O(n^2); n is length of the array nums
// S.C = O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        // consider window size starting from 1 to num.length
        for(int i=1; i<=nums.length; i++){
            int sum = 0;
            for(int j=0; j<nums.length; j++){
                // if we haven't reached the window size yet, just add the element
                if(j < i){
                    sum+=nums[j];
                    // if we reached window size, compare the sum with result
                    if(j+1==i) res = Math.max(sum,res);
                    continue;
                }
                // maintain the window size by removing the starting element and adding the currrent element
                sum = sum - nums[j-i] + nums[j];
                res = Math.max(sum,res);
            }
        }
        return res;
    }
}