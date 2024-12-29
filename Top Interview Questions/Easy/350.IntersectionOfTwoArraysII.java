// Using HashMap
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        // record the frequencies of numbers in nums1
        for(int i: nums1){
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        // loop through nums2 and compare with hashmap
        for(int i: nums2){
            // if match found and frequency is positive, add it result and decrement the frequency
            if(map.containsKey(i) && map.get(i)>0){
                res.add(i);
                map.put(i, map.get(i)-1);
            }
        }
        int[] array = new int[res.size()];
        for(int k = 0; k < res.size(); k++) array[k] = res.get(k);

        return array;
    }
}

// Sort arrays and do inplace memory update
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int i=0;
        int j=0;
        int k=0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]<nums2[j]){
                i++;
            }else if(nums2[j]<nums1[i]){
                j++;
            }else{
                nums1[k++] = nums1[i++];
                j++;
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }
}

// Sort arrays and compare
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        int i=0;
        int j=0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // loop through until i or j reaches end of array
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]==nums2[j]){
                res.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i]<nums2[j]){
                i++;
            }else if(nums2[j]<nums1[i]){
                j++;
            }
        }
        int[] array = new int[res.size()];
        for(int k = 0; k < res.size(); k++) array[k] = res.get(k);

        return array;
    }
}

// BruteForce approach
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<nums1.length; i++){
            for(int j=0; j<nums2.length; j++){
                if(nums1[i]==nums2[j]){
                    res.add(nums1[i]);
                    nums2[j] = -1;
                    break;
                }
            }
        }
        int[] array = new int[res.size()];
        for(int i = 0; i < res.size(); i++) array[i] = res.get(i);
        return array;
    }
}