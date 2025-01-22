// T.C  = O(logn)
// S.C = O(1)
class Solution {
    public double myPow(double x, int n) {
        double res = 1;
        // Convert n to long to handle edge case
        long nn = n;
        if(nn < 0) nn = Math.abs(nn);

        while(nn > 0) {
            // If nn is odd, multiply res by x
            if(nn % 2 == 1) {
                res *= x;
            }
            // Square the base and Divide n by 2
            x *= x;
            nn /= 2;
        }
        return n<0 ? 1/res : res;
    }
}