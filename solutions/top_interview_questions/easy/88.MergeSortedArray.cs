// T.C = O(m+n)
// S.C = O(1)
public class Solution {
    public void Merge(int[] nums1, int m, int[] nums2, int n) {
        int k=m+n-1; // start filling largest numbers from end of nums1
        int i=m-1;
        int j=n-1;
        while(j>=0){ // loop through until nums2 is empty
            if(i>=0 && nums1[i]>nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }else{
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }
}