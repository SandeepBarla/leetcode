class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        for(int num: nums){
            if(num!=0){ // arrange all the non-zero numbers in-order starting from zero index
                nums[i] = num;
                i++;
            }
        }
        // fill the remaining elements with zeroes
        while(i<nums.length){
            nums[i] = 0;
            i++;
        }
    }
}