public class Solution {
    public string LongestCommonPrefix(string[] strs) {
        bool flag = true;
        int res = 0;
        int n = strs.Length;
        if(n==1) return strs[0];
        int count = 0;
        while(flag){
            for(int i=1; i<n; i++){
                if(strs[i].Length > res && strs[0].Length > res && strs[0][res] == strs[i][res]){
                    count++;
                }else{
                    flag = false;
                    break;
                }
            }
            if(count == n-1) res = res+1;
            count=0;
        }

        return strs[0].Substring(0,res);
    }
}