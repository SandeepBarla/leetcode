// T.C = O(n^2); n is length of the string s
// S.C = O(1)
class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for(int i=0; i<s.length(); i++){
            // when palindrome length is odd, we will have one center which is i
            int oddLength = expandAroundCenter(s, i, i);
            // when palindrome length is even, we will have two centers which are i,i+1
            int evenLength = expandAroundCenter(s, i, i+1);
            int max_len = Math.max(oddLength,evenLength);
            if(max_len > end-start+1){
                start = i - (max_len-1)/2;  // this will effectively calculate the start index, irrespective of odd or even length palindrome
                end = i + max_len/2;
            }
        }
        return s.substring(start, end+1);
    }
    // method to calculate the max length of the palindrome of center/centers low,high 
    private int expandAroundCenter(String s, int low, int high){
        while(low>=0 && high<s.length() && s.charAt(low)==s.charAt(high)){
            low--;
            high++;
        }
        return high-low-1;
    }
}