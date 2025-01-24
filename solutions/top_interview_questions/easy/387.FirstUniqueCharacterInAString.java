// Using Bucket Array
class Solution {
    public int firstUniqChar(String s) {
        // 0 -> 25
        // a -> z
        int[] arr = new int[26];
        // iterate through string s and record the frequency of each character by incrementing the corresponding value in bucket array
        for(char c: s.toCharArray()){
            arr[c-'a']+=1;
        }
        // find out the first character whose frequency is 1
        for(int i=0; i<s.length(); i++){
            if(arr[s.charAt(i)-'a']==1) return i;
        }
        return -1;
    }
}

// Using HashMap
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        // record the frequencies
        for(int i=0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
        }
        // find out the first character whose frequency is 1
        for(int i=0; i<s.length(); i++){
            if(map.get(s.charAt(i))==1) return i;
        }
        return -1;
    }
}

// BruteForce approach
class Solution {
    public int firstUniqChar(String s) {
        // loop through each character
        for(int i=0; i<s.length(); i++){
            int count=0;
            // compare with all other characters
            for(int j=0; j<s.length(); j++){
                if(i!=j){
                    // increment the count if not equals, else break the loop
                    if(s.charAt(i)!=s.charAt(j)) count++;
                    else break;
                }
            }
            // return i, if the character doesn't matches with any other character
            if(count == s.length()-1) return i;
        }
        return -1;
    }
}