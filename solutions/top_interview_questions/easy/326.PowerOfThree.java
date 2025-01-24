// Using Logarithm
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n<=0) return false;
        // n = 3^x
        // log n = log(3^x)
        // if log n to base 3 is integer, it means n is power of 3
        return ((Math.log10(n)/Math.log10(3))%1)==0;
    }
}

// checking if number is divisible by 3
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n<=0) return false;
        // divide the number by 3 as long as it is divisible by 3
        while(n%3==0) n/=3;
        return n==1;
    }
}