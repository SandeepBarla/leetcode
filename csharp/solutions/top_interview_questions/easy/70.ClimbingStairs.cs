// With no extra space
// T.C = O(n)
// S.C = O(1)
class Solution {
    public int climbStairs(int n) {
        if(n<=3) return n;
        int prev2 = 2;
        int prev1 = 3;
        int curr = 0;
        for(int i=4; i<=n; i++){
            curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }
}

// T.C = O(n)
// S.C = O(n); n is size of list
public class Solution {
    public int ClimbStairs(int n) {
        // create a new list to store fibonacci series
        List<int> fib = new List<int>();
        fib.Add(1);
        fib.Add(1);
        for(int i=2; i<=n; i++){
            fib.Add(fib[i-1]+fib[i-2]);
        }
        // return fibonacci series value of nth index
        return fib[n];
    }
}