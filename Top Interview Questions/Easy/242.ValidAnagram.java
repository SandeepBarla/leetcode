// Using Bucket Array
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        // 0 -> 26
        // a -> z
        int[] arr = new int[26];
        // iterate through string s
        for(int i=0; i<s.length(); i++){
            arr[s.charAt(i)-'a']+=1; // increment the corresponding value of character in the array
        }
        // iterate through string t 
        for(int i=0; i<t.length(); i++){
            // if corresponding value of character in the array is 0, it means that character didn't exist in string s
            if(arr[t.charAt(i)-'a']==0) return false;
            // decrement the corresponding value of character in the array
            arr[t.charAt(i)-'a']-=1;
        }
        return true;
    }
}

// Using HashMap
class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        // record frequencies of each character in string s
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
                continue;
            }
            map.put(c,1);
        }
        // decrement the frequency when same character is found else return false
        for(int i=0; i<t.length(); i++){
            char c = t.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)-1);
            }else{
                return false;
            }
        }
        // check if all the frequencies are zero
        for(int i: map.values()){
            if(i!=0) return false;
        } 
        return true;
    }
}

// Using HashMap
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c,0)+1);
        }
        for(int i=0; i<t.length(); i++){
            char c = t.charAt(i);
            if(!map.containsKey(c) || map.get(c)==0){
                return false;
            }
            map.put(c, map.get(c)-1);
        }
        return true;
    }
}