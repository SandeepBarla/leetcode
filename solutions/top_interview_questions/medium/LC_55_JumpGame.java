package solutions.top_interview_questions.medium;

import common.ArrayUtils;
import common.Solution;

// T.C = O(n); n is length of the array nums
// S.C = O(1)
public class LC_55_JumpGame implements Solution{
    public boolean canJump(int[] nums) {
        int pos = nums.length-1;
        for(int j=pos-1; j>=0; j--){
            if(nums[j]>=pos-j){
                pos = j;
            }
        }
        return pos==0;
    }

    @Override
    public void run() {
        int[] nums = {2, 3, 1, 1, 4};
        
        // Using ArrayUtils to print the array
        ArrayUtils.printArray("Input Array", nums);

        System.out.println("Output: " + canJump(nums));
    }
}
