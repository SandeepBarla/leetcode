// Using sorted string as a key for grouping anagrams
// T.C = O(n * klogk); n is length of the array strs; k is length of the string;
// S.C = O(n*k)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        // iterate through all the strings
        for(String str: strs){
            // convert them to char array and sort the array
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            // conver the sorted char array to a string and use it as a angram key for its anagram group
            String key = new String(arr);
            List<String> temp = map.getOrDefault(key, new ArrayList<String>());
            map.putIfAbsent(key, new ArrayList<String>());
            map.get(key).add(str)
        }
        return new ArrayList<>(map.values());
    }
}

// BruteForce approach
// T.C = O(n*n*k); n is length of the array strs; k is length of the string;
// T.C = O(n*k)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        // iterate through all the strings in the array strs
        for(int i=0; i<strs.length; i++){
            boolean isAdded = false;
            int n = res.size();
            // compare it with all the first strings of the anagram groups
            for(int j=0; j<n; j++){
                if(isAnagram(strs[i], res.get(j).get(0))){
                    isAdded = true;
                    // if anagram found, add it to the list
                    res.get(j).add(strs[i]);
                    break;
                }
            }
            // if its not found, create a new list with this string and add it to the result
            if(!isAdded){
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                res.add(temp);
            }
        }
        return res;
    }
    // private method to check if the two strings are anagrams
    private boolean isAnagram(String str1, String str2){
        if(str1.length()!=str2.length()) return false;
        int[] arr = new int[26];
        for(int i=0; i<str1.length(); i++){
            arr[str1.charAt(i)-'a'] +=1;
        }
        for(int i=0; i<str2.length(); i++){
            arr[str2.charAt(i)-'a'] -=1;
            if(arr[str2.charAt(i)-'a']==-1) return false;
        }
        return true;
    }
}