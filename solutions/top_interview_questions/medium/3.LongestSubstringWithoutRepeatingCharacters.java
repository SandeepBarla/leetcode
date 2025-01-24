package solutions.top_interview_questions.medium;
// Using Bucket Array
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // create an array of 128 elements, whose indexes representing 0-127 ascii values of characters
        // and values representing the indexes of those characters in the string plus one
        // (which indicates index to where the left pointer has to shift)          
        int[] charIndex = new int[128];
        int left = 0;
        int maxLength = 0;
        for(int right=0; right<s.length(); right++){
            char currChar = s.charAt(right);
            // check if the left pointer has to be updated 
            left = Math.max(left, charIndex[currChar]);
            // after updating the left pointer, calculate the maxLength
            maxLength = Math.max(maxLength, right-left+1);
            // store the index of the character in the charIndex array
            charIndex[currChar] = right+1;
        }
        return maxLength;
    }
}

// Using HashSet
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int max = 0;
        for(int right=0; right<s.length(); right++){
            char c = s.charAt(right);
            if(!set.contains(c)){
                // if the current char is not in set, increment the max value
                max = Math.max(max, right-left+1);
            }else{
                // if the current char exists in set, start removing elements from the left till the duplicate char
                while(set.contains(c)){
                    set.remove(s.charAt(left));
                    left++;
                }
            }
            set.add(c);
        }
        return max;
    }
}

// BruteForce approach
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) return 0;
        if(s.length()==1) return 1;
        Set<Character> set = new HashSet<>();
        int max = 0;
        for(int i=0; i<s.length()-1; i++){
            int count = 0;
            for(int j=i; j<s.length(); j++){
                if(!set.contains(s.charAt(j))){
                    set.add(s.charAt(j));
                    count++;
                }else{
                    break;
                }
            }
            max = Math.max(count, max);
            set.clear();
        }
        return max;
    }
}