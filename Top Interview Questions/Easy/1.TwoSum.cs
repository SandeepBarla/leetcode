public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        Dictionary<int,int> dic = new Dictionary<int,int>();
        int diff = 0;
        for(int i=0; i<nums.Length; i++){
            diff = target - nums[i];
            if(dic.ContainsKey(diff)){
                return new int[] {dic[diff], i};
            }
            dic[nums[i]] = i;
        }
        return new int[] {0,0};
    }
}