// Using HashSet
// T.C = O(n); n is length of the array nums
// S.C = O(n); n is length of the array nums
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i: nums){
            if(set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }
}

// Sorting the array
// T.C = O(n * log n); n is length of the array nums
// S.C = O(log n); n is length of the array nums
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0; i<nums.length-1; i++){
            if(nums[i]==nums[i+1]) return true;
        }
        return false;
    }
}