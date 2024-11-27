public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        Dictionary<int,int> dic = new Dictionary<int,int>();
        int diff = 0;
        for(int i=0; i<nums.Length; i++){ // loop through each element
            diff = target - nums[i]; // find the difference between target and the element
            if(dic.ContainsKey(diff)){ // if difference is found in dictionary return the value
                return new int[] {dic[diff], i};
            }
            dic[nums[i]] = i; // if difference is not found in the dictionary, add element to dictionary
        }
        return new int[] {0,0};
    }
}