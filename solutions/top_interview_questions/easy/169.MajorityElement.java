// Boyer-Moore Voting Algorithm
class Solution {
    public int majorityElement(int[] nums) {
        int res = 0;
        int count = 0;
        for(int i: nums){
            if(count==0) res=i; // whenever there is no majority we set the result to current element
            count+= (i==res) ? 1:-1; // if the current element is majority increment it, else decrement it
        }
        return res;
    }
}

// Using HashMap
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // store the element as key and it's frequency as value in the hashmap
        for(int i: nums){
            if(map.containsKey(i)){
                map.put(i, map.get(i)+1);
            }else{
                map.put(i, 1);
            }
        }
        int max = Integer.MIN_VALUE;
        int res = 0;
        // find the max frequency in the hashamp
        for(int i: map.keySet()){
            if(map.get(i)>max){
                max = map.get(i);
                res = i;
            }
        }
        return res;
    }
}