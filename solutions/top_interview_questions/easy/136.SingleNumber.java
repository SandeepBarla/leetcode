// XOR Approach:
// IMPORTANT XOR CALCULATIONS:
// A XOR 0 = A
// A XOR A = 0
// so for our solution, if we XOR all the elements in the array we will be left with the single integer (as all other integers will cancel out and becomes 0)
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int num : nums)
            res ^= num;
        return res;
    }
}

// HashMap Approach
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i: nums){
            if(map.containsKey(i))
                map.put(i, 2);
            else map.put(i, 1);
        }
        for(int i: map.keySet()){
            if(map.get(i)==1) return i;
        }
        return -1;
    }
}