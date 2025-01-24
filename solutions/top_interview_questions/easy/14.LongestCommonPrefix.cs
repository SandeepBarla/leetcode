// Using indexOf method (Java)
// T.C = O(m*n); m is length of array strs and n is length of prefix being shortened
// S.C = O(1); constant space for string prefix
class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for(int i=1; i<strs.length; i++){
            while(strs[i].indexOf(prefix)!=0){
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        return prefix;
    }
}

// Using Sorting (Java)
// T.C = O(n*logn); for quick sort
// S.C = O(logn) Avg SC for quick sort or O(n) Worst case SC for quick sort
class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        int count = 0;
        String first = strs[0];
        String last = strs[strs.length-1];
        for(int i=0; i<Math.min(first.length(),last.length()); i++){
            if(first.charAt(i)!=last.charAt(i)){
                break;
            }
            count++;
        }
        return strs[0].substring(0,count);
    }
}

// Bruteforce approach
// T.C = O(m*n); m is length of array strs and n is length of strs[0]
// S.C = O(1); constant space for all variables
public class Solution {
    public string LongestCommonPrefix(string[] strs) {
        bool flag = true;
        int res = 0;
        int n = strs.Length;
        if(n==1) return strs[0];
        int count = 0;
        while(flag){ // loop through until prefix breaks (i.e, flag == false)
            for(int i=1; i<n; i++){
                if(strs[i].Length > res && strs[0].Length > res && strs[0][res] == strs[i][res]){
                    count++;
                }else{
                    flag = false;
                    break;
                }
            }
            if(count == n-1) res = res+1; // at this point, we found another char of prefix, so increment res value
            count=0;
        }

        return strs[0].Substring(0,res);
    }
}