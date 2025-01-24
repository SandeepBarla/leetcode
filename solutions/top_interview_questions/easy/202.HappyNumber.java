// Using two pointers
class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do{
            slow = findSum(slow);
            fast = findSum(findSum(fast));
        }while(slow!=fast); // run the loop until fast pointer catches the slow pointer
        return slow==1;
    }
    // private method to find the sum of squares of digits
    private int findSum(int n){
        int res = 0;
        while(n!=0){
            int digit = n%10;
            res = res + (digit*digit);
            n = n/10;
        }
        return res;
    }
}

// Using HashSet
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        // end the loop if the result is 1 or if it already exists in the set
        while(n>1 && !set.contains(n)){
            int res = 0;
            set.add(n);
            // iterate through the loop to find the sum of squares of digits
            while(n!=0){
                int digit = n%10;
                res = res + (digit*digit);
                n = n/10;
            }
            n = res;
        }
        if(n==1) return true;
        return false;
    }
}