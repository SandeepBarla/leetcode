// Binary Search
// T.C = O(log x)
// S.C = O(1)
public class Solution {
    public int MySqrt(int x) {
        if(x==0) return 0;
        int l=1;
        int r=x;

        while(l<=r){ // binary search
            int mid = l + (r-l)/2; // ovoid interger overflow
            int sqrt = x / mid; // as calculating square is costly, find x/mid value and compare it with mid value
            // compare square of mid value with x
            if(sqrt == mid) return mid; // (x/mid == mid)
            else if(sqrt < mid) r = mid-1; // (x/mid < mid)
            else l = mid+1;
        }
        return h;
    }
}