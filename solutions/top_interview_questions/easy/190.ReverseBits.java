public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        int mask = 0;
        for(int i=0; i<32; i++){
            mask = 1 & n; // find the LSB
            res = res<<1 | mask; // append it to the result
            n = n>>1; // do one right shift to the original number to find the LSB easily in the next iteration
        }
        return res;
    }
}

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        int temp = 0;
        int mask = 0;
        for(int i=31; i>=0; i--){
            mask = 1<<i;
            temp = n & mask; // get the left most digit
            if(temp!=0){ // if it is one
                mask = 1<<(31-i); // set the right most digit to one
                res = res | mask;
            } // else you don't have to set the digit, as it is already zero
        }
        return res;
    }
}