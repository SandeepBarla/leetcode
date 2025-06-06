// T.C = O(m*n); m is length of the string haystack and n is length of the string needle
// S.C = O(1); constant space for variables
public class Solution {
    public int StrStr(string haystack, string needle) {
        int j = 0;
        int m = haystack.Length;
        int n = needle.Length;
        for(int i=0; i<m; i++){
            int temp=i; // store i value in temp to reset it after while loop
            while(i<m && j<n && haystack[i]==needle[j]){
                i++;j++; // increment i and j when char match found
            }
            if(j==n) return i-j; // when needle is found in haystack return the index
            else{ // when not found reset i value and j value to start a fresh search
                i=temp; // reset i value to its original using temp value
                j=0;
            }
        }
        return -1;
    }
}

// Using Substring
// T.C = O(m*n); m is difference between length of the strings haystack and needle and n is length of substring
// S.C = O(1); constant space
public class Solution {
    public int StrStr(string haystack, string needle) {
        for(int i=0; i<=haystack.Length-needle.Length; i++){
            if(haystack.Substring(i,needle.Length) == needle) return i;
        }
        return -1;
    }
}