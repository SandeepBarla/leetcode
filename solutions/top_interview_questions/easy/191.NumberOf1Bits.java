// Using Bit Manipulation
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while(n!=0){
            count = count + (n&1); // increment the count if LSB of n is one
            n = n>>1; // Perform right shift to find LSB of n easily in next iteration
        }
        return count;
    }
}

// Using Math Calculation
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        int i=31;
        while(n>0 && i>=0){
            if(n >= Math.pow(2,i)){
                count++;
                n = n - (int)Math.pow(2,i);
            }
            i--;
        }
        return count;
    }
}