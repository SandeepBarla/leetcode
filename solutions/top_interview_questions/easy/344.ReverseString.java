class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        // swap the characters and loop through until left and right pointers meet
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}