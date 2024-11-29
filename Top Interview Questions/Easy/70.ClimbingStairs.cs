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