// Using ASCII values
// ASCII of A is 65 (So, to make it 1 subtract it with 64)
class Solution {
    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int res = 0;
        // For Excel Sheet Column Numbers we have to multiply digit with 26 power units place, where as is for integers we multiply digit with 10 power units place
        for(int i=0; i<n; i++){
            res+=((int) columnTitle.charAt(i) - 64) * Math.pow(26, n-i-1);
        }
        return res;
    }
}

class Solution {
    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int res = 0;
        for(int i=0; i<n; i++){
            res = res*26 + (columnTitle.charAt(i)-'A'+1);
        }
        return res;
    }
}