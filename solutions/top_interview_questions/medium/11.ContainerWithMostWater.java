// Two pointer approach
// T.C = O(n); n is length of the array height
// S.C = O(1)
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length-1;
        int maxArea = 0;
        while(l<r){
            // calculate the area of the container and keep track of the maxArea
            maxArea = Math.max(maxArea, Math.min(height[l],height[r])*(r-l));
            // ignore the minimum height and increment or decrement the pointers accordingly
            if(height[l]<height[r]) l++;
            else r--;
        }
        return maxArea;
    }
}