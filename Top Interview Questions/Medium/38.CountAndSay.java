// Using recursion and StringBuilder
// T.C = O(2^n)
// S.C = O(2^n)
class Solution {
    public String countAndSay(int n) {
        // base case
        if(n==1) return "1";
        // get the string from previous call
        String input = countAndSay(n-1);
        // initialize the counter to keep track of the frequency of equal consecutive values 
        int count = 0;
        StringBuilder result = new StringBuilder();
        for(int i=0; i<input.length(); i++){
            count++;
            if(i==input.length()-1 || input.charAt(i)!=input.charAt(i+1)){
                // append the count and the value, when the corresponding values do not match
                result.append(count);
                result.append(input.charAt(i));
                // reset the counter
                count = 0;
            }
        }
        return result.toString();
    }
}