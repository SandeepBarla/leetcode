// T.C = O(n); n is length of string s
// S.C = O(1)
class Solution {
    public int myAtoi(String s) {
        int n = s.length(), sign = 1, res = 0, i=0;

        // ignore leading whitespaces
        while(i<n && s.charAt(i)==' ') i++;

        // check the sign
        if(i<n && s.charAt(i)=='-'){
            sign = -1;
            i++;   
        }else if(i<n && s.charAt(i)=='+') i++;

        // check for non-digit character and calculate the result
        while(i<n && Character.isDigit(s.charAt(i))){
            int digit = s.charAt(i) - '0';

            // check for integer overflow
            if(res > (Integer.MAX_VALUE - digit)/10){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res*10 + digit;
            i++;
        }

        return sign * res;

    }
}