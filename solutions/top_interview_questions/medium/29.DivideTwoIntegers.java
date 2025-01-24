// Using Bit Manipulation
// T.C = O(log^2(n)); n is dividend
// S.C = O(1);
class Solution {
    public int divide(int dividend, int divisor) {
        // handle the edge case
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        // determine sign of the result
        boolean isNegative = (dividend<0) ^ (divisor<0);
        // convert dividend and divisor to long and remove the sign to perform further calculations
        long absDividend = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);

        int quotient = 0;
        // loop through as long as dividend is greater than or equal to divisor
        while(absDividend>=absDivisor){
            long tempDivisor = absDivisor;
            int multiple = 1;
            // double the divisor and check if it is less than or equal to dividend
            while(absDividend>=(tempDivisor<<1)){
                tempDivisor<<=1;
                // double the multiple each time
                multiple<<=1;
            }
            // caculate the left off dividend for next iteration
            absDividend-=tempDivisor;
            // add the multiple to result
            quotient+=multiple;
        }
        return isNegative ? -1*quotient : quotient;
    }
}