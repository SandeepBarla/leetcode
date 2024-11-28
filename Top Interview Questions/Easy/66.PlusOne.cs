public class Solution {
    public int[] PlusOne(int[] digits) {
        int n = digits.Length;
        int i = n-1;
        while(i>=0 && digits[i]==9){ // if digit is 9
            digits[i] = 0;
            i--;
        }
        if(i>=0){ // It means first digit (i.e, digits[0]) is not 9 and our while loop hasn't altered first digit
            digits[i] = digits[i] + 1;
            return digits;
        }

        // First digit (i.e, digits[0]) is 9 and our while loop has altered it. So, we have to put 1 as first digit
        int[] res = new int[n+1];
        res[0] = 1;
        // for(int j=0; j<n; j++){ // You don't have to copy elements from digits array, as all of them are zeroes at this point
        //     res[j+1] = digits[j];
        // }
        return res;
    }
}

// Short solution with for loop
public class Solution {
    public int[] PlusOne(int[] digits) {
        for(int i=digits.Length-1; i>=0; i--){
            digits[i] = digits[i] + 1;
            if(digits[i] != 10) return digits; // if digit is not 10 after increment then just return  the array
            digits[i] = 0; // if digit is 10 after increment then just reset it to zero
        }

        // at this point, all the digits are 10, so create a new array with first element as one and rest as zero
        int[] res = new int[digits.Length+1];
        res[0] = 1;
        return res;
    }
}